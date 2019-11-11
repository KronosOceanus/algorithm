import java.nio.BufferUnderflowException;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

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
            System.out.println();
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


    //练习！！！
    //只用对根的引用，计算树中节点、叶、满节点的个数
    public void printCount(){
        System.out.println("Nodes=" + countNodes(root) + "\t" +
                "Leaves=" + countLeaves(root) + "\t" +
                "Full=" + countFull(root));
    }
    //二叉树的区间打印
    public void printAreaDatas(T begin, T end) {
        printAreaDatas(root, begin, end);
    }
    //层序遍历
    public void printByLevel(){
        System.out.println();
        printByLevel(root);
    }

    //练习！！！private
    //递归计算左右子树包含数量，相加
    private int countNodes(BinaryNode<T> t){
        if (t == null){
            return 0;
        }
        return countNodes(t.left) + countNodes(t.right) + 1;
    }
    private int countLeaves(BinaryNode<T> t){
        if (t == null){
            return 0;
        }else if(t.left == null && t.right == null){
            return 1;
        }else {
            return countLeaves(t.left) + countLeaves(t.right);
        }
    }
    private int countFull(BinaryNode<T> t){
        if (t == null){
            return 0;
        }else if (t.left != null && t.right != null){
            //子树满节点 + 父节点
            return countFull(t.left) + countFull(t.right) + 1;
        }else {
            return countFull(t.left) + countFull(t.right);
        }
    }
    //递归，中序遍历输出
    private void printAreaDatas(BinaryNode<T> t, T begin, T end) {
        if (t == null) {//树为空
            return;
        }

        //当前值比起始值大
        if (cmp.compare(t.element, begin) > 0) {
            //往左走
            printAreaDatas(t.left, begin, end);
        }
        //当前值比起始值大，比结束值小
        if (cmp.compare(t.element, begin) >= 0 &&
                cmp.compare(t.element, end) <= 0) {
            //符合要求 打印
            System.out.print(t.element + "\t");
        }
        if (cmp.compare(t.element, end) < 0) ;
        {
            //只有当前值比end 值小的时候才有必要访问其右子树
            printAreaDatas(t.right, begin, end);
        }
    }
    //层序遍历（边输出，边将两个儿子结点入队）
    private void printByLevel(BinaryNode<T> t){
        //队列
        LinkedList<BinaryNode<T>> queue = new LinkedList<>();
        BinaryNode<T> front;

        queue.offer(t);
        while(! queue.isEmpty()){
            front = queue.pollFirst();  //获取并删除队列头元素

            //两个儿子结点入队
            if (front.left != null){
                queue.offer(front.left);
            }
            if (front.right != null){
                queue.offer(front.right);
            }

            System.out.print(front.element + "\t");
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
