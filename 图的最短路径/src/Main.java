public class Main {

    public static void main(String[] args) throws Exception {
        LinkedGraph l = GraphUtils.createDirectGraph("D:\\project\\algorithm\\图的最短路径\\src\\graph\\加权图.txt");
        l.dijkstra("v1");
    }
}
