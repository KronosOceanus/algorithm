import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        for (int i=0;i<n;i++){
            a[i] = sc.nextInt();
        }
        int x = sc.nextInt();

        int i=0;
        for (;i<n;i++){
            if (x == a[i]){
                System.out.println(i + 1);
                break;
            }
        }
        if (i == n){
            System.out.println(-1);
        }
    }
}
