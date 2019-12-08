import java.io.BufferedReader;
import java.io.FileReader;

public class GraphUtils {

    /**
     * 根据文件创建图
     * @param filePath 图文件
     * @param isWeight 是否加权
     * @param isDirect 是否有向
     */
    public static LinkedGraph createGraph(String filePath, boolean isWeight, boolean isDirect) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(filePath));

        //得到顶点数和边数
        String vexAndArcNum = br.readLine();
        int vexNum = Integer.parseInt(vexAndArcNum.split(" ")[0]);
        //根据顶点数创建图
        LinkedGraph linkedGraph = new LinkedGraph(vexNum);
        int arcNum = Integer.parseInt(vexAndArcNum.split(" ")[1]);

        //注入
        linkedGraph.setVexNum(vexNum);
        linkedGraph.setArcNum(arcNum);
        linkedGraph.setWeight(isWeight);
        linkedGraph.setDirect(isDirect);

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
            //加权图
            if (isWeight){
                for (String relation : relationship){
                    String[] r = relation.split("-");
                    linkedGraph.findAndSetterVex(r[0], r[1], Integer.parseInt(r[2]));
                    //无向图
                    if (! isDirect){
                        linkedGraph.findAndSetterVex(r[1], r[0], Integer.parseInt(r[2]));
                    }
                }
            }else {
                for (String relation : relationship) {
                    String[] r = relation.split("-");
                    linkedGraph.findAndSetterVex(r[0], r[1]);
                    //无向图
                    if (! isDirect){
                        linkedGraph.findAndSetterVex(r[1], r[0]);
                    }
                }
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
