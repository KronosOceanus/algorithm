public class Main {

    /**
     * 通用散列函数
     * @param x：散列目标
     * @param A：1 < A < P-1
     * @param B：0 < B < P-1
     * @param P：P > M 且为质数
     * @param M：M 为 Tablesize
     */
    private static int universalHash(int x, int A, int B, int P, int M){
        return (int)((((long)A * x) + B) % P) % M;
    }

    /**
     * 卡特-韦格曼绝招
     * 即在上个方法中，令 P 为梅森素数（例如 2^5 - 1, 2^61 - 1, 2^31 - 1 等）
     */
    private static final int DIGS = 31;
    private static final int mersennep = (1 << DIGS) - 1; //即 P
    private static int caterWegmanTrick(int x, int A, int B, int M){
        long hashVal = (long)A * x + B;

        hashVal = ((hashVal >> DIGS) + (hashVal & mersennep));
        if (hashVal >= mersennep){
            hashVal -= mersennep;
        }

        return (int)hashVal % M;
    }

    public static void main(String[] args) {
        for (int i=0;i<10;i++){
            System.out.print(universalHash(i, 2, 3, 11, 7) + "\t");
        }
        System.out.println();
        for (int i=0;i<10;i++){
            System.out.print(caterWegmanTrick(i, 2, 3, 7) + "\t");
        }
    }
}
