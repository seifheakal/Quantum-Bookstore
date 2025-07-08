package com.fawry.quantumbookstore.service;

import com.fawry.quantumbookstore.model.*;

public class PurchaseService {
    private final BookInventoryService inventoryService;
    private final ShippingService shippingService;
    private final MailService mailService;

    public PurchaseService(BookInventoryService inventoryService, ShippingService shippingService, MailService mailService) {
        if (inventoryService == null || shippingService == null || mailService == null) {
            throw new IllegalArgumentException("Services cannot be null");
        }
        this.inventoryService = inventoryService;
        this.shippingService = shippingService;
        this.mailService = mailService;
    }

    public double buyBook(String isbn, int quantity, Customer customer) {
        if (isbn == null || isbn.isBlank()) {
            throw new IllegalArgumentException("Quantum book store: ISBN cannot be empty");
        }
        if (customer == null) {
            throw new IllegalArgumentException("Quantum book store: Customer cannot be null");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantum book store: Quantity must be positive");
        }
        Book book = inventoryService.getBook(isbn);
        if (book instanceof DemoBook) {
            throw new IllegalArgumentException("Quantum book store: DemoBook is not for sale");
        }
        double totalPrice = book.getPrice() * quantity;
        
        if (book instanceof PaperBook) {
            PaperBook paperBook = (PaperBook) book;
            if (paperBook.getStock() < quantity) {
                throw new IllegalStateException("Quantum book store: Not enough stock for PaperBook");
            }
        }
        
        if (customer.getBalance() < totalPrice) {
            throw new IllegalStateException(String.format("Quantum book store: Insufficient balance (%.2f < %.2f)", customer.getBalance(), totalPrice));
        }
        
        customer.deductBalance(totalPrice);
        if (book instanceof PaperBook) {
            PaperBook paperBook = (PaperBook) book;
            paperBook.setStock(paperBook.getStock() - quantity);
            System.out.println("Quantum book store: Shipping PaperBook to " + customer.getAddress());
            shippingService.shipPaperBook(paperBook, quantity, customer.getAddress());
        } else if (book instanceof EBook) {
            System.out.println("Quantum book store: Sending EBook to " + customer.getEmail());
            mailService.sendEBook((EBook) book, quantity, customer.getEmail());
        } else {
            throw new IllegalArgumentException("Quantum book store: Unsupported book type");
        }
        System.out.println("Quantum book store: Purchase successful. Paid amount: " + totalPrice);
        return totalPrice;
    }
}
