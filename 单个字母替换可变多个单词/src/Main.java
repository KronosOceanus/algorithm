import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Map的应用
 * 以89000个单词测试结果
 */
public class Main {

    //一个单词通过替换至少可以变成其他 15 个单词
    private static final int MIN_WORDS = 15;

    //输出符合条件的单词
    public static void printHighChangeables(Map<String, List<String>> adjWords){
        //遍历每个单词，以及该单词的映射（替换后的单词）
        for (Map.Entry<String,List<String>> entry : adjWords.entrySet()){

            List<String> words = entry.getValue();

            if(words.size() > MIN_WORDS){
                System.out.print(entry.getKey() + " (");
                System.out.print(words.size() + "): ");
                for(String w : words){
                    System.out.print(" " + w);
                }
                System.out.println();
            }

        }
    }

    //比较两个单词是否只有一个字母不同
    private static boolean oneCharOff(String word1, String word2){
        if (word1.length() != word2.length()){
            return false;
        }

        int diff = 0;

        for (int i=0;i<word1.length();i++){
            if (word1.charAt(i) != word2.charAt(i)){
                if (++ diff > 1){
                    return false;
                }
            }
        }

        return diff == 1;
    }

    /**
     * 方法一：
     * 转换成数组遍历比较（参数为单词词典），耗时约 75s
     * key为某个单词，value为对应单词替换一个字母得到的单词
     */
    public static Map<String,List<String>>
    computeAdjacentWords_One(List<String> theWords){
        //目标单词映射
        Map<String, List<String>> adjWords = new TreeMap<>();
        //开辟数组，并填充
        String[] words = new String[theWords.size()];
        theWords.toArray(words);
        //一个一个对比
        for (int i=0;i<words.length;i++){
            for (int j=i+1;j<words.length;j++){
                if (oneCharOff(words[i],words[j])){
                    update(adjWords, words[i], words[j]);
                    update(adjWords, words[j], words[i]);
                }
            }
        }
        return adjWords;
    }

    //更新符合条件的单词Map（接下来两个方法key不一定是String类型）
    private static <K> void update(Map<K, List<String>> m,
                                   K key, String value){
        //m中如果不存在key对应的value，则新建value
        // 并且加入m中（并发实现应覆盖该方法）
        List<String> lst = m.computeIfAbsent(key, k -> new ArrayList<>());
        lst.add(value);
    }

    /**
     * 方法二：
     * 使用2个Map，把单词按照长度分组，耗时约 16s
     * 新增的Map中key是单词的长度，value是该长度的所有单词集合
     */
    public static Map<String,List<String>>
    computeAdjacentWords_Two(List<String> theWords){
        //目标单词映射
        Map<String, List<String>> adjWords = new TreeMap<>();
        //把单词按照长度分组的Map
        Map<Integer,List<String>> wordsByLength = new TreeMap<>();

        //分组
        for (String w : theWords){
            update(wordsByLength, w.length(), w);
        }
        //把每一组化为数组，比较
        for (List<String> groupsWords : wordsByLength.values()){
            //转换成数组
            String[] words = new String[groupsWords.size()];
            groupsWords.toArray(words);

            for (int i=0;i<words.length;i++){
                for (int j=i+1;j<words.length;j++){
                    if (oneCharOff(words[i],words[j])){
                        update(adjWords, words[i], words[j]);
                        update(adjWords, words[j], words[i]);
                    }
                }
            }
        }
        return adjWords;
    }

    /**
     * 方法三：
     * 使用附加Map，长度分组，耗时约 4s
     * 附加的Map中key是删除某个字母得到的字母组合
     * value是该前/中/后缀对应的单词
     * 使用新的Map再处理删除其他字母的情况
     */
    public static Map<String,List<String>>
    computeAdjacentWords_Three(List<String> theWords){
        Map<String, List<String>> adjWords = new TreeMap<>();
        Map<Integer, List<String>> wordsByLength = new TreeMap<>();

        for (String w : theWords){
            update(wordsByLength, w.length(), w);
        }
        //遍历按单词长度分组的 Map
        for (Map.Entry<Integer, List<String>> entry : wordsByLength.entrySet()){
            List<String> groupWords = entry.getValue();
            int groupNum = entry.getKey();

            for (int i=0;i<groupNum;i++){
                //附加的Map
                Map<String, List<String>> repToWord = new TreeMap<>();

                //把除掉一个字母后的 前/中/后缀 及其 value 添加到附加的Map中
                for (String str : groupWords){
                    String rep = str.substring(0,i) + str.substring(i + 1);
                    update(repToWord, rep, str);
                }

                //从前/中/后缀相同的单词中，找到不同的单词（单个字母不同）
                for (List<String> wordClique : repToWord.values()){
                    if (wordClique.size() >= 2){
                        for (String s1 : wordClique){
                            for (String s2 : wordClique){
                                if (! s1.equals(s2)){
                                    update(adjWords, s1, s2);
                                }
                            }
                        }
                    }
                }
            }
        }
        return adjWords;
    }



    public static void main(String[] args) {
        String str = "qwertyuiop";
        System.out.println(str.substring(0, 2) + str.substring(3));
    }
}
