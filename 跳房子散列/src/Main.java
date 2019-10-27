public class Main {

    public static void main(String[] args) {
        HopScotchHashTable<Integer> hsht = new HopScotchHashTable<>();
        for (int i=0;i<100;i++){
            hsht.insert(i);
        }
        hsht.printHash();
    }
}
