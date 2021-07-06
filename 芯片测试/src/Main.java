import java.util.Scanner;

public class Main {

    static int n;
    static int[][] a;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        a = new int[n][n];
        for (int i=0;i<n;i++){
            for (int j=0;j<n;j++){
                a[i][j] = sc.nextInt();
            }
        }
        sc.close();

        boolean flag;
        for (int i=0;i<n;i++){
            flag = true;
            // j 也是从下一个芯片开始
            for (int j=i+1;j<n;j++){
                if (a[i][j] == 1){
                    flag = flag && isGood(i, j);
                }
            }
            if (flag){
                for (int j=0;j<n;j++){
                    if (a[i][j] == 1){
                        System.out.print(j + 1 + " ");
                    }
                }
                break;
            }
        }
    }

    //假设 i 是好芯片时，j 芯片是否是好芯片
    private static boolean isGood(int i, int j){
        for (int p=0;p<n;p++){
            if (a[i][p] != a[j][p]){
                return false;
            }
        }
        return true;
    }
}
