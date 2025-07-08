package com.fawry.quantumbookstore.model;

public class Customer {
    private final String id;
    private final String name;
    private final String email;
    private final String address;
    private double balance;

    public Customer(String id, String name, String email, String address, double initialBalance) {
        if (id == null || id.isBlank()) throw new IllegalArgumentException("Customer ID cannot be empty");
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Customer name cannot be empty");
        if (email == null || email.isBlank()) throw new IllegalArgumentException("Email cannot be empty");
        if (address == null || address.isBlank()) throw new IllegalArgumentException("Address cannot be empty");
        if (initialBalance < 0) throw new IllegalArgumentException("Initial balance cannot be negative");
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.balance = initialBalance;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public double getBalance() {
        return balance;
    }

    public void deductBalance(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Deduction amount cannot be negative");
        }
        if (balance < amount) {
            throw new IllegalStateException(String.format("Insufficient balance (%.2f < %.2f)", balance, amount));
        }
        balance -= amount;
    }

    public void addBalance(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Added amount cannot be negative");
        }
        balance += amount;
    }

    @Override
    public String toString() {
        return String.format("Customer: %s (ID: %s, Email: %s, Address: %s, Balance: %.2f)", name, id, email, address, balance);
    }
}
