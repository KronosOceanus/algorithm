public class Main{

    //设置字符数组
    //可以添加任意不重复字符，提高能转换的进制的上限
    private static char[] chs = new char[36];
    static {        //初始化为字符 0-9，大写字母 A-Z
        for(int i = 0; i < 10 ; i++) {
            chs[i] = (char)('0' + i);
        }
        for(int i = 10; i < chs.length; i++) {
            chs[i] = (char)('A' + (i - 10));
        }
    }

    private static String transRadix(String num, int fromRadix, int toRadix) {
        int number = Integer.valueOf(num, fromRadix);       //将字符串转换为十进制数字
        StringBuilder sb = new StringBuilder();
        while (number != 0) {       //辗转相除法，将十进制转换为其他进制
            sb.append(chs[number%toRadix]);
            number = number / toRadix;
        }
        return sb.reverse().toString();     //反向打印
    }

    //运用 API 的简便方法
    private static String transRadix2(String num, int fromRadix, int toRadix) {
        int number = Integer.valueOf(num, fromRadix);       //将字符串转换为十进制数字
        return Long.toString(number, toRadix);
    }

    //测试
    public static void main(String[] args) {
        System.out.println(transRadix2("12", 3, 10));
    }
}