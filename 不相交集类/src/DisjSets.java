/**
 * 数组下标（0 开始），同时也代表元素值
 * 数组内容代表父亲节点
 */
public class DisjSets {

    private int[] s;

    public DisjSets(int numElements){
        s = new int[numElements];
        for (int i=0;i<s.length;i++){
            s[i] = -1;
        }
    }
    //在树根合并
    public void union(int root1, int root2){
        s[root2] = root1;
    }
    //按大小（负值）合并
    public void unionBySize(int root1, int root2){
        if (s[root2] < s[root1]){   // root2 更大
            s[root2] += s[root1];
            s[root1] = root2;   //更新大小
        }else {
            s[root1] += s[root2];
            s[root2] = root1;
        }
    }
    //按高度合并
    public void unionByHeight(int root1, int root2){
        if (s[root2] < s[root1]){
            s[root1] = root2;
        }else {
            if (s[root1] == s[root2]){
                s[root1] --;    //更新高度（高度 +1）
            }
            s[root2] = root1;
        }
    }

    //递归查找 x 所在树的树根
    public int find(int x){
        if (s[x] < 0){
            return x;
        }else {
            return find(s[x]);
        }
    }
    //路径压缩（与按大小合并兼容/与按高度合并不兼容）
    public int findPathZip(int x){
        if (s[x] < 0){
            return x;
        }else {
            return s[x] = find(s[x]);
        }
    }


    public int get(int pos){
        return s[pos];
    }
    public void show(){
        for(Integer i : s){
            System.out.println(i);
        }
    }
}
