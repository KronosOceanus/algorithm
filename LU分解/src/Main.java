import java.util.Arrays;
import java.util.List;

/**
 * 用高斯消去法解方程组Ax = b（A，b 已知）
 * 用得到的A的LU分解,解方程 Ay = c
 *
 * 先对 A 矩阵初等变换求出上三角矩阵 U
 * 下三角矩阵 L 对应位置数为上三角矩阵变换时被减数的系数，对角线为 1
 * 则 LUx = b（前代）
 * 令 Ux = V，则 LV = b
 * 得到 V，回代 Ux = V，得到 x（回代）
 */
public class Main{

    public static void main(String[] args) {
        double[][] A ={{2,4,-2},{4,9,-3},{-2,-1,7}};
        double[] b = {2,8,10};
        int row = 3;
        double[] x = solve(A, b);
        for (double x1 : x) {
            System.out.print(x1+"\t");
        }

    }

    private static double[] solve(double[][] a, double[] b) {
        List<double[][]> LAndU = decomposition(a);  //LU 分解
        double[][] L = LAndU.get(0);
        double[][] U = LAndU.get(1);
        double[] UMultiX = getUMultiX(a, b, L);   //前代（前代和回代都是解线性方程的）
        return getSolution(a, U, UMultiX);        //回代
    }


    private static List<double[][]> decomposition(double[][] a){
        double[][] L = createIndentityMatrix(a.length);

        for(int j=0; j<a[0].length - 1; j++) {
            if(a[j][j] == 0) {      //主子式不能为 0，才能 LU 分解
                throw new IllegalArgumentException("zero pivot encountered.");
            }

            for(int i=j+1; i<a.length; i++) {
                double mult = a[i][j] / a[j][j];        //因子
                for(int k=j; k<a[i].length; k++) {
                    a[i][k] = a[i][k] - a[j][k] * mult;
                    //得出上三角矩阵U,通过减去矩阵的第一行,第二行,第一行(第二行)得到上三角矩阵
                }
                L[i][j] = mult;  //得到下三角矩阵是得出上三角矩阵的乘积因子
            }
        }
        return Arrays.asList(L, a);

    }
    //创建单位矩阵
    private static double[][] createIndentityMatrix(int row){
        double[][] identityMatrix = new double[row][row];
        for(int i=0;i<row;i++){
            identityMatrix[i][i] = 1;
        }
        return identityMatrix;
    }



    /**
     * Get solution of the equations 
     * @param a - Coefficient matrix of the equations 
     * @param U - U of LU Decomposition 
     * @param UMultiX - U multiply X 
     * @return Equations solution 
     */
    private static double[] getSolution(double[][] a, double[][] U,
                                        double[] UMultiX) {
        double[] solutions = new double[a[0].length];
        for(int i=U.length-1; i>=0; i--) {
            double right_hand = UMultiX[i];
            for(int j=U.length-1; j>i; j--) {
                right_hand -= U[i][j] * solutions[j];
            }
            solutions[i] = right_hand / U[i][i];
        }
        return solutions;
    }

    /**
     * Get U multiply X 
     * @param a - Coefficient matrix of the equations 
     * @param b - right-hand side of the equations 
     * @param L - L of LU Decomposition 
     * @return U multiply X 
     */
    private static double[] getUMultiX(double[][] a, double[] b, double[][] L) {
        double[] UMultiX = new double[a.length];        //Ux
        for(int i=0; i<a.length; i++) {
            double right_hand = b[i];
            for(int j=0; j<i; j++) {        //下三角矩阵
                right_hand -= L[i][j] * UMultiX[j];
            }
            UMultiX[i] = right_hand / L[i][i];      //Ux = b/L
        }
        return UMultiX;
    }
}