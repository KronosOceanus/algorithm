public class Main {

    public static void main(String[] args) {

        AVLTree<Integer> tree = new AVLTree<>(Integer::compare);

        for (int i=0;i<10;i++){
            tree.insert(i);
        }
        tree.printTree();
        System.out.println();
        System.out.println(tree.isBalance());
    }
}
