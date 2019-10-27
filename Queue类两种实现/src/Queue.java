import java.util.NoSuchElementException;

public class Queue<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private T[] items;
    private int theSize;
    private int front, back;

    public Queue(){
        clear();
        front = back = 0;
    }
    public void clear(){
        items = (T[]) new Object[DEFAULT_CAPACITY];
        theSize = 0;
        //注意清空后起始位置和终止位置相同（但是不为一定0）
        front = back;
    }

    public int size(){
        return theSize;
    }
    public boolean isEmpty(){
        return theSize == 0;
    }

    public void enqueue(T x){
        items[back] = x;
        back ++;
        theSize ++;
        judge();
    }
    public T dequeue(){
        if (isEmpty()){
            throw new NoSuchElementException();
        }

        T result = items[front];
        front ++;
        theSize --;
        judge();
        return result;
    }

    //判断开始和结束下标是否越界（越界就返回 0）
    private void judge(){
        if (front == DEFAULT_CAPACITY){
            front = 0;
        }
        if (back  == DEFAULT_CAPACITY){
            back = 0;
        }
    }

}
