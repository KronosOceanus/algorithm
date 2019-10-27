/**
 * 附加额外的散列函数 myhash2(x)
 * 使用函数 f(i) = i * myhash2(x)
 */
public class DoubleHashTable<T> {

    //初始化
    public DoubleHashTable(){
        allocateArray(DEFAULT_TABLE_SIZE);
        makeEmpty();
    }
    public DoubleHashTable(int size){
        allocateArray(size);
        makeEmpty();
    }
    public void makeEmpty(){
        currentSize = 0;
        for (int i=0;i<array.length;i++){
            array[i] = null;
        }
    }

    private static class HashEntry<T>{
        public T element;
        public boolean isActive; //是否被删除（懒惰删除）

        public HashEntry(){}

        public HashEntry(T element, boolean isActive){
            this.element = element;
            this.isActive = isActive;
        }
    }

    //成员变量
    private static final int DEFAULT_TABLE_SIZE = 11;
    private static final int R = 7; // R < DEFAULT_TABLE_SIZE，且是素数
    private HashEntry<T>[] array;
    private int currentSize;

    //测试操作
    public int size(){
        return currentSize;
    }
    public int tableSize(){return array.length; }

    //主要操作
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

    //双散列主要实现！！！！！！！！！！！！！
    private int findPos(T x){
        // i 表示本次冲突次数
        int i=0;
        int currentPos = myhash(x);

        //冲突！
        while(array[currentPos] != null && !array[currentPos].element.equals(x)){
            i++;
            //双散列！
            currentPos = i * myhash2(x); //使用函数 f(i) = i * hash2(x);

            if (currentPos >= array.length){
                currentPos -= array.length;
            }
        }

        return currentPos;
    }
    //类内操作
    private void allocateArray(int arraySize){
        array = new HashEntry[nextPrime(arraySize)];
    }
    private boolean isActive(int currentPos){
        return array[currentPos] != null && array[currentPos].isActive;
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
    //附加散列函数，其中 R 为 < tableSize 的质数
    private int myhash2(T x){
        return R - (x.hashCode() % R);
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
