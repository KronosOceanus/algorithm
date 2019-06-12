public class OrderedCollection {

    private Comparable[] comparables;
    private int size;
    public OrderedCollection(){
         comparables = new Comparable[50];
         size = 0;
    }

    //设置底层数组长度
    private void setComparablesLength(int length){
        //新建一个数字
        Comparable[] cs = new Comparable[length];
        //元素复制
        for (int i=0;i<size;i++){
            cs[i] = comparables[i];
        }
        comparables = cs;
    }
    //集合的大小
    public int size(){
        return this.size;
    }
    //判断是否为空
    public boolean isEmpty(){
        return size == 0;
    }
    //清空集合
    public void makeEmpty(){
        for (int i=0;i<size;i++){
            comparables[i] = null;
        }
        size = 0;
    }
    //插入数据
    public Comparable insert(Comparable c){
        //扩充集合
        if (size  == comparables.length){
            setComparablesLength(size + 1);
        }
        comparables[size] = c;
        size ++;
        return c;
    }
    //删除数据
    public Comparable remove(Comparable c){
        for (int i=0;i<size;i++){
            if (c.compareTo(comparables[i]) == 0){
                comparables[i] = null;
                //集合整体向前移动
                for (int j=i;j<size;j++){
                    comparables[j] = comparables[j+1];
                }
                comparables[size] = null;
                size--;
            }
        }
        return c;
    }
    //找最大值
    public Comparable finMax(){
        Comparable max = comparables[0];
        for (int i=0;i<size;i++){
            if (max.compareTo(comparables[i]) < 0){
                max = comparables[i];
            }
        }
        return max;
    }
    //输出集合
    public String toString(){
        StringBuffer sb = new StringBuffer("");
        for (int i=0;i<size;i++){
            sb.append(comparables[i].toString()).append(",");
        }
        sb.delete(sb.length() - 1,sb.length());
        return sb.toString();
    }
}
