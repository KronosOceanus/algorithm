public class Main {

    /**
     * 堆排序，O(N logN)
     * 用数组元素构建堆，堆大小 - 1，再通过 delete 操作放回数组空缺的位置中（省去了附加内存）
     * 不同之处，改变堆序（max 堆），且元素从数组 0 位置开始
     */
    //得到左孩子
    private static int leftChild(int i) {
        return 2 * i + 1;
    }
    /**
     * 将 i 处的元素向堆下方移动
     * @param i 要下滤的空穴
     * @param n 表示当前堆的理论大小
     */
    private static <T extends Comparable<? super T>> void perDown(T[] a, int i, int n){
        int child;
        T tmp;

        // i 表示空穴， tmp 表示空穴原来元素
        for (tmp = a[i]; leftChild(i) < n; i = child){
            child = leftChild(i);
            if (child != n-1 && a[child].compareTo(a[child + 1]) < 0){
                child ++;
            }
            if (tmp.compareTo(a[child]) < 0){
                a[i] = a[child];
            }
            else
                break;
        }
        a[i] = tmp;
    }
    //交换数组 i ，j 处元素
    private static <T extends Comparable<? super T>> void swap(T[] a, int i, int j){
        T tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
    //排序
    public static <T extends Comparable<? super T>> void heapSort(T[] a){
        //构建堆
        for (int i=a.length/2 - 1; i>=0; i--){
            perDown(a, i, a.length);
        }
        //交换位置 0 与 i （数组末尾）的元素，再下滤 0 处的元素
        //即相当于执行 delete 操作后，将 0 处的元素放到 i 处，堆大小 - 1，再下滤保持堆序
        for (int i=a.length - 1; i>=0;i--){
            swap(a, 0, i);
            perDown(a, 0, i);
        }
    }



    public static void main(String[] args) {
        Integer[] integers = new Integer[5];
        integers[0] = 2;
        integers[1] = 4;
        integers[2] = 1;
        integers[3] = 5;
        integers[4] = 0;
        heapSort(integers);
        for (Integer integer : integers) {
            System.out.print(integer + "\t");
        }
    }
}
