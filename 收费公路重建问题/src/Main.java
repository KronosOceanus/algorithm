import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {

    /**
     * 驱动
     * @param x     存放 x 点的坐标
     * @param d     存放所有距离
     * @param n     x 的长度
     * @return      是否可行
     */
    private static boolean trunpike(int[] x, PriorityQueue<Integer> d, int n){
        x[1] = 0;
        x[n] = d.remove();
        x[n-1] = d.remove();
        if (d.contains(x[n] - x[n-1])){
            d.remove(x[n] - x[n-1]);
            return place(x, d, n, 2, n-2);   //摆放
        }else {
            return false;
        }
    }
    private static boolean place(int[] x, PriorityQueue<Integer> d, int n, int left, int right){
        int dmax;
        boolean found = false;

        //递归终止条件
        if (d.isEmpty()){
            return true;
        }

        dmax = d.remove();
        //是否能摆放
        boolean flag = true;
        for (int i=0; i<left; i++){
            flag = d.contains(Math.abs(x[i] - dmax));
        }
        for (int i=n; i>right; i--){
            flag = d.contains(Math.abs(x[i] - dmax));
        }
        if (flag){
            //从右边开始摆放
            x[right] = dmax;
            for (int i=0; i<left; i++){
                d.remove(Math.abs(x[i] - dmax));
            }
            for (int i=n; i>right; i--){
                d.remove(Math.abs(x[i] - dmax));
            }
            //递归
            found = place(x, d, n, left, right-1);
            //回溯
            if (! found){
                for (int i=0; i<left; i++){
                    d.offer(Math.abs(x[i] - dmax));
                }
                for (int i=n; i>right; i--){
                    d.offer(Math.abs(x[i] - dmax));
                }
            }
        }

        //从左边开始摆放 x
        flag = true;
        for (int i=0; i<left; i++){
            flag = d.contains(Math.abs(x[n] - dmax - x[i]));
        }
        for (int i=n; i>right; i--){
            flag = d.contains(Math.abs(x[n] - dmax - x[i]));
        }
        if (! found && flag){
            //从左边开始摆放
            x[left] = x[n] - dmax;
            for (int i=0; i<left; i++){
                d.remove(Math.abs(x[n] - dmax - x[i]));
            }
            for (int i=n; i>right; i--){
                d.remove(Math.abs(x[n] - dmax - x[i]));
            }
            //递归
            found = place(x, d, n, left+1, right);
            //回溯
            if (! found){
                for (int i=0; i<left; i++){
                    d.offer(Math.abs(x[n] - dmax - x[i]));
                }
                for (int i=n; i>right; i--){
                    d.offer(Math.abs(x[n] - dmax - x[i]));
                }
            }
        }

        return found;
    }


    public static void main(String[] args) {
        int[] x = new int[11];
        PriorityQueue<Integer> d = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        d.offer(1);
        d.offer(2);
        d.offer(2);
        d.offer(2);
        d.offer(3);
        d.offer(3);
        d.offer(3);
        d.offer(4);
        d.offer(5);
        d.offer(5);
        d.offer(5);
        d.offer(6);
        d.offer(7);
        d.offer(8);
        d.offer(10);
        System.out.println(trunpike(x, d, 10));
    }
}
