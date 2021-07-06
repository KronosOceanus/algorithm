import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.close();

        int[] a = new int[9999];
        //初始化
        a[0] = 1;
        // count+1 表示结果的位数，count2+1 存储上一次 * i 之后的位数
        int count = 0, count2 = 0;
        // i 表示要乘的数
        for (int i=2;i<=n;i++){
            int jw = 0;
            for (int j=0;j<a.length;j++){	//乘数组中不为 0 时的所有数字
                int temp = a[j]*i + jw; //加上余数
                //除去为 0 的时候，第一次相乘可以为 0，* i 之后 j 一定 < count2
                if (temp == 0 && i != 2 && count2 <= j) break;
                a[j] = temp % 10; //本位数存入 a[j]
                jw = temp / 10; //进位
                if (i != 2) count = j; //不是第一次相乘，记录结果的位数
            }
            count2 = count;
        }

        //倒序存储
        for (int i=count;i>=0;i--){
            System.out.print(a[i]);
        }
        System.out.println();
    }
}
