public class Main {

    //必须先转化为字符串
    public static void main(String[] args) {
        for (int i=1000;i<10000;i++){
            String a = String.valueOf(i);
            StringBuffer sb = new StringBuffer(a);
            String b = String.valueOf(sb.reverse());

            if (a.equals(b)){
                System.out.println(a);
            }
        }
    }
}
