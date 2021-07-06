public class Main {

    public static void main(String[] args) {
        Random rd = new Random();
        System.out.println(rd.randomInt());

        Random48 r = new Random48();
        System.out.println(r.randomInt());
        System.out.println(r.random0_1());
    }
}
