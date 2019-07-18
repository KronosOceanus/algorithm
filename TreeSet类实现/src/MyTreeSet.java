import java.util.Comparator;
import java.util.SortedSet;
import java.util.Spliterator;

/**
 * AVL（平衡二叉查找树）实现，传入比较器版本
 * 迭代器使用二叉查找树，每个节点上添加一个指向父节点的链
 */
public class MyTreeSet<T> implements SortedSet {

    private int theSize;
    private Node<T> root;
    //需要排序操作时若没有比较器，抛出异常
    private Comparator<? super T> cmp;

    public MyTreeSet(){
        this(null);
    }
    public MyTreeSet(Comparator<? super T> cmp){
        clear();
        this.cmp = cmp;
    }

    @Override
    public void clear() {
        this.root = null;
        theSize = 0;
    }
    @Override
    public int size() {
        return theSize;
    }
    @Override
    public boolean isEmpty() {
        return theSize == 0;
    }

    //节点类
    private static class Node<T>{
        public T element;
        //指向父节点的链
        public Node<T> father;
        public Node<T> left;
        public Node<T> right;
        //当前节点的高度
        public int height;

        public Node(T element){
            this(element,null,null,null);
        }
        public Node(T element,Node<T> father,Node<T> left,Node<T> right){
            this.element = element;
            this.father = father;
            this.left = left;
            this.right = right;
            this.height = 0;
        }
    }
}
