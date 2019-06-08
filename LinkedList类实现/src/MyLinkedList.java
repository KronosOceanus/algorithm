import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * add，remove ++
 * set，get --
 */
public class MyLinkedList<T> implements Iterable<T>{

    private int theSize;
    //链表修改次数（迭代器中也有储存，每次修改对比，不同则迭代器失效）
    private int modCount = 0;
    private Node<T> beginMarker;
    private Node<T> endMarker;

    public MyLinkedList(){
        doClear();
    }
    public void clear(){
        doClear();
    }
    private void doClear(){
        beginMarker = new Node<>(null,null,null);
        endMarker = new Node<>(null,beginMarker,null);
        beginMarker.next = endMarker;

        theSize = 0;
        modCount ++;
    }

    public int size(){
        return theSize;
    }
    public boolean isEmpty(){
        return size() == 0;
    }

    //默认添加到尾部
    public boolean add(T x){
        add(size(),x);
        return true;
    }
    public void add(int idx, T x){
        //添加位置包括尾结点之前，所以用size()而不是size() - 1;
        addBefore(getNode(idx, 0, size()),x);
    }
    public T get(int idx){
        return getNode(idx).data;
    }
    public T set(int idx, T newVal){
        Node<T> p = getNode(idx);
        T oldVal = p.data;
        p.data = newVal;
        return oldVal;
    }
    public T remove(int idx){
        return remove(getNode(idx));
    }


    private void addBefore(Node<T> p, T x){
        Node<T> newNode = new Node<>(x,p.prev,p);
        newNode.prev.next = newNode;
        p.prev = newNode;
        theSize ++;
        modCount ++;
    }
    private T remove(Node<T> p){
        p.next.prev = p.prev;
        p.prev.next = p.next;
        theSize --;
        modCount ++;

        return p.data;
    }
    //在0 ~ size() - 1之间（有头尾结点的干扰）寻找idx（从0开始）处的结点
    private Node<T> getNode(int idx){
        return getNode(idx, 0, size() - 1);
    }
    private Node<T> getNode(int idx, int lower, int upper){
        Node<T> p;
        if (idx < lower || idx > upper){
            throw new IndexOutOfBoundsException();
        }
        //决定开始移动的位置
        if(idx < size()/2){
            p = beginMarker.next;
            for (int i=0;i<idx;i++){
                p = p.next;
            }
        }else {
            p = endMarker;
            for (int i=size();i>idx;i--){
                p = p.prev;
            }
        }
        return p;
    }
    //得到


    public Iterator<T> iterator(){
        return new LinkedListIterator();
    }
    /**
     * 内部类，可以直接与集合泛型关联
     * 迭代器失效（进行了迭代器之外的add或remove操作）
     */
    private class LinkedListIterator implements Iterator<T>{

        private Node<T> current = beginMarker.next;
        //记得进行操作时候 ++ 防止迭代器失效
        private int expectedModCount = modCount;
        //是否可以被移除（刚被next(读取)）
        private boolean okToRemove = false;

        public void addBefore(T x){
            MyLinkedList.this.addBefore(current,x);
            expectedModCount ++;
        }

        public boolean hasNext(){
            return current != endMarker;
        }
        public T next(){
            if (modCount != expectedModCount){
                throw new ConcurrentModificationException();
            }
            if (! hasNext()){
                throw new NoSuchElementException();
            }

            T nextItem = current.data;
            current = current.next;
            okToRemove = true;
            return nextItem;
        }
        //移除上一项，
        public void remove(){
            if (modCount != expectedModCount){
                throw new ConcurrentModificationException();
            }
            if (! okToRemove){
                throw new IllegalStateException();
            }

            //调用外部类的remove方法（modCount 会 ++）
            MyLinkedList.this.remove(current.prev);
            //迭代器不会失效
            expectedModCount ++;
            okToRemove = false;
        }
    };

    //遍历链表，完成contains操作
    public boolean contains(T x){
        if (size() == 0){
            return false;
        }
        for(Node<T> i=beginMarker.next;i != null;i=i.next){
            if (i.data.equals(x)){
                return true;
            }
        }
        return false;
    }
    //交换相邻两个元素
    public boolean exchange(int idx){
        if(idx >= size()){
            throw new IndexOutOfBoundsException();
        }
        Node<T> a = beginMarker, b;
        //找到指定位置
        for (int i=0;i<idx;i++){
            a = a.next;
        }
        b = a.next;

        //双向链表交换连接
        a.next = b.next;
        a.prev.next = b;
        b.prev = a.prev;
        a.prev = b;
        b.next.prev = a;
        b.next = a;

        return true;
    }

    //结点类（嵌套类）
    private static class Node<T>{

        public T data;
        public Node<T> prev;
        public Node<T> next;

        public Node(T d, Node<T> p, Node<T> n){
            this.data = d;
            this.prev = p;
            this.next = n;
        }
    }




    //练习
    //从List中删除另一个items集合（必须全部包含）（过于复杂，需要改进）
    public void removeAll(Iterable<? extends T> items, int itemsSize){
        //先将List转换成数组（方便进行get操作）
        T[] listArray = (T[]) new Object[theSize];
        int k = 0;
        for (Node<T> i = beginMarker.next; i != endMarker; i = i.next){
            listArray[k] = i.data;
            k ++;
        }
        //然后在items中查找相交元素的位置并记录在一个数组中，排序
        Iterator<? extends T> iterator = items.iterator();
        int[] indexs = new int[itemsSize];
        k = 0;
        //判断是否包含items中的元素（每个元素判断一次）
        boolean flag = false;
        while (iterator.hasNext()){
            T theNext = iterator.next();
            for (int i=0;i<theSize;i++){
                if (theNext.equals(listArray[i])){
                    flag = true;
                    indexs[k ++] = i;
                };
            }
            //不包含，抛出异常
            if (! flag){
                throw new NoSuchElementException();
            }
            flag = false;
        }
        Arrays.sort(indexs);
        //最后根据索引删除List中的项
        //k表示上一个要删除的位置索引
        k = 0;
        Node<T> x = beginMarker;
        for (int i=0;i<indexs.length;i++){///////////////////////////////////////////////
            for (int j=0;j<indexs[i] - k;j++){
                x = x.next;
            }
            remove(x.next);
            k = indexs[i] + 1;
        }
    }
    //将lst内容删除，添加到itr之前
    public void splice(Iterator<T> itr, MyLinkedList<T> lst){
        LinkedListIterator iteratorTo = (LinkedListIterator)itr;
        Iterator iteratorFrom = lst.iterator();
        while(iteratorFrom.hasNext()){
            iteratorTo.addBefore((T)iteratorFrom.next());
            iteratorFrom.remove();
        }
    }
}
