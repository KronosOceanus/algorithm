public class Main {

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
    public static void main(String[] args) {
        System.out.println(gcd(22, 2));
    }
}
