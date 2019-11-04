import java.util.Comparator;

/**
 * 二项队列
 * 主要操作：insert（常数时间）
 *           merge（log N）
 *           deleteMin（log N）
 */
public class BinomialQueue<T> {

    private static final int DEFAULT_TREES = 1;

    private int currentSize;
    private Node<T>[] theTrees; //二项树数组
    private Comparator<? super T> cmp;

    public BinomialQueue(Comparator<? super T> cmp){
        this.theTrees = (Node<T>[]) new Node[DEFAULT_TREES];
        this.currentSize = 0;
        this.cmp = cmp;
    }
    private int myCompare(T x1, T x2){
        return cmp.compare(x1, x2);
    }

    public boolean isEmpty(){
        return currentSize == 0;
    }
    public int size(){
        return currentSize;
    }

    public void insert(T x){
        //新建一个只有一个元素的二项树
        BinomialQueue<T> bq = new BinomialQueue<>(cmp);
        bq.theTrees[0] = new Node<>(x);
        bq.currentSize ++;
        //合并
        merge(bq);
    }
    //主要操作
    public void merge(BinomialQueue<T> rhs){
        if (this == rhs){
            return ;
        }

        currentSize += rhs.currentSize;
        //扩充数组
        if (currentSize > capacity()){
            int maxLength = Math.max(theTrees.length, rhs.theTrees.length);
            expandTheTrees(maxLength + 1);
        }

        Node<T> carry = null;
        //遍历二项树数组
        for (int i=0,j=1;j<=currentSize;i++,j*=2){
            Node<T> t1 = theTrees[i];
            Node<T> t2 = i < rhs.theTrees.length ? rhs.theTrees[i] : null;

            int whichCase = t1 == null ? 0 : 1;
            whichCase += t2 == null ? 0 : 2;
            whichCase += carry == null ? 0 : 4;

            switch (whichCase){
                case 0:
                case 1:break;
                //将 t2 直接移动到 theTrees 中
                case 2:theTrees[i] = t2;
                    rhs.theTrees[i] = null;
                    break;
                //组合树，并在下一次循环中放入 theTrees 的下一个位置
                // 数组二进制表示树 （theTrees[i] != null，则用 1 表示）
                case 3:carry = combineTrees(t1, t2);
                    theTrees[i] = rhs.theTrees[i] = null;
                    break;
                //将组合后的树放到 theTrees 中
                case 4:theTrees[i] = carry;
                    carry = null;
                    break;
                case 5:carry = combineTrees(t1, carry);
                    theTrees[i] = null;
                    break;
                case 6:carry = combineTrees(t2, carry);
                    rhs.theTrees[i] = null;
                    break;
                case 7:theTrees[i] = carry;
                    carry = combineTrees(t1, t2);
                    rhs.theTrees[i] = null;
                    break;
            }
        }

        //清空 rhs
        for (int k=0;k<rhs.theTrees.length;k++){
            rhs.theTrees[k] = null;
        }
        rhs.currentSize = 0;
    }
    public T deleteMin(){
        if (isEmpty()){
            throw new RuntimeException();
        }

        //得到最小元素
        int minIndex = findMinIndex();
        T minItem = theTrees[minIndex].element;

        //得到删除最小元素后，树的剩余部分
        Node<T> deletedTree = theTrees[minIndex].leftChild;

        //创建 H''
        BinomialQueue<T> deletedQueue = new BinomialQueue<>(cmp);
        //扩容
        deletedQueue.expandTheTrees(minIndex + 1);

        //更新元素数量
        deletedQueue.currentSize = (1 << minIndex) - 1;
        //将树的剩余部分分开，变为二项树放入 theTrees 数组中
        for (int j = minIndex-1;j>=0;j--){
            deletedQueue.theTrees[j] = deletedTree;
            deletedTree = deletedTree.nextSibling;
            deletedQueue.theTrees[j].nextSibling = null;
        }

        //创建 H'（将删除元素所在的整棵树从 theTrees 中删去）
        theTrees[minIndex] = null;
        currentSize -= deletedQueue.currentSize + 1;

        merge(deletedQueue);
        return minItem;
    }




    //合并大小相同的两棵二项树
    private Node<T> combineTrees(Node<T> t1, Node<T> t2){
        //确保 t1.element < t2.element
        if (myCompare(t1.element, t2.element) > 0){
            return combineTrees(t2, t1);
        }
        //连接
        t2.nextSibling = t1.leftChild;
        t1.leftChild = t2;
        return t1;
    }
    //找到最小值数组下标
    private int findMinIndex(){
        int minIndex = 0;
        for (int i=0;i<theTrees.length;i++){
            if (theTrees[i] != null){
                minIndex = i;
                break;
            }
        }

        for (int i=0;i<theTrees.length;i++){
            if (theTrees[i] != null && theTrees[minIndex] != null){
                if (myCompare(theTrees[i].element, theTrees[minIndex].element) < 0){
                    minIndex = i;
                }
            }
        }
        return minIndex;
    }

    //二项树数组扩容
    private void expandTheTrees(int newNumTrees){
        Node<T>[] newTrees = (Node<T>[]) new Node[newNumTrees];
        System.arraycopy(theTrees, 0, newTrees, 0, theTrees.length);
        theTrees = newTrees;
    }
    //数组中可以容纳多少节点
    private int capacity(){
        return (1 << theTrees.length) - 1;
    }

    //一般树的标准表示法
    private static class Node<T>{
        public T element;
        public Node<T> leftChild;
        public Node<T> nextSibling;

        public Node(T x){
            this(x, null, null);
        }
        public Node(T x, Node<T> leftChild, Node<T> nextSibling){
            this.element = x;
            this.leftChild = leftChild;
            this.nextSibling = nextSibling;
        }
    }
}
