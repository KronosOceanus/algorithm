public class Main {

    /**
     * 希尔排序，不同的希尔增量有不同的时间界，大约在 O(N^7/6) 到 O(N^2) 之间
     * 在使用增量 hk 一次排序后，对于每一个 i 都有 a[i] <= a[i + hk]
     * 相当于间隔一定长度的插入排序
     *
     * Hibbard 增量 2^k - 1，能到达 O(N^3/2)
     */
    public static <T extends Comparable<? super T>> void shellSort(T[] a){
        int j;

        //常用增量序列（不好）
        for (int gap = a.length/2; gap > 0; gap /= 2){
            for (int i=gap; i<a.length; i++){
                T tmp = a[i];
                for (j=i; j>= gap && tmp.compareTo(a[j - gap]) < 0; j -= gap){
                    a[j] = a[j - gap];
                }
                a[j] = tmp;
            }
        }
    }


    public static void main(String[] args) {
        Integer[] integers = new Integer[5];
        integers[0] = 2;
        integers[1] = 4;
        integers[2] = 1;
        integers[3] = 5;
        integers[4] = 0;
        shellSort(integers);
        for (Integer integer : integers) {
            System.out.print(integer + "\t");
        }
    }
}
