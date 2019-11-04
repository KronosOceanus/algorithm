public class Main {

    public static void main(String[] args) {
        LeftistHeap<Integer> lh1 = new LeftistHeap<>(Integer::compareTo);
        LeftistHeap<Integer> lh2 = new LeftistHeap<>(Integer::compareTo);
        for (int i=0;i<10;i++){
            lh1.insert(i);
            lh2.insert(i+20);
        }
        lh1.merge(lh2);
        int size = lh1.size();
        for (int i=0;i<size;i++){
            System.out.print(lh1.deleteMin() + "\t");
        }
    }
}
