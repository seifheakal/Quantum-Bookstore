# Quantum Bookstore

A robust Java implementation of an extensible online bookstore, supporting multiple book types, inventory management, and purchase workflows.

---

## Key Features

### Book Management
- **Supports multiple book types:**
  - **PaperBook:** Physical books with stock and shipping.
  - **EBook:** Digital books with file type, sent via email.
  - **DemoBook:** Showcase/demo books, not for sale.
- **Author, ISBN, title, year, and price tracking**
- **Real-time stock validation** for PaperBooks

### Purchase Operations
- **Buy books by ISBN and quantity**
- **Automatic stock reduction and validation**
- **Shipping (PaperBook) and email delivery (EBook) simulation**
- **Comprehensive error handling** for out-of-stock, insufficient balance, and business rule violations

### Inventory Management
- **Add, remove, and restock books**
- **Remove outdated books by publication year**
- **Case-sensitive ISBN lookup**

### Extensible Design
- Easily add new book/product types without modifying core logic

---

## Business Rules & Specifications

- **All print statements are prefixed with:** `Quantum book store:`
- **DemoBooks are not for sale**
- **PaperBooks require stock management**
- **EBooks require file type and email delivery**
- **Purchases require sufficient customer balance**
- **No operation proceeds if validation fails**

---

## Class Structure
com.fawry.quantumbookstore ├── model │ ├── Book.java # Abstract base for all books │ ├── PaperBook.java # Physical book │ ├── EBook.java # Digital book │ ├── DemoBook.java # Showcase/demo book │ ├── Customer.java # Customer data and balance │ └── Inventory.java # Inventory management ├── service │ ├── BookInventoryService.java # Inventory operations │ ├── PurchaseService.java # Purchase workflow │ ├── ShippingService.java # Shipping simulation │ ├── MailService.java # Email delivery simulation │ └── YearFilterService.java # Outdated book filtering └── QuantumBookstoreFullTest.java # Comprehensive test suite

---

## Example Usage

```java
// Create books
PaperBook javaBook = new PaperBook("ISBN-001", "Java Basics", "Seif", 2015, 150.0, 5);
EBook quantumBook = new EBook("ISBN-002", "Quantum Computing", "Tamer", 2020, 200.0, "PDF");

// Create customer
Customer seif = new Customer("cust-1", "Seif", "seif@mail.com", "123 Main St", 1000.0);

// Add books to inventory
BookInventoryService inventoryService = new BookInventoryService(new Inventory());
inventoryService.addBook(javaBook);
inventoryService.addBook(quantumBook);

// Purchase a book
PurchaseService purchaseService = new PurchaseService(inventoryService, new ShippingService(), new MailService());
purchaseService.buyBook("ISBN-001", 2, seif);
```

---

## Sample Output

```
Quantum book store: Shipping PaperBook to 123 Main St
Quantum book store: Shipped 2 x 'Java Basics' to address: 123 Main St
Quantum book store: Purchase successful. Paid amount: 300.0
Quantum book store: Remaining stock: 3
```