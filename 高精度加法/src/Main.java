import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuffer aa = new StringBuffer(sc.next()).reverse();
        StringBuffer bb = new StringBuffer(sc.next()).reverse();
        sc.close();

        //初始化
        int len = Math.max(aa.length(),bb.length());
        int[] a = new int[len];
        int[] b = new int[len];
        int[] c = new int[len];
        for (int i=0;i<aa.length();i++){
            a[i] = (aa.charAt(i) - 48);
        }
        for (int i=0;i<bb.length();i++){
            b[i] = (bb.charAt(i) - 48);
        }


        boolean flag = false; // flag 表示是否进位
        int t = 0; // t 表示本次相加结果
        for (int i=0;i<len;i++){
            t = a[i] + b[i];
            if (flag){
                t ++;
                flag = false;
            }
            if (t >= 10){
                flag = true;
            }
            c[i] = t % 10;
        }
        if (flag){
            System.out.print(1);
        }
        for (int i=len-1;i>=0;i--){
            System.out.print(c[i]);
        }
    }
}
