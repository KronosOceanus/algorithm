public class Main {

    public static void main(String[] args) {
        OrderedCollection oc = new OrderedCollection();
        for (int i=0;i<100;i++){
            oc.insert(new Comp(i));
        }
        System.out.println(oc);
    }
}
