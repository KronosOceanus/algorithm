public class Main {

    public static void main(String[] args) throws Exception {
        LinkedGraph l = GraphUtils.createGraph("D:\\project\\algorithm\\最大流问题\\src\\graph\\maxFlow2.txt", true, true);
        System.out.println("最大流： " + l.maxFlow("s", "t"));
        l.printGraph();
    }
}
