package bankaccounttest;

public class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        if (initialBalance < 0) {
            throw new IllegalArgumentException("Số dư ban đầu không được âm.");
        }
        this.balance = initialBalance;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Số tiền gửi phải lớn hơn 0.");
        }
        balance += amount;
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Số tiền rút phải lớn hơn 0.");
        }
        if (amount > balance) {
            throw new IllegalArgumentException("Số tiền rút vượt quá số dư hiện tại.");
        }
        balance -= amount;
    }

    public double getBalance() {
        return balance;
    }
}
