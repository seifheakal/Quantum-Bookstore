package com.fawry.quantumbookstore.model;

public class DemoBook extends Book {
    public DemoBook(String isbn, String title, String author, int year, double price) {
        super(isbn, title, author, year, price);
    }

    @Override
    public String toString() {
        return super.toString() + " [DemoBook, Not for Sale]";
    }
}
