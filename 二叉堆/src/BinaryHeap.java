import java.nio.BufferUnderflowException;
import java.util.Arrays;
import java.util.Comparator;

/**
 * 二叉堆，根元素总比左右子堆元素小
 * 主要操作是 insert（上滤）和 deleteMin（下滤）
 * 上/下滤的本质是空穴向上/下移动
 */
public class BinaryHeap <T>{

    private static final int DEFAULT_CAPACITY = 10;

    private T[] array;
    private int currentSize;
    private Comparator<? super T> cmp;

    //初始化
    public BinaryHeap(){
        this(DEFAULT_CAPACITY);
    }
    public BinaryHeap(Comparator<? super T> cmp){
        this(DEFAULT_CAPACITY);
        this.cmp = cmp;
    }
    public BinaryHeap(int capacity){
        array = (T[])new Object[capacity];
        currentSize = 0;
    }
    public BinaryHeap(T[] items, Comparator<? super T> cmp){
        this.cmp = cmp;
        currentSize = items.length;
        array = (T[])new Object[(currentSize + 2) * 11 / 10];

        int i=1;
        for (T item : items){
            array[i ++] = item;
        }
        buildHeap();
    }
    //比较
    private int myCompare(T x1, T x2){
        return cmp.compare(x1, x2);
    }

    //主要操作
    //插入（上滤）
    public void insert(T x){
        if (currentSize == array.length - 1){
            enlargeArray(array.length * 2 + 1);
        }

        int hole = ++ currentSize;
        for (array[0] = x; myCompare(x, array[hole / 2]) < 0; hole /= 2){
            array[hole] = array[hole / 2];
        }
        array[hole] = x;
    }
    public T deleteMin(){
        if (isEmpty()){
            throw new BufferUnderflowException();
        }

        //得到最小元
        T minItem = findMin();
        //将 x 放入根
        array[1] = array[currentSize --];
        //从根开始下滤
        percolateDown(1);

        return minItem;
    }
    public T findMin(){
        if (isEmpty()){
            throw new BufferUnderflowException();
        }

        return array[1];
    }

    //次要操作
    public int size(){
        return currentSize;
    }
    public boolean isEmpty(){
        return currentSize == 0;
    }
    public void makeEmpty(){
        array = (T[])new Object[DEFAULT_CAPACITY];
        currentSize = 0;
    }

    //类内操作
    //下滤
    private void percolateDown(int hole){
        int child;
        T x = array[hole];  //得到 x

        for ( ; hole * 2 <= currentSize; hole = child){
            child = hole * 2;
            //如果有右子堆，且右子堆元素比较小
            if (child != currentSize && myCompare(array[child +1], array[child]) < 0){
                child ++;   //向右走
            }
            //下滤
            if (myCompare(array[child], x) < 0){
                array[hole] = array[child];
            }
            else break;
        }

        array[hole] = x;
    }
    //构建堆
    private void buildHeap(){
        for (int i=currentSize / 2; i>0; i--){
            percolateDown(i);
        }
    }
    //数组扩容（类似于再散列）
    private void enlargeArray(int newSize){
        T[] oldArray = array;
        array = (T[]) new Object[newSize];
        //数组复制
        System.arraycopy(oldArray, 1, array, 1, currentSize);
    }
}
