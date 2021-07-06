import java.util.Scanner;

public class Main {

    static int x,y;
    static int[][] a;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        x = sc.nextInt();
        y = sc.nextInt();
        a = new int[x][y];
        for (int i=0;i<x;i++){
            for (int j=0;j<y;j++){
                a[i][j] = sc.nextInt();
            }
        }
        sc.close();

        int direct = 0;     //判断方向 0,1,2,3 分别代表 下，右，上，左
        int count = 0;  //记录输出了多少个数
        int i, j;
        i = j = 0;
        int n = x * y;
        while(count < n){
            switch (direct){
                case 0:while(i < x && a[i][j] != -1){
                    count ++;
                    System.out.print(a[i][j] + " ");
                    a[i ++][j] = -1;
                }
                    direct = (direct + 1) % 4;  //改变方向
                    i --; j ++;
                    break;
                case 1:while(j < y && a[i][j] != -1){
                    count ++;
                    System.out.print(a[i][j] + " ");
                    a[i][j ++] = -1;
                }
                    direct = (direct + 1) % 4;  //改变方向
                    j --; i--;
                    break;
                case 2:while(i >= 0 && a[i][j] != -1){
                    count ++;
                    System.out.print(a[i][j] + " ");
                    a[i --][j] = -1;
                }
                    direct = (direct + 1) % 4;  //改变方向
                    i ++; j--;
                    break;
                case 3:while(j >= 0 && a[i][j] != -1){
                    count ++;
                    System.out.print(a[i][j] + " ");
                    a[i][j --] = -1;
                }
                    direct = (direct + 1) % 4;  //改变方向
                    j ++; i++;
                    break;
                default:break;
            }
        }
    }
}
