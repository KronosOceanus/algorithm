import java.util.Random;

public class Main {

    public static void main(String[] args) {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>(
                // lambda 表达式引用
                Integer::compare
        );

        Random r = new Random();
        for (int i=0;i<100;i++){
            tree.insert(r.nextInt(100));
        }
        tree.printTree();
        tree.printCount();
        tree.printAreaDatas(20,50);
        tree.printByLevel();

        tree.makeEmpty();
        for (int i=0;i<10;i++){
            tree.insert(i);
        }
        tree.printByLevel();
    }
}
