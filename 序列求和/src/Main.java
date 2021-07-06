import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        long n = in.nextLong();
        long sum = 0;

        sum = (1 + n) * n / 2;
        System.out.println(sum);
    }

}