import java.util.Iterator;
import java.util.Scanner;


public class Task1_BankLinkedList implements Iterable<BankAccount> {


    private static class MyNode<E> {
        E data;
        MyNode<E> next;

        MyNode(E data) {
            this.data = data;
            // next = null; // redundant
        }
    }


    private MyNode<BankAccount> head; // entry point
    private MyNode<BankAccount> tail; // last node
    private int size;


    public void add(BankAccount newItem) {
        MyNode<BankAccount> newNode = new MyNode<>(newItem);
        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail      = newNode;
        }
        size++;
    }


    public void displayAll() {
        if (head == null) {
            System.out.println("No accounts found.");
            return;
        }
        System.out.println("Accounts List:");
        MyNode<BankAccount> current = head;
        int index = 1;
        while (current != null) {
            System.out.println(index + ". " + current.data.username
                    + " -- Balance: " + current.data.balance);
            current = current.next;
            index++;
        }
    }


    public BankAccount searchByUsername(String username) {
        MyNode<BankAccount> current = head;
        while (current != null) {
            if (current.data.username.equalsIgnoreCase(username)) {
                return current.data;
            }
            current = current.next;
        }
        return null;
    }

    public int getSize() { return size; }


    @Override
    public Iterator<BankAccount> iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<BankAccount> {
        MyNode<BankAccount> cursor = head;

        @Override
        public boolean hasNext() { return cursor != null; }

        @Override
        public BankAccount next() {
            BankAccount nextItem = cursor.data;
            cursor = cursor.next;
            return nextItem;
        }
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Task1_BankLinkedList list = new Task1_BankLinkedList();

        boolean running = true;
        while (running) {
            System.out.println("\n--- Task 1: Account Storage ---");
            System.out.println("1. Add account");
            System.out.println("2. Display all accounts");
            System.out.println("3. Search by username");
            System.out.println("0. Exit");
            System.out.print("Choose: ");
            int choice = Integer.parseInt(scanner.nextLine().trim());

            switch (choice) {
                case 1:
                    System.out.print("Account number: ");
                    String accNum = scanner.nextLine();
                    System.out.print("Username: ");
                    String username = scanner.nextLine();
                    System.out.print("Balance: ");
                    double balance = Double.parseDouble(scanner.nextLine());
                    list.add(new BankAccount(accNum, username, balance));
                    System.out.println("Account added successfully");
                    break;

                case 2:
                    list.displayAll();
                    break;

                case 3:
                    System.out.print("Enter username to search: ");
                    String name = scanner.nextLine();
                    BankAccount found = list.searchByUsername(name);
                    if (found != null) {
                        System.out.println("Found: " + found);
                    } else {
                        System.out.println("Account not found.");
                    }
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