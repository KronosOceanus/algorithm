import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.close();
        StringBuffer[] s = new StringBuffer[n];
        s[0] = new StringBuffer("A");
        for (int i=1;i<n;i++){
            char ch = (char)('A' + i);
            String k = s[i - 1].reverse().toString();
            s[i] = s[i - 1].append(ch).append(k);
        }
        System.out.println(s[n-1]);
    }
}
