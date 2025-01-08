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

    @Test
    public void testDepositMinValue() {
        BankAccount account = new BankAccount(100.0);
        account.deposit(Double.MIN_VALUE); // Gần bằng 0
        assertEquals(100.0 + Double.MIN_VALUE, account.getBalance(), 0.001);
    }
    
    @Test
    public void testWithdrawMinValue() {
        BankAccount account = new BankAccount(100.0);
        account.withdraw(Double.MIN_VALUE); // Gần bằng 0
        assertEquals(100.0 - Double.MIN_VALUE, account.getBalance(), 0.001);
    }
    
    @Test
    public void testDepositMaxValue() {
        BankAccount account = new BankAccount(Double.MAX_VALUE / 2);
        account.deposit(Double.MAX_VALUE / 2);
        assertEquals(Double.MAX_VALUE, account.getBalance(), 0.001);
    }

    @Test
    public void testConcurrentDeposits() throws InterruptedException {
        BankAccount account = new BankAccount(100.0);
        Runnable depositTask = () -> {
            for (int i = 0; i < 1000; i++) {
                account.deposit(1.0);
            }
        };
        Thread thread1 = new Thread(depositTask);
        Thread thread2 = new Thread(depositTask);
    
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
    
        assertEquals(100.0 + 1000 * 2, account.getBalance(), 0.001);
    }
    
    @Test
    public void testWithdrawExactBalance() {
        BankAccount account = new BankAccount(100.0);
        account.withdraw(100.0);
        assertEquals(0.0, account.getBalance(), 0.001);
    }
    
    @Test
    public void testWithdrawSlightlyLessThanBalance() {
        BankAccount account = new BankAccount(100.0);
        account.withdraw(99.99);
        assertEquals(0.01, account.getBalance(), 0.001);
    }
    
    @Test
    public void testConcurrentTransactions() throws InterruptedException {
        BankAccount account = new BankAccount(100.0);
        Runnable depositTask = () -> {
            for (int i = 0; i < 5000; i++) {
                account.deposit(1.0);
            }
        };
        Runnable withdrawTask = () -> {
            for (int i = 0; i < 5000; i++) {
                account.withdraw(1.0);
            }
        };
    
        Thread thread1 = new Thread(depositTask);
        Thread thread2 = new Thread(withdrawTask);
        Thread thread3 = new Thread(depositTask);
        Thread thread4 = new Thread(withdrawTask);
    
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    
        thread1.join();
        thread2.join();
        thread3.join();
        thread4.join();
    
        assertEquals(100.0, account.getBalance(), 0.001);
    }

    @Test
    public void testStressTransactions() {
        BankAccount account = new BankAccount(0.0);
        for (int i = 0; i < 1_000_000; i++) {
            account.deposit(1.0);
        }
        assertEquals(1_000_000.0, account.getBalance(), 0.001);
    }
    
    @Test
    public void testPerformance() {
        BankAccount account = new BankAccount(0.0);
        long startTime = System.nanoTime();
        for (int i = 0; i < 1_000_000; i++) {
            account.deposit(1.0);
        }
        long endTime = System.nanoTime();
        System.out.println("Execution time: " + (endTime - startTime) + " nanoseconds");
        assertEquals(1_000_000.0, account.getBalance(), 0.001);
    }
    @Test
    public void testDepositNaN() {
        BankAccount account = new BankAccount(100.0);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            account.deposit(Double.NaN);
        });
        assertEquals("Deposit amount must be a valid number.", exception.getMessage());
    }
     
}
