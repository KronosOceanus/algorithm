import java.util.ArrayList;

/**
 * 使用List实现（还需要写一个字符串实现）
 */
public class Main {

    public static void main(String[] args) {

        //次方数
        final int COUNT = 100;
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);

        for (int i=0;i<COUNT;i++) {
            // 进位标志位（十进制）
            int flag = 0;
            //倒着读取List
            for (int j=list.size()-1;j>=0;j--) {

                //每一位 * 2 加上 flag（相当于原来的数 * 2 + flag * 10^j）
                int p = list.get(j) * 2 + flag;
                //flag 表示下一位该向上一位进几位
                flag = p / 10;
                //p 表示该位上的数字
                //位运算取模 x % 31 = x & (2^5 - 1)，运算数必须是 2^n - 1
                p %= 10;

                list.set(j, p);
                System.out.println("i:"+i+"\tj:" + j + "\tflag:" + flag + "\tp:" + p + "\tlist:" + list.toString());
            }

            // 最高位添加进位
            if (flag == 1) {
                list.add(0, 1);
                System.out.println("\t"+list.toString());
            }
        }

        //结果
        StringBuffer sb = new StringBuffer("");
        for (Integer s : list){
            sb.append(s);
        }
        System.out.println(sb.toString());
    }
}