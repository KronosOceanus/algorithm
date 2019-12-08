public class Main {

    private static final int CUTOFF = 1;
    /**
     * 利用快速排序求解选择问题
     * 与快速排序不同的是只递归一次
     * 运行之后，a[k - 1]即要找的元素
     */
    public static <T extends Comparable<? super T>> void quickSelect(T[] a, int k){
        quickSelect(a, 0, a.length-1, k);
    }
    //三数中值分割法
    private static <T extends Comparable<? super T>> T median3(T[] a, int left, int right){
        int center = (left + right) / 2;
        if (a[center].compareTo(a[left]) < 0){
            swap(a, center, left);
        }
        if (a[right].compareTo(a[left]) < 0){
            swap(a, right, left);
        }
        if (a[right].compareTo(a[center]) < 0){
            swap(a, right, center);
        }

        //将枢纽元放在 right - 1 位置上
        swap(a, center, right - 1);
        return a[right - 1];
    }
    private static <T extends Comparable<? super T>> void quickSelect(T[] a, int left, int right, int k){
        if (left + CUTOFF <= right){
            T pivot = median3(a, left, right);

            //交换策略
            int i = left, j = right - 1;
            for ( ; ; ){
                while(i != right - 1 && a[++ i].compareTo(pivot) < 0){}
                while (j != 0 && a[-- j].compareTo(pivot) > 0){}
                if (i < j){
                    swap(a, i, j);
                }else {
                    break;
                }
            }

            //枢纽元复位
            swap(a, i, right - 1);

            if (k <= i){
                quickSelect(a, left, i - 1, k);
            }else if (k > i+1){
                quickSelect(a, i + 1, right, k);
            }
        }else {
            insertSort(a, left, right);
        }
    }
    //交换数组 i j 元素
    private static <T extends Comparable<? super T>> void swap(T[] a, int i, int j){
        T tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
    //插入排序
    private static <T extends Comparable<? super T>> void insertSort(T[] a, int left, int right){
        int j=0;
        for (int p=left; p<right-left; p++){
            T tmp = a[p];
            for (j=p; j>left && tmp.compareTo(a[j - 1]) < 0; j--){
                a[j] = a[j - 1];
            }
            a[j] = tmp;
        }
    }



    public static void main(String[] args) {
        Integer[] integers = new Integer[5];
        integers[0] = 2;
        integers[1] = 4;
        integers[2] = 1;
        integers[3] = 5;
        integers[4] = 0;
        quickSelect(integers, 3);
        // 0 1 2 4 5
        System.out.println(integers[3 - 1]);
    }
}
