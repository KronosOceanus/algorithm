import java.util.ArrayList;

public class Main {

    /**
     * 基数排序（扩展桶排序）
     * 将数从低位开始，桶排序后，再从倒数第二位开始新的桶排序，直到最高位
     * 以定长字符串排序作为例子（还有一种不使用 ArrayList 的方法）
     */
    public static void radixSortA(String[] arr, int stringLen){
        //桶的数目
        final int BUCKETS = 256;
        ArrayList<String>[] buckets = new ArrayList[BUCKETS];

        for (int i=0;i<BUCKETS;i++){
            buckets[i] = new ArrayList<>();
        }

        for (int pos=stringLen-1; pos>=0; pos--){
            for (String s : arr){
                buckets[s.charAt(pos)].add(s);
            }

            int idx = 0;
            for (ArrayList<String> thisBucket : buckets){
                for (String s : thisBucket){
                    arr[idx ++] = s;
                }

                thisBucket.clear();
            }
        }
    }



    public static void main(String[] args) {
        String[] sArr = new String[]{
                "aaaa",
                "ffff",
                "dddd",
                "cccc"
        };
        radixSortA(sArr, 4);
        for (String s : sArr){
            System.out.print(s + "\t");
        }
    }
}
