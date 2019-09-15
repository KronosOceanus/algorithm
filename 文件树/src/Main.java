import java.io.File;

public class Main {

    public static void main(String[] args) {
        FileTree ft = new FileTree(new File("D:\\web"));
        ft.printTree();
    }
}
