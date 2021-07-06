import java.math.BigDecimal;
import java.util.*;

/**
 * 凸包问题
 */
public class Main{

    //蛮力法（出错……）
    public static Point[] getConvexPoint1(Point[] P){
        Point[] result = new Point[P.length];
        int len = 0;  //用于计算最终返回结果中是凸包中点的个数

        for(int i=0; i<P.length; i++){
            for(int j=0; j<P.length; j++){
                if(i == j)     //除去选中作为确定直线的第一个点
                    continue;

                //标记数组，存放点到直线距离所使用判断公式的结果
                double[] judge = new double[P.length];

                //扫描所有点，判断是否在两点组成的直线的一端
                for(int k=0; k<P.length; k++){
                    double a = P[j].y - P[i].y;
                    double b = P[i].x - P[j].x;
                    double c = P[i].x * P[j].y - P[i].y * P[j].x;

                    judge[k] = a * P[k].x + b * P[k].y - c;  //根据公式计算具体判断结果
                }

                // 如果点均在直线的一边,则相应的 A[i]，A[j] 是凸包中的点
                if(judgeArray(judge)){
                    if (! Arrays.asList(result).contains(P[i])){
                        result[len ++] = P[i];
                    }
                    if (! Arrays.asList(result).contains(P[j])){
                        result[len ++] = P[j];
                    }
                }
            }
        }

        //复制
        Point[] result1 = new Point[len];
        System.arraycopy(result, 0, result1, 0, len);
        return result1;
    }
    //判断标记数组中元素是否全部大于等于 0 或者小于等于 0
    private static boolean judgeArray(double[] Array){
        boolean isZheng = false;
        boolean flag = false;
        int len1 = 0, len2 = 0;

        //判断正负
        for (double i : Array){
            if (i > 0){
                isZheng = true;
                break;
            }else if (i < 0){
                break;
            }
        }

        //判断数量
        if (isZheng){
            for (double i : Array) {
                if (i >= 0)
                    len1++;
            }
        }else {
            for (double i : Array) {
                if (i <= 0)
                    len2++;
            }
        }

        if(len1 == Array.length || len2 == Array.length)
            flag = true;
        return flag;
    }

    //分治法
    private static int len = 0;     //凸包点个数
    public static Point[] getConvexPoint2(Point[] P){
        Point[] result = new Point[P.length];
        Arrays.sort(P, Comparator.comparingDouble(o -> o.x));
        //求解上下凸包
        hull(0, P.length-1, P, result, true);
        hull(0, P.length-1, P, result, false);
        //结果复制
        Point[] result1 = new Point[len];
        System.arraycopy(result, 0, result1, 0, len);
        return result1;
    }
    /**
     * @param a 左端点下标
     * @param b 右
     * @param P 源数组
     * @param result 保存结果
     * @param isUp 是否求上包
     */
    private static void hull(int a, int b, Point[] P, Point[] result, boolean isUp){
        double maxDist = 0.0;
        int maxIndex = -1;
        int left = Math.min(a, b);
        int right = Math.max(a, b);

        if (isUp){
            for (int i=left+1; i<right; i++){
                double dist = determinant(P[left], P[i], P[right]);
                if (dist <= 0){      //寻找上包中的点（顺时针）
                    if (dist <= maxDist){
                        maxDist = dist;
                        maxIndex = i;
                    }
                }
            }
        }else {
            for (int i=left+1; i<right; i++){
                double dist = determinant(P[left], P[i], P[right]);
                if (dist >= 0){      //寻找下包中的点（顺时针）
                    if (dist >= maxDist){
                        maxDist = dist;
                        maxIndex = i;
                    }
                }
            }
        }

        //未找到第三个点，则两点直接相连
        if (maxIndex == -1){
            if (! Arrays.asList(result).contains(P[left])){
                result[len ++] = P[left];
            }
            if (! Arrays.asList(result).contains(P[right])){
                result[len ++] = P[right];
            }
            return;
        }

        hull(left, maxIndex, P, result, isUp);
        hull(maxIndex, right, P, result, isUp);
    }
    //三阶行列式（求最大距离，若 < 0 则 p1->p2->p3 为顺时针）
    private static double determinant(Point p1, Point p2, Point p3){
        return p1.x * p2.y + p3.x * p1.y + p2.x * p3.y
                - p3.x * p2.y - p2.x * p1.y - p1.x * p3.y;
    }



    public static void main(String[] args){
        Random r = new Random();
        Point[] P = new Point[10];
        for (int i=0;i<10;i++){
            //生成两位小数
            P[i] = new Point(new BigDecimal(r.nextDouble()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue(),
                    new BigDecimal(r.nextDouble()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        }

        printHull(P, true);
        Point[] result1 = getConvexPoint1(P);
        printHull(result1, true);    //顺时针输出
        Point[] result2 = getConvexPoint2(P);
        printHull(result2, true);    //顺时针输出
    }



    //顺时针 / 逆时针输出凸包
    private static void printHull(Point[] result, boolean isShun){
        TreeMap<Double, Point> tm1;
        TreeMap<Double, Point> tm2;
        //选择排序方式
        if (isShun){
             tm1 = new TreeMap<>((o1, o2) -> Double.compare(o2, o1));
             tm2 = new TreeMap<>((o1, o2) -> Double.compare(o2, o1));
        }else {
            tm1 = new TreeMap<>(Double::compareTo);
            tm2 = new TreeMap<>(Double::compareTo);
        }

        //在凸包内找一点
        Point k = new Point((result[0].x + result[result.length-1].x) / 2,
                (result[0].y + result[result.length-1].y) / 2);

        //分成两部分，根据斜率排序
        for (Point p : result){
            if (p.x < k.x){
                tm1.put((p.y-k.y)/(p.x-k.x), p);
            }else if (p.x > k.x){
                tm2.put((p.y-k.y)/(p.x-k.x), p);
            }else if (p.y < k.y){   //斜率不存在
                tm1.put(Double.MIN_VALUE, p);
            }else if (p.y > k.y){
                tm2.put(Double.MAX_VALUE, p);
            }
        }

        //输出
        for (Point p : tm1.values()){
            System.out.print(p);
        }
        for (Point p : tm2.values()){
            System.out.print(p);
        }
        System.out.println();
    }
}

