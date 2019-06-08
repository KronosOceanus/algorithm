import java.util.Iterator;

public class Main {

    public static void main(String[] args) {

        MyLinkedList<Integer> list = new MyLinkedList<>();
        for (int i=0;i<10;i++){
            list.add(i);
        }
        System.out.println("集合大小====" + list.size());
        System.out.println("是否为空====" + list.isEmpty());



        //容量测试
        /**
        for (int i=10;i<100;i++){
            list.add(i);
        }
        list.exchange(99);
        for (int i=0;i<100;i++){
            System.out.print(list.get(i) + "\t");
        }
        System.out.println();
         */



        //迭代器测试
        Iterator<Integer> iterator = list.iterator();
        int k=0;
        while(iterator.hasNext()){
            k++;
            System.out.print(iterator.next() + "\t");
            if (k == 7 || k == 8){
                //迭代器不会失效
                iterator.remove();
            }
        }
        System.out.println("\n迭代器remove之后====");
        //迭代器remove过后
        for (int i=0;i<list.size();i++){
            System.out.print(list.get(i) + "\t");
        }
        System.out.println();



        //removeAll测试
        MyLinkedList<Integer> newList = new MyLinkedList<>();
        for (int i=0;i<5;i++){
            newList.add(i);
        }
        for (int i=100;i<105;i++){
            newList.add(i);
        }
        System.out.println("要删除的元素集合====");
        for (int i=0;i<newList.size();i++){
            System.out.print(newList.get(i) + "\t");
        }
        System.out.println();
        Iterable<Integer> integers = newList;
        for (int i=100;i<115;i++){
            list.add(i);
        }
        list.removeAll(integers,newList.size());
        System.out.println("删除其他集合之后的集合====");
        for (int i=0;i<list.size();i++){
            System.out.print(list.get(i) + "\t");
        }
        System.out.println();



        //splice测试
        MyLinkedList<Integer> fromList = new MyLinkedList<>();
        for (int i=50;i<60;i++){
            fromList.add(i);
        }
        Iterator<Integer> iteratorTo = list.iterator();
        k = 0;
        System.out.println("剪贴之后的集合====");
        while(iteratorTo.hasNext()){
            k++ ;
            iteratorTo.next();
            //走到中间，向前面插入
            if (k == 5){
                break;
            }
        }
        list.splice(iteratorTo, fromList);
        for(int i=0;i<list.size();i++){
            System.out.print(list.get(i) + "\t");
        }
        System.out.println();
    }
}
