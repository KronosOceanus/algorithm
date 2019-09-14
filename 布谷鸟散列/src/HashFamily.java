/**
 * 储存散列函数组
 */
public interface HashFamily<T> {

    //使用第 i 个散列函数
    int hash(T x, int which);
    //得到散列函数数量
    int gerNumberOfFunctions();
    //更换一组散列函数
    void generateNewFunctions();
}
