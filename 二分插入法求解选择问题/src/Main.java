import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 先读取前 K = N/2 个数到数组中，排序
 * 读取剩下的数，如果比第 K 个数小，则跳过
 * 否则二分搜索插入位置并插入
 */
public class Main {

    private static final int N = 10;
    private static final int K = N/2;
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        int[] s = new int[K];
        String line = br.readLine();
        //赋值（前 K 个）
        for (int i=0;i<K;i++){
            s[i] = Integer.parseInt(line.split(",")[i]);
        }

        //冒泡排序
        sort(s);

        System.out.println("====输入剩余的值");
        line = br.readLine();

        //二分法搜索
        for (int i=0;i<K;i++){
            int in = Integer.parseInt(line.split(",")[i]);
            if (in < s[K-1]){
                continue;
            }
            binarysort(s,in);
        }

        //输出数组
        printl(s);
    }

    //二分搜索并插入
    private static void binarysort(int[] s,int in){
        int low = 0;
        int high = K-1;
        int mid;
        while(low <= high){
            mid = (low + high) / 2;
            if (in > s[mid]){
                if (mid == 0 || s[mid - 1] > in){
                    insert(in,s,mid);
                    break;
                }
                high = mid - 1;
            } else{
                if (mid == K-1 || s[mid + 1] < in){
                    insert(in,s,mid);
                    break;
                }
                low = mid + 1;
            }
        }
    }
    //输出数组
    private static void printl(int[] s){
        for (int l : s){
            System.out.print(l + "\t");
        }
    }
    //冒泡排序
    private static void sort(int[] s){
        for (int i=0;i<K-1;i++){
            for (int j=0;j<K-1-i;j++){
                if (s[j] < s[j+1]){
                    int t = s[j];
                    s[j] = s[j+1];
                    s[j+1] = t;
                }
            }
        }
    }
    //指定下标位置插入（数组后移）
    private static void insert(int in,int[] s,int pos){
        for(int i=K-1;i>pos;i--){
            s[i] = s[i-1];
        }
        s[pos] = in;
    }
}
