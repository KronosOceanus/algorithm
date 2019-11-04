import java.util.Comparator;

/**
 * left.npl >= right.npl
 * null.npl = -1
 * 主要操作：merge（log N）
 *           deleteMin 中也用到了 merge（合并根的两个子堆）
 * 未考虑两个堆数据重复问题
 */
public class LeftistHeap<T> {

    private Node<T> root;
    private int currentSize;
    private Comparator<? super T> cmp;

    public LeftistHeap(Comparator<? super T> cmp){
        this.root = null;
        currentSize = 0;
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

    //插入是特殊的合并
    public void insert(T x){
        root = merge(new Node<>(x), root);
        currentSize ++;
    }
    public T deleteMin(){
        if (isEmpty()){
            throw new RuntimeException();
        }

        T minItem = root.element;
        root = merge(root.left, root.right);
        currentSize --;

        return minItem;
    }

    //与 rhs 堆合并
    public void merge(LeftistHeap<T> rhs){
        if (this == rhs){
            return;
        }

        root =  merge(root, rhs.root);
        currentSize += rhs.size();
        rhs.root = null;
    }
    //概念合并
    private Node<T> merge(Node<T> h1, Node<T> h2){
        if (h1 == null){
            return h2;
        }
        if (h2 == null){
            return h1;
        }
        if (myCompare(h1.element, h2.element) < 0){
            return merge1(h1, h2);
        }
        else return merge1(h2, h1);
    }
    //实际合并（其中 h1.element < h2.element）
    private Node<T> merge1(Node<T> h1, Node<T> h2){
        if (h1.left == null){
            //插入
            h1.left = h2;
        }
        else {
            //合并 根值小的堆的右子堆 与 根值大的堆
            h1.right = merge(h1.right, h2);
            //左式堆序被破坏（将该条件省去，则是斜堆）
            if (h1.left.npl < h1.right.npl){
                //交换左右子堆
                swapChildren(h1);
            }
            //更新零路径长
            h1.npl = h1.right.npl + 1;
        }
        return h1;
    }



    //交换左右子堆
    private void swapChildren(Node<T> h){
        Node<T> temp = h.left;
        h.left = h.right;
        h.right = temp;
    }

    private static class Node<T>{
        public T element;
        public Node<T> left;
        public Node<T> right;
        int npl;

        public Node(T x){
            this(x, null, null);
        }
        public Node(T x, Node<T> left, Node<T> right){
            this.element = x;
            this.left = left;
            this.right = right;
        }
    }
}
