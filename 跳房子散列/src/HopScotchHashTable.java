public class HopScotchHashTable<T> {

    private HashItem<T>[] arr;
    private int currentSize;

    private static final int DEFAULT_SIZE = 101;
    private static final double MAX_LOAD = 0.5;
    //最大跳跃距离
    private static final int MAX_DIST = 4;

    //初始化
    public HopScotchHashTable(){
        this(DEFAULT_SIZE);
    }
    public HopScotchHashTable(int size){
        arr = new HashItem[size];

        for (int i=0;i<size;i++){
            arr[i] = new HashItem<>(null);
        }
        currentSize = 0;
    }

    //打印测试
    public void printHash(){
        T value = null;
        for (HashItem<T> item : arr){
            value = item.element;
            if (value != null){
                System.out.print(value + "\t");
            }
        }
    }

    public boolean contains(T x){
        return findPos(x) != -1;
    }
    public void insert(T x){
        //再散列
        if (++ currentSize >= arr.length * MAX_LOAD){
            rehash();
        }
        insertHelper(x);
    }
    public void remove(T x){
        int pos = findPos(x);
        int hash = myhash(x);
        if (pos != -1){
            arr[pos] = null;
            arr[hash].dist -= 1 << (MAX_DIST - 1 - pos + hash);
            currentSize --;
        }
    }

    //主要实现！！！！！！
    private int findPos(T x){
        int hash = myhash(x);
        for (int i=0;i<MAX_DIST;i++){
            int dist = arr[hash].dist;
            if ((dist >> i) % 2 == 1){
                if (arr[hash + MAX_DIST - 1 - i].element.equals(x)){
                    return hash + MAX_DIST -1 - i;
                }
            }
        }
        return -1;
    }
    private void insertHelper(T x){
        while (true){
            int pos = myhash(x);
            int temp = pos;
            while(arr[pos].element != null){
                pos ++;
            }
            if (pos <= temp + MAX_DIST - 1){
                arr[pos].element = x;
                arr[temp].dist += 1 << (MAX_DIST - 1 + pos + temp);
                currentSize ++;
                return ;
            }
            while(true){
                boolean isNotDist = false;
                for (int i=MAX_DIST -1;i>0;i--){
                    for (int j=MAX_DIST - 1;j>MAX_DIST -1 - i;j--){
                        if ((arr[pos - i].dist >> j) % 2 == 1){
                            HashItem<T> item = arr[pos - i + MAX_DIST  - 1 - j];
                            arr[pos].element = item.element;
                            item.dist -= (1 << j) + 1;
                            pos = pos - i + MAX_DIST  - 1 - j;
                            if (pos <= temp + 3){
                                arr[pos].element = x;
                                arr[temp].dist += 1 << (MAX_DIST - 1 - pos + temp);
                                currentSize ++;
                                return ;
                            }else {
                                isNotDist = true;
                                break;
                            }
                        }
                    }
                    if (isNotDist){
                        break;
                    }
                }
                if (! isNotDist){
                    break;
                }
            }
            rehash();
        }
    }













    private void rehash(){
        HashItem<T>[] oldArr = arr;
        arr = new HashItem[(int)(arr.length / MAX_LOAD)];
        for (int i=0;i<arr.length;i++){
            arr[i] = new HashItem<>(null);
        }

        currentSize = 0;
        for (HashItem<T> oldItem : oldArr){
            if (oldItem.element != null){
                insert(oldItem.element);
            }
        }
    }
    private int myhash(T x){
        int hashVal = x.hashCode();

        hashVal %= arr.length;
        while(hashVal < 0){
            hashVal += arr.length;
        }
        return hashVal;
    }
    //元素
    private static class HashItem<T>{
        public T element;
        public int dist;

        public HashItem(T element){
            this.element = element;
        }
    }
}
