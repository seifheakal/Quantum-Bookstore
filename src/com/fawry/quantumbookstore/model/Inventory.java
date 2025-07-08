package com.fawry.quantumbookstore.model;

import java.util.*;
import java.time.Year;

public class Inventory {
    private final Map<String, Book> books = new LinkedHashMap<>(); // ISBN -> Book

    public void addBook(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("Book cannot be null");
        }
        if (books.containsKey(book.getIsbn())) {
            throw new IllegalArgumentException("Book with ISBN already exists: " + book.getIsbn());
        }
        books.put(book.getIsbn(), book);
    }

    public Book getBook(String isbn) {
        if (isbn == null || isbn.isBlank()) {
            throw new IllegalArgumentException("ISBN cannot be empty");
        }
        Book book = books.get(isbn);
        if (book == null) {
            throw new NoSuchElementException("No book found with ISBN: " + isbn);
        }
        return book;
    }

    public Book removeBook(String isbn) {
        if (isbn == null || isbn.isBlank()) {
            throw new IllegalArgumentException("ISBN cannot be empty");
        }
        Book removed = books.remove(isbn);
        if (removed == null) {
            throw new NoSuchElementException("No book found with ISBN: " + isbn);
        }
        return removed;
    }

    public List<Book> removeOutdatedBooks(int years) {
        int currentYear = Year.now().getValue();
        List<Book> removed = new ArrayList<>();
        Iterator<Map.Entry<String, Book>> it = books.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Book> entry = it.next();
            Book book = entry.getValue();
            if (currentYear - book.getYear() > years) {
                removed.add(book);
                it.remove();
            }
        }
        return removed;
    }

    public Collection<Book> getAllBooks() {
        return Collections.unmodifiableCollection(books.values());
    }

    public boolean contains(String isbn) {
        return books.containsKey(isbn);
    }
}
