import java.util.*;

/**
 * 与 GraphUtils 搭配使用（耦合严重……）
 */
public class LinkedGraph {

    //顶点数组
    private Vertex[] apex;
    //保存边
    private ArrayList<Edge> edges;
    //是否加权
    private boolean isWeight;
    //是否有向
    private boolean isDirect;
    //根据节点数创建图
    public LinkedGraph(int vexNum){
        apex = new Vertex[vexNum];
    }
    //输出图的信息
    public void printGraph(){
        for (Vertex v : apex){
            System.out.println(v);
        }
    }


    /**
     * 重点！！！！！！！！！！！！
     */
    /**
     * 最小生成树 prim 算法
     * 与 dijkstra 基本算法完全相同（不同 s.path = null）
     * 最后结果储存在每个顶点的 path 中（sName 顶点除外）
     */
    public void prim(String sName){
        if (! isWeight){
            throw new RuntimeException("不是加权图！");
        }
        if (isDirect){
            throw new RuntimeException("不是无向图！");
        }

        PriorityQueue<Vertex> heap = new PriorityQueue<>(Comparator.comparingInt(o -> o.dist));

        Vertex s = findVex(sName);
        s.dist = 0;
        //不同之处在于，是无向图，所以要提前修改 s.path（设置邻接点的时候设置了 path）
        s.path = null;
        heap.add(s);

        while(! heap.isEmpty()){
            Vertex v = heap.remove();

            v.known = true;
            for (Vertex w : v.adjVex.keySet()){
                //如果未处理
                if (! w.known){
                    //得到权
                    int weight = v.adjVex.get(w);
                    //找到更短路径，更新
                    if (weight < w.dist){
                        w.dist = weight;
                        w.path = v;
                        //入堆
                        heap.add(w);
                    }
                }
            }
        }

        printPrim();
    }

    /**
     * 最小生成树 kruskal 算法
     * 贪婪算法的一种
     * 最后结果储存在每个顶点的 path 中（sName 顶点除外）
     *
     * 每个点单独形成一个集合，如果两点之间不连通，则合并集合
     * （使用边 Edge 类实现会很容易）
     * 先把边排序，从最小的开始选择，判断是否可以连接，然后是否合并集合
     */
    public List<Edge> kruskals(String sName){
        if (! isWeight){
            throw new RuntimeException("不是加权图！");
        }
        if (isDirect){
            throw new RuntimeException("不是无向图！");
        }
        //先设置边集（方便排序）
        initEdges();

        //连通集中集（在同一个集合中，则两点连通）
        Set<Set<Vertex>> ds = new HashSet<>(apex.length);
        for (Vertex v : apex){
            Set<Vertex> vset = new HashSet<>();
            vset.add(v);
            ds.add(vset);
        }

        //选择权值最小边
        PriorityQueue<Edge> heap = new PriorityQueue<>(
                Comparator.comparingInt(o -> o.weight));
        heap.addAll(edges);

        List<Edge> result = new ArrayList<>();

        //顶点数 - 1 = 边数
        while(result.size() != apex.length - 1){
            Edge e = heap.remove();
            Set<Vertex> viSet = null;
            Set<Vertex> vjSet = null;
            for (Set<Vertex> vset : ds){
                if (vset.contains(e.vi)){
                    viSet = vset;
                }
                if (vset.contains(e.vj)){
                    vjSet = vset;
                }
            }

            //两点不连通
            if (viSet != vjSet && viSet != null && vjSet != null){
                result.add(e);
                //合并集合
                ds.remove(viSet);
                ds.remove(vjSet);
                viSet.addAll(vjSet);
                ds.add(viSet);
            }
        }

        return result;
    }
    //输出 kruskal 结果
    public void kruskal(String sName){
        List<Edge> edgeList = kruskals(sName);
        for (Edge e : edgeList){
            System.out.print("(" + e.vi.name + "," + e.vj.name + ")\t");
        }
    }


    //输出 prim 最小生成树的所有边
    private void printPrim(){
        for (Vertex v : apex){
            //s.path = null
            if(v.path != null){
                System.out.print("(" + v.name + "," + v.path.name + ")\t");
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

        //计算入度
        v2.indegree ++;

        //设置邻接点与实际路径
        v1.adjVex.put(v2, weight);
        v2.path = v1;
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
    //通过 apex 装填 edges
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

        //需求添加
        public int indegree;           //入度
        public int topNum;             //拓扑编号

        //根据不同的图选择
        public boolean known;          //是否处理过
        public int dist;               //从 s 节点出发的路径长


        //构造器
        public Vertex(String name) {
            this.name = name;
            this.path = null;

            this.adjVex = new HashMap<>();

            this.indegree = 0;
            this.topNum = 0;

            this.known = false;
            this.dist = (int) Double.POSITIVE_INFINITY;
        }

        @Override
        public String toString() {
            StringBuffer result = new StringBuffer();
            result.append("顶点名：");
            result.append(name);
            result.append("\t\t\t");
            result.append("邻接顶点名：");
            for (Vertex v : adjVex.keySet()) {
                result.append(v.name);

                int weight = 0;
                if ((weight = adjVex.get(v)) != 0){
                    result.append(".weight = ");
                    result.append(weight);
                    result.append("\t");
                }else {
                    result.append("\t");
                }
            }
            result.append("\t\t\t");
            if (indegree != 0){
                result.append("路径上上一个顶点名：");
                result.append(path.name);
            }
            return result.toString();
        }
    }
    //边类（保存节点引用 / 权信息）
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
