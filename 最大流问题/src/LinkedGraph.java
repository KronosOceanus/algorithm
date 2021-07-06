import java.util.*;

/**
 * 与 GraphUtils 搭配使用（耦合严重……）
 */
public class LinkedGraph {

    //图 G（加权有向图，起点 s，终点 t），同时也作为残余图
    private Vertex[] G;
    //流图
    private Vertex[] Gf;
    //是否加权
    private boolean isWeight;
    //是否有向
    private boolean isDirect;

    //根据节点数创建图
    public LinkedGraph(int vexNum){
        G = new Vertex[vexNum];
        Gf = new Vertex[vexNum];
    }
    //输出图的信息
    public void printGraph(){
        System.out.println("图G：==========================");
        for (Vertex v : G){
            System.out.println(v);
        }
        System.out.println("图Gf：=========================");
        for (Vertex v : Gf){
            System.out.println(v);
        }
    }

    /**
     * 重点！！！！！！
     * 最大流算法
     */
    public int maxFlow(String sName, String tName){
        int result = 0;
        Vertex s = findVex(sName, false);
        s.path = null;      //起始点 path 必须为 null
        Vertex t = findVex(tName, false);
        while (true){
            List<Vertex> pathList = findAPath(s, t);       //寻找增广路径
            if (pathList == null){     //不存在增广路径，返回结果
                break;
            }
            System.out.print("增广路径：   ");
            for (Vertex v : pathList){
                System.out.print(v.name + "\t");
            }
            System.out.println();
            result += changeAndGet(pathList);      //改变两个图
        }
        return result;
    }
    //驱动程序                               起点，终点
    private LinkedList<Vertex> findAPath(Vertex s, Vertex t){
        LinkedList<Vertex> result = new LinkedList<>();
        result.add(s);
        return dfs(s, t, result) ? result : null;
    }
    //从残余图中寻找一条增广路径（回溯）
    private boolean dfs(Vertex v, Vertex t, LinkedList<Vertex> result){
        if (v == t){
            return true;    //找到增广路径
        }
        //标记
        v.known = true;
        //遍历邻接点
        for (Vertex w : v.adjVex.keySet()){
            if (! w.known){
                result.add(w);
                //是否寻找成功
                boolean flag = dfs(w, t, result);
                if (flag){
                    //记录路径
                    w.path = v;
                    w.known = false;
                    return true;
                }else {
                    //未成功，回溯，开始下一轮寻找
                    result.remove(w);
                }
            }
        }

        return false;
    }
    //根据增广路径改变 G 和 Gf，并得到本次流量
    private int changeAndGet(List<Vertex> l){
        int result = getFlow(l);
        //先改变 G（反向）
        for (Vertex v : l){
            if (v.path != null){
                int x = v.path.adjVex.get(v);   //正向边
                if (x == result){       //删除原来的边
                    v.path.adjVex.remove(v);
                    v.adjVex.put(v.path, result);
                }else {         //部分反向
                    int y = x - result;
                    v.path.adjVex.put(v, y);
                    v.adjVex.put(v.path, result);
                }
            }
        }
        //改变图 Gf
        for (Vertex v : l){
            if (v.path != null){
                Vertex x = findVex(v.path.name, true);
                Vertex y = findVex(v.name, true);
                x.adjVex.put(y, result);        //设置 Gf 中的流
            }
        }
        return result;
    }
    //得到一次最大流量
    private int getFlow(List<Vertex> l){
        int result = Integer.MAX_VALUE;
        for (Vertex v : l){
            if (v.path != null){
                int k = v.path.adjVex.get(v);
                if (v.path.adjVex.get(v) < result){
                    result = k;
                }
            }
        }
        return result;
    }







    // GraphUtils 使用的方法
    //设置 G / Gf[index] 处顶点名为 name
    public void setIndexApex(int index, String name) {
        G[index] = new Vertex(name);
        Gf[index] = new Vertex(name);
    }
    //设置 name 顶点的邻接顶点 adjName
    public void findAndSetterVex(String name, String adjName){
        findAndSetterVex(name, adjName, 0);
    }
    //设置 G / Gf（权为 0） 加权邻接点
    public void findAndSetterVex(String name, String adjName, Integer weight){
        Vertex v1 = findVex(name, false);
        Vertex v2 = findVex(adjName, false);

        //计算入度
        v2.indegree ++;

        //设置邻接点与实际路径
        v1.adjVex.put(v2, weight);
        v2.path = v1;


        //图 Gf
        Vertex v11 = findVex(name, true);
        Vertex v22 = findVex(adjName, true);

        v22.indegree ++;

        v11.adjVex.put(v22, 0);
        v22.path = v11;
    }
    // G / Gf（bool 判断） 中查找名为 name 的顶点并返回
    private Vertex findVex(String name, boolean isf){
        Vertex vex = null;
        for (Vertex v : isf ? Gf : G){
            if (v.name.equals(name)){
                vex = v;
            }
        }
        return vex;
    }


    //节点类
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
            if (indegree != 0 && path != null){
                result.append("路径上上一个顶点名：");
                result.append(path.name);
            }
            return result.toString();
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
