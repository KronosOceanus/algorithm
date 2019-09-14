/**
 * 平方探测散列表，缺点是可能发生二次聚集
 */
public class QuadraticProbingHashTable<T> {

    //初始化
    public QuadraticProbingHashTable(){
        allocateArray(DEFAULT_TABLE_SIZE);
        makeEmpty();
    }
    public QuadraticProbingHashTable(int size){
        allocateArray(size);
        makeEmpty();
    }
    public void makeEmpty(){
        currentSize = 0;
        for (int i=0;i<array.length;i++){
            array[i] = null;
        }
    }

    //基本操作
    public boolean contains(T x){
        //不管是否包含，都会返回一个位置
        int currentPos = findPos(x);
        return isActive(currentPos);
    }
    public void insert(T x){
        int currentPos = findPos(x);
        if (isActive(currentPos)){
            return;
        }
        //初始化空间，并插入
        array[currentPos] = new HashEntry<>(x, true);
        //λ > 1/2 时，再散列
        if (++ currentSize > array.length / 2){
            rehash();
        }
    }
    public void remove(T x){
        int currentPos = findPos(x);
        if (isActive(currentPos)){
            array[currentPos].isActive = false;
        }
    }

    //测试操作
    public int size(){
        return currentSize;
    }
    public int tableSize(){return array.length; }

    //成员变量
    private static class HashEntry<T>{
        public T element;
        public boolean isActive; //是否被删除（懒惰删除）

        public HashEntry(){}

        public HashEntry(T element, boolean isActive){
            this.element = element;
            this.isActive = isActive;
        }
    }
    private static final int DEFAULT_TABLE_SIZE = 11;
    private HashEntry<T>[] array;
    private int currentSize;

    //类内操作
    private void allocateArray(int arraySize){
        array = new HashEntry[nextPrime(arraySize)];
    }
    private boolean isActive(int currentPos){
        return array[currentPos] != null && array[currentPos].isActive;
    }
    //平方探测散列主要实现！！！！！！！！！！
    private int findPos(T x){
        int offset = 1;
        int currentPos = myhash(x);

        //平方探测！
        while (array[currentPos] != null &&
            !array[currentPos].element.equals(x)){

            //快速简单的平方探测，使用函数 f(i) = f(i-1) + 2*i - 1
            currentPos += offset;
            offset += 2;
            if (currentPos >= array.length){    //防止越界
                currentPos -= array.length;
            }
        }

        return currentPos;
    }
    private void rehash(){
        HashEntry<T>[] oldArray = array;
        allocateArray(array.length * 2);

        currentSize = 0; //后面执行插入
        for (HashEntry<T> entry : oldArray){
            if (entry != null && entry.isActive){
                insert(entry.element);
            }
        }
    }

    //辅助操作
    private int myhash(T x){
        int hashVal = x.hashCode();

        hashVal %= array.length;
        if (hashVal < 0){
            hashVal += array.length;
        }

        return hashVal;
    }
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
