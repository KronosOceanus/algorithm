import java.util.*;

public class Main{


    public static void main(String[] args) {
        System.out.println(removeDuplicateLetters("bcabc"));
    }

    public static String removeDuplicateLetters(String s) {
        Stack<Character> st = new Stack<>();
        Set<Character> h = new HashSet<>();
        Map<Character,Integer> charCount = comCount(s);    //计数 Map

        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            if(! st.contains(c)){
                while(!st.isEmpty() && st.peek() > c && charCount.get(st.peek()) > 0){
                    st.pop();
                }
                st.push(c);
            }
            charCount.put(c, charCount.get(c)-1);
        }

        StringBuffer sb = new StringBuffer();
        while(!st.isEmpty()){
            sb.append(st.pop());
        }

        //测试计数 Map
//        for (Map.Entry<Character,Integer> entry : charCount.entrySet()){
//            System.out.println(entry.getKey() + ":" + entry.getValue());
//        }
        return sb.reverse().toString();
    }

    private static Map<Character,Integer> comCount(String s){
        Map<Character,Integer> result = new HashMap<>();
        for (int i=0;i<s.length();i++){
            char c = s.charAt(i);
            if (!result.containsKey(c)){
                result.put(c, 1);
            }else {
                int count = result.get(c);
                result.put(c, count+1);
            }
        }
        return result;
    }
}