import java.util.Random;

/**
 * 费马小定理：如果 P 是素数，0 < A < P，则 A^P-1 === 1 % P
 */
public class Main {

    private static final int TRIALS = 5;

    private static boolean isPrime(long n){
        Random r = new Random();
        for (int counter = 0; counter < TRIALS; counter ++){
            if (witness(r.nextInt((int)n-4) + 2, n-1, n) != 1){
                return false;
            }
        }
        return true;
    }

    /**
     * 递归测试
     * @param a [2, n-2]的随机数
     * @param i 递归计数
     * @param n 要判断的数
     * @return =1 是素数
     */
    private static long witness(long a, long i, long n){
        if (i == 0){
            return 1;
        }

        long x = witness(a, i/2, n);
        if (x == 0){
            return 0;
        }

        long y = (x * x) % n;
        if (y == 1 && x != 1 && x != n-1){
            return 0;
        }

        if (i % 2 != 0){
            y = (a * y) % n;
        }

        return y;
    }
    public static void main(String[] args) {
        System.out.println(isPrime(1545305));
    }
}
