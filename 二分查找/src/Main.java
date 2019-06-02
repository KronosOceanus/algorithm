import java.util.Arrays;

public class Main {

    //得到索引
    private static int binarySearch(int[] a,int x){
        Arrays.sort(a);
        int low = 0;
        int high = a.length - 1;
        //直到交叉为止
        while(low <= high){
            int mid = (low + high)/2;
            if (x < a[mid]){
                high = mid - 1;
            }else if (x > a[mid]){
                low = mid + 1;
            }else {
                return mid;
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        int[] a = {7,8,2,3,0,5,4,6};
        System.out.println(binarySearch(a,5));
    }
}
