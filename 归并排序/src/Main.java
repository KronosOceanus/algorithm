public class Main {

    public static <T extends Comparable<? super T>> void mergeSort(T[] a){
        //使用附加内存
        T[] tmpArray = (T[])new Comparable[a.length];

        mergeSort(a, tmpArray, 0, a.length - 1);
    }
    private static <T extends Comparable<? super T>> void mergeSort(T[] a, T[] tmpArray, int left, int right){
        if (left < right){
            int center = (left + right) / 2;
            mergeSort(a, tmpArray, left, center);
            mergeSort(a, tmpArray, center + 1, right);
            // center + 1 代表右边起始位置，根据它可以算出左边结束位置
            merge(a, tmpArray, left, center + 1, right);
        }
    }
    private static <T extends Comparable<? super T>> void merge(T[] a, T[] tmpArray,
                                                                int leftPos, int rightPos, int rightEnd){
        int leftEnd = rightPos - 1;
        int tmpPos = leftPos;
        int numElements = rightEnd - leftPos + 1;

        //合并到附加数组
        while(leftPos <= leftEnd && rightPos <= rightEnd){
            if (a[leftPos].compareTo(a[rightPos]) <= 0){
                tmpArray[tmpPos ++] = a[leftPos ++];
            }
            else {
                tmpArray[tmpPos ++] = a[rightPos ++];
            }
        }
        while(leftPos <= leftEnd){
            tmpArray[tmpPos ++] = a[leftPos ++];
        }
        while(rightPos <= rightEnd){
            tmpArray[tmpPos ++] = a[rightPos ++];
        }

        //最终递归终止条件，numElement == 0
        //之所以从 rightEnd 开始复制，是因为只有这一个量在当前递归中保持不变，能复制到对应的位置
        for (int i=0;i<numElements;i++, rightEnd--){
            a[rightEnd] = tmpArray[rightEnd];
        }
    }

    public static void main(String[] args) {
        Integer[] integers = new Integer[5];
        integers[0] = 2;
        integers[1] = 4;
        integers[2] = 1;
        integers[3] = 5;
        integers[4] = 0;
        mergeSort(integers);
        for (Integer integer : integers) {
            System.out.print(integer + "\t");
        }
    }
}
