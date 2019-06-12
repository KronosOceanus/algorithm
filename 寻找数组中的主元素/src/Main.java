/**
 * 首先假设主元素是X，则遍历数组时出现与X相等的元素时，X出现的数目+1，
 * 不相等时，如果计数值变为1，则这个X可能不是主元素，需要将假定值更改
 * 为新出现的元素，计数值不为1时，则X出现的数目-1.遍历完后的X就是主元素。
 * 最后遍历数组判断出现的次数是否 > N/2（N为奇数则 >= N/2）
 * 复杂度为O(N)
 */
public class Main {

    private static int findMain(int[] a){
        //假设主元素为a[0]
        int result = a[0];
        //判断是否是候选元的标记（后续操作中 == 1则不是）（与N也有关）
        int mark = 1;
        for (int i=1;i<a.length;i++){
            //两个相邻的数相同，则可能是主元素，不用执行判断
            if (result == a[i]){
                mark ++;
            }
            //不可能是主元素，重新假设主元素为a[i]，重置计数
            else if (mark == 1){
                result = a[i];
                mark = 1;
            }
            //两个相邻的数不同
            else {
                mark --;
            }
        }
        //出现次数
        int count = 0;
        for (int s : a){
            if (s == result){
                count ++;
            }
        }
        //出现次数是否 > N/2（N为奇数则 >= N/2）
        if (((count & 1) == 0 && count > a.length / 2)
            || ((count & 1) != 0 && count >= a.length / 2)){
            return result;
        }else {
            return -65535;
        }
    }
    public static void main(String[] args) {
        int[] a = {3,3,4,2,4,4,2,4,3};
        System.out.println(findMain(a));
    }
}
