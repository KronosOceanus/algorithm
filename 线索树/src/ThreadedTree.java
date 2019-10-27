import java.util.Comparator;

/**
 * 先插入，再线索化（最后一个结点的右边不是线索）
 * contains 操作需要线索化
 */
public class ThreadedTree<T> {

    //这棵树是否线索化
    private boolean isThread;
    //记录遍历的前驱结点
    private Node<T> preNode;

    private Node<T> root;
    private Comparator<? super T> cmp;
    //构造器
    public ThreadedTree(){
        makeEmpty();
    }
    public ThreadedTree(Comparator<? super T> cmp){
        makeEmpty();
        this.cmp = cmp;
    }
    //通过比较器比较（不需要T实现Comparable接口）
    private int myCompare(T lhs, T rhs){
        if (cmp != null){
            return cmp.compare(lhs, rhs);
        }else {
            return ((Comparable)lhs).compareTo(rhs);
        }
    }
    //次要操作
    public void makeEmpty(){
        isThread = false;
        root = null;
        preNode = null;
    }
    public boolean contains(T x){
        return contains(x, root);
    }
    public T findMin(){
        return findMin(root).element;
    }
    public T findMax(){
        return findMax(root).element;
    }
    public void printTree(){
        printTree(root);
    }


    /**
     * 主要操作！！！
     */
    public boolean isThread(){ return isThread;}
    public void insert(T x){
        root = insert(x, root);
    }
    public void inThreadOrder(){ inThreadOrder(root); isThread = true; }
    public void printByThread(){
        if (isThread)
            printByThread(findMin(root));
        else
            throw new RuntimeException("没有线索化！");
    }


    private boolean contains(T x, Node<T> t){
        if (t == null){
            return false;
        }

        int compareResult = myCompare(x, t.element);

        if (compareResult < 0 && !t.isThreadLeft){
            return contains(x, t.left);
        }else if (compareResult < 0){ //左边是线索
            return false;
        } else  if (compareResult > 0 && !t.isThreadRight){
            return contains(x, t.right);
        }else if (compareResult > 0){
            return false;
        }
        return true;
    }
    private Node<T> findMin(Node<T> t){
        if (t == null || t.isThreadLeft){
            return t;
        }
        return findMin(t.left);
    }
    private Node<T> findMax(Node<T> t){
        if (t == null){
            return t;
        }
        while(t.right != null && ! t.isThreadRight){
            t = t.right;
        }
        return t;
    }

    /**
     * 重点！！！
     * 插入（也可完成线索树的插入）
     */
    private Node<T> insert(T x, Node<T> t){
        if (t == null){
            return new Node<>(x);
        }

        int compareResult = myCompare(x, t.element);

        if (compareResult < 0 && !t.isThreadLeft){
            t.left = insert(x, t.left);
        }else if (compareResult < 0){   //如果左边是线索
            //找到目标前驱和后缀结点
            Node<T> pre = t.left;
            Node<T> back = t;
            //先把线索清空（不然会造成插入到线索，无限循环）
            t.left = null;
            //得到目标节点
            t.left = insert(x, t.left);
            //连接前驱和后缀节点
            t.left.left = pre;
            t.left.isThreadLeft = true;
            t.left.right = t;
            t.left.isThreadRight = true;
            //更改原结点线索
            t.isThreadLeft = false;
        }else if (compareResult > 0 && !t.isThreadRight){
            t.right = insert(x, t.right);
        }else if (compareResult > 0) {   //如果右边是线索
            //找到目标前驱和后缀结点
            Node<T> pre = t;
            Node<T> back = t.right;
            //清空线索
            t.right = null;
            //得到目标节点
            t.right = insert(x, t.right);
            //连接前驱和后缀节点
            t.right.right = back;
            t.right.isThreadLeft = true;
            t.right.left = t;
            t.right.isThreadRight = true;
            //更改原结点线索
            t.isThreadRight = false;
        }

        return t;
    }
    //中序遍历线索化二叉树
    private void inThreadOrder(Node<T> t){
        if (t == null){
            return;
        }
        //处理左子树
        inThreadOrder(t.left);
        //连接前驱结点
        if (t.left == null){
            t.left = preNode;
            t.isThreadLeft = true;
        }
        //前驱结点连接该节点
        if (preNode != null && preNode.right == null){
            preNode.right = t;
            preNode.isThreadRight = true;
        }
        //该节点变为前驱结点
        preNode = t;
        //处理右子树
        inThreadOrder(t.right);
    }
    //按照线索输出二叉树（线索化过后才能执行）
    private void printByThread(Node<T> t){
        while(t.right != null){
            System.out.print(t.element + "\t");
            t = t.right;
        }
        System.out.println(t.element + "\t");
    }
    private void printTree(Node<T> t){
        if (t != null){
            if (! t.isThreadLeft){
                printTree(t.left);
            }
            System.out.println(t.element + "\t t.isThreadLeft==" + t.isThreadLeft + "\t t.isThreadRight==" + t.isThreadRight);
            if (! t.isThreadRight){
                printTree(t.right);
            }
        }
    }



    private static class Node<T>{
        public T element;
        public Node<T> left;
        public Node<T> right;
        public boolean isThreadLeft;
        public boolean isThreadRight;

        public Node(T element){
            this.element = element;
            this.left = null;
            this.right = null;
            this.isThreadLeft = false;
            this.isThreadRight = false;
        }
    }
}
