public class Main {

    private static final int CUTOFF = 1;

    /**
     * 快速排序
     * 与归并排序相似，不过是用枢纽元将数组划分成两部分，分别排序后合并
     */
    public static <T extends Comparable<? super T>> void quickSort(T[] a){
        quickSort(a, 0, a.length-1);
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
    private static <T extends Comparable<? super T>> void quickSort(T[] a, int left, int right){
        if (left + CUTOFF <= right){
            T pivot = median3(a, left, right);

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

            quickSort(a, left, i - 1);
            quickSort(a, i + 1, right);
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
        quickSort(integers);
        for (Integer integer : integers) {
            System.out.print(integer + "\t");
        }
    }
}
