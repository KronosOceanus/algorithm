import java.util.Random;

public class Main {

    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        Random r = new Random(15);
        bst.insert(1);
        for (int i=0;i<15;i++){
            bst.insert(r.nextInt(15));
        }
        bst.printTree();
        bst.remove(1);
        bst.remove(2);
        System.out.println("\n" + bst.getTreeSize() + "  " + bst.getDelSize());
        System.out.println(bst.findMin());
        bst.printTree();
    }
}
