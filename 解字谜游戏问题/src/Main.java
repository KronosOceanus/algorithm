/**
 * 下列二维数组中，横/竖/斜能组成哪些单词
 */
public class Main {

    private static final int LEN = 4;
    public static void main(String[] args) {
        StringBuffer sb1 = new StringBuffer();
        StringBuffer sb2 = new StringBuffer();
        String[] words =  {"this","two","fat","that"};
        char[][] cs = new char[LEN][];
        cs[0] = new char[]{'t','h','i','s'};
        cs[1] = new char[]{'w','a','t','s'};
        cs[2] = new char[]{'o','a','h','g'};
        cs[3] = new char[]{'f','g','d','t'};

        //遍历行/列
        for (int i=0;i<LEN;i++){
            //遍历单词
            for (int j=0;j<words.length;j++){
                int wordLen = words[j].length();
                //根据单词长度多次遍历行/列
                for (int m=0;m<=LEN - wordLen;m++){
                    //组成单词
                    for (int n=m;n<m+wordLen;n++){
                        sb1.append(cs[i][n]);
                        sb2.append(cs[n][i]);
                    }
                    //输出组成的单词
                    if (sb1.toString().equals(words[j])){
                        System.out.println(sb1);
                    }
                    if (sb2.toString().equals(words[j])){
                        System.out.println(sb2);
                    }
                    //清空内容
                    sb1.setLength(0);
                    sb2.setLength(0);
                }
            }
        }
    }
}
