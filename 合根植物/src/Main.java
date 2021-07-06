import java.util.Scanner;
import java.util.HashSet;
import java.util.Set;

/**
 * 合根植物
 */
public class Main {

    private static int [] head;     //下标表示植物，值表示与该植物连接的植物

    public static void main(String[] args) {
        //并查集
        Scanner s=new Scanner(System.in);
        int m = s.nextInt();
        int n = s.nextInt();
        int k = s.nextInt();

        head = new int [m*n];
        for(int i=0; i<m*n; i++) {
            head[i] = -1;
        }

        int a,b;
        for(int i=0; i<k; i++) {
            a = s.nextInt()-1;      // -1 换成对应二维数组坐标
            b = s.nextInt()-1;
            union(a,b);     //合并
        }

        Set<Integer> set= new HashSet<>();      //判断有几个不相交集
        for(int i=0; i<m*n; i++) {
            set.add(f(i));
        }
        System.out.println(set.size());
    }

    private static void union(int a, int b) {
        if(f(a) == f(b)) return;        //在同一个集合内

        head[f(a)] = f(b);      //合并（a 所属的集合的根，指向 b 所属集合的根）
    }

    //求集合的根
    private static int f(int i) {
        if(head[i] == -1) return i;      //根

        return head[i] = f(head[i]);        //路径压缩
    }
}