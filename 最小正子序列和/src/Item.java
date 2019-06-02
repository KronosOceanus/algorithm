import java.util.Comparator;

/**
 * 用来保存前index项的合
 */
public class Item implements Comparator<Item> {

    int index;
    int value;

    @Override
    public int compare(Item o1, Item o2) {
        return Integer.compare(o1.value,o2.value);
    }

}
