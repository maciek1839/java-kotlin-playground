package com.showmeyourcode.playground.java.code.paradigm.prcedural;

import java.util.ArrayList;

// Class to represent a customer
class Customer {
    private String name;
    private String address;
    private ArrayList<Account> accounts;

    public Customer(String name, String address) {
        this.name = name;
        this.address = address;
        this.accounts = new ArrayList<>();
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }
}

// Class to represent an account
class Account {
    private String accountId;
    private double balance;
    private ArrayList<Transaction> transactions;

    public Account(String accountId) {
        this.accountId = accountId;
        this.balance = 0;
        this.transactions = new ArrayList<>();
    }

    public void deposit(double amount) {
        balance += amount;
        transactions.add(new Transaction("Deposit", amount));
    }

    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            transactions.add(new Transaction("Withdrawal", amount));
        } else {
            System.out.println("Insufficient funds.");
        }
    }

    public double getBalance() {
        return balance;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }
}

// Class to represent a transaction
class Transaction {
    private String type;
    private double amount;

    public Transaction(String type, double amount) {
        this.type = type;
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }
}

// Main class to demonstrate procedural programming
//
// This example showcases procedural programming by organizing the code into procedures (methods) for different tasks,
// such as managing accounts, handling transactions, and interacting with customers.
// It emphasizes sequential execution of steps to achieve banking operations.
class BankSystem {
    public static void main() {
        // Create a customer
        Customer customer = new Customer("John Doe", "123 Main St");

        // Create accounts for the customer
        Account checkingAccount = new Account("1001");
        customer.addAccount(checkingAccount);

        // Deposit and withdraw from the account
        checkingAccount.deposit(1000);
        checkingAccount.withdraw(500);

        // Print account balance and transactions
        System.out.println("Account balance: $" + checkingAccount.getBalance());
        System.out.println("Transactions:");
        for (Transaction transaction : checkingAccount.getTransactions()) {
            System.out.println(transaction.getType() + ": $" + transaction.getAmount());
        }
    }
}

public class ProceduralProgramming {

    public static void main(){
        BankSystem.main();
    }
}
