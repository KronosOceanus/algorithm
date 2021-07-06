import java.util.Scanner;

/**
 * @author zjg
 * <p> 2019/3/11 11:23 </p>
 */

/**
 * 1：sin(1)+1
 * 2：(sin(1)+2) sin(1-sin(2)) +1
 * 3：((sin(1)+3)sin(1-sin(2))+2) sin(1-sin(2+sin(3))) +1
 */
public class Main2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.println(printfSN(n, n));
    }

    private static String printfSN(int n, int index) {
        if (index == 1) {
            return printfAn(1, 1, "")+"+"+n;
        }
        //                                       //                               //
        return "("+printfSN(n,index-1)+")"+printfAn(index,1,"")+"+"+(n - index + 1);
    }

    private static String printfAn (int n, int index, String str) {
        if (n == index) {
            return "sin("+index+")";
        }
        String operation = "-";
        if (index % 2 == 0) {
            operation = "+";
        }
        return str+"sin("+index+operation+printfAn(n, index + 1, str)+")";
    }

}