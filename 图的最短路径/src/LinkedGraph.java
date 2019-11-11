import java.util.*;

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


    /**
     * 无权最短路径
     * 队列操作 offer / poll
     */
    public void unweightedMinPath(String sName){
        LinkedList<Vertex> queue = new LinkedList<>();

        Vertex s = findVex(sName);
        s.dist = 0;
        //入队
        queue.offer(s);

        while(! queue.isEmpty()){
            Vertex v = queue.poll();

            for (Vertex w : v.adjVex.keySet()){
                if (w.dist == (int) Double.POSITIVE_INFINITY){
                    w.dist = v.dist + 1;
                    w.path = v;
                    queue.offer(w);
                }
            }
        }

        //输出所有路径
        for (Vertex v : apex){
            //输出路径长度
            System.out.print(v.name + ".dist=" + v.dist + "\t path=");
            printPath(v);
            System.out.println();
        }
    }

    /**
     * 加权最短路径 Dijkstra 算法
     * 需要用到堆操作 add / remove
     *
     * 从某个顶点开始，一边更新 dist，一边根据已知 dist 大小选择下一个要处理的顶点（通过堆的 deleteMin 操作实现选择）
     * 如果处理后的 dist 比前面处理的 dist 小，则更新（更新完之后 insert 入堆，供下一次选择）
     */
    public void dijkstra(String sName){
        PriorityQueue<Vertex> heap = new PriorityQueue<>(Comparator.comparingInt(o -> o.dist));

        Vertex s = findVex(sName);
        s.dist = 0;
        //入堆
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
                    if (v.dist + weight < w.dist){
                        w.dist = v.dist + weight;
                        w.path = v;
                        //入堆
                        heap.add(w);
                    }
                }
            }
        }

        //输出所有路径
        for (Vertex v : apex){
            //输出路径长度
            System.out.print(v.name + ".dist=" + v.dist + "\t path=");
            printPath(v);
            System.out.println();
        }
    }

    //根据某个终点输出某个顶点到该点的路径和路经长
    private void printPath(Vertex v){
        if (v.path != null && v.dist != 0){
            printPath(v.path);
            System.out.print("->");
        }
        System.out.print(v.name);
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
        public boolean known;          //是否处理过
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
