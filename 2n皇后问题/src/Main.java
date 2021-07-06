import java.util.Scanner;

public class Main {

    private static int[][] x;       //二维数组用于创建棋盘
    private static int sum = 0;     //用于记录放法数目
    private static int n;       //棋盘的大小

    public static void main(String[] args)//创建棋盘
    {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        x = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++)
                x[i][j] = sc.nextInt();
        }
        DFS(0);
        System.out.println(sum);
    }


    // a 表示已经摆放的皇后数量
    private static void DFS(int a)//回溯
    {
        // 2n 个皇后摆放完成，sum ++
        if (a == 2 * n) {
            sum ++;
            return;
        }

        // bj 表示皇后种类（先摆放 n 个白皇后，再……）
        int bj;
        if (a < n)
            bj = 2;
        else
            bj = 3;

        //尝试在 a % n 行 i 列摆放皇后
        for (int i = 0; i < n; i++) {
            if (pd(a, i)) {
                x[a % n][i] = bj;   //摆放皇后到位置上
                DFS(a + 1);     //下一个位置是否能摆放皇后
                x[a % n][i] = 1;   //摆放完之后棋盘该位置回到开始状态
            }
        }
    }

    //判断 a % n 行 i 列是否能摆放皇后
    private static boolean pd(int a, int i)
    {
        //确定皇后种类
        int bj;
        if (a < n)
            bj = 2;
        else
            bj = 3;

        if (x[a % n][i] != 1)   //数组该处不能摆放皇后
            return false;
        for (int j = 0; j < a % n; j++)     //第 i 列已经摆放过皇后
            if (x[j][i] == bj)
                return false;
        int s = i;                      //行--，列--
        for (int w = a % n - 1; w >= 0; w--, s--)   //对角线上已经摆放过皇后（从右下角到左上角遍历）
            if (s - 1 >= 0 && x[w][s - 1] == bj)
                return false;
        s = i;
        for (int w = a % n - 1; w >= 0; w--, s++)   //另一对角线
            if (s + 1 < n && x[w][s + 1] == bj)
                return false;

        return true;
    }
}
