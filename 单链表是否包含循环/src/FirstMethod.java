/**
 * 边遍历边将链表反转
 * 如果碰到null，则没有循环
 * 如果 = head，则有循环
 */
public class FirstMethod {

    public static boolean containsLoop(Node head){
        Node p, q, r;
        p = head;
        q = head.next;
        head.next = null;
        while(q.next != null){
            //走位
            r = q.next;
            //反向链接
            q.next = p;
            //调整到下一个位置
            p = q;
            q = r;
        }
        return q == head;
    }
}
