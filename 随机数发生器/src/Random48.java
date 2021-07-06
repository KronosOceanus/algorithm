/**
 * 未完成！！！！
 */
public class Random48 {

    private static final long A = 25_214_903_917L;
    private static final long B = 48;   // 48 位
    private static final long C = 11;   //奇数
    private static final long M = (1L << B);   // M = 2^B
    private static final long MASK = M - 1;

    private long state;
    public Random48(){
        state = System.nanoTime() & MASK;
    }
    public int randomInt(){
        return next(32);
    }
    public double random0_1(){
        return ((long)(next(26)) << 27) + next(27) / (double)(1L << 53);
    }

    private int next(int bits){
        if (bits <= 0 || bits > 32){
            throw new IllegalStateException();
        }

        state = (A * state + C) & MASK;
        return (int)(state >>> (B - bits));
    }

}
