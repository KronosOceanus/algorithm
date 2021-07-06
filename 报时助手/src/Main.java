import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        int y = sc.nextInt();
        sc.close();
        StringBuffer result = new StringBuffer();

        if (x > 20){
            int xA = x / 10 * 10;
            int xB = x % 10;
            pend(result, xA);
            pend(result, xB);
        }else {
            pend(result, x);
        }
        if (y > 20){
            int yA = y / 10 * 10;
            int yB = y % 10;
            pend(result, yA);
            if (yB != 0){
                pend(result, yB);
            }
        }else if (y == 0){
            result.append("o'clock");
        }else {
            pend(result, y);
        }

        System.out.println(result);
    }

    private static void pend(StringBuffer result, int i){
        switch (i){
            case 0:result.append("zero ");break;
            case 1:result.append("one ");break;
            case 2:result.append("two ");break;
            case 3:result.append("three ");break;
            case 4:result.append("four ");break;
            case 5:result.append("five ");break;
            case 6:result.append("six ");break;
            case 7:result.append("seven ");break;
            case 8:result.append("eight ");break;
            case 9:result.append("nine ");break;
            case 10:result.append("ten ");break;
            case 11:result.append("eleven ");break;
            case 12:result.append("twelve ");break;
            case 13:result.append("thirteen ");break;
            case 14:result.append("fourteen ");break;
            case 15:result.append("fifteen ");break;
            case 16:result.append("sixteen ");break;
            case 17:result.append("seventeen ");break;
            case 18:result.append("eighteen ");break;
            case 19:result.append("nineteen ");break;
            case 20:result.append("twenty ");break;
            case 30:result.append("thirty ");break;
            case 40:result.append("forty ");break;
            case 50:result.append("fifty ");break;
        }
    }
}
