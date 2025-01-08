package bankaccounttest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BankAccountTest {

    @Test
    public void testInitialBalance() {
        BankAccount account = new BankAccount(100.0);
        assertEquals(100.0, account.getBalance(), 0.001);
    }

    @Test
    public void testDepositValidAmount() {
        BankAccount account = new BankAccount(100.0);
        account.deposit(50.0);
        assertEquals(150.0, account.getBalance(), 0.001);
    }

    @Test
    public void testDepositInvalidAmount() {
        BankAccount account = new BankAccount(100.0);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            account.deposit(-20.0);
        });
        assertEquals("Deposit amount must be greater than 0.", exception.getMessage());
    }

    @Test
    public void testWithdrawValidAmount() {
        BankAccount account = new BankAccount(100.0);
        account.withdraw(40.0);
        assertEquals(60.0, account.getBalance(), 0.001);
    }

    @Test
    public void testWithdrawInvalidAmount() {
        BankAccount account = new BankAccount(100.0);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            account.withdraw(-30.0);
        });
        assertEquals("Withdrawal amount must be greater than 0.", exception.getMessage());
    }

    @Test
    public void testWithdrawExceedBalance() {
        BankAccount account = new BankAccount(100.0);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            account.withdraw(150.0);
        });
        assertEquals("Withdrawal amount exceeds the current balance.", exception.getMessage());
    }

    @Test
    public void testMultipleTransactions() {
        BankAccount account = new BankAccount(200.0);
        account.deposit(100.0);    // 200 + 100 = 300
        account.withdraw(50.0);    // 300 - 50 = 250
        account.deposit(25.0);     // 250 + 25 = 275
        account.withdraw(75.0);    // 275 - 75 = 200
        assertEquals(200.0, account.getBalance(), 0.001);
    }

    // Edge case: Deposit amount is 0
    @Test
    public void testDepositZeroAmount() {
        BankAccount account = new BankAccount(100.0);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            account.deposit(0);
        });
        assertEquals("Deposit amount must be greater than 0.", exception.getMessage());
    }

    // Edge case: Withdraw amount is 0
    @Test
    public void testWithdrawZeroAmount() {
        BankAccount account = new BankAccount(100.0);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            account.withdraw(0);
        });
        assertEquals("Withdrawal amount must be greater than 0.", exception.getMessage());
    }

    // Edge case: Withdraw all balance, then deposit and withdraw again
    @Test
    public void testWithdrawAndDepositAfterEmptyBalance() {
        BankAccount account = new BankAccount(100.0);
        account.withdraw(100.0);  // Balance becomes 0
        assertEquals(0.0, account.getBalance(), 0.001);

        account.deposit(50.0);   // Deposit 50
        assertEquals(50.0, account.getBalance(), 0.001);

        account.withdraw(25.0);  // Withdraw 25
        assertEquals(25.0, account.getBalance(), 0.001);
    }

    @Test
    public void testDepositLargeAmount() {
        BankAccount account = new BankAccount(100.0);
        account.deposit(Double.MAX_VALUE - 100.0);
        assertEquals(Double.MAX_VALUE, account.getBalance(), 0.001);
    }
    
    @Test
    public void testWithdrawAllBalanceMultipleTimes() {
        BankAccount account = new BankAccount(500.0);
        for (int i = 0; i < 10; i++) {
            account.withdraw(50.0); // Withdraw 50 multiple times
            account.deposit(50.0);  // Deposit 50 back
        }
        assertEquals(500.0, account.getBalance(), 0.001);
    }
}
