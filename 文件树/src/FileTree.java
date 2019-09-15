import java.io.File;

public class FileTree {

    private Node root;

    private static class Node{
        //保存一个文件/目录
        public File element;
        //同级右边，下一级下边
        public Node right;
        public Node below;

        public Node(File x){
            this.element = x;
            this.right = this.below = null;
        }
    }

    public FileTree(File file){
        root = new Node(file);
        findAndConnect(root);
    }

    //找到 文件夹/文件 子树并连接起来
    private void findAndConnect(Node root){
        String path = root.element.getPath();
        //如果是目录，则连接
        if (root.element.isDirectory()){
            boolean flag = true;
            //中介结点，类似于 p1,p2
            Node connectNode = null;
            for (String f : root.element.list()){
                //第一次连接到文件夹下方
                if (flag){
                    connectNode = root.below = new Node(new File(path + "\\" + f));
                    flag = false;
                }
                //同级文件都连接到右边
                else {
                    connectNode = connectNode.right = new Node(new File(path + "\\" + f));
                }
                //如果是目录，迭代连接
                if (connectNode.element.isDirectory()){
                    findAndConnect(connectNode);
                }
            }
        }
    }

    public void printTree(){
        printT(root);
    }

    //迭代输出文件树最底层所有文件
    private void printT(Node node){
        if (node.below == null){
            System.out.println(node.element.getPath());
        }else {
            printT(node.below);
        }

        if (node.right != null){
            printT(node.right);
        }
    }
}
