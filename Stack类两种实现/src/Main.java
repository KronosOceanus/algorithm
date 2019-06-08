public class Main {

    public static void main(String[] args) {

        MyStack_List<Integer> stack = new MyStack_List<>();
        for (int i=0;i<10;i++){
            stack.push(i);
        }
        System.out.println(stack.size());
        for (int i=0;i<10;i++){
            System.out.print(stack.top() + "……");
            System.out.print(stack.pop() + "\t");
        }
        System.out.println();
        System.out.println(stack.size());
    }
}
