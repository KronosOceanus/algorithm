import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        // A, B 代表不同的矩形
        double xA1 = sc.nextDouble();
        double yA1 = sc.nextDouble();
        double xA2 = sc.nextDouble();
        double yA2 = sc.nextDouble();
        double xB1 = sc.nextDouble();
        double yB1 = sc.nextDouble();
        double xB2 = sc.nextDouble();
        double yB2 = sc.nextDouble();
        sc.close();

        double x1, y1, x2, y2;
        //先同比矩形比较
        x1 = Math.max(Math.min(xA1, xA2), Math.min(xB1, xB2));
        y1 = Math.max(Math.min(yA1, yA2), Math.min(yB1, yB2));
        x2 = Math.min(Math.max(xA1, xA2), Math.max(xB1, xB2));
        y2 = Math.min(Math.max(yA1, yA2), Math.max(yB1, yB2));

        if (x1 > x2 || y1 > y2){
            System.out.println("0.00");
        }else {
            double x = (x2-x1) * (y2-y1);
            System.out.printf("%.2f", x);
        }
    }
}