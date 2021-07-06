import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int sec = sc.nextInt();
        sc.close();

        int h, m, s;
        h = sec / 3600;
        m = (sec - 3600 * h) / 60;
        s = (sec - 3600 * h) % 60;
        System.out.println(h + ":" + m + ":" + s);
    }
}
