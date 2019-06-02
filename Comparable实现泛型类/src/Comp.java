public class Comp implements Comparable {

    private int ID;
    public Comp(int ID){
        this.ID = ID;
    }

    @Override
    public int compareTo(Object o) {
        return Integer.compare(ID, ((Comp) o).ID);
    }

    @Override
    public String toString() {
        return String.valueOf(this.ID);
    }
}
