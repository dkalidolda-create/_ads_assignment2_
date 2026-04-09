public class BankAccount {
    String accountNumber;
    String username;
    double balance;

    BankAccount(String accountNumber, String username, double balance) {
        this.accountNumber = accountNumber;
        this.username      = username;
        this.balance       = balance;
    }

    @Override
    public String toString() {
        return accountNumber + " | " + username + " -- Balance: " + balance;
    }
}
