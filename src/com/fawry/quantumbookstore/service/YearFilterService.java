package com.fawry.quantumbookstore.service;

import com.fawry.quantumbookstore.model.Book;
import java.util.*;
import java.time.Year;

public class YearFilterService {
    public List<Book> filterBooksOlderThan(Collection<Book> books, int years) {
        if (years <= 0) throw new IllegalArgumentException("Years must be positive");
        if (books.isEmpty()) throw new IllegalStateException("Books collection is empty");
        if (books == null) throw new IllegalArgumentException("Books collection cannot be null");
        int currentYear = Year.now().getValue();
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (currentYear - book.getYear() > years) {
                result.add(book);
            }
        }
        return result;
    }
}
