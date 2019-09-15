import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * AVL（平衡二叉查找树）实现，传入比较器版本
 * 迭代器使用二叉查找树，每个节点上添加一个指向父节点的链
 */
public class MyTreeSet<T>{

    private int theSize;
    private int modCount;
    private BinaryNode<T> root;
    //需要排序操作时若没有比较器，抛出异常
    private Comparator<? super T> cmp;

    public MyTreeSet(){
        this(null);
    }
    public MyTreeSet(Comparator<? super T> cmp){
        clear();
        this.cmp = cmp;
    }

    public void clear() {
        this.root = null;
        theSize = 0;
        modCount = 0;
    }
    public int size() {
        return theSize;
    }
    public boolean isEmpty() {
        return theSize == 0;
    }
    public boolean contains(T x) {
        return contains(x, root);
    }
    public Iterator<T> iterator() {
        return new TreeSetIterator();
    }
    public void insert(T x){
        root = insert(x, root , null);
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
    private boolean contains(T x, BinaryNode<T> t){
        if (t == null){
            return false;
        }

        int compareResult = cmp.compare(x, t.element);

        if (compareResult < 0){
            return contains(x, t.left);
        }else  if (compareResult > 0){
            return contains(x, t.right);
        }else {
            return true;
        }
    }
    //遍历
    private void printTree(BinaryNode<T> t){
        if (t != null){
            printTree(t.left);
            System.out.print(t.element + "\t");
            printTree(t.right);
        }
    }




    //指向父节点的链，主要实现！！！！！！！！
    //传入父节点，递归最后一次调用连接（其他附加的链也是一样）
    private BinaryNode<T> insert(T x, BinaryNode<T> t, BinaryNode<T> pt){
        //递归出口（新建一个包含x的Node赋值给对应的t.left/right）
        if (t == null){
            modCount ++;
            theSize ++;
            return new BinaryNode<>(x, null, null, pt);
        }

        int compareResult = cmp.compare(x, t.element);

        if (compareResult < 0){
            t.left = insert(x, t.left, t);
        }else if (compareResult > 0){
            t.right = insert(x, t.right, t);
        }
        return t;
    }
    private BinaryNode<T> remove(T x, BinaryNode<T> t){
        //直到t == null，切断连接
        if (t == null){
            theSize --;
            return t;
        }

        int compareResult = cmp.compare(x, t.element);

        if (compareResult < 0){
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
            /**
             * 如果目标只有一个 != null 的节点
             * 直接走向 != null 节点，并与目标节点的上一个节点连接
             * 如果没有左右节点，则直接走向右边，即 t = null;
             */
            modCount ++;
            //用于连接的节点
            BinaryNode<T> connectNode = (t.left != null) ? t.left : t.right;
            if (connectNode != null){
                connectNode.parent = t.parent;
            }
            t = connectNode;
        }
        return t;
    }
    //迭代器主要实现！！！！！！！！！！
    private class TreeSetIterator implements Iterator<T>{

        //保存两个位置（迭代器相当于在这两个位置中间）
        private BinaryNode<T> current = findMin(root);
        private BinaryNode<T> previous;
        private int expectedModCount = modCount;
        private boolean okToRemove = false;
        private boolean atEnd = false;  //是否到最后

        @Override
        public boolean hasNext() {
            return !atEnd;
        }
        @Override
        public T next() {
            //异常处理
            if (modCount != expectedModCount){
                throw new ConcurrentModificationException();
            }
            if (! hasNext()){
                throw new NoSuchElementException();
            }
            //更换位置
            T nextItem = current.element;
            previous = current;
            //走向右子树最小节点
            if (current.right != null){
                current = findMin(current.right);
            }
            //走向父节点
            else {
                //保存上一个节点（作为子节点）
                BinaryNode<T> child = current;
                current = current.parent;
                //根据父子关系判断迭代器移动方向（如果不是左儿子，代表需要继续走向父节点）
                while(current != null && current.left != child){
                    child = current;
                    current = current.parent;
                }
                //到达最后
                if (current == null){
                    atEnd = true;
                }
            }
            okToRemove = true;
            return nextItem;
        }
        public void remove(){
            if (modCount != expectedModCount){
                throw new ConcurrentModificationException();
            }
            if (! okToRemove){
                throw new IllegalStateException();
            }

            MyTreeSet.this.remove(previous.element);
            okToRemove = false;
        }

    }

    //节点类
    private static class BinaryNode<T>{
        public T element;
        public BinaryNode<T> left;
        public BinaryNode<T> right;
        //指向父节点的链
        public BinaryNode<T> parent;

        public BinaryNode(T element){
            this(element,null,null,null);
        }
        public BinaryNode(T element, BinaryNode<T> left, BinaryNode<T> right, BinaryNode<T> parent){
            this.element = element;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }
    }
}
