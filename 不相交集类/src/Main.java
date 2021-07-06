import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Main {

    //生成迷宫
    private static LinkedList<Integer[]> migong(){
        LinkedList<Integer[]> result = new LinkedList<>();      //存储打破的两面墙壁坐标
        int row = 3;
        int col = 3;
        DisjSets d = new DisjSets(row * col);
        Random r = new Random();
        while(d.find(0) != d.find(8)){
            int x = r.nextInt(row);     //选择第几行
            int y = r.nextInt(col);
            int xx, yy;
            boolean flag = r.nextBoolean();
            if (flag){      //纵向选择相邻墙壁
                xx = x-1<0 ? x+1: x-1;
                yy = y;
            }else {
                xx = x;
                yy = y-1<0 ? y+1: y-1;
            }
            int m = d.find(x * col + y);
            int n = d.find(xx * col + yy);
            if (m != n){
                d.unionBySize(m, n);    //合并（只起判断作用）
                Integer[] k = new Integer[2];   //两面墙壁坐标
                k[0] = x * col + y;
                k[1] = xx * col + yy;
                result.add(k);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<Integer[]> l = migong();
        for (Integer[] k : l){
            for (Integer i : k){
                System.out.print(i + "\t");
            }
            System.out.println();
        }
    }
}
