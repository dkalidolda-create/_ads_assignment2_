import java.util.Scanner;


public class Task5_AccountOpeningQueue {


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

        public boolean isEmpty() { return head == null; }
        public int getSize()     { return size; }

        public void displayAll() {
            if (head == null) {
                System.out.println("No pending requests.");
                return;
            }
            System.out.println("Pending Account Requests:");
            MyNode<T> current = head;
            int index = 1;
            while (current != null) {
                System.out.println(index + ". " + current.data);
                current = current.next;
                index++;
            }
        }
    }


    private MyQueue<BankAccount>      accountRequests = new MyQueue<>();
    private Task1_BankLinkedList      approvedAccounts = new Task1_BankLinkedList();


    public void submitRequest(BankAccount account) {
        accountRequests.enqueue(account);
        System.out.println("Request submitted for: " + account.username);
    }


    public void processNextRequest() {
        if (accountRequests.isEmpty()) {
            System.out.println("No pending requests.");
            return;
        }
        BankAccount approved = accountRequests.dequeue();
        approvedAccounts.add(approved);
        System.out.println("Account approved and created for: " + approved.username);
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Task5_AccountOpeningQueue task = new Task5_AccountOpeningQueue();

        boolean running = true;
        while (running) {
            System.out.println("\n--- Task 5: Account Opening Queue ---");
            System.out.println("--- USER ---");
            System.out.println("1. Submit account opening request");
            System.out.println("--- ADMIN ---");
            System.out.println("2. Process next request");
            System.out.println("3. View pending requests");
            System.out.println("4. View approved accounts");
            System.out.println("0. Exit");
            System.out.print("Choose: ");
            int choice = Integer.parseInt(scanner.nextLine().trim());

            switch (choice) {
                case 1:
                    System.out.print("Account number: ");
                    String accNum = scanner.nextLine();
                    System.out.print("Username: ");
                    String uname = scanner.nextLine();
                    System.out.print("Initial balance: ");
                    double bal = Double.parseDouble(scanner.nextLine());
                    task.submitRequest(new BankAccount(accNum, uname, bal));
                    break;

                case 2:
                    task.processNextRequest();
                    break;

                case 3:
                    task.accountRequests.displayAll();
                    break;

                case 4:
                    task.approvedAccounts.displayAll();
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