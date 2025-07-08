package com.fawry.quantumbookstore.service;

import com.fawry.quantumbookstore.model.*;
import java.util.List;
import java.util.NoSuchElementException;

public class BookInventoryService {
    private final Inventory inventory;

    public BookInventoryService(Inventory inventory) {
        if (inventory == null) throw new IllegalArgumentException("Inventory cannot be null");
        this.inventory = inventory;
    }

    public void addBook(Book book) {
        if (book == null || book.getIsbn() == null || book.getIsbn().isBlank()) throw new IllegalArgumentException("Book cannot be null or have an empty ISBN");
        inventory.addBook(book);
    }

    public Book getBook(String isbn) {
        if (isbn == null || isbn.isBlank()) throw new IllegalArgumentException("ISBN cannot be empty");
        return inventory.getBook(isbn);
    }

    public Book removeBook(String isbn) {
        if (isbn == null || isbn.isBlank()) throw new IllegalArgumentException("ISBN cannot be empty");
        if (!inventory.contains(isbn)) {
            throw new NoSuchElementException("No book found with ISBN: " + isbn);
        }
        return inventory.removeBook(isbn);
    }

    public void restockPaperBook(String isbn, int quantity) {
        if (isbn == null || isbn.isBlank()) throw new IllegalArgumentException("ISBN cannot be empty");
        Book book = inventory.getBook(isbn);
        if (!(book instanceof PaperBook)) {
            throw new IllegalArgumentException("Only PaperBooks can be restocked");
        }
        PaperBook paperBook = (PaperBook) book;
        if (quantity <= 0) throw new IllegalArgumentException("Quantity must be positive");
        paperBook.setStock(paperBook.getStock() + quantity);
    }

    public List<Book> removeOutdatedBooks(int years) {
        if (years <= 0) throw new IllegalArgumentException("Years must be positive");
        if (inventory.getAllBooks().isEmpty()) {
            throw new IllegalStateException("Inventory is empty");
        }
        return inventory.removeOutdatedBooks(years);
    }

    public boolean contains(String isbn) {
        if (isbn == null || isbn.isBlank()) throw new IllegalArgumentException("ISBN cannot be empty");
        if (!inventory.getAllBooks().stream().anyMatch(book -> book.getIsbn().equals(isbn))) {
            throw new NoSuchElementException("No book found with ISBN: " + isbn);
        }
        return inventory.contains(isbn);
    }

    public List<Book> getAllBooks() {
        if (inventory.getAllBooks().isEmpty()) {
            throw new IllegalStateException("Inventory is empty");
        }
        return List.copyOf(inventory.getAllBooks());
    }
}
