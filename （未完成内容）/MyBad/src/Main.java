import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

    private static int N, G, U, C; // C 表示几组测试数据
    //门/输出映射
    private static Node[] g;
    private static int[] out;   //即 out[i]，代表第 i 个输出对应的门编号
    //输入/输出
    private static int[][] n;
    private static int[][] u;
    //正确输出
    private static int[][] rightU;

    public static class Node{
        char type;
        String next1;
        String next2;

        public Node(char type, String next1, String next2){
            this.type = type;
            this.next1 = next1;
            this.next2 = next2;
        }
    }

    //计算正确输出
    private static void compute(){
        rightU = new int[C][U];
        for (int i=0;i<C;i++){
            for (int j=0;j<U;j++){
                rightU[i][j] = getDoor(i, out[j]);
            }
        }
    }
    //计算单个门（计算第 i 组数据中第 j 个门）
    private static int getDoor(int i, int j){
        switch (g[j].type){
            case 'n': return not(getByString(i, g[j].next1));
            case 'a': return and(getByString(i, g[j].next1), getByString(i, g[j].next2));
            case 'o': return or(getByString(i, g[j].next1), getByString(i, g[j].next2));
            case 'x': return ex(getByString(i, g[j].next1), getByString(i, g[j].next2));
            default:break;
        }
        return 0;
    }
    //根据字符串返回对应东西
    private static int getByString(int i, String s){
        if (s.charAt(0) == 'i'){
            return n[i][Integer.parseInt(s.substring(1)) - 1];
        }else {
            return getDoor(i, Integer.parseInt(s.substring(1)) - 1);
        }
    }
    //四种计算
    private static int and(int x1, int x2){
        if (x1 == 0 || x2 == 0){
            return 0;
        }
        return 1;
    }
    private static int or(int x1, int x2){
        if (x1 == 1 || x2 == 1){
            return 1;
        }
        return 0;
    }
    private static int ex(int x1, int x2){
        if (x1 != x2){
            return 1;
        }
        return 0;
    }
    private static int not(int x){
        return x == 0 ? x + 1 : x - 1;
    }

    //对比数据，找到异常输出编号
    private static int getExceptionOut(){
        for (int i=0;i<C;i++){
            for (int j=0;j<U;j++){
                if (u[i][j] != rightU[i][j]){
                    return j;
                }
            }
        }
        return -1;  //没有异常
    }
    //根据异常输出编号找到异常的门
    private static int getExceptionDoor(int k){
        if (k == -1){
            return -1;
        }
        //得到当前门
        Node nowG = g[out[k]];
        if ((nowG.type != 'n' && (nowG.next1.charAt(0) != 'g' || nowG.next2.charAt(0) != 'g')) || nowG.next1.charAt(0) != 'g'){
            return k;
        }else {
            return new Random().nextInt(k);
        }
    }
    //根据门编号得到异常种类，得到结果
    private static String getException(int m){
        if (m == -1){
            return "No faults detected";
        }

        int notSameTime = 0;
        int isOne = 0;
        int isZero = 0;
        for (int i=0;i<C;i++){
            if (u[i][m] != rightU[i][m]){
                notSameTime ++;
            }
            if (u[i][m] == 0){
                isZero ++;
            }
            if (u[i][m] == 1){
                isOne ++;
            }
        }

        //异常确定
        if (C == 1){
            return "Unable to totally classify the failure";
        }
        if (notSameTime == C && isZero != C && isOne != C){
            return "Gate " + (out[m]+1) + " is failing; output inverted";
        }else if (isZero == C){
            return "Gate " + (out[m]+1) + " is failing; output stuck at 0";
        }else if (isOne == C){
            return "Gate " + (out[m]+1) + " is failing; output stuck at 1";
        }else {
            return "Unable to totally classify the failure";
        }
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<String> result = new LinkedList<>();
        int T = 0;  //记录输出的 case
        while((N = sc.nextInt()) != 0){
            G = sc.nextInt();
            U = sc.nextInt();


            //创建门
            g = new Node[G];
            for (int i=0;i<G;i++){
                char type;
                if ((type = sc.next().charAt(0)) == 'n'){
                    g[i] = new Node(type, sc.next(), null);
                }else {
                    g[i] = new Node(type, sc.next(), sc.next());
                }
            }

            //映射
            out = new int[U];
            for (int i=0;i<U;i++){
                out[i] = sc.nextInt() - 1;
            }

            //得到测试数据
            C = sc.nextInt();
            n = new int[C][N];
            u = new int[C][U];
            for (int i=0;i<C;i++){
                for (int j=0;j<N;j++){
                    n[i][j] = sc.nextInt();
                }
                for (int j=0;j<U;j++){
                    u[i][j] = sc.nextInt();
                }
            }

            //得到正确输出数据
            compute();

            T ++;

            //对比数据，找到异常输出编号（-1表示无异常）
            int k = getExceptionOut();


            //未完成！！！！！！！！！
            //根据异常输出找到异常的门
            int m = getExceptionDoor(k);
            //判断异常种类，输出结果
            result.add("Case " + T + ": " + getException(m));
        }

        for (String r : result){
            System.out.println(r);
        }
    }
}
