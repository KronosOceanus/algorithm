import java.util.Comparator;

public class Main {

    public static void main(String[] args) {

        AVLTree<Integer> tree = new AVLTree<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1,o2);
            }
        });

        for (int i=0;i<10;i++){
            tree.insert(i);
        }
        tree.printTree();


        tree.remove(8);
        System.out.println();
        tree.printTree();
    }
}
