import java.util.Arrays;

public class Main{

    public static void main(String[] args) {
        int[] a = new int[3];
        for (int i = 0; i < a.length; i++) {
            a[i] = i+1;
        }
        fun(a,0, a.length);
    }

    private static void fun(int[] a, int begin, int end) {  //begin代表当前层，end代表总层数
        if(begin >= end){   //当每层都赋值完毕，即可输出结果
            System.out.println(Arrays.toString(a));
        }
        for (int i = begin; i < end; i++) {
            swap(a,begin,i);        //给当前层赋值，利用交换赋值
            fun(a,begin+1,end);
            swap(a,begin,i);        //回溯,回到初始状态
        }
    }

    //交换
    private static void swap(int[] a, int begin, int i) {
        int t = a[begin];
        a[begin] = a[i];
        a[i] = t;
    }
}