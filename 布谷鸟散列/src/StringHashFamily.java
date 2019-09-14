import java.util.Random;

/**
 * 提供字符串函数组
 */
public class StringHashFamily implements HashFamily<String> {

    private final int[] MULTIPLIERS;
    private final Random r = new Random();

    // d 表示函数组中散列函数的数量
    public StringHashFamily(int d){
        MULTIPLIERS = new int[d];
        //填充随机数组（每个数字代表一个散列函数的 key）
        generateNewFunctions();
    }

    @Override
    public int hash(String x, int which) {
        final int multiplier = MULTIPLIERS[which];
        int hashVal = 0;

        for (int i=0;i<x.length();i++){
            hashVal = multiplier * hashVal + x.charAt(i);
        }

        return hashVal;
    }

    @Override
    public int gerNumberOfFunctions() {
        return MULTIPLIERS.length;
    }

    @Override
    public void generateNewFunctions() {
        for (int i=0;i<MULTIPLIERS.length;i++){
            //储存随机数到数组（相当于保存了散列函数的 key）
            MULTIPLIERS[i] = r.nextInt();
        }
    }
}
