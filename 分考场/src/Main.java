import java.util.Scanner;

/**
 * 分考场
 * 递归，回溯问题
 */
public class Main {
    private static int min = 100;       //记录至少需要的考场数
    private static int n,m;     //学生编号 1~n
    private static int[][] whetherF;    //判断两个学生（i,j）是否认识，认识则为 1，否则为 0
    private static int[][] examRoom;    // i 号考场中的学生的第 j 个学生
    private static int[] stuNum;    // i 号个考场学生人数

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        whetherF = new int[110][110];
        examRoom = new int[110][110];   //examRoom[1][1] = 2，examRoom[1][2] = 0; 1 号考场的第 1 个人编号为 2，无第 2 个人（编号为 0）
        stuNum = new int[110];         //各考场学生人数
        while(m-- > 0)      //标记认识的人
        {
            int a = sc.nextInt();
            int b = sc.nextInt();
            whetherF[a][b] = whetherF[b][a] = 1;        // a 和 b 认识
        }
        solve(1,0);     //递归
        System.out.println(min);
        sc.close();
    }

    /**
     * 尝试放置 id 学生到 num 考场
     * @param id 学生编号
     * @param num 考场编号
     */
    private static void solve(int id, int num){
        if(num >= min) return;      //需要比原来方案更多的考场，不行，回溯
        if(id > n) {        //安排完所有学生，得到需要得考场数
            min = Math.min(min, num);
            return;
        }

        for(int i=1; i<=num; i++)       //首先看之前的考场是否能放进去
        {
            int stuInRm = stuNum[i];        //考场人数
            int UFnum = 0;      //该考生在当前考场不认识的人数
            for(int j=1; j<=stuInRm; j++)
            {
                if(whetherF[id][examRoom[i][j]] == 0) {     //得到与此人不认识的人数
                    UFnum++;
                }
            }
            if(UFnum == stuInRm)        //当前学生与该考场中的学生都不认识
            {
                examRoom[i][++stuNum[i]] = id;        //将该学生放到这个考场中
                solve(id+1,num);        //尝试在该考场放置下一个学生
                stuNum[i]--;
            }
        }

        //新增一个考场
        examRoom[num+1][++stuNum[num+1]]=id;        //把该考生放到下一个考场
        solve(id+1,num+1);      //放置下一名考生到下一个考场
        --stuNum[num+1];        //回溯，下一个考场学生数 -1
    }

}