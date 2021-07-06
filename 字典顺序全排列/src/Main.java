public class Main {

    //字典顺序全排列
    private static void dictCombin(int[] a){
        print(a);       //输出自身
        int count = 1;

        while(true){
            int index = rfind(a);        //倒序寻找第一个 a[i] < a[i+1] 的地方，不存在则全排列完成
            if (index == -1){
                break;
            }
            int index2 = rfind2(a, index);        //寻找比 a[index] 大最后一个数
            if (index2 != -1){
                swap(a, index, index2);         //交换位置
                reverse(a, index);      //反转 index 开始的的子序列
                print(a);       //输出
                count ++;
            }
            else {
                break;
            }
        }
        System.out.println(count);
    }


    //倒序寻找第一个 a[i] < a[i+1] 的下标，不存在则全排列完成
    private static int rfind(int[] a){
        for (int i=a.length-2;i>=0;i--){
            if (a[i] < a[i+1]){
                return i;
            }
        }
        return -1;
    }
    //寻找比 a[index] 大最后一个数下标
    private static int rfind2(int[] a, int index){
        for (int i=a.length-1;i>=index;i--){
            if (a[i] > a[index]){
                return i;
            }
        }
        return -1;
    }
    //反转 index 开始的的子序列
    private static void reverse(int[] a, int index){
        for (int i=index+1, j=a.length-1; i<j; i++, j--){
            swap(a, i, j);
        }
    }
    //交换
    private static void swap(int[] a, int i, int j){
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
    //输出一次排列
    private static void print(int[] a){
        for (Integer i : a){        //输出
            System.out.print(i + "");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] a = new int[4];
        for (int i=0;i<a.length;i++){
            a[i] = i+1;
        }
        dictCombin(a);
    }
}
