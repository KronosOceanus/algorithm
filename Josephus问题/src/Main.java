public class Main {

    //人员编号是 1 - NUMBER
    private static final int NUMBER = 41;
    private static final int JUMP = 3;
    public static void main(String[] args) {
        CircularLinkedList<Integer> list = new CircularLinkedList<>();
        for (int i=0;i<NUMBER;i++){
            list.add(i + 1);
        }
        while(list.size() > 1){
            for (int i=0;i<JUMP;i++){
                list.toNext();
            }
            list.remove();
        }
        System.out.println(list.get());






        /**
         * 测试
         *  for (int i=0;i<10;i++){
         *      list.add(i);
         *  }
         *  for (int i=0;i<100;i++){
         *      System.out.print(list.get() + "\t");
         *  }
         */
    }
}
