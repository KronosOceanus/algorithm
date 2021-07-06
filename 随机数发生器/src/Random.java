public class Random {

    private static final int A = 48271;
    private static final int M = 2147483647;
    private static final int Q = M / A;
    private static final int R = M % A;

    private int state;
    public Random(){
        state = (int)(System.currentTimeMillis()) % Integer.MAX_VALUE;
    }

    //不溢出的随机数发生器
    public int randomInt(){
        int tmpState = A * ( state % Q ) - R * ( state / Q );
        state = tmpState >= 0 ? tmpState : tmpState + M;
        return state;
    }
}
