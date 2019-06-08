import java.util.ListIterator;

public class Main {

    public static void main(String[] args) {

        MyArrayList<Integer> list = new MyArrayList<>();
        for (int i=0;i<10;i++){
            list.add(i);
        }
        System.out.println("长度====" + list.size());
        System.out.println("是否为空====" + list.isEmpty());



        //容量测试
        for (int i=10;i<100;i++){
            list.add(i);
        }
        list.ensureCapacity(150);
        list.remove(98);
        list.trimToSize();
        System.out.println("扩容之后====");
        for (int i=0;i<list.size();i++){
            System.out.print(list.get(i) + "\t");
        }
        System.out.println();



        //迭代器测试
        ListIterator<Integer> iterator = list.iterator();
        int i=0;
        System.out.println("迭代器迭代====");
        while(iterator.hasNext()){
            i++;
            System.out.print(iterator.next() + "\t");
            if (i == 5){
                System.out.println("====迭代器添加50====");
                iterator.add(50);
                System.out.print("====访问前一个元素（再设置为25）====   ");
                System.out.println(iterator.previous());
                iterator.set(25);
                /** 迭代器失效，抛出异常
                iterator.remove();
                */
            }
        }
    }
}
