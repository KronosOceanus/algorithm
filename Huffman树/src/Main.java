import java.util.Scanner;
import java.util.TreeMap;

/**
 * 采用 TreeMap， key 为数字，value 为数字出现次数
 */
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        TreeMap<Integer, Integer> map = new TreeMap<>(Integer::compareTo);    //<数字，出现次数>，已经根据 key 排序
        int key, value;
        for (int i=0;i<n;i++){
            key = sc.nextInt();
            value = map.containsKey(key)? map.get(key) + 1 : 1;
            map.put(key, value);
        }
        sc.close();

        int result = 0;
        int a, valueA, b, valueB, c, valueC;    // c 表示费用
        while(map.size() != 1){		//最少找最小的 2 个元素
            a = map.firstKey();
            valueA = map.get(a) - 1;    //出现次数 -1
            if (valueA == 0){
                map.pollFirstEntry();
                b = map.firstKey();
                valueB = map.get(b) - 1;
                if (valueB == 0){
                    map.pollFirstEntry();
                }else {
                    map.put(b, valueB);
                }
            }else {
                b = a;
                valueB = valueA - 1;
                if (valueB == 0){
                    map.pollFirstEntry();
                }else {
                    map.put(b, valueB);
                }
            }

            c = a + b;    //本次费用
            valueC = map.containsKey(c) ? map.get(c) + 1 : 1;
            map.put(c, valueC);

            result += c;
        }
        System.out.println(result);
    }
}
