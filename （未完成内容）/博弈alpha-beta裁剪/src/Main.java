/**
 * 三连棋
 */
public class Main {

    private static final int DRAW = 0;
    private static final int COMP_LOSS = Integer.MIN_VALUE;
    private static final int COMP_WIN = Integer.MAX_VALUE;
    private static final int SIZE = 10;
    private static final int COMP = 1;
    private static final int HUMAN = 2;
    //下标 1-9 是棋盘
    private static int[] p = new int[SIZE];
    private static int hasPlace = 0;

    public static MoveInfo findCompMove(int alpha, int beta){
        int responseValue;
        int value, bestMove = 1;
        MoveInfo quickWinInfo;

        if (hasPlace == SIZE - 1){
            value = DRAW;
        }else if ((quickWinInfo = immdiateWin(COMP)) != null){
            return quickWinInfo;
        }else {
            value = alpha;
            for (int i=1; i<SIZE && value < beta; i++){
                if (p[i] == 0){
                    place(i, COMP);
                    responseValue = findHumanMove(value, beta).value;
                    unplace(i);

                    if (responseValue > value){
                        value = responseValue;
                        bestMove = i;
                    }
                }
            }
        }
        return new MoveInfo(bestMove, value);
    }
    public static MoveInfo findHumanMove(int alpha, int beta){
        int responseValue;
        int value, bestMove = 1;
        MoveInfo quickWinInfo;

        if (hasPlace == SIZE - 1){
            value = DRAW;
        }else if ((quickWinInfo = immdiateWin(HUMAN)) != null){
            return quickWinInfo;
        }else {
            value = beta;
            for (int i=1; i<SIZE && alpha < value; i++){
                if (p[i] == 0){
                    place(i, HUMAN);
                    responseValue = findCompMove(alpha, value).value;
                    unplace(i);

                    if (responseValue < value){
                        value = responseValue;
                        bestMove = i;
                    }
                }
            }
        }
        return new MoveInfo(bestMove, value);
    }

    //判断某方是否能赢
    private static MoveInfo immdiateWin(int vary){
        return null;
    }
    private static void place(int i, int vary){
        p[i] = vary;
        hasPlace ++;
    }
    private static void unplace(int i){
        p[i] = 0;
        hasPlace --;
    }

    public static void main(String[] args) {
        for (int i=0;i<4;i++){
            MoveInfo m = findCompMove(COMP_LOSS, COMP_WIN);
            place(m.move, COMP);
            MoveInfo n = findHumanMove(COMP_LOSS, COMP_WIN);
            place(n.move, HUMAN);
            System.out.println(m.move);
            System.out.println(n.move);
        }
    }
}
