/**
 * 从矩阵的右上角开始,而右上角是第一行的最大值,如果当前元素小于要找的数x,
 * 说明该行不可能存在x,因为每一行中,第n-1列是该行最大值,因此就到下一行i++,
 * 如果a[i][j]大于要找的数,则说明该数存于这一行中,进行j–-操作
 */
public class Main {

    //数组，边长，要查找的数
    private static boolean findX(int[][] a,int n,int x){
        for(int i=0,j=n-1;    j>=0 && i<n;   ){
            if(a[i][j] == x){
                return true;
            }else if(a[i][j] < x){
                i++;
            }else{
                j--;
            }
        }
        return false;
    }
    public static void main(String[] args) {
        int[][] a = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
        System.out.println(findX(a,a.length,11));
    }
}
