public class Main {

    public static void main(String[] args) {

        QuadraticProbingHashTable<Integer> qpht = new QuadraticProbingHashTable<>();
        for (int i=0;i<10;i++){
            qpht.insert(i);
        }
        System.out.println(qpht.tableSize());
    }
}
