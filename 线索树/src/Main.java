public class Main {

    public static void main(String[] args) {
        ThreadedTree<Integer> t = new ThreadedTree<>(Integer::compare);

        t.insert(7);
        t.insert(4);
        t.insert(5);
        t.insert(2);
        t.insert(1);
        t.insert(9);
        t.insert(20);

        System.out.println("是否线索化：" + t.isThread());
        t.printTree();
        t.inThreadOrder();
        System.out.println("是否线索化：" + t.isThread());
        t.printTree();
        System.out.println("按照线索输出：");
        t.printByThread();
        System.out.println("是否包含20：" + t.contains(20));
        System.out.println("是否包含15：" + t.contains(15));

    }
}
