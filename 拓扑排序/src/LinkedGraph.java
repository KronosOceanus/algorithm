import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * 与 GraphUtils 搭配使用（耦合严重……）
 */
public class LinkedGraph {

    //顶点数组
    private Vertex[] apex;
    //是否加权
    private boolean isWeight;

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
    //拓扑排序
    public void topSort(){
        LinkedList<Vertex> queue = new LinkedList<>();
        int counter = 0;    //拓扑编号记录

        //入度为 0 的顶点全部入队
        for (Vertex v : apex){
            if (v.indegree == 0){
                queue.offer(v);
            }
        }

        while (! queue.isEmpty()){
            Vertex v = queue.pollFirst();
            System.out.println(v);
            v.topNum = ++ counter;

            //去掉 v 之后入度为 0 的顶点入队
            for (Vertex w : v.adjVex.keySet()){
                if (-- w.indegree == 0){
                    queue.offer(w);
                }
            }
        }

        if (counter != apex.length){
            throw new RuntimeException("发现环！！！");
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
        public boolean known;           //是否被处理过
        public int dist;               //从 s 节点出发的路径长


        //构造器
        public Vertex(String name) {
            this(name, false);
        }
        //加权构造器
        public Vertex(String name, boolean isWeight) {
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





    // setter + getter
    public boolean isWeight() {
        return isWeight;
    }
    public void setWeight(boolean weight) {
        isWeight = weight;
    }

}
