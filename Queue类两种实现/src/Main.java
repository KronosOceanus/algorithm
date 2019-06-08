public class Main {

    public static void main(String[] args) {

        Queue<Integer> queue = new Queue<>();
        //
        for (int i=0;i<5;i++){
            queue.enqueue(i);
        }
        queue.clear();
        System.out.println(queue.isEmpty());


        //
        for (int i=0;i<5;i++){
            queue.enqueue(i);
        }
        System.out.println(queue.isEmpty());
        for (int i=0;i<5;i++){
            System.out.print(queue.dequeue() + "\t");
        }
        System.out.println();


        //
        for (int i=0;i<5;i++){
            queue.enqueue(i);
        }
        for (int i=0;i<5;i++){
            System.out.print(queue.dequeue() + "\t");
        }
        System.out.println();
    }
}
