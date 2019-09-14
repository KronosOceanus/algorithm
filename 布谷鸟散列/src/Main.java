public class Main {

    public static void main(String[] args) {
        HashFamily<String> shf = new StringHashFamily(5);
        CuckooHashTable<String> cht = new CuckooHashTable<>(shf);
        for (int i=0;i<10;i++){
            cht.insert("string_" + i);
        }
        System.out.println(cht.size() + "\t" + cht.tableSize());
    }
}
