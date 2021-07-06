import java.util.Scanner;

/**
 * 1：sin(1)+1
 * 2：(sin(1)+2)sin(1-sin(2))+1
 * 3：((sin(1)+3)sin(1-sin(2))+2)sin(1-sin(2+sin(3)))+1
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        S(n);
    }

    static void S(int n) {
        for (int i = 1; i < n; i++) {   //输出 n-1 个（
            System.out.print("(");
        }
        for (int i = 1; i <= n; i++) {
            A(i);   //输出分为 3 部分
            System.out.print("+" + (n - i + 1));
            if (i != n)
                System.out.print(")");
        }

    }

    private static void A(int n) {
        for (int i = 1; i <= n; i++) {
            System.out.print("sin(" + i);
            if (i % 2 == 0 && i != n)
                System.out.print("+");

            else if (i % 2 != 0 && i != n) {
                System.out.print("-");
            }
        }
        for (int i = 1; i <= n; i++)
            System.out.print(")");
    }
}