import java.util.Scanner;


public class Task2_DepositWithdraw {


    private Task1_BankLinkedList accounts = new Task1_BankLinkedList();


    public void deposit(String username, double amount) {
        BankAccount acc = accounts.searchByUsername(username);
        if (acc == null) {
            System.out.println("Account not found.");
            return;
        }
        acc.balance += amount;
        System.out.println("Deposit: " + amount);
        System.out.println("New balance: " + acc.balance);
    }


    public void withdraw(String username, double amount) {
        BankAccount acc = accounts.searchByUsername(username);
        if (acc == null) {
            System.out.println("Account not found.");
            return;
        }
        if (acc.balance < amount) {
            System.out.println("Insufficient funds. Balance: " + acc.balance);
            return;
        }
        acc.balance -= amount;
        System.out.println("Withdraw: " + amount);
        System.out.println("New balance: " + acc.balance);
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Task2_DepositWithdraw task = new Task2_DepositWithdraw();

        // pre-load two accounts so demo works immediately
        task.accounts.add(new BankAccount("001", "Ali",  150000));
        task.accounts.add(new BankAccount("002", "Sara", 220000));

        boolean running = true;
        while (running) {
            System.out.println("\n--- Task 2: Deposit & Withdraw ---");
            System.out.println("1. Add account");
            System.out.println("2. Display all accounts");
            System.out.println("3. Deposit");
            System.out.println("4. Withdraw");
            System.out.println("0. Exit");
            System.out.print("Choose: ");
            int choice = Integer.parseInt(scanner.nextLine().trim());

            switch (choice) {
                case 1:
                    System.out.print("Account number: ");
                    String accNum = scanner.nextLine();
                    System.out.print("Username: ");
                    String uname = scanner.nextLine();
                    System.out.print("Balance: ");
                    double bal = Double.parseDouble(scanner.nextLine());
                    task.accounts.add(new BankAccount(accNum, uname, bal));
                    System.out.println("Account added successfully");
                    break;

                case 2:
                    task.accounts.displayAll();
                    break;

                case 3:
                    System.out.print("Enter username: ");
                    String depUser = scanner.nextLine();
                    System.out.print("Deposit amount: ");
                    double depAmt = Double.parseDouble(scanner.nextLine());
                    task.deposit(depUser, depAmt);
                    break;

                case 4:
                    System.out.print("Enter username: ");
                    String wdUser = scanner.nextLine();
                    System.out.print("Withdraw amount: ");
                    double wdAmt = Double.parseDouble(scanner.nextLine());
                    task.withdraw(wdUser, wdAmt);
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