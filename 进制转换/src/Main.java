import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Main m = new Main();
        m.dToH();
    }

    //三种进制转换
    public void hToO(){
        Scanner sc = new Scanner(System.in);
        //个数
        int n = sc.nextInt();
        String[] ss = new String[n];
        for (int i=0; i<n; i++){
            ss[i] = sc.next();
        }
        sc.close();
        for (int i=0;i<n;i++){
            //字符数组，方便，不用 charAt
            tranform16to8(ss[i].toCharArray(), ss[i].length());
        }
    }
    public void hToD(){
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        sc.close();
        System.out.println(Long.parseLong(s, 16));
    }
    public void dToH(){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.close();
        System.out.println(Integer.toHexString(n).toUpperCase());
    }

    /*
     * 3位16进制等价于4位8进制
     */
    //栈顶在数组后面
    int[] stack = new int[40000];
    private void tranform16to8(char[] str, int length) {
        char[] buff = new char[4];
        //栈顶初始化
        int top = -1;
        //倒序 3 位遍历 16 进制字符数组
        for (int i = length - 1; i >= 0; i -= 3) {
            // 3 位相加
            int sum = 0;
            //倒序遍历 3 位中的每一位（注意 i-j > 0）
            for (int j = 0; j < 3 && i - j >= 0; j++) {     // i-j>=0防止不够三个的情况
                int tmp = str[i - j] >= '0' && str[i - j] <= '9' ? str[i - j] - '0'
                        : str[i - j] - 'A' + 10;        //区分是数字，还是字符，数字就 - '0'，字符 - 'A' + 10
                sum += (tmp << (4*j));      //tmp << 4 == tmp*16,而tmp<<4*j分别是16进制的个位，十位。。。。。
                // ∴sum+=(tmp<<4*j)就是sum = tmp*16^0+tmp*16^1
            }
            //将 3 位十进制化结果放入栈顶
            stack[++top]=sum;       //sum的结果是16进制转化10进制的结果，每3个16进制变成10进制，再变8进制
        }
        while(stack[top]==0){       //排除前导为0的判断
            top--;
        }

        //遍历栈
        for(int i=top;i>=0;i--){
            String s = Integer.toOctalString(stack[i]);        //从10进制转化成8进制
            if(i!=top && s.length()<4){
                //不是最左边的一个，就不用去掉前导0,而默认是去掉0的，所以要进行补全
                for(int y=0;y<4-s.length();y++)
                    System.out.print("0");
            }
            System.out.print(s);
        }
        System.out.println();
    }
}