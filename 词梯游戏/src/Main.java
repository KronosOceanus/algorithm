import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 输出一个单词通过一系列单个字母变换转化成另一个单词的过程
 * 如 zero hero here hire fire five
 * 每个单词是一个顶点，可以单个字母变换得到的单词之间有边
 */
public class Main {

    /**
     * 找到所有路径（构建图）并保存在一个 Map 中
     * @param adjacentWords 单词变换邻接表
     * @param first 初始单词 zero
     * @param second 目标单词 five
     */
    public static List<String>
    findChain(Map<String, List<String>> adjacentWords, String first, String second){

        //保存遍历到的所有路径
        Map<String, String> previousWord = new HashMap<>();
        LinkedList<String> queue = new LinkedList<>();

        queue.offer(first);
        while(! queue.isEmpty()){
            String current = queue.poll();
            //得到邻接点
            List<String> adj = adjacentWords.get(current);

            if (adj != null){
                for (String adjWord : adj){
                    if (previousWord.get(adjWord) == null){
                        //记录实际路径
                        previousWord.put(adjWord, current);
                        queue.offer(adjWord);
                    }
                }
            }
        }

        //设置起始点路径为 null，作为下一个方法的终止条件
        previousWord.put(first, null);

        return getChainFromPreviousMap(previousWord, first, second);
    }

    /**
     * 通过实际路径得到目标最短路径
     */
    private static List<String> getChainFromPreviousMap(Map<String, String> prev, String first, String second){
        LinkedList<String> result = null;

        if (prev.get(second) != null){
            result = new LinkedList<>();
            //从 second 找路径直到 first
            for (String str = second; str != null; str = prev.get(str)){
                result.add(str);
            }
        }

        return result;
    }



    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
}
