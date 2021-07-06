import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        sc.close();

        int len = Math.max(m, n);

        char[] c = new char[len];
        for (int i=0;i<len;i++){
            c[i] = (char)('A' + i);
        }


        for (int k=0;k<m;k++){
            int p=0;
            for (int i=k;i>0;i--){
                System.out.print(c[i]);
                p++;
                if (p == n){
                    break;
                }
            }
            for (int i=0;i<n-k;i++){
                System.out.print(c[i]);
            }
            System.out.println();
        }
    }
}
