import java.util.*;

/**
 * 与 GraphUtils 搭配使用（耦合严重……）
 */
public class LinkedGraph {

    //给先序遍历 num 赋值（初始化为 1）
    private int counter;

    //顶点数
    private int vexNum;
    //边数
    private int arcNum;
    //顶点数组
    private Vertex[] apex;
    //保存边（方便某些需要边的算法使用）
    private ArrayList<Edge> edges;
    //是否加权（这两个变量在 GraphUtils 中注入）
    private boolean isWeight;
    //是否有向
    private boolean isDirect;
    //根据节点数创建图
    public LinkedGraph(int vexNum){
        apex = new Vertex[vexNum];
        this.counter = 1;
    }
    //输出图顶点的信息
    public void printGraph(){
        for (Vertex v : apex){
            System.out.println(v);
        }
    }

    /**
     * 重点！！！！！！！！！！
     */
    //深度优先遍历
    private void dfs(Vertex v){
        v.visited = true;
        for (Vertex w : v.adjVex.keySet()){
            if (! w.visited){
                dfs(w);
            }
        }
    }
    public void dfs(String sName){
        Vertex v = findVex(sName);
        dfs(v);
    }

    /**
     * 双连通性（找出割点）
     */
    private void findArt(Vertex v){
        v.visited = true;
        v.low = v.num = counter ++;
        for (Vertex w : v.adjVex.keySet()){
            if (! w.visited){
                w.parent = v;
                findArt(w);
                if (w.low >= v.num){
                    //在根处总是满足该条件，所以最好验证 v 是不是生成树的根
                    System.out.println(v.name + " 是割点！");
                }
                v.low = Math.min(v.low, w.low);
            }else if (v.parent != w){
                v.low = Math.min(v.low, w.num);
            }
        }
    }
    public void findArt(String sName){
        Vertex v = findVex(sName);
        findArt(v);
        //counter 重新初始化
        counter = 1;
    }

    /**
     * 欧拉回路
     * 只有每个顶点的度为偶数 / 只有两个度数为奇数的顶点（起点和终点不同），才能出现欧拉回路
     */
    public void eulerCircuit(){
        if (isDirect){
            throw new RuntimeException("不是无向图！");
        }

        for (Vertex v : euler()){
            System.out.print(v.name + "\t");
        }
    }
    private List<Vertex> euler(){
        List<Vertex> euler = null;
        for (Vertex v : apex){
            if (! v.adjVex.keySet().isEmpty()){
                //新一次深度优先遍历形成的路径
                List<Vertex> circuit = new LinkedList<>();
                //环起始点
                circuit.add(v);
                dfsEuler(v, circuit);
                //拼接环到欧拉回路上
                euler = montage(euler, circuit);
            }
        }
        return euler;
    }
    //欧拉回路的一次深度优先遍历（得到一个环）
    private void dfsEuler(Vertex v, List<Vertex> circuit){
        if (! v.adjVex.keySet().isEmpty()){
            //从 map 中取出一个 key
            Vertex w = v.adjVex.keySet().toArray(new Vertex[0])[0];
            //从 map 中删除该 key（以后要找出尚未访问的第一个顶点，进行另外一次深度优先遍历）
            v.adjVex.remove(w);
            //反向也要删除
            Vertex vv = findVex(w.name);
            vv.adjVex.remove(v);

            circuit.add(w);
            dfsEuler(w, circuit);
        }
    }
    /**
     * 拼接环到欧拉回路上（顶点）
     * 例如 0 1 2 3 4 5 0 和 3 7 9 8 3 拼接为 0 1 2 /3 7 9 8 3/ 4 5 0
     */
    private List<Vertex> montage(List<Vertex> euler, List<Vertex> circuit){
        if (euler == null){
            return circuit;
        }
        if (circuit.isEmpty()){
            return euler;
        }
        //得到拼接点
        Vertex point = circuit.get(0);
        int size = euler.size();
        for (int i=0;i<size;i++){
            //得到并删除
            Vertex v = euler.get(i);
            if (v.name.equals(point.name)){
                euler.remove(i);
                for (Vertex w : circuit){
                    euler.add(i++, w);
                }
                return euler;
            }
        }
        return euler;
    }

