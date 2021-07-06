public class Main {

    public static void main(String[] args) {
        //二叉堆测试
        BinaryHeap<Integer> bh = new BinaryHeap<>(Integer::compareTo);
        for (int i=1;i<50;i++){
            bh.insert(i);
        }
        bh.changeKey(40,39);
        for (int i=1;i<50;i++){
            System.out.print(bh.deleteMin() + "\t");
        }
        System.out.println();

        //构建二叉堆测试
        Integer[] array = new Integer[]{7,8,2,3,0,5,4,6,8};
        BinaryHeap<Integer> bh2 = new BinaryHeap<>(array, Integer::compareTo);
        bh2.findLeftX(8, 5);
    }
}
