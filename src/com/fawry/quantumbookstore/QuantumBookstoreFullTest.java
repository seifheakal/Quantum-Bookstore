package com.fawry.quantumbookstore;

import com.fawry.quantumbookstore.model.*;
import com.fawry.quantumbookstore.service.*;
import java.util.List;

public class QuantumBookstoreFullTest {
    public static void main(String[] args) {
        // Setup services
        Inventory inventory = new Inventory();
        BookInventoryService inventoryService = new BookInventoryService(inventory);
        ShippingService shippingService = new ShippingService();
        MailService mailService = new MailService();
        PurchaseService purchaseService = new PurchaseService(inventoryService, shippingService, mailService);
        YearFilterService yearFilterService = new YearFilterService();

        // Create test books
        PaperBook javaBook = new PaperBook("ISBN-001", "Java Basics", "Seif", 2015, 150.0, 5);
        EBook quantumBook = new EBook("ISBN-002", "Quantum Computing", "Tamer", 2020, 200.0, "PDF");
        DemoBook demoBook = new DemoBook("ISBN-003", "Showcase Only", "Heakal", 2010, 0.0);

        // Add books to inventory
        inventoryService.addBook(javaBook);
        inventoryService.addBook(quantumBook);
        inventoryService.addBook(demoBook);

        // Create customers
        Customer Seif = new Customer("cust-1", "Seif", "seif@mail.com", "123 Main St", 1000.0);
        Customer Tamer = new Customer("cust-2", "Tamer", "tamer@mail.com", "456 Elm St", 200.0);
        Customer Heakal = new Customer("cust-3", "Heakal", "heakal@mail.com", "789 Nile St", 5000.0);

        // ===== TEST 1: Normal PaperBook purchase =====
        System.out.println("\n===== TEST 1: Normal PaperBook Purchase =====");
        try {
            double paid = purchaseService.buyBook("ISBN-001", 2, Seif);
            System.out.println("Quantum book store: Remaining stock: " + javaBook.getStock());
        } catch (Exception e) {
            System.out.println("Quantum book store: ERROR: " + e.getMessage());
        }

        // ===== TEST 2: Normal EBook purchase (should fail for insufficient balance) =====
        System.out.println("\n===== TEST 2: Normal EBook Purchase =====");
        try {
            double paid = purchaseService.buyBook("ISBN-002", 2, Tamer);
        } catch (Exception e) {
            System.out.println("Quantum book store: ERROR: " + e.getMessage());
        }

        // ===== TEST 3: DemoBook not for sale =====
        System.out.println("\n===== TEST 3: DemoBook Not For Sale =====");
        try {
            purchaseService.buyBook("ISBN-003", 1, Seif);
        } catch (Exception e) {
            System.out.println("Quantum book store: EXPECTED ERROR: " + e.getMessage());
        }

        // ===== TEST 4: Out of stock =====
        System.out.println("\n===== TEST 4: Out of Stock =====");
        try {
            purchaseService.buyBook("ISBN-001", 10, Seif);
        } catch (Exception e) {
            System.out.println("Quantum book store: EXPECTED ERROR: " + e.getMessage());
        }

        // ===== TEST 5: Remove outdated books =====
        System.out.println("\n===== TEST 5: Remove Outdated Books (>10 years) =====");
        List<Book> outdated = inventoryService.removeOutdatedBooks(10);
        for (Book b : outdated) {
            System.out.println("Quantum book store: Removed outdated book: " + b);
        }

        // ===== TEST 6: Buy with zero quantity =====
        System.out.println("\n===== TEST 6: Buy with Zero Quantity =====");
        try {
            purchaseService.buyBook("ISBN-001", 0, Seif);
        } catch (Exception e) {
            System.out.println("Quantum book store: EXPECTED ERROR: " + e.getMessage());
        }

        // ===== TEST 7: Buy with negative quantity =====
        System.out.println("\n===== TEST 7: Buy with Negative Quantity =====");
        try {
            purchaseService.buyBook("ISBN-001", -1, Seif);
        } catch (Exception e) {
            System.out.println("Quantum book store: EXPECTED ERROR: " + e.getMessage());
        }

        // ===== TEST 8: Restock PaperBook and buy again =====
        System.out.println("\n===== TEST 8: Restock PaperBook and Buy Again =====");
        try {
            inventoryService.restockPaperBook("ISBN-001", 5);
            double paid = purchaseService.buyBook("ISBN-001", 3, Seif);
            System.out.println("Quantum book store: Remaining stock: " + javaBook.getStock());
        } catch (Exception e) {
            System.out.println("Quantum book store: ERROR: " + e.getMessage());
        }

        // ===== TEST 9: Try to buy non-existent book =====
        System.out.println("\n===== TEST 9: Buy Non-Existent Book =====");
        try {
            purchaseService.buyBook("ISBN-999", 1, Seif);
        } catch (Exception e) {
            System.out.println("Quantum book store: EXPECTED ERROR: " + e.getMessage());
        }

        // ===== TEST 10: Remove all books and check inventory =====
        System.out.println("\n===== TEST 10: Remove All Books and Check Inventory =====");
        try {
            inventoryService.removeBook("ISBN-001");
            inventoryService.removeBook("ISBN-002");
            System.out.println("Quantum book store: Remaining books: " + inventoryService.getAllBooks());
        } catch (Exception e) {
            System.out.println("Quantum book store: ERROR: " + e.getMessage());
        }
    }
}
