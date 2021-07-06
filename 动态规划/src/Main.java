public class Main {

    /**
     * 矩阵乘法顺序规划
     * @param c 每个矩阵的列数（c[0] 存放矩阵 A1 的行数）
     * @param m 最优顺序下乘法次数
     * @param lastChange 保留最优经过矩阵
     */
    private static void optMatrix(int[] c, long[][] m, int[][] lastChange){
        int n = c.length - 1;

        for (int left=1; left<=n; left++){
            m[left][left] = 0;
        }
        for (int k=1;k<n;k++){      // k = right - left
            for (int left=1; left<n-k; left++){
                int right = left + k;   //遍历 m
                m[left][right] = Long.MAX_VALUE;

                for (int i=left; i<right; i++){
                    //递推公式，计算本次乘法需要得最优次数
                    long thisCost = m[left][i] + m[i+1][right]
                            + c[left-1] * c[i] * c[right];
                    //记录
                    if (thisCost < m[left][right]){
                        m[left][right] = thisCost;
                        lastChange[left][right] = i;
                    }
                }
            }
        }
    }

    /**
     * 最优二叉查找树
     * @param p 存放单词出现的概率
     * @param m 存放最小价值
     * @param lastChange 记录让两个节点最小价值的根
     */
    private static void bestBinaryTree(int[] p, int[][] m, int[][] lastChange){
        int n = p.length;

        for (int left=0; left<n; left++){
            m[left][left] = p[left];
        }
        for (int k=1; k<n; k++){
            for (int left=0; left<n-k; left++){
                int right = left + k;   // right 最大为 n-1
                m[left][right] = Integer.MAX_VALUE;

                int pSum = 0;
                for(int i=left; i<=right; i++){
                    pSum += p[i];
                }

                //在中间寻找最优根
                for (int i=left+1; i<=right-1; i++){
                    int thisCost = m[left][i-1] + m[i+1][right] + pSum;     //代价
                    if (thisCost < m[left][right]){
                        m[left][right]= thisCost;
                        lastChange[left][right]= i;
                    }
                }
                //如果以 left 或者 right 为根（p 大的为根）
                if (m[left][right] == Integer.MAX_VALUE){
                    int i = m[left][left] < m[right][right] ? left : right;
                    m[left][right] = pSum + m[i][i];
                    lastChange[left][right] = i;
                }
            }
        }
    }

    private static final int NOT_A_VERTEX = -1;

    /**
     * 多源最短路径计算
     * @param a 矩阵
     * @param d 路径长度
     * @param path 记录最短路径中间节点
     */
    private static void allPairs(int[][] a, int[][] d, int[][] path){
        int n = a.length;

        for (int i=0;i<n;i++){
            for (int j=0;j<n;j++){
                d[i][j] = a[i][j];
                path[i][j] = NOT_A_VERTEX;
            }
        }

        for (int k=0;k<n;k++){
            for (int i=0;i<n;i++){
                for (int j=0;j<n;j++){
                    if (d[i][k] + d[k][j] < d[i][j]){
                        d[i][j] = d[i][k] + d[k][j];
                        path[i][j] = k;
                    }
                }
            }
        }
    }



    public static void main(String[] args) {

        //最优二叉查找树测试
        int[] p = new int[7];
        p[0] = 22;
        p[1] = 18;
        p[2] = 20;
        p[3] = 5;
        p[4] = 25;
        p[5] = 2;
        p[6] = 8;
        int[][] m = new int[7][7];
        int[][] lastChange = new int[7][7];
        bestBinaryTree(p, m, lastChange);
        for (int i=0;i<7;i++){
            for (int j=i;j<7;j++){
                if (m[i][j] != 0){
                    System.out.print(m[i][j] + "\t\t");
                }
            }
            System.out.println();
        }
        for (int i=0;i<7;i++){
            for (int j=i;j<7;j++){
                if (m[i][j] != 0){
                    System.out.print(lastChange[i][j] + "\t\t");
                }
            }
            System.out.println();
        }
    }
}
