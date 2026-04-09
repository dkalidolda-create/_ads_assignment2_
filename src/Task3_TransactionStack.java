import java.util.Scanner;


public class Task3_TransactionStack {


    static class MyStack<T> {

        private static class MyNode<E> {
            E data;
            MyNode<E> next;

            MyNode(E data) {
                this.data = data;
            }
        }

        private MyNode<T> top;
        private int size;

        // push – add to top O(1)
        public void push(T newItem) {
            MyNode<T> newNode = new MyNode<>(newItem);
            newNode.next = top;
            top          = newNode;
            size++;
        }


        public T pop() {
            if (top == null) {
                throw new IndexOutOfBoundsException("Stack is empty!");
            }
            T removed = top.data;
            top       = top.next;
            size--;
            return removed;
        }


        public T peek() {
            if (top == null) {
                throw new IndexOutOfBoundsException("Stack is empty!");
            }
            return top.data;
        }

        public boolean isEmpty() { return top == null; }
        public int getSize()     { return size; }


        public void displayAll() {
            if (top == null) {
                System.out.println("No transactions yet.");
                return;
            }
            System.out.println("Transaction History (latest first):");
            MyNode<T> current = top;
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
        MyStack<String> transactionHistory = new MyStack<>();

        boolean running = true;
        while (running) {
            System.out.println("\n--- Task 3: Transaction History (Stack) ---");
            System.out.println("1. Add Deposit transaction");
            System.out.println("2. Add Withdraw transaction");
            System.out.println("3. Add Bill Payment transaction");
            System.out.println("4. Undo last transaction (pop)");
            System.out.println("5. Show last transaction (peek)");
            System.out.println("6. Display all transactions");
            System.out.println("0. Exit");
            System.out.print("Choose: ");
            int choice = Integer.parseInt(scanner.nextLine().trim());

            switch (choice) {
                case 1:
                    System.out.print("Username: ");
                    String depUser = scanner.nextLine();
                    System.out.print("Amount: ");
                    double depAmt = Double.parseDouble(scanner.nextLine());
                    String depTx = "Deposit " + depAmt + " to " + depUser;
                    transactionHistory.push(depTx);
                    System.out.println(depTx);
                    break;

                case 2:
                    System.out.print("Username: ");
                    String wdUser = scanner.nextLine();
                    System.out.print("Amount: ");
                    double wdAmt = Double.parseDouble(scanner.nextLine());
                    String wdTx = "Withdraw " + wdAmt + " from " + wdUser;
                    transactionHistory.push(wdTx);
                    System.out.println(wdTx);
                    break;

                case 3:
                    System.out.print("Bill name: ");
                    String billName = scanner.nextLine();
                    System.out.print("Amount: ");
                    double billAmt = Double.parseDouble(scanner.nextLine());
                    String billTx = "Bill payment " + billAmt + " for " + billName;
                    transactionHistory.push(billTx);
                    System.out.println(billTx);
                    break;

                case 4:
                    if (transactionHistory.isEmpty()) {
                        System.out.println("No transactions to undo.");
                    } else {
                        String removed = transactionHistory.pop();
                        System.out.println("Undo → " + removed + " removed");
                    }
                    break;

                case 5:
                    if (transactionHistory.isEmpty()) {
                        System.out.println("No transactions yet.");
                    } else {
                        System.out.println("Last transaction: " + transactionHistory.peek());
                    }
                    break;

                case 6:
                    transactionHistory.displayAll();
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