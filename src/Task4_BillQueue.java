import java.util.Scanner;


public class Task4_BillQueue {


    static class MyQueue<T> {

        private static class MyNode<E> {
            E data;
            MyNode<E> next;

            MyNode(E data) {
                this.data = data;
            }
        }

        private MyNode<T> head;
        private MyNode<T> tail;
        private int size;


        public void enqueue(T newItem) {
            MyNode<T> newNode = new MyNode<>(newItem);
            if (head == null) {
                head = tail = newNode;
            } else {
                tail.next = newNode;
                tail      = newNode;
            }
            size++;
        }


        public T dequeue() {
            if (head == null) {
                throw new IndexOutOfBoundsException("Queue is empty!");
            }
            T removed = head.data;
            if (head == tail) {
                head = tail = null;
            } else {
                head = head.next;
            }
            size--;
            return removed;
        }


        public T peek() {
            if (head == null) {
                throw new IndexOutOfBoundsException("Queue is empty!");
            }
            return head.data;
        }

        public boolean isEmpty() { return head == null; }
        public int getSize()     { return size; }


        public void displayAll() {
            if (head == null) {
                System.out.println("Queue is empty.");
                return;
            }
            System.out.println("Bill Queue (front → back):");
            MyNode<T> current = head;
            int index = 1;
            while (current != null) {
                System.out.println(index + ". " + current.data);
                current = current.next;
                index++;
            }
        }
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MyQueue<String> billQueue = new MyQueue<>();

        boolean running = true;
        while (running) {
            System.out.println("\n--- Task 4: Bill Payment Queue ---");
            System.out.println("1. Add bill payment request");
            System.out.println("2. Process next bill (dequeue)");
            System.out.println("3. Show next bill (peek)");
            System.out.println("4. Display all pending bills");
            System.out.println("0. Exit");
            System.out.print("Choose: ");
            int choice = Integer.parseInt(scanner.nextLine().trim());

            switch (choice) {
                case 1:
                    System.out.print("Bill name: ");
                    String bill = scanner.nextLine();
                    billQueue.enqueue(bill);
                    System.out.println("Added: " + bill);
                    break;

                case 2:
                    if (billQueue.isEmpty()) {
                        System.out.println("No bills in queue.");
                    } else {
                        String processed = billQueue.dequeue();
                        System.out.println("Processing: " + processed);
                        if (!billQueue.isEmpty()) {
                            System.out.println("Remaining: " + billQueue.peek());
                        } else {
                            System.out.println("Queue is now empty.");
                        }
                    }
                    break;

                case 3:
                    if (billQueue.isEmpty()) {
                        System.out.println("No bills in queue.");
                    } else {
                        System.out.println("Next bill: " + billQueue.peek());
                    }
                    break;

                case 4:
                    billQueue.displayAll();
                    break;

                case 0:
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        }
        scanner.close();
    }
}
