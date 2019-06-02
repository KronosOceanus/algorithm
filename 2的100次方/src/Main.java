import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        //次方数
        final int COUNT = 5;
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(1);

        for (int i=0;i<COUNT;i++) {
            // 进位标志位（十进制）
            int flag = 0;
            for (int j=list.size()-1;j>=0;j--) {
                int p = list.get(j) * 2 + flag;
                flag = p / 10;
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