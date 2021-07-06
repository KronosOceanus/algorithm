import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Random r = new Random();
        int[] arr = new int[10];
        for (int i=0;i<arr.length;i++){
            arr[i] = r.nextInt(100);
        }
        binaryInsertSort(arr);
        for (Integer i : arr){
            System.out.print(i + "\t");
        }
    }

    private static void binaryInsertSort(int[] a)
    {
        for(int i=1;i<a.length;i++)
        {
            int tmp = a[i];
            int low = 0;    //同时也是插入位置
            int high = i - 1;
            while(low <= high)
            {
                int mid = (low + high) / 2;
                if(tmp < a[mid]) {
                    high = mid - 1;
                }
                else {
                    low = mid + 1;
                }
            }

            for(int j=i;j>=low+1;j--)
            {
                a[j] = a[j - 1];
            }
            a[low] = tmp;
        }
    }
}
