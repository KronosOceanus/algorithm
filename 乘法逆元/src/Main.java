/**
 * 乘法逆元，是指群 G 中任意一个元素 a
 * 都在 G 中有唯一的逆元a'，具有性质 a×a'=a'×a=e，其中 e 为该群的单位元。
 * 当 a 与 b 互素时，a 关于模 b 的乘法逆元有解
 * 扩展欧几里得算法计算逆元
 */
public class Main {

    private static int extEuclid(int a, int b){
        int or = a;
        int r = b;
        int os = 1;
        int s = 0;

        if (b == 0){
            return 1;
        }

        //辗转相除
        while(r != 0){
            int q = or / r;
            int tmp;
            tmp = or;
            or = r;
            r = tmp - q * r;

            tmp = os;
            os = s;
            s = tmp - q * s;
        }
        return ((os % b) + b) % b;
    }

    public static void main(String[] args) {
        System.out.println(extEuclid(35, 3));
    }
}
