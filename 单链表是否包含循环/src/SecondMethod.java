/**
 * 两个指针
 * 快慢不同（p一步，q两步）
 * 最后若相遇，则存在循环
 */
/**
 * 方法2还可以计算
 * 环的长度：记录下碰撞点，从该点起，再次碰撞所走过的操作数
 * 碰撞点到连接点距离 = 头指针到连接点距离
 */
public class SecondMethod {

    public static boolean containsLoop(Node head){
        Node p, q;
        p = q = head;
        while(p.next != null && q.next != null && q.next.next != null){
            p = p.next;
            q = q.next.next;
            if (p == q){
                return true;
            }
        }
        return false;
    }
}
