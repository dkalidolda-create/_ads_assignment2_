import java.util.Scanner;


public class Main {


    private static Task1_BankLinkedList accounts         = new Task1_BankLinkedList();
    private static Task3_TransactionStack.MyStack<String> history = new Task3_TransactionStack.MyStack<>();
    private static Task4_BillQueue.MyQueue<String>        billQueue = new Task4_BillQueue.MyQueue<>();
    private static Task5_AccountOpeningQueue.MyQueue<BankAccount> accountRequests
            = new Task5_AccountOpeningQueue.MyQueue<>();

    private static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {

        // preload some accounts so the app is usable from the start
        accounts.add(new BankAccount("001", "Ali",  150000));
        accounts.add(new BankAccount("002", "Sara", 220000));

        boolean running = true;
        while (running) {
            System.out.println("\n=============================");
            System.out.println("   MINI BANKING SYSTEM");
            System.out.println("=============================");
            System.out.println("1 -- Enter Bank");
            System.out.println("2 -- Enter ATM");
            System.out.println("3 -- Admin Area");
            System.out.println("4 -- Exit");
            System.out.print("Choose: ");

            int choice = readInt();

            switch (choice) {
                case 1: bankMenu();  break;
                case 2: atmMenu();   break;
                case 3: adminMenu(); break;
                case 4:
                    System.out.println("Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
        scanner.close();
    }


    private static void bankMenu() {
        boolean running = true;
        while (running) {
            System.out.println("\n--- BANK MENU ---");
            System.out.println("1. Submit account opening request");
            System.out.println("2. Deposit money");
            System.out.println("3. Withdraw money");
            System.out.println("4. View transaction history");
            System.out.println("0. Back");
            System.out.print("Choose: ");
            int choice = readInt();

            switch (choice) {
                case 1:
                    // Task 5: submit to queue
                    System.out.print("Account number: ");
                    String accNum = scanner.nextLine();
                    System.out.print("Username: ");
                    String uname = scanner.nextLine();
                    System.out.print("Initial balance: ");
                    double initBal = Double.parseDouble(scanner.nextLine());
                    accountRequests.enqueue(new BankAccount(accNum, uname, initBal));
                    System.out.println("Request submitted for: " + uname);
                    break;

                case 2:
                    // Task 2 + Task 3: deposit + record in history
                    System.out.print("Username: ");
                    String depUser = scanner.nextLine();
                    System.out.print("Amount: ");
                    double depAmt = Double.parseDouble(scanner.nextLine());
                    BankAccount depAcc = accounts.searchByUsername(depUser);
                    if (depAcc == null) {
                        System.out.println("Account not found.");
                    } else {
                        depAcc.balance += depAmt;
                        String tx = "Deposit " + depAmt + " to " + depUser;
                        history.push(tx);
                        System.out.println(tx);
                        System.out.println("New balance: " + depAcc.balance);
                    }
                    break;

                case 3:
                    // Task 2 + Task 3: withdraw + record in history
                    System.out.print("Username: ");
                    String wdUser = scanner.nextLine();
                    System.out.print("Amount: ");
                    double wdAmt = Double.parseDouble(scanner.nextLine());
                    BankAccount wdAcc = accounts.searchByUsername(wdUser);
                    if (wdAcc == null) {
                        System.out.println("Account not found.");
                    } else if (wdAcc.balance < wdAmt) {
                        System.out.println("Insufficient funds. Balance: " + wdAcc.balance);
                    } else {
                        wdAcc.balance -= wdAmt;
                        String tx = "Withdraw " + wdAmt + " from " + wdUser;
                        history.push(tx);
                        System.out.println(tx);
                        System.out.println("New balance: " + wdAcc.balance);
                    }
                    break;

                case 4:
                    // Task 3: show stack
                    history.displayAll();
                    break;

                case 0:
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }


    private static void atmMenu() {
        boolean running = true;
        while (running) {
            System.out.println("\n--- ATM MENU ---");
            System.out.println("1. Balance enquiry");
            System.out.println("2. Withdraw");
            System.out.println("0. Back");
            System.out.print("Choose: ");
            int choice = readInt();

            switch (choice) {
                case 1:
                    System.out.print("Username: ");
                    String user = scanner.nextLine();
                    BankAccount acc = accounts.searchByUsername(user);
                    if (acc == null) {
                        System.out.println("Account not found.");
                    } else {
                        System.out.println("Balance: " + acc.balance);
                    }
                    break;

                case 2:
                    System.out.print("Username: ");
                    String atmUser = scanner.nextLine();
                    System.out.print("Amount: ");
                    double atmAmt = Double.parseDouble(scanner.nextLine());
                    BankAccount atmAcc = accounts.searchByUsername(atmUser);
                    if (atmAcc == null) {
                        System.out.println("Account not found.");
                    } else if (atmAcc.balance < atmAmt) {
                        System.out.println("Insufficient funds. Balance: " + atmAcc.balance);
                    } else {
                        atmAcc.balance -= atmAmt;
                        String tx = "ATM Withdraw " + atmAmt + " from " + atmUser;
                        history.push(tx);
                        System.out.println("Dispensing: " + atmAmt);
                        System.out.println("Remaining balance: " + atmAcc.balance);
                    }
                    break;

                case 0:
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }


    private static void adminMenu() {
        boolean running = true;
        while (running) {
            System.out.println("\n--- ADMIN MENU ---");
            System.out.println("1. View pending account requests");
            System.out.println("2. Process next account request");
            System.out.println("3. View all approved accounts");
            System.out.println("4. Add bill payment to queue");
            System.out.println("5. Process next bill payment");
            System.out.println("6. View bill payment queue");
            System.out.println("7. Undo last transaction");
            System.out.println("0. Back");
            System.out.print("Choose: ");
            int choice = readInt();

            switch (choice) {
                case 1:
                    // Task 5: display pending requests
                    accountRequests.displayAll();
                    break;

                case 2:
                    // Task 5: process one request → move to LinkedList
                    if (accountRequests.isEmpty()) {
                        System.out.println("No pending requests.");
                    } else {
                        BankAccount approved = accountRequests.dequeue();
                        accounts.add(approved);
                        System.out.println("Account approved and created for: " + approved.username);
                    }
                    break;

                case 3:
                    // Task 1: display all approved accounts
                    accounts.displayAll();
                    break;

                case 4:
                    // Task 4: add bill to queue
                    System.out.print("Bill name: ");
                    String bill = scanner.nextLine();
                    billQueue.enqueue(bill);
                    System.out.println("Added: " + bill);
                    break;

                case 5:
                    // Task 4: process bill
                    if (billQueue.isEmpty()) {
                        System.out.println("No bills in queue.");
                    } else {
                        String processed = billQueue.dequeue();
                        System.out.println("Processing: " + processed);
                    }
                    break;

                case 6:
                    // Task 4: view queue
                    billQueue.displayAll();
                    break;

                case 7:
                    // Task 3: undo last transaction
                    if (history.isEmpty()) {
                        System.out.println("No transactions to undo.");
                    } else {
                        System.out.println("Undo → " + history.pop() + " removed");
                    }
                    break;

                case 0:
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }


    private static int readInt() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}