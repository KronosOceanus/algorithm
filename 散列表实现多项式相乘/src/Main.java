import java.util.*;

/**
 * 多项式链表相乘
 * 散列表存放<次数， 多项式项>
 * 边相乘，边将结果放入散列表
 */
public class Main {

    private static HashMap<Integer, Item> hashMap;

    //多项式相乘
    private static HashMap<Integer, Item> multiplication(List<Item> A, List<Item> B){
        //散列表最大次数
        hashMap = new HashMap<>(getExponent(A) + getExponent(B) + 1);
        int resultCoefficient;
        int resultExponent;
        for (Item itemA : A){
            for (Item itemB : B){
                resultCoefficient = itemA.coefficient * itemB.coefficient;
                resultExponent = itemA.exponent + itemB.exponent;

                //散列表中是否包含该阶次的项
                if (hashMap.containsKey(resultExponent)){
                    Item mapItem = hashMap.get(resultExponent);
                    resultCoefficient *= mapItem.coefficient;
                }
                //加入散列表
                Item hashItem = new Item(resultCoefficient, resultExponent);
                hashMap.put(resultExponent ,hashItem);  //散列表第几个位置代表几个阶次
            }
        }
        return hashMap;
    }

    //得到多项式中最大次
    private static int getExponent(List<Item> itemList){
        int maxExponent =  0x80000000;
        for (Item item : itemList){
            if (maxExponent < item.exponent){
                maxExponent = item.exponent;
            }
        }
        return maxExponent;
    }
    //输出散列表
    private static void printHashMap(){
        if (hashMap == null){
            return;
        }

        for (Item item : hashMap.values()){
            System.out.print(item + " + ");
        }
    }

    public static void main(String[] args) {
        List<Item> A = new ArrayList<>();
        List<Item> B = new ArrayList<>();
        for (int i=1;i<5;i++){
            Item itemA = new Item(i, i+5);
            Item itemB = new Item(i, i+3);
            A.add(itemA);
            B.add(itemB);
        }

        multiplication(A, B);
        printHashMap();
    }
}
