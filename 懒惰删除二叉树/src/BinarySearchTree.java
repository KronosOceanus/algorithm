import java.nio.BufferUnderflowException;
import java.util.Comparator;

/**
 * 重写，实现懒惰删除
 */
public class BinarySearchTree<T> {

    private BinaryNode<T> root;
    private Comparator<? super T> cmp;
    //总数量,懒惰删除数量
    private int treeSize;
    private int delSize;

    public BinarySearchTree(){
        this.root = null;
        treeSize = 0;
        delSize = 0;
    }
    //传入比较器
    public BinarySearchTree(Comparator<? super T> c){
        this.root = null;
        this.cmp = c;
        this.treeSize = 0;
        this.delSize = 0;
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
        remove(x, root);
    }
    public void printTree(){
        if (root == null){
            System.out.println("Empty tree！");
        }else {
            printTree(root);
        }
    }







    //暂时放下…………………………………………………………
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
     * 新增方法，彻底删除
     * 当 delSize = 1/2 * currentSize，执行彻底删除
     * 跟普通二叉树删除思路一样
     * 寻找左/右子树未被删除的最大/小节点替代，然后迭代删除
     */
    private void delete(){};



    /**
     * 懒惰删除改写方法
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
            return t.isActive;
        }
    }
    /**
     * 查找并返回，为空则 null
     */
    private BinaryNode<T> findAndReturn(T x, BinaryNode<T> t){
        //返回 null 也需要分配空间，供以后方法调用
        if (t == null){
            return new BinaryNode<>(null, null, null);
        }

        int compareResult = myCompare(x, t.element);

        if (compareResult < 0){
            return findAndReturn(x, t.left);
        }else  if (compareResult > 0){
            return findAndReturn(x, t.right);
        }
        return t;
    }

    /**
     * 插入
     * 如果找到，修改变量，终止递归
     */
    private BinaryNode<T> insert(T x, BinaryNode<T> t){
        //递归出口（新建一个包含x的Node赋值给对应的t.left/right）
        if (t == null){
            treeSize ++;
            return new BinaryNode<>(x, null, null);
        }

        int compareResult = myCompare(x, t.element);

        if (compareResult < 0){
            t.left = insert(x, t.left);
        }else if (compareResult > 0){
            t.right = insert(x, t.right);
        }else {
            t.isActive = true;
            //修改变量，终止递归
            return t;
        }
        //如果重复，可以用附加域表示频率
        return t;
    }
    /**
     * 删除
     * 找到元素，修改 isActive
     * 返回值表示成功失败
     */
    private void remove(T x, BinaryNode<T> t){
        BinaryNode<T> result = findAndReturn(x, t);
        if (result == null){
            return;
        }
        if (result.isActive){
            result.isActive = false;
            delSize ++;
        }
    }
    //遍历
    private void printTree(BinaryNode<T> t){
        if (t != null){
            printTree(t.left);
            if (t.isActive){
                System.out.print(t.element + "\t");
            }
            printTree(t.right);
        }
    }



    //节点类
    private static class BinaryNode<T>{
        public T element;
        public boolean isActive;
        public BinaryNode<T> left;
        public BinaryNode<T> right;

        public BinaryNode(T ele){
            this(ele, null, null);
        }
        public BinaryNode(T ele, BinaryNode<T> lt, BinaryNode<T> rt){
            this.element = ele;
            this.isActive = true;
            this.left = lt;
            this.right = rt;
        }
    }




    //测试成员变量
    public int getTreeSize() {
        return treeSize;
    }

    public void setTreeSize(int treeSize) {
        this.treeSize = treeSize;
    }

    public int getDelSize() {
        return delSize;
    }

    public void setDelSize(int delSize) {
        this.delSize = delSize;
    }
}
