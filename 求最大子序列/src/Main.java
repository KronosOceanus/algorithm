public class Main {

    public static void main(String[] args) {

        int[] arrs = {7,-4,5,-10,5,7,-1,4};
        int maxSum = 0,thisSum = 0;
        for(int i=0;i<arrs.length;i++){
            thisSum += arrs[i];
            if (maxSum < thisSum){
                maxSum = thisSum;
            }
            //将该出看做起点（负数不可能是起点）
            if (thisSum < 0){
                thisSum = 0;
            }
        }
        System.out.println(maxSum);
    }
}
