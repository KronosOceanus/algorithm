import java.util.Scanner;

public class Main {

    public static void f(int n) {
        //五位数和六位数之间寻找
        for (int i = 10000; i < 1000000; i++) {
            //五位数（逐个算出个十百千位，相加判断）
            if (i < 100000) {
                int a, b, c, d, e;
                a = i / 10000;
                b = i / 1000 % 10;
                c = i / 100 % 10;
                d = i / 10 % 10;
                e = i % 10;
                if (a == e && b == d && a + b + c + d + e == n) {
                    System.out.println(i);
                }
            }
            //六位数
            else {
                int a, b, c, d, e, f;
                a = i / 100000;
                b = i / 10000 % 10;
                c = i / 1000 % 10;
                d = i / 100 % 10;
                e = i / 10 % 10;
                f = i % 10;
                if (a == f && b == e && c == d && a + b + c + d + e + f == n) {
                    System.out.println(i);
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        f(n);
    }
}