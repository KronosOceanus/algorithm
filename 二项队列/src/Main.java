public class Main {

    public static void main(String[] args) {
        BinomialQueue<Integer> bq1 = new BinomialQueue<>(Integer::compareTo);
        BinomialQueue<Integer> bq2 = new BinomialQueue<>(Integer::compareTo);
        for (int i=0;i<10;i++){
            bq1.insert(i);
            bq2.insert(i+10);
        }
        bq1.merge(bq2);
        int size = bq1.size();
        for (int i=0;i<size;i++){
            System.out.print(bq1.deleteMin() + "\t");
        }
    }
}
