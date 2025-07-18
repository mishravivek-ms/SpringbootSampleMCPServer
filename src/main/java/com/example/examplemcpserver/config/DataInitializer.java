package com.example.examplemcpserver.config;

import com.example.examplemcpserver.entity.Book;
import com.example.examplemcpserver.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DataInitializer implements CommandLineRunner {
    
    private final BookService bookService;
    
    @Autowired
    public DataInitializer(BookService bookService) {
        this.bookService = bookService;
    }
    
    @Override
    public void run(String... args) throws Exception {
        // Check if books already exist to avoid duplicates
        if (bookService.getTotalBooksCount() == 0) {
            initializeSampleBooks();
        }
    }
    
    private void initializeSampleBooks() {
        try {
            // Classic Literature
            bookService.addBook(new Book("To Kill a Mockingbird", "Harper Lee", 1960, new BigDecimal("12.99")));
            bookService.addBook(new Book("1984", "George Orwell", 1949, new BigDecimal("13.99")));
            bookService.addBook(new Book("Pride and Prejudice", "Jane Austen", 1813, new BigDecimal("10.99")));
            bookService.addBook(new Book("The Great Gatsby", "F. Scott Fitzgerald", 1925, new BigDecimal("11.99")));
            
            // Science Fiction
            bookService.addBook(new Book("Dune", "Frank Herbert", 1965, new BigDecimal("16.99")));
            bookService.addBook(new Book("Foundation", "Isaac Asimov", 1951, new BigDecimal("14.99")));
            bookService.addBook(new Book("The Hitchhiker's Guide to the Galaxy", "Douglas Adams", 1979, new BigDecimal("12.99")));
            bookService.addBook(new Book("Neuromancer", "William Gibson", 1984, new BigDecimal("15.99")));
            
            // Fantasy
            bookService.addBook(new Book("The Lord of the Rings", "J.R.R. Tolkien", 1954, new BigDecimal("18.99")));
            bookService.addBook(new Book("A Game of Thrones", "George R.R. Martin", 1996, new BigDecimal("16.99")));
            bookService.addBook(new Book("The Name of the Wind", "Patrick Rothfuss", 2007, new BigDecimal("17.99")));
            
            // Programming & Technology
            bookService.addBook(new Book("Clean Code", "Robert C. Martin", 2008, new BigDecimal("45.99")));
            bookService.addBook(new Book("Design Patterns", "Gang of Four", 1994, new BigDecimal("54.99")));
            bookService.addBook(new Book("Effective Java", "Joshua Bloch", 2017, new BigDecimal("49.99")));
            bookService.addBook(new Book("Spring in Action", "Craig Walls", 2020, new BigDecimal("52.99")));
            
            // Non-Fiction
            bookService.addBook(new Book("Sapiens", "Yuval Noah Harari", 2011, new BigDecimal("19.99")));
            bookService.addBook(new Book("The Lean Startup", "Eric Ries", 2011, new BigDecimal("24.99")));
            bookService.addBook(new Book("Thinking, Fast and Slow", "Daniel Kahneman", 2011, new BigDecimal("22.99")));
            
            // Recent Publications
            bookService.addBook(new Book("Klara and the Sun", "Kazuo Ishiguro", 2021, new BigDecimal("26.99")));
            bookService.addBook(new Book("The Thursday Murder Club", "Richard Osman", 2020, new BigDecimal("15.99")));
            bookService.addBook(new Book("Project Hail Mary", "Andy Weir", 2021, new BigDecimal("27.99")));
            
            System.out.println("✅ Sample books have been successfully loaded into the H2 database!");
            System.out.println("📚 Total books initialized: " + bookService.getTotalBooksCount());
            
        } catch (Exception e) {
            System.err.println("❌ Error initializing sample books: " + e.getMessage());
        }
    }
}
