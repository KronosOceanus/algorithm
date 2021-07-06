import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        sc.close();

        List<Integer> zhishu = getZhishu(b);  //存放质数
        for (int i=a;i<=b;i++){
            //质数直接输出
            if (zhishu.contains(i)){
                System.out.println(i + "=" + i);
            }else {
                List<Integer> yinshu = getYinshu(i, zhishu);
                System.out.print(i + "=");
                StringBuffer yinshi = new StringBuffer();
                for (Integer y : yinshu){
                    yinshi.append(y).append("*");
                }
                yinshi.deleteCharAt(yinshi.length() - 1);
                System.out.println(yinshi);
            }
        }
    }

    //得到 b 之前的所有质数集合
    private static LinkedList<Integer> getZhishu(int b){
        LinkedList<Integer> result = new LinkedList<>();
        result.add(2);
        result.add(3);  //先加入特例
        for (int i=5;i<=b;i += 6){
            if (isPrime(i)){
                result.add(i);
            }
            if (isPrime(i+2)){
                result.add(i+2);
            }
        }
        return result;
    }

    //判断素数
    private static boolean isPrime(long n){
        //特殊情况
        if (n == 2 || n == 3){
            return true;
        }
        //不在6的倍数两侧
        if (n % 6 != 1 && n % 6 != 5){
            return false;
        }
        //在两侧也可能不是（整除两侧的数就不是）
        for (int i=5;i<=Math.sqrt(n);i += 6){
            if (n % i == 0 || n % (i + 2) == 0){
                return false;
            }
        }
        return true;
    }

    //得到某个数 x 的因数集合
    private static LinkedList<Integer> getYinshu(int x, List<Integer> zhishu){
        LinkedList<Integer> result = new LinkedList<>();
        while(x != 1){
            for (Integer i : zhishu){
                if (x % i == 0){
                    result.add(i);
                    x = x / i;
                    break;
                }
            }
        }
        return result;
    }
}
