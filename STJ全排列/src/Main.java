public class Main {

    // STJ 全排列
    private static void STJ(int[] a){
        print(a);
        int count = 1;      //统计全排列数

        boolean[] b = new boolean[a.length];    //记录移动方向，false 为左

        while(true){
            int maxIndex = findMaxCanMove(a, b);     //找到最大的可移动元素下标
            if (maxIndex != -1){
                count ++;
                if (b[maxIndex]){
                    swap(a, b, maxIndex, ++ maxIndex);     //移动
                }else {
                    swap(a, b, maxIndex, -- maxIndex);
                }
                print(a);
                reverse(a, b, maxIndex);        //反转
            }else {
                System.out.println("全排列共：" + count + " 种.");
                break;
            }
        }
    }

    //交换数组元素位置
    private static void swap(int[] a, boolean[] b, int i, int j){
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
        boolean temp = b[i];
        b[i] = b[j];
        b[j] = temp;
    }
    //找到最大的可移动元素（可移动方向上存在元素且元素比它小）
    private static int findMaxCanMove(int[] a, boolean[] b){
        int maxIndex = -1;      // -1 表示未找到，算法结束
        for (int i=0;i<a.length;i++){
            if (maxIndex == -1){        //寻找可以移动的
                if (b[i]){
                    if (i+1 < a.length && a[i] > a[i+1]){
                        maxIndex = i;
                    }
                }else {
                    if (i-1 >= 0 && a[i] > a[i-1]){
                        maxIndex = i;
                    }
                }
            }else {         //在可以移动的元素中寻找最大的
                if (b[i]){
                    if (i+1 < a.length && a[i] > a[i+1] && a[i] > a[maxIndex]){
                        maxIndex = i;
                    }
                }else {
                    if (i-1 > 0 && a[i] > a[i-1] && a[i] > a[maxIndex]){
                        maxIndex = i;
                    }
                }
            }
        }
        return maxIndex;
    }
    //反转比 a[k] 大的数字的移动方向
    private static void reverse(int[] a, boolean[] b, int k){
        for (int i=0;i<a.length;i++){
            if (a[i] > a[k]){
                b[i] = !b[i];
            }
        }
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
        STJ(a);
    }
}
