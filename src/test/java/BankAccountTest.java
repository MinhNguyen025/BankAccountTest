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
        assertEquals("Số tiền gửi phải lớn hơn 0.", exception.getMessage());
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
        assertEquals("Số tiền rút phải lớn hơn 0.", exception.getMessage());
    }

    @Test
    public void testWithdrawExceedBalance() {
        BankAccount account = new BankAccount(100.0);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            account.withdraw(150.0);
        });
        assertEquals("Số tiền rút vượt quá số dư hiện tại.", exception.getMessage());
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
}
