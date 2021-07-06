import java.util.LinkedList;
import java.util.List;

public class Main {

    private static int horner(List<Integer> list, int x){
        int ans = 0;
        for(Integer i : list){
            ans = i + x * ans;
        }
        return ans;
    }
    public static void main(String[] args) {
        List<Integer> l = new LinkedList<>();
        l.add(2);
        l.add(-1);
        l.add(3);
        l.add(1);
        l.add(-8);
        System.out.println(horner(l, 3));
    }
}
