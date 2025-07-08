package com.fawry.quantumbookstore.model;

public abstract class Book {
    private final String isbn;
    private final String title;
    private final String author;
    private final int year;
    private final double price;

    public Book(String isbn, String title, String author, int year, double price) {
        if (isbn == null || isbn.isBlank()) {
            throw new IllegalArgumentException("ISBN cannot be empty");
        }
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
        if (author == null || author.isBlank()) {
            throw new IllegalArgumentException("Author name cannot be empty");
        }
        if (year <= 0) {
            throw new IllegalArgumentException("Year must be positive");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.year = year;
        this.price = price;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return String.format("%s by %s (ISBN: %s, Year: %d, Price: %.2f)", title, author, isbn, year, price);
    }
}
