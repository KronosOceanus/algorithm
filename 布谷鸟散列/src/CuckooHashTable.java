import java.util.Random;

public class CuckooHashTable<T> {

    //初始化
    public CuckooHashTable(HashFamily<? super T> hf){
        this(hf, DEFAULT_TABLE_SIZE);
    }
    public CuckooHashTable(HashFamily<? super T> hf, int size){
        allocateArray(size);
        doClear();
        this.hashFunctions = hf;
        numHashFunctions = hf.gerNumberOfFunctions();
    }

    //成员变量
    private static final double MAX_LOAD = 0.4; //最大装载因子
    private static final int ALLOWED_REHASHES = 1;  //最多再散列（只换函数组）次数
    private static final int DEFAULT_TABLE_SIZE = 11;

    private final HashFamily<? super T> hashFunctions;
    private final int numHashFunctions;
    private T[] array;
    private int currentSize;

    //测试操作
    public int size(){
        return currentSize;
    }
    public int tableSize(){return array.length; }

    //主要操作
    public boolean contains(T x){
        return findPos(x) != -1;
    }
    public boolean insert(T x){
        if (contains(x)){
            return false;
        }
        if (currentSize >= array.length * MAX_LOAD){
            //再散列扩容
            expand();
        }

        //交给插入辅助操作处理
        return insertHelper(x);
    }
    public boolean remove(T x){
        int pos = findPos(x);

        if (pos != -1){
            array[pos] = null;
            currentSize --;
        }

        return pos != -1;
    }

    //布谷鸟散列主要实现！！！！！！！！！！！！！！！！
    private int rehashes = 0;
    private Random r = new Random();
    private boolean insertHelper(T x){
        final int COUNT_LIMIT = 100;

        while(true){
            int lastPos = -1;
            int pos;

            //循环替换主要实现
            for (int count=0;count<COUNT_LIMIT;count++){
                for (int i=0;i<numHashFunctions;i++){
                    pos = myhash(x, i);
                    //找到位置，插入成功
                    if (array[pos] == null){
                        array[pos] = x;
                        currentSize ++;
                        return true;
                    }
                }

                //冲突
                int i=0;
                do {
                    //随机选函数得到地址，与上一个元素地址不同
                    pos = myhash(x, r.nextInt(numHashFunctions));
                }while(pos == lastPos && i++ < 5);

                //循环替换一次
                T temp = array[lastPos = pos];
                array[pos] = x;
                x = temp;

                //再散列次数过多
                if ( ++ rehashes > ALLOWED_REHASHES){
                    //扩容
                    expand();
                }
                else{
                    //换函数组
                    rehash();
                }
            }
        }
    }


    //类内操作
    private void allocateArray(int arraySize){
        //创建泛型数组
        array = (T[])new Object[nextPrime(arraySize)];
    }
    private void doClear(){
        currentSize = 0;
        for (int i=0;i<array.length;i++){
            array[i] = null;
        }
    }
    //用所有的散列函数，遍历全表
    private int findPos(T x){
        for (int i=0;i<numHashFunctions;i++){
            int pos = myhash(x, i);
            if (array[pos] != null && array[pos].equals(x)){
                return pos;
            }
        }
        return -1;
    }
    //再散列扩容
    private void expand(){
        rehash((int) (array.length / MAX_LOAD));
    }
    //再散列换函数
    private void rehash(){
        hashFunctions.generateNewFunctions();
        rehash(array.length);
    }
    //再散列
    private void rehash(int arraySize){
        T[] oldArray = array;
        allocateArray(nextPrime(arraySize));
        currentSize = 0;

        for (T str : oldArray){
            if (str != null){
                insert(str);
            }
        }
    }

    //辅助操作
    //根据第 which 个函数得到地址
    private int myhash(T x, int which){
        int hashVal = hashFunctions.hash(x, which);

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
