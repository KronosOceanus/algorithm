import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int len = sc.nextInt();
        int[] a = new int[len];
        int[] b = new int[len];
        for (int i=0;i<len;i++){
            a[i] = sc.nextInt();
        }
        for (int i=0;i<len;i++){
            b[i] = sc.nextInt();
        }
        sc.close();

        Arrays.sort(a);
        Arrays.sort(b);
        int aL = 0, aR = len - 1;
        int bL = 0, bR = len - 1;
        int aMid, bMid;
        while(true){
            //输出两个中位数
            if ((aR - aL) == 1 && (bR - bL) == 1){
                System.out.println(a[aL] >= b[bL] ? a[aL] : b[bL]);
                System.out.println(a[aR] <= b[bR] ? a[aR] : b[bR]);
                break;
            }else {
                if (len % 2 == 0){
                    aMid = aL + len / 2 - 1;
                    bMid = bL + len / 2 - 1;
                }else {
                    aMid = aL + len / 2;
                    bMid = bL + len / 2;
                }

                //一个中位数
                if (a[aMid] == b[bMid]){
                    System.out.println(a[aMid] + " " + b[bMid]);
                    break;
                }else if (a[aMid] < b[bMid]){
                    aL = aMid + 1;
                    len = aR - aL;
                    bR = bL + len;
                }else {
                    bL = bMid + 1;
                    len = bR - bL;
                    aR = aL + len;
                }
            }
        }
    }
}
