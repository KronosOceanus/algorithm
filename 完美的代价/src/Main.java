import java.util.Scanner;

public class Main {

    static int cnt = 0;     //统计交换次数
    static boolean hasMid = false;  //是否有中间数

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        char[] s = sc.next().toCharArray();

        if (palindrome(s, 0, n-1)){
            System.out.println(cnt);
        }else {
            System.out.println("Impossible");
        }
    }

    //递归替换
    private static boolean palindrome(char[] s, int a, int b){
        if (b <= a){
            return true;    //递归结束标志，全部都交换完成形成回文数了
        }

        for (int i=b;i>a;i--){     // 倒序遍历该字符串
            if (s[a] == s[i]){
                exchange(s, i, b);   //将 i，b 处元素互换位置
                cnt += getExchangeTimes(i, b);
                return palindrome(s, a+1, b-1);    //递归查找前半部分下一个字符
            }
        }
        //任意一次没有匹配到字符，即为匹配到了中间字符（a 处）
        if (! hasMid){
            hasMid = true;
            //将中间字符串交换到字符串中间
            cnt += getExchangeTimes(a, s.length/2);
            return palindrome(s, a+1, b);
        }
        return false;
    }

    private static int getExchangeTimes(int a, int b) {
        return b - a;
    }

    private static void exchange(char[] s, int a, int b){
        char tmp = s[a];
        if (b - a >= 0) System.arraycopy(s, a + 1, s, a, b - a);
        s[b] = tmp;
    }
}