    /**
     * 查找强分支………………………………
     * 将图的边反向，遍历并编号，从编号最高的顶点开始执行深度优先搜索，形成深度优先森林
     * 森林中的每棵树即为一个强分支
     */
    public void strongBranchs(){
        List<List<Vertex>> branchList = findStrongBranchs();
        for (List<Vertex> strongBranch : branchList){
            for (Vertex v : strongBranch){
                System.out.print(v.name + "\t");
            }
            System.out.println();
        }
    }
    //查找强分支，返回多个分支
    private List<List<Vertex>> findStrongBranchs(){
        //储存所有强分支
        List<List<Vertex>> branchList = new LinkedList<>();

        //反转
        reverse();

        //编号和查找强分支同时进行…………………………

        return branchList;
    }
    //将有向图的边反向
    private void reverse(){
        if (! isDirect){
            throw new RuntimeException("不是有向图！");
        }

        //标记某条边是否被反转过


        for (Vertex v : apex){
            Iterator<Vertex> iterator = v.adjVex.keySet().iterator();
            while(iterator.hasNext()){
                Vertex w = iterator.next();
                //边没有反转
                if (! v.reverse.get(w) || ! w.reverse.get(v))
                {
                    //标记
                    v.reverse.put(w, true);
                    w.reverse.put(v, true);
                    //反转
                    w.adjVex.put(v, v.adjVex.get(w));
                    iterator.remove();
                }
            }
        }
    }










    // GraphUtils 使用的方法
    //设置 apex[index] 处顶点名为 name
    public void setIndexApex(int index, String name) {
        apex[index] = new Vertex(name);
    }
    //设置 name 顶点的邻接顶点 adjName
    public void findAndSetterVex(String name, String adjName){
        findAndSetterVex(name, adjName, 0);
    }
    //设置加权邻接点
    public void findAndSetterVex(String name, String adjName, Integer weight){
        Vertex v1 = findVex(name);
        Vertex v2 = findVex(adjName);

        //设置邻接点与实际路径
        v1.adjVex.put(v2, weight);
        v2.path = v1;

        //设置是否反转
        v1.reverse.put(v2, false);
        v2.reverse.put(v1, false);
    }
    // apex 中查找名为 name 的顶点并返回
    private Vertex findVex(String name){
        Vertex vex = null;
        for (Vertex v : apex){
            if (v.name.equals(name)){
                vex = v;
            }
        }
        return vex;
    }
    //通过 apex 装填 edges（Edge 类）
    public void initEdges(){
        edges = new ArrayList<>();
        for (Vertex vi : apex){
            for (Vertex vj : vi.adjVex.keySet()){
                int weight = vi.adjVex.get(vj);
                Edge e = new Edge(vi, vj, weight);
                edges.add(e);
            }
        }
    }

    //顶点类
    private static class Vertex {
        public String name;             //顶点名称
        public Vertex path;            //记录路径上一个顶点

        //记录本顶点与其他顶点之间的权（其他顶点，权），无权图则 value = 0
        public Map<Vertex, Integer> adjVex;

        //标记该边是否被反转过（与上面的 map 对应）
        public Map<Vertex, Boolean> reverse;
        //深度优先生成树
        public int num;                //遍历序号
        public int low;                //最小编号
        public boolean visited;        //是否遍历过
        public Vertex parent;          //生成树结点的父亲


        //加权构造器
        public Vertex(String name) {
            this.name = name;
            this.path = null;

            this.adjVex = new HashMap<>();
            this.reverse = new HashMap<>();

            this.num = 0;
            this.low = 0;
            this.visited = false;
            this.parent = null;
        }

        @Override
        public String toString() {
            StringBuffer result = new StringBuffer();
            result.append("顶点名：");
            result.append(name);
            result.append("\t\t\t");
            result.append("顶点编号：");
            result.append(num);
            result.append("\t\t\t");
            result.append("最小编号");
            result.append(low);
            result.append("\t\t\t");
            result.append("是否已被访问：");
            result.append(visited);
            result.append("\t\t\t");
            if (parent != null){
                result.append("生成树父节点：");
                result.append(parent.name);
                result.append("\t\t\t");
            }

            return result.toString();
        }
    }
    private static class Edge{
        public Vertex vi;
        public Vertex vj;
        public int weight;

        public Edge(Vertex vi, Vertex vj){
            this(vi, vj, 0);
        }
        public Edge(Vertex vi, Vertex vj, int weight){
            this.vi = vi;
            this.vj = vj;
            this.weight = weight;
        }
    }




    // setter + getter
    public int getVexNum() {
        return vexNum;
    }
    public void setVexNum(int vexNum) {
        this.vexNum = vexNum;
    }
    public int getArcNum() {
        return arcNum;
    }
    public void setArcNum(int arcNum) {
        this.arcNum = arcNum;
    }
    public boolean isWeight() {
        return isWeight;
    }
    public void setWeight(boolean weight) {
        isWeight = weight;
    }
    public boolean isDirect() {
        return isDirect;
    }
    public void setDirect(boolean direct) {
        isDirect = direct;
    }

}
