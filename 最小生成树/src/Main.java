public class Main {

    public static void main(String[] args) throws Exception {
        LinkedGraph l = GraphUtils.createGraph(
                "D:\\project\\algorithm\\最小生成树\\src\\graph\\加权图.txt",
                true, false);
        l.prim("v1");
    }
}
