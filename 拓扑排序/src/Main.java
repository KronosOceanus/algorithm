public class Main {

    public static void main(String[] args) throws Exception{

        LinkedGraph l = GraphUtils.createNoDirectGraph("D:\\project\\algorithm\\拓扑排序\\src\\graph\\拓扑排序图.txt");
        System.out.println("拓扑排序：");
        l.topSort();
    }
}
