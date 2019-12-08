public class Main {

    /**
     * 插入排序 O(N^2)
     * 在第 p 次排序，将位置 p 上的元素向左移动，直到它在 p+1 个元素中找到正确的位置
     */
    public static <T extends Comparable<? super T>> void insertSort(T[] a){
        int j;
        for (int p=1; p<a.length; p++){
            T tmp = a[p];
            for (j=p; j>0 && tmp.compareTo(a[j - 1]) < 0; j--){
                //元素向右移
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
        insertSort(integers);
        for (Integer integer : integers) {
            System.out.print(integer + "\t");
        }
    }
}
