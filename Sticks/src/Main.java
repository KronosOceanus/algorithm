import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final int MAX_SIZE = 64;

    private static int l;  //结果
    private static int g;  //拼接后的木棒数
    private static int n;   //切断后的木棒数（输入）
    private static Stick[] sticks = new Stick[MAX_SIZE];

    //定义木棍类
    private static class Stick{
        int len;
        boolean isUsed;

        public Stick(int len, boolean isUsed){
            this.len = len;
            this.isUsed = isUsed;
        }
    }

    //数组根据成员变量由小到大排队
    private static void sort(){
        Stick s;
        for (int i=0;i<n-1;i++){
            for (int j=0;j<n-i-1;j++){
                if (sticks[j].len > sticks[j+1].len){
                    s = sticks[j];
                    sticks[j] = sticks[j+1];
                    sticks[j+1] = s;
                }
            }
        }
    }

    /**
     * @param nowLen 目前拼接好的长度
     * @param nowGet 目前拼接好的
     * @param index  下次拼接木棒的起始下标
     * @return
     */
    private static boolean dfs(int nowLen, int nowGet, int index){
        if (index >= n) return false;   //数组越界
        if (nowGet == g) return true;   //符合条件（木棒数量拼接够了）

        //遍历查找
        for (int i=index;i<n;i++){
            if (! sticks[i].isUsed){
                //找到一根木棒刚好能拼接成结果长度
                if (nowLen + sticks[i].len == l){
                    sticks[i].isUsed = true;

                    //拼下一根，起始位置从根数开始
                    if (dfs(0, nowGet + 1, nowGet)){
                        return true;
                    }

                    //本次（拼多根）没拼好，解除使用
                    sticks[i].isUsed = false;
                    return false;
                }

                if (nowLen + sticks[i].len < l){
                    sticks[i].isUsed = true;
                    //继续拼这一根，起始位置从 i+1 开始
                    if (dfs(nowLen + sticks[i].len, nowGet, i+1)){
                        return true;
                    }

                    //这一根没拼好，解除使用
                    sticks[i].isUsed = false;

                    //第一次一根都拼不好
                    if (nowLen == 0) return false;
                    //下一根跟这一根一样长，这一根没拼好，下一根直接跳过
                    for ( ; sticks[i].len == sticks[i+1].len && i+1 < n ;  i++);
                }
            }
        }
        return false;
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Integer> result = new LinkedList<>();

        while((n = sc.nextInt()) != 0){
            //初始化所有信息
            int sum = 0;    //所有木棒总长
            for (int i=0;i<n;i++){
                sticks[i] = new Stick(sc.nextInt(), false); //标记未使用过
                sum += sticks[i].len;
            }

            //按照长度从小到大排序
            sort();

            for (l = sticks[0].len; l <= sum; l++){
                if (sum % l != 0) continue; //（剪枝 1）

                g = sum / l;    //拼接后的木棒数
                if (dfs(0, 0, 0)) break;    //剪枝，满足要求退出循环
            }
            result.add(l);
        }
        sc.close();

        //输出最后结果
        for (Integer i : result){
            System.out.println(i);
        }
    }
}
