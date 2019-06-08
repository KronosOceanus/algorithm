public class Main {

    public static void main(String[] args) {

        Node head = new Node();
        Node p = head;
        for (int i=0;i<5;i++){
            p.next = new Node();
            p = p.next;
        }
        Node mark = p;
        for (int i=0;i<5;i++){
            p.next = new Node();
            p = p.next;
        }
        p.next = mark;

        //方法2测试
        System.out.println(SecondMethod.containsLoop(head));
        //方法1测试（注意会反转链表）
        System.out.println(FirstMethod.containsLoop(head));
    }
}
