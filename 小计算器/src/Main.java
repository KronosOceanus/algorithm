import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 小计算器
 * 不考虑运算优先级
 */
public class Main {

    private static String op = "";      //运算符
    private static int hex = 10;        //进制
    private static long[] num = new long[2];        //两个运算数

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < n; i++) {
            String[] tmp = bufferedReader.readLine().split(" ");
            operate(tmp);
        }

    }

    //操作
    private static void operate(String[] tmp) throws IOException {

        switch (tmp[0]){
            case "NUM" :
                if(op.equalsIgnoreCase("")){        //输入第一个数字
                    num[0] = Long.valueOf(tmp[1], hex);
                } else {
                    num[1] = Long.valueOf(tmp[1], hex);
                    num[0] = caculate();        //运算结果存储
                    op = "";        //运算符初始化
                }
                break;
            case "ADD" :
                op = "ADD";
                break;
            case "SUB" :
                op = "SUB";
                break;
            case "MUL" :
                op = "MUL";
                break;
            case "DIV" :
                op = "DIV";
                break;
            case "MOD" :
                op = "MOD";
                break;
            case "CHANGE" :
                hex = Integer.parseInt(tmp[1]);
                break;
            case "EQUAL" :
                print();
                break;
            case "CLEAR" :
                num[0] = 0;
                num[1] = 0;
                op = "";
                break;
            default :
                break;
        }

    }
    //输出
    private static void print(){
        System.out.println(Long.toString(num[0], hex).toUpperCase());
    }
    //计算
    private static long caculate(){
        long ret = 0;
        switch (op) {
            case "ADD" :
                ret = num[0]+num[1];
                break;
            case "SUB" :
                ret = num[0]-num[1];;
                break;
            case "MUL" :
                ret = num[0]*num[1];
                break;
            case "DIV" :
                ret = num[0]/num[1];
                break;
            case "MOD":
                ret = num[0]%num[1];
            default :
                break;
        }
        return ret;
    }

}