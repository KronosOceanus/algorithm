import java.nio.BufferUnderflowException;
import java.util.Comparator;

/**
 * 各种操作的平均时间约为 O(log N)
 */
public class BinarySearchTree<T> {

    private BinaryNode<T> root;
    private Comparator<? super T> cmp;

    public BinarySearchTree(){
        this.root = null;
    }
    //传入比较器
    public BinarySearchTree(Comparator<? super T> c){
        this.root = null;
        this.cmp = c;
    }

    //通过比较器比较（不需要T实现Comparable接口）
    private int myCompare(T lhs, T rhs){
        if (cmp != null){
            return cmp.compare(lhs, rhs);
        }else {
            return ((Comparable)lhs).compareTo(rhs);
        }
    }

    public void makeEmpty(){
        root = null;
    }
    public boolean isEmpty(){
        return root == null;
    }

    public boolean contains(T x){
        return contains(x, root);
    }
    public T findMin(){
        if (isEmpty()){
            throw new BufferUnderflowException();
        }
        return findMin(root).element;
    }
    public T findMax(){
        if (isEmpty()){
            throw new BufferUnderflowException();
        }
        return findMax(root).element;
    }

    public void insert(T x){
        root = insert(x, root);
    }
    public void remove(T x){
        root = remove(x, root);
    }
    public void printTree(){
        if (root == null){
            System.out.println("Empty tree！");
        }else {
            printTree(root);
        }
    }

    /**
     * private 方法
     */
    private boolean contains(T x, BinaryNode<T> t){
        if (t == null){
            return false;
        }

        int compareResult = myCompare(x, t.element);

        if (compareResult < 0){
            return contains(x, t.left);
        }else  if (compareResult > 0){
            return contains(x, t.right);
        }else {
            return true;
        }
    }
    //递归
    private BinaryNode<T> findMin(BinaryNode<T> t){
        if (t == null){
            return null;
        }else  if (t.left == null){
            return t;
        }
        return findMin(t.left);
    }
    //非递归
    private BinaryNode<T> findMax(BinaryNode<T> t){
        //注意不要改变t的引用
        if (t != null){
            while(t.right != null){
                t = t.right;
            }
        }
        return t;
    }

    /**
     * 插入
     * 找到要插入元素应该在的位置
     * 新建一个包含x的Node赋值给对应指针
     */
    private BinaryNode<T> insert(T x, BinaryNode<T> t){
        //递归出口（新建一个包含x的Node赋值给对应的t.left/right）
        if (t == null){
            return new BinaryNode<>(x, null, null);
        }

        int compareResult = myCompare(x, t.element);

        if (compareResult < 0){
            t.left = insert(x, t.left);
        }else if (compareResult > 0){
            t.right = insert(x, t.right);
        }
        //如果重复，可以用附加域表示频率
        return t;
    }
    /**
     * 删除
     * 查找元素，找到后使用目标元素右子树的最小结点代替
     * 并删除目标元素右子树的最小节点
     * 效率不高（可以采用懒惰删除）
     */
    private BinaryNode<T> remove(T x, BinaryNode<T> t){
        //直到t == null，切断连接
        if (t == null){
            return t;
        }

        int compareResult = myCompare(x, t.element);

        if (compareResult < 0){
            //从左子树删除 x
            t.left = remove(x, t.left);
        }else if (compareResult > 0){
            t.right = remove(x, t.right);
        }
        //找到要被删除的元素，它的左右两个节点都 != null
        else if (t.left != null && t.right != null){
            //以目标右子树的最小数据代替（该数据总比目标大）
            // 然后递归删除右子树最小的节点
            t.element = findMin(t.right).element;
            t.right = remove(t.element, t.right);
        }else {
            //如果目标只有一个 != null 的节点
            //直接走向 != null 节点，并与目标节点的上一个节点连接
            t = (t.left != null) ? t.left : t.right;
        }
        return t;
    }
    //遍历
    private void printTree(BinaryNode<T> t){
        if (t != null){
            printTree(t.left);
            System.out.print(t.element + "\t");
            printTree(t.right);
        }
    }



    //节点类
    private static class BinaryNode<T>{
        public T element;
        public BinaryNode<T> left;
        public BinaryNode<T> right;

        public BinaryNode(T ele){
            this(ele, null, null);
        }
        public BinaryNode(T ele, BinaryNode<T> lt, BinaryNode<T> rt){
            this.element = ele;
            this.left = lt;
            this.right = rt;
        }
    }
}
