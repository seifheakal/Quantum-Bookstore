package com.fawry.quantumbookstore.service;

import com.fawry.quantumbookstore.model.PaperBook;

public class ShippingService {
    public void shipPaperBook(PaperBook book, int quantity, String address) {
        System.out.println(String.format("Quantum book store: Shipped %d x '%s' to address: %s", quantity, book.getTitle(), address));
    }
}
