import java.math.BigDecimal;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * 贪心算法
 */
public class Main {

    private static class Node{
        public int x;
        public int y;
        public double price;

        public Node(int x, int y, double price){
            this.x = x;
            this.y = y;
            this.price = price;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        int y = sc.nextInt();
        int[] xs = new int[x];
        int[] xx = new int[x];
        int[] ys = new int[y];
        int[] yy = new int[y];
        PriorityQueue<Node> p1 = new PriorityQueue<>(Comparator.comparingDouble(o -> o.price));
        PriorityQueue<Node> p2 = new PriorityQueue<>((o1, o2) -> Double.compare(o2.price, o1.price));
        for (int i=0;i<x;i++){
            xs[i] = xx[i] = sc.nextInt();
        }
        for (int i=0;i<y;i++){
            ys[i] = yy[i] = sc.nextInt();
        }
        for (int i=0;i<x;i++){
            for (int j=0;j<y;j++){
                double price = sc.nextDouble();
                p1.offer(new Node(i, j, price));
                p2.offer(new Node(i, j, price));
            }
        }

        BigDecimal a = new BigDecimal(compute(x, y, xs, ys, p1));
        double min = a.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        BigDecimal b = new BigDecimal(compute(x, y, xx, yy, p2));
        double max = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        System.out.println(min + " to " + max);
    }

    private static double compute(int x, int y, int[] xs, int[] ys, PriorityQueue<Node> p){
        double sum = 0D;
        Node n;
        int quti;
        for (int i=0;i<x * y;i++){
            n = p.remove();
            if (n.price == -1) continue;    //不能搭配

            quti = Math.min(xs[n.x], ys[n.y]);  //最小数量
            xs[n.x] -= quti;
            ys[n.y] -= quti;

            sum += n.price * quti;
        }
        return sum;
    }
}
