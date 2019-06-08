import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * get，set，print ++
 * add，remove（要移动数组元素） --
 */
public class MyArrayList<T> implements Iterable<T>{

    private static final int DEFAULT_CAPACITY = 10;

    private int theSize;
    private T[] theItems;
    //初始化
    public MyArrayList(){
        doClear();
    }
    //清理集合
    public void doClear(){
        theSize = 0;
        ensureCapacity(DEFAULT_CAPACITY);
    }
    //内容长度，是否为空，节约内存
    public int size(){
        return theSize;
    }
    public boolean isEmpty(){
        return theSize == 0;
    }
    public void trimToSize(){
        ensureCapacity(theSize);
    }

    public T get(int idx){
        if (idx < 0 || idx >= size()){
            throw new ArrayIndexOutOfBoundsException();
        }
        return theItems[idx];
    }
    public T set(int idx, T newVal){
        if (idx < 0 || idx >= size()){
            throw new ArrayIndexOutOfBoundsException();
        }
        T old = theItems[idx];
        theItems[idx] = newVal;
        return old;
    }
    //修改容量
    public void ensureCapacity(int newCapacity){
        if (newCapacity < theSize){
            return;
        }
        T[] old = theItems;
        theItems = (T[])new Object[newCapacity];
        for (int i=0;i<size();i++){
            theItems[i] = old[i];
        }
    }

    public boolean add(T x){
        add(size(),x);
        return true;
    }
    //防止外部直接扩容插入
    private void add(int idx, T x){
        if (theItems.length == size()){
            ensureCapacity(size() * 2 + 1);
        }
        //数组后移
        for (int i=theSize;i>idx;i--){
            theItems[i] = theItems[i - 1];
        }
        theItems[idx] = x;
        theSize ++;
    }
    //将集合添加到List后面
    public void addAll(Iterable<? extends T> items){
        Iterator<? extends T> iterator = items.iterator();
        while(iterator.hasNext()){
            add(iterator.next());
        }
    }

    public T remove(int idx){
        T removeItem = theItems[idx];
        for (int i=idx;i<size();i++){
            theItems[i] = theItems[i + 1];
        }
        theSize --;
        return removeItem;
    }

    public ListIterator<T> iterator(){
        return new ArrayListIterator();
    }

    //使用内部类保留了MyArrayList的隐式引用
    private class ArrayListIterator implements ListIterator<T>{

        //相当于两个元素中间（偏后）
        private int current = 0;

        //新增方法
        @Override
        public void add(T t) {
            MyArrayList.this.add(current,t);
        }
        @Override
        public void set(T t) {
            MyArrayList.this.set(current,t);
        }

        @Override
        public boolean hasPrevious() {
            return current > 0;
        }
        @Override
        public T previous() {
            if (! hasPrevious()){
                throw new NoSuchElementException();
            }
            return theItems[-- current];
        }
        @Override
        public int previousIndex() {
            if (! hasPrevious()){
                throw new NoSuchElementException();
            }
            return current - 1;
        }

        @Override
        public boolean hasNext() {
            return current < size();
        }
        @Override
        public T next() {
            if (! hasNext()){
                throw new NoSuchElementException();
            }
            return theItems[current ++];
        }
        @Override
        public int nextIndex() {
            if (! hasNext()){
                throw new NoSuchElementException();
            }
            return current;
        }

        //调用外部类的remove方法，迭代器失效
        @Override
        public void remove() {
            MyArrayList.this.remove(-- current);
        }
    }

}
