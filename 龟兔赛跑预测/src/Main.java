import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int v1 = sc.nextInt();
        int v2 = sc.nextInt();
        int t = sc.nextInt();
        int s = sc.nextInt();
        int l = sc.nextInt();
        sc.close();

        int time = 0;
        int l1 = 0;
        int l2 = 0;
        int k = 0;
        boolean isStop = false;
        while(l1 != l && l2 != l){
            time ++;
            l1 += isStop ? 0 : v1; //兔子路程
            l2 += v2;  //乌龟路程

            //兔子是否停止
            if(isStop){
                k ++;
                if (k == s){
                    isStop = false;
                    k = 0;
                }
            }else if(l1 - l2 >= t){
                isStop = true;
            }
        }
        char result = l1 == l2 ? 'D' : (l1 > l2 ? 'R' : 'T');
        System.out.println(result);
        System.out.println(time);
    }
}
