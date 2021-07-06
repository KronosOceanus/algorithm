public class Main {

    private static boolean isPrime(long n){
        //特殊情况
        if (n == 2 || n == 3){
            return true;
        }
        //不在6的倍数两侧
        if (n % 6 != 1 && n % 6 != 5){
            return false;
        }
        //在两侧也可能不是
        for (int i=5;i<=Math.sqrt(n);i += 6){
            if (n % i == 0 || n % (i + 2) == 0){
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        System.out.println(isPrime(25));
        System.out.println(isPrime(993));
    }
}
