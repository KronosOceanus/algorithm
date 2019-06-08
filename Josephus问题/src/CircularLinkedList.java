/**
 * 循环链表
 * */
public class CircularLinkedList<T> {

    private int theSize;
    private Node<T> current;
    private Node<T> beginMarker;
    //结点类
    private static class Node<T>{
        public T data;
        public Node<T> next;

        public Node(T x){
            this.data = x;
            this.next = null;
        }
    }

    public CircularLinkedList(){
        clear();
    }
    public void clear(){
        theSize = 0;
    }
    public int size(){
        return theSize;
    }

    //向最后添加
    public boolean add(T x){
        Node<T> newNode = new Node<>(x);
        if (size() == 0){
            current = beginMarker = newNode;
        }else {
            current.next = newNode;
            current = newNode;
        }
        newNode.next = beginMarker;
        theSize ++;
        return true;
    }
    //添加完毕
    public void addFinish(){
        current = beginMarker;
    }
    //得到当前结点内容，并且向下一步
    public T get(){
        T result = current.data;
        current = current.next;
        return result;
    }
    //向下一步
    public void toNext(){
        current = current.next;
    }
    //删除当前结点
    public T remove(){
        if (theSize == 0){
            throw new IndexOutOfBoundsException();
        }

        T result = current.data;
        current = current.next;
        theSize --;
        return result;
    }

}
