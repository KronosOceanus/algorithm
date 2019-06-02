//复杂度都是O(logN)
public class Main {

    public static void main(String[] args) {
        System.out.println(pow1(2,2));
        System.out.println(pow2(2,2));
    }

    //计算x的n次方（递归分治）
    private static long pow1(long x,int n){
        if (n == 0){
            return 1;
        }else if ((n&1) == 0){
            return pow1(x * x,n >> 1);
        }else {
            return pow1(x * x,n >> 1) * x;
        }
    }

    /**
     * 例如；11（指数）的二进制是1011
     * 11 = （2^3×1 + 2^2×0 + 2^1×1 + 2^0×1） = （8 + 0 + 2 + 1）
     * 所以x^11 = x^8 * x^2 * x^1
     * base为基数，以x^1 , x^2 , x^4的方式递增
     */
    private static long pow2(long x,int n){
        long result = 1;
        long base = x;
        while (n != 0){
            //n的二进制某一位为1，乘基数
            if ((n & 1) != 0){
                result *= base;
            }
            //基数变为原来的平方
            base *= base;
            //指数变为原来的一半
            n >>= 1;
        }
        return result;
    }
}