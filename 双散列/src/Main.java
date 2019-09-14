public class Main {

    public static void main(String[] args) {
        DoubleHashTable<Integer> dht = new DoubleHashTable<>();
        for (int i=0;i<10;i++){
            dht.insert(i);
        }
        System.out.println(dht.size() + "\t" + dht.tableSize());
    }
}
