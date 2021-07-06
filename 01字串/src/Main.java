public class Main {

    public static void main(String[] args) {
        for (int i=0;i<32;i++){
            String s = Integer.toBinaryString(i);
            if (s.length() < 5){        //补齐前面的 0
                for (int j=0;j<5-s.length();j++){
                    System.out.print(0);
                }
            }
            System.out.println(s);
        }
    }
}
