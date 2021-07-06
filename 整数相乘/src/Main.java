import java.util.Scanner;

/**
 * 将 N 位数字 X Y 平均拆分为 XL, XR, YL, YR
 * 令 K = (N+1) / 2
 * 则 X * Y = XL * YL * 10^(2*K) + (XL * XR + YL * YR) * 10^K + XR * YR
 * 其中 XL * XR + YL * YR = (XL - XR)(YR - YL) + XL * YL + XR * YR
 * 降低了时间复杂度
 */
public class Main {

    private static final int CUTOFF = 2;    //递归终止条件
    //递归相乘
    private static long multi(long X, long Y, int N){
        //终止条件
        if (N <= CUTOFF){
            return X * Y;
        }

        int K = (N+1)/2;
        long XL = (long)(X / Math.pow(10, K));
        long XR = (long)(X - (XL * Math.pow(10, K)));
        long YL = (long)(Y / Math.pow(10, K));
        long YR = (long)(Y - (YL * Math.pow(10, K)));

        long r1 = multi(XL, YL, N-K);
        long r2 = multi(XR, YR, K);
        long r3 = multi(XL - XR, YR - YL, N-K);

        return (long)(r1 * Math.pow(10, 2*K) + (r1 + r2 + r3) * Math.pow(10, K) + r2);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();   //数字位数
        long X = sc.nextLong();
        long Y = sc.nextLong();
        System.out.println(multi(X, Y, N));
        System.out.println(X * Y);
    }
}
