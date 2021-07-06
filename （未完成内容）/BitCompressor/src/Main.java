import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * DP！！！！！！！
 */
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int L = sc.nextInt();   //压缩前总长
        int N = sc.nextInt();   //压缩前有几个 1
        StringBuffer s = new StringBuffer(sc.next());
        sc.close();

        int ZN = L - N; // 压缩前 0 的个数
        //找出字符串反转后所有 10000 之中 1 的下标（即分割点），并将后面几个 0 放入value
        Map<Integer, Integer> map = findIndexOfTenByZero(s.toString().toCharArray());
        System.out.println(map);

        //未完成！！！！！！！！
        int sum = 0;    //总位数
        int z = 0;  //当前分割策略取 0 的位数
        int start = 0, end = 0;//每次分割起始，结束位置
        //用不同策略分割（递归），再转化成 10 进制，加上压缩前 0 的个数，若等于压缩前长度，则可行
        for (Integer key : map.keySet()){
            end = key;
            for (int i=0;i<map.get(key);i++){
                String s1 = s.substring(start, end - i);
                System.out.println(s1);
            }
            System.out.println();
            start = key;
        }
        System.out.println(s.substring(end));
    }


    private static Map<Integer, Integer> findIndexOfTenByZero(char[] rev){
        Map<Integer, Integer> result = new TreeMap<>(Integer::compareTo);
        int k = -1;  //记录 1 的位置
        for (int i=rev.length-1;i>0;i--){
            if (rev[i] == '1' && rev[i-1] == '0'){
                result.put(i, 0);
                k = i;
            }
            if (rev[i] == '0' && k != -1){
                result.put(k, result.get(k)+1);
            }
        }
        return result;
    }
}
