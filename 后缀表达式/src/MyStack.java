/**
 * 单链表实现
 */
public class MyStack<T> {

    private static class Node<T>{
        private T data;
        private Node<T> next;

        public Node(T x, Node<T> n){
            this.data = x;
            this.next = n;
        }
    }

    private int theSize;
    private Node<T> endMarker;
    private Node<T> current;
    public MyStack(){
        doClear();
    }
    public void clear(){
        doClear();
    }
    private void doClear(){
        endMarker = new Node<>(null, null);
        current = endMarker;
        theSize = 0;
    }

    public int size(){
        return theSize;
    }
    public boolean isEmpty(){
        return theSize == 0;
    }

    public boolean push(T x){
        current = new Node<>(x, current);
        theSize ++;
        return true;
    }

    public T pop(){
        if (theSize == 0){
            throw new IndexOutOfBoundsException();
        }

        T result = current.data;
        current = current.next;
        theSize --;
        return result;
    }

    public T top(){
        if (theSize == 0){
            throw new IndexOutOfBoundsException();
        }
        return current.data;
    }
}
