package com.fawry.quantumbookstore.service;

import com.fawry.quantumbookstore.model.EBook;

public class MailService {
    public void sendEBook(EBook book, int quantity, String email) {
        System.out.println(String.format("Quantum book store: Sent %d x '%s' (%s) to email: %s", quantity, book.getTitle(), book.getFileType(), email));
    }
}
