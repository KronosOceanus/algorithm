public class Main {

    public static void main(String[] args) throws Exception {
        LinkedGraph l = GraphUtils.createGraph(
                "D:\\project\\algorithm\\深度优先搜索\\src\\graph\\强分支.txt",
                false, true);
        l.strongBranchs();
        l.printGraph();
    }
}
