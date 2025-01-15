# BankAccountTest

BankAccountTest is a Java-based project that implements a simple `BankAccount` class with methods for deposit, withdrawal, and balance retrieval. The project also includes extensive JUnit test cases to ensure robust functionality and error handling.

---

## Features

### BankAccount Class
- **Initial Balance Validation**: Ensures that the initial balance is a valid number and non-negative.
- **Deposit**:
  - Adds a specified amount to the account balance.
  - Validates that the amount is a valid positive number.
- **Withdrawal**:
  - Deducts a specified amount from the account balance.
  - Ensures the amount is valid, positive, and does not exceed the current balance.
- **Thread-Safe Operations**: Synchronized methods to handle concurrent deposits and withdrawals.

---

### Test Coverage
The project includes comprehensive JUnit test cases to cover the following scenarios:

#### Valid Cases
- Creating an account with an initial balance.
- Depositing valid amounts into the account.
- Withdrawing valid amounts within the account balance.
- Performing multiple transactions in sequence.

#### Invalid Cases
- Attempting to create an account with a negative balance.
- Depositing negative or invalid amounts (e.g., `NaN`, `Infinity`).
- Withdrawing negative amounts or amounts exceeding the account balance.

#### Edge Cases
- Depositing and withdrawing the smallest and largest possible double values.
- Handling concurrent deposits and withdrawals with multiple threads.
- Testing for balance consistency after numerous operations.

#### Performance
- Stress testing with a large number of transactions.
- Measuring execution time for performance evaluation.

---

## Requirements

- **Java Version**: JDK 8 or higher
- **Build Tool**: Maven or any other Java build system
- **Testing Framework**: JUnit 5

---

## Installation

1. Clone the repository:
   ```sh
   git clone https://github.com/MinhNguyen025/BankAccountTest.git
   ```

2. Navigate to the project directory:
   ```sh
   cd BankAccountTest
   ```

3. Ensure dependencies are installed:
   ```sh
   mvn install
   ```

4. Run the tests:
   ```sh
   mvn test
   ```

---

## Usage

### Creating a Bank Account
Instantiate a `BankAccount` object:
```java
BankAccount account = new BankAccount(100.0);
```

### Deposit
Add funds to the account:
```java
account.deposit(50.0);
```

### Withdraw
Deduct funds from the account:
```java
account.withdraw(30.0);
```

### Get Balance
Retrieve the current account balance:
```java
System.out.println(account.getBalance());
```

---

## Running Tests

The project includes JUnit test cases located in the `src/test/java` directory. Use the following command to run the tests:
```sh
mvn test
```
# Test Result
![image](https://github.com/user-attachments/assets/cf8e84a8-e668-4b21-a2b6-1acc21a99a21)

---

## Contribution

Contributions are welcome! To contribute:
1. Fork the repository.
2. Create a new branch for your feature or bugfix.
3. Commit your changes and push them to your branch.
4. Create a pull request to the `main` branch.

---

## License

This project is licensed under the MIT License. See the LICENSE file for details.

---

## Contact

For any questions or feedback, please reach out to Minh Nguyen via the [GitHub repository](https://github.com/MinhNguyen025/BankAccountTest).

## ChatGPT share: [Source](https://chatgpt.com/c/677cdbdb-8b14-8000-b039-9fcc050a3d95)
## ChatGPT mark: 10/10 [Source](https://chatgpt.com/c/677df1e9-1f54-8010-8ccd-06ad02f70693?model=o1-mini)
## ChatGPT mark: ver2 [Source](https://chatgpt.com/c/677df0d4-d398-8010-91ae-fb3e002074a9)

