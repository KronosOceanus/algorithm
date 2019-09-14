import java.util.LinkedList;
import java.util.List;

/**
 * 分离链接法散列表
 * 内容：默认表大小，二级链表，当前大小
 *      构造器 * 2，清空，包含，插入，移除
 *      散列函数，再散列，下一个质数
 */
public class SeparateChainingHashTable<T> {

    private static final int DEFAULT_TABLE_SIZE = 101;
    private List<T>[] theLists;
    private int currentSize;

    //两个构造器（根据 size 初始化表空间）
    public SeparateChainingHashTable(){
        this(DEFAULT_TABLE_SIZE);
    }
    public SeparateChainingHashTable(int size){
        theLists = new LinkedList[nextPrime(size)];
        for(int i=0;i<theLists.length;i++){
            theLists[i] = new LinkedList<>();
        }
    }
    //清空散列
    public void makeEmpty(){
        for (List<T> theList : theLists) {
            theList.clear();    //应用 api
        }
        currentSize = 0;
    }


    //主要操作
    public boolean contains(T x){
        List<T> whichList = theLists[myhash(x)];
        return whichList.contains(x);
    }
    public void insert(T x){
        List<T> whichList = theLists[myhash(x)];
        if (! whichList.contains(x)){
            whichList.add(x);
            //负载因子 > 1
            if (++ currentSize > theLists.length){
                rehash(); //再散列
            };
        }
    }
    public void remove(T x){
        List<T> whichList = theLists[myhash(x)];
        if (whichList.contains(x)){
            whichList.remove(x);
            currentSize --;
        }
    }


    //计算地址（在这里是数组下标，根据下标得到链表）
    private int myhash(T x){
        int hashVal = x.hashCode();

        hashVal %= theLists.length;
        if (hashVal < 0){  //防止负值
            hashVal += theLists.length;
        }

        return hashVal;
    }
    //再散列
    private void rehash(){
        List<T>[] oldLists = theLists;
        theLists = new LinkedList[nextPrime(theLists.length * 2)];
        for (int i=0;i<theLists.length;i++){
            theLists[i] = new LinkedList<>();
        }

        currentSize = 0;
        //遍历插入
        for (List<T> oldList : oldLists){
            for (T x : oldList){
                insert(x);
            }
        }
    }


    //得到 n 之后的第一个质数……
    private int nextPrime(int n){
        int res;
        int order = 0; //计算找到质数需要次数
        for (int i=n+1;i<2*n;i++){
            for (int j=2;j<(i/j+1);j++){
                order ++;
                res = i/j;
                //本例的关键
                if((res-j)<=1 && i%j!=0){
                    return i;
                }
                if(i%j == 0){
                    break;
                }
            }
        }
        return 0;
    }
}
