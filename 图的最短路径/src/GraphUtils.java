import java.io.BufferedReader;
import java.io.FileReader;

public class GraphUtils {

    /**
     * 根据文件创建无权图
     * @param filePath 文件路径
     */
    public static LinkedGraph createNoDirectGraph(String filePath) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(filePath));

        //得到顶点数和边数
        String vexAndArcNum = br.readLine();
        int vexNum = Integer.parseInt(vexAndArcNum.split(" ")[0]);
        //根据顶点数创建图
        LinkedGraph linkedGraph = new LinkedGraph(vexNum);
        int arcNum = Integer.parseInt(vexAndArcNum.split(" ")[1]);

        //无权图
        linkedGraph.setWeight(false);

        //得到结点名称
        String vexNames = br.readLine();
        String[] vexNamess = vexNames.split(" ");
        //注入图的顶点数组
        for (int i=0;i<vexNum;i++){
            linkedGraph.setIndexApex(i, vexNamess[i]);
        }

        //设置邻接边结点
        String arc;
        int currArcNum = 0;
        while((arc = br.readLine()) != null && currArcNum < arcNum){
            String[] relationship = arc.split(" ");
            for (String relation : relationship){
                String[] r = relation.split("-");
                linkedGraph.findAndSetterVex(r[0], r[1]);
            }

            currArcNum ++;
        }
        return linkedGraph;
    }


    /**
     * 图的文件格式（不加权）：.txt
     *
     * 顶点数 边数
     * 顶点名1 顶点名2 顶点名3   …………
     * 顶点对
     *
     * 顶点对格式：
     *
     * 顶点名1-顶点名3 顶点名2-顶点名5 顶点名4-顶点名2    …………
     *
     * 例如文件 无权图.txt
     */


    /**
     * 根据文件创建加权图
     * @param filePath 文件路径
     */
    public static LinkedGraph createDirectGraph(String filePath) throws Exception{
        BufferedReader br = new BufferedReader(new FileReader(filePath));

        //得到顶点数和边数
        String vexAndArcNum = br.readLine();
        int vexNum = Integer.parseInt(vexAndArcNum.split(" ")[0]);
        //根据顶点数创建图
        LinkedGraph linkedGraph = new LinkedGraph(vexNum);
        int arcNum = Integer.parseInt(vexAndArcNum.split(" ")[1]);

        //加权图
        linkedGraph.setWeight(true);

        //得到结点名称
        String vexNames = br.readLine();
        String[] vexNamess = vexNames.split(" ");
        //注入图的顶点数组
        for (int i=0;i<vexNum;i++){
            linkedGraph.setIndexApex(i, vexNamess[i]);
        }

        //设置邻接边结点（加权）
        String arc;
        int currArcNum = 0;
        while((arc = br.readLine()) != null && currArcNum < arcNum){
            String[] relationship = arc.split(" ");
            for (String relation : relationship){
                String[] r = relation.split("-");
                linkedGraph.findAndSetterVex(r[0], r[1], Integer.parseInt(r[2]));
            }

            currArcNum ++;
        }
        return linkedGraph;
    }

    /**
     * 图的文件格式（加权）：.txt
     *
     * 顶点数 边数
     * 顶点名1 顶点名2 顶点名3   …………
     * 顶点对
     *
     * 顶点对格式：
     *
     * 顶点名1-顶点名3-权 顶点名2-顶点名5-权 顶点名4-顶点名2-权    …………
     *
     * 例如文件 加权图.txt
     */




}
