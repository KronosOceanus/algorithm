import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int r = sc.nextInt();
        double s;
        s = Math.PI * r * r;
        System.out.printf("%.7f\n",s);
    }
}
