public class Item {

    public int coefficient;
    public int exponent;

    public Item(int coefficient, int exponent){
        this.coefficient = coefficient;
        this.exponent = exponent;
    }

    @Override
    public String toString() {
        return coefficient + "x^" + exponent;
    }
}
