import java.util.Arrays;

/**
 * 先求一下从第一位开始的到第i位的累加，最前面加上0（一个都不选的情况）
 * 4，-1，5，-2，-1，2，6，-2 => 0 4 3 8 6 5 7 13 11
 *
 * 对这个累加的数列排个序，（value +）0 3 4 5 6 7 8 11 13，并记录对应的下标
 * （index）0 2 1 5 4 6 3 8 7，然后只要判断邻近的两个数是否可以组成序列
 * （可以组成序列表示累加相减之后的子序列 > 0），比如4和3就不可以，因为
 * 4 > 3而4对应下标为1,3对应为2。4和5就可以，4对应下标1,5对应下标5，这样
 * 的话，从第1个数到第4个数，就组成了一个和为1的序列。
 *
 * 解释一下为什么只需检查相邻2个数就可以，设ABC是排序后的结果，
 * 如果A同B不能组成序列，而A同C可以组成序列，那么B同C也可以组成序列，并且BC会是
 * 一个更优的解。
 *
 * 排序的时候处理一下，数字相等的话可以合并成1个元素，只记录索引的最大最小值就可以。
 */
public class Main {

    /**
     * 主要思想为使用排序后的b[i + 1].index的序列和
     * 减去b[i].index的序列和得到正子序列和
     */
    private static int minSubsquenceSum(int[] a){
        int thisSum = 0;
        int minSum = 0;
        Item[] b = new Item[a.length];

        //前index + 1位的累加（数组）
        for (int i=0;i<a.length;i++){
            b[i] = new Item();
            b[i].index = i;
            thisSum += a[i];
            b[i].value = thisSum;
        }
        //序列和数组排序
        Arrays.sort(b,new Item());
        //判断是否存在 < 0的累加，不能作为最小正子序列和（初始化）
        minSum = b[0].value > 0 ? b[0].value : 2 << 29;

        for (int i=0;i<a.length - 1;i++){
            /**
             * b[i + 1]包含b[i]
             * b[i + 1]与b[i]相减的得到正子序列和（> 0）
             * < 先前的最小正子序列和
             */
            if (b[i + 1].index > b[i].index &&
                    b[i + 1].value - b[i].value > 0 &&
                            b[i + 1].value - b[i].value < minSum){
                minSum = b[i + 1].value - b[i].value;
            }
        }

        return minSum;
    }

    public static void main(String[] args) {
        int[] a = {4,-1,6,-2,-1,3,6,-2};
        System.out.println(minSubsquenceSum(a));
    }
}
