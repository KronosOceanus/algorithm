import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); //阶数
        int x = sc.nextInt(); //幂数
        int[][] a = new int[n][n];
        int[][] b = new int[n][n];  //与原矩阵一样，后来用来存储多次幂中间结果
        for (int i=0;i<n;i++){
            for (int j=0;j<n;j++){
                b[i][j] = a[i][j] = sc.nextInt();
            }
        }
        sc.close();

        //根据幂数分类
        if (x == 0){
            for (int i=0;i<n;i++){
                for (int j=0;j<n;j++){
                    if (i == j){
                        System.out.print(1 + " ");
                    }else {
                        System.out.print(0 + " ");
                    }
                }
                System.out.println();
            }
        }else if (x == 1){
            for (int i=0;i<n;i++){
                for (int j=0;j<n;j++){
                    System.out.print(a[i][j] + " ");
                }
                System.out.println();
            }
        }else {
            for (int k=1;k<x;k++){
                int[][] tmp = new int[n][n];    //暂时存放本次相乘结果，最后放入 b 中
                for (int i=0;i<n;i++){
                    for (int j=0;j<n;j++){
                        int add = 0;
                        for (int p=0;p<n;p++){
                            add += a[i][p] * b[p][j];
                        }
                        tmp[i][j] = add;
                    }
                }
                b = tmp;
            }
            for (int i=0;i<n;i++){
                for (int j=0;j<n;j++){
                    System.out.print(b[i][j] + " ");
                }
                System.out.println();
            }
        }
    }
}
