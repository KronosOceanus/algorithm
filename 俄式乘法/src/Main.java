import java.util.Scanner;

public class Main {

    private static int mul(int x, int y){
        System.out.println(x + " " + y);
        if (x == 0){
            return 0;
        }
        if (x == 1){
            return y;
        }
        if (x%2 != 0){
            return mul((x-1)/2, y*2) + y;
        }else {
            return mul(x/2, y*2);
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        int y = sc.nextInt();
        System.out.println(mul(x, y));
    }
}