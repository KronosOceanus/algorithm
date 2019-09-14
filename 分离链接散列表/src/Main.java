public class Main {

    public static void main(String[] args) {

        SeparateChainingHashTable<Integer> scht = new SeparateChainingHashTable<>();
        for (int i=0;i<20;i++){
            scht.insert(i);
        }
        System.out.println(scht.contains(5));
    }
}
