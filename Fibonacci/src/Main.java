import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n + 2];
        a[1] = 1;
        a[2] = 1;
        for (int i = 3; i <= n; i++)
            a[i] = (a[i - 1] + a[i - 2]) % 10007;
        System.out.println(a[n]);
        sc.close();
    }
}