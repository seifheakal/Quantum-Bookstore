package com.fawry.quantumbookstore.model;

public class EBook extends Book {
    private final String fileType;

    public EBook(String isbn, String title, String author, int year, double price, String fileType) {
        super(isbn, title, author, year, price);
        if (fileType == null || fileType.isBlank()) {
            throw new IllegalArgumentException("File type cannot be empty");
        }
        this.fileType = fileType;
    }

    public String getFileType() {
        return fileType;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" [EBook, FileType: %s]", fileType);
    }
}
