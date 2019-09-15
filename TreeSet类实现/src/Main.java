import java.util.Comparator;
import java.util.Iterator;

public class Main {

    public static void main(String[] args) {

        MyTreeSet<Integer> mts = new MyTreeSet<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        });
        for (int i=0;i<10;i++){
            mts.insert(i);
        }
        mts.remove(8);
        mts.printTree();
    }
}
