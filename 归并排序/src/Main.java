public class Main {

    //递归
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

    //非递归
    public static <T extends Comparable<? super T>> void mergeSort2(T[] a){
        //使用附加内存
        T[] tmpArray = (T[])new Comparable[a.length];

        mergeSort2(a, tmpArray, 0, a.length - 1);
    }
    private static <T extends Comparable<? super T>> void mergeSort2(T[] a, T[] tmpArray, int left, int right){
        int n = a.length;

        for (int subListSize=1; subListSize<n; subListSize*=2){
            int part1Start = 0;

            while(part1Start + subListSize < n){
                int part2Start = part1Start + subListSize;
                int part2End = Math.min(n-1, part2Start + subListSize - 1);

                //                           相当于 center + 1
                merge(a, tmpArray, part1Start, part2Start, part2End);
                part1Start = part2End + 1;
            }
        }
    }

    //公用方法
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
        mergeSort2(integers);
        for (Integer integer : integers) {
            System.out.print(integer + "\t");
        }
    }
}
