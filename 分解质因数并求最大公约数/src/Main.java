import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    //优化 gcd
    private static long gcd(long m,long n){
        if (m < n){
            return gcd(n, m);
        }

        while (n != 0){
            long rem = m % n;
            m = n;
            n = rem;
            //在循环中利用gcd性质判断，减少计算量
            if (n != 0){
                if ((m & 1) == 0 && (n & 1) == 0){
                    m = m >> 1;
                    n = n >> 1;
                }else if ((m & 1) == 0 && (n & 1) == 1){
                    m = m >> 1;
                }else if ((m & 1) == 1 && (n & 1) == 0){
                    n = n >> 1;
                }else if ((m & 1) == 1 && (n & 1) == 1){
                    m = (m + n) >> 1;
                    n = (m - n) >> 1;
                }
            }
        }
        return m;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] s = bf.readLine().split(" ");

        if (s.length == 1){
            int k = Integer.parseInt(s[0]);
            int i = 2;
            System.out.print(k + "=");
            while (i <= k){    //进行循环判断
                if(i == k){
                    //循环结束条件
                    System.out.print(k);
                        break;
                }else if (k % i == 0){
                     System.out.print(i + "*");
                     k = k / i;
                }else{
                    i ++;
                };
            }
        }else {
            int m = Integer.parseInt(s[0]);
            int n = Integer.parseInt(s[1]);
            System.out.println(gcd(m, n));
        }
    }
}
