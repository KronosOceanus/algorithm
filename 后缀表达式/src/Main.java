public class Main {

    public static void main(String[] args) {
        String s = "7.22+3.5*1.1+(5.3*6.2+3.2)*2.5";
        InSuffix is = new InSuffix(s);
        System.out.println(is.getSuffix());
        System.out.println(is.getResult());
    }
}
