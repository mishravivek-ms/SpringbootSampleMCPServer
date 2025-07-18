package com.example.examplemcpserver.service;

import com.example.examplemcpserver.entity.Book;
import com.example.examplemcpserver.repository.BookRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookService {
    
    private final BookRepository bookRepository;
    
    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    
    /**
     * Add a new book
     */
    public Book addBook(@Valid Book book) {
        // Check if book with same name and author already exists
        Optional<Book> existingBook = bookRepository.findByBookNameIgnoreCaseAndAuthorIgnoreCase(
            book.getBookName(), book.getAuthor());
        
        if (existingBook.isPresent()) {
            throw new IllegalArgumentException(
                "Book with name '" + book.getBookName() + "' by author '" + book.getAuthor() + "' already exists");
        }
        
        return bookRepository.save(book);
    }
    
    /**
     * Get all books
     */
    @Transactional(readOnly = true)
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
    
    /**
     * Get book by ID
     */
    @Transactional(readOnly = true)
    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }
    
    /**
     * Update an existing book
     */
    public Book updateBook(Long id, @Valid Book updatedBook) {
        Optional<Book> existingBookOpt = bookRepository.findById(id);
        
        if (existingBookOpt.isEmpty()) {
            throw new IllegalArgumentException("Book with ID " + id + " not found");
        }
        
        Book existingBook = existingBookOpt.get();
        
        // Check if another book with same name and author exists (excluding current book)
        Optional<Book> duplicateBook = bookRepository.findByBookNameIgnoreCaseAndAuthorIgnoreCase(
            updatedBook.getBookName(), updatedBook.getAuthor());
        
        if (duplicateBook.isPresent() && !duplicateBook.get().getId().equals(id)) {
            throw new IllegalArgumentException(
                "Another book with name '" + updatedBook.getBookName() + 
                "' by author '" + updatedBook.getAuthor() + "' already exists");
        }
        
        // Update fields
        existingBook.setBookName(updatedBook.getBookName());
        existingBook.setAuthor(updatedBook.getAuthor());
        existingBook.setYearOfPublishing(updatedBook.getYearOfPublishing());
        existingBook.setPrice(updatedBook.getPrice());
        
        return bookRepository.save(existingBook);
    }
    
    /**
     * Delete a book by ID
     */
    public boolean deleteBook(Long id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
    /**
     * Search books by name
     */
    @Transactional(readOnly = true)
    public List<Book> searchBooksByName(String bookName) {
        return bookRepository.findByBookNameContainingIgnoreCase(bookName);
    }
    
    /**
     * Search books by author
     */
    @Transactional(readOnly = true)
    public List<Book> searchBooksByAuthor(String author) {
        return bookRepository.findByAuthorContainingIgnoreCase(author);
    }
    
    /**
     * Get books by year
     */
    @Transactional(readOnly = true)
    public List<Book> getBooksByYear(Integer year) {
        return bookRepository.findByYearOfPublishing(year);
    }
    
    /**
     * Get books by year range
     */
    @Transactional(readOnly = true)
    public List<Book> getBooksByYearRange(Integer startYear, Integer endYear) {
        return bookRepository.findByYearOfPublishingBetween(startYear, endYear);
    }
    
    /**
     * Get books by price range
     */
    @Transactional(readOnly = true)
    public List<Book> getBooksByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        return bookRepository.findByPriceRange(minPrice, maxPrice);
    }
    
    /**
     * Get books ordered by year (newest first)
     */
    @Transactional(readOnly = true)
    public List<Book> getBooksOrderedByYear() {
        return bookRepository.findAllOrderByYearDesc();
    }
    
    /**
     * Get total count of books
     */
    @Transactional(readOnly = true)
    public long getTotalBooksCount() {
        return bookRepository.count();
    }
}
