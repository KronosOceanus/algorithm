import java.math.BigDecimal;
import java.util.*;

/**
 * 最近点问题，分治
 * 递归边界，遍历终止
 * 计算 x 中线，分为两组
 * 递归得到 dL，dR（可能为 null）
 * 确定带的范围，获取带中的点，并按 y 坐标排序
 * 计算 dC
 */
public class Main {

    private static final int CUTOFF = 4;    //区域内点数 <= 4，作为递归终止条件计算
    private static double ds = Double.POSITIVE_INFINITY;    //记录最短距离

    private static class Point{
        double x;
        double y;

        public Point(double x, double y){
            this.x = x;
            this.y = y;
        }
    }

    //计算两点距离
    private static double dist(Point p1, Point p2){
        return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
    }

    /**
     * 分治算法
     * @param P 按照 x 坐标排序后的点集
     * @return  组成 dC 的两个点（左边的 dC 记为 dL，右 dR）
     */
    private static Point[] closedPoints(Point[] P){
        Point[] result = new Point[2];

        //递归终止条件（遍历）
        if (P.length <= CUTOFF){
            double mind = Double.POSITIVE_INFINITY;
            for (int i=0;i<P.length;i++){
                for (int j=i+1;j<P.length;j++){
                    double d = dist(P[i], P[j]);
                    if (d < mind){
                        ds = mind = d;
                        result[0] = P[i];
                        result[1] = P[j];
                    }
                }
            }
            return result;
        }

        //求 x 中线坐标
        double center = (P[0].x + P[P.length - 1].x) / 2;
        //根据中线分为两组
        List<Point> PL = new LinkedList<>();
        List<Point> PR = new LinkedList<>();
        for (Point p : P){
            if (p.x <= center){
                PL.add(p);
            }else {
                PR.add(p);
            }
        }

        //递归得到 dL 和 dR（对应的点对）
        Point[] dLP = closedPoints(PL.toArray(Point[]::new));
        Point[] dRP = closedPoints(PR.toArray(Point[]::new));
        //确定带的范围
        if (dLP[0] != null && dRP[0] != null){
            result = dist(dLP[0], dLP[1]) < dist(dRP[0], dRP[1]) ? dLP : dRP;
        }else {
            result = dRP[0] == null ? dLP : dRP;
        }
        ds = dist(result[0], result[1]);

        //得到带中的点
        List<Point> pInStripList = new LinkedList<>();
        for (Point p : P){
            if (p.x >= center - ds && p.x <= center + ds){
                pInStripList.add(p);
            }
        }
        //带中的点按照 y 坐标排序
        pInStripList.sort(Comparator.comparingDouble(o -> o.y));

        //计算 dC
        Point[] pInStrip = pInStripList.toArray(Point[]::new);
        for (int i=0;i<pInStrip.length;i++){
            for (int j=i+1;j<pInStrip.length;j++){
                if (pInStrip[j].y - pInStrip[i].y > ds){
                    break;
                }
                double d = dist(pInStrip[i], pInStrip[j]);
                if (d < ds){
                    ds = d;
                    result[0] = pInStrip[i];
                    result[1] = pInStrip[j];
                }
            }
        }

        return result;
    }

    //遍历（测试结果是否正确）
    private static Point[] closedPointsB(Point[] P){
        Point[] result = new Point[2];
        double mind = Double.POSITIVE_INFINITY;
        for (int i=0;i<P.length;i++){
            for (int j=i+1;j<P.length;j++){
                double d = dist(P[i], P[j]);
                if (d < mind){
                    mind = d;
                    result[0] = P[i];
                    result[1] = P[j];
                }
            }
        }
        return  result;
    }

    public static void main(String[] args) {
        Point[] p = new Point[20];
        Random r = new Random();
        for (int i=0;i<p.length;i++){
            p[i] = new Point(new BigDecimal(r.nextDouble()).setScale(2, BigDecimal.ROUND_HALF_EVEN).doubleValue(),
                    new BigDecimal(r.nextDouble()).setScale(2, BigDecimal.ROUND_HALF_EVEN).doubleValue());
        }
        for (Point f : closedPoints(p)){
            System.out.println("(" + f.x + "," + f.y + ")" + "=" + ds);
        }
        for (Point z : closedPointsB(p)){
            System.out.println("(" + z.x + "," + z.y + ")");
        }
    }
}
