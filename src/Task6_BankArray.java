public class Task6_BankArray {

    public static void main(String[] args) {


        BankAccount[] bankAccounts = new BankAccount[3];


        bankAccounts[0] = new BankAccount("001", "Ali",   150000);
        bankAccounts[1] = new BankAccount("002", "Sara",  220000);
        bankAccounts[2] = new BankAccount("003", "Aibek", 310000);


        System.out.println("Bank Accounts (Array):");
        for (int i = 0; i < bankAccounts.length; i++) {
            System.out.println((i + 1) + ". " + bankAccounts[i]);
        }
    }
}
