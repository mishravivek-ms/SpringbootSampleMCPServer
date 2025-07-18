package com.example.examplemcpserver;

import com.example.examplemcpserver.entity.Book;
import com.example.examplemcpserver.service.BookService;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Component
public class BookTool {
    
    private final BookService bookService;
    
    @Autowired
    public BookTool(BookService bookService) {
        this.bookService = bookService;
    }
    
    @Tool(name = "add_book", description = "Add a new book to the library")
    public String addBook(String bookName, String author, Integer yearOfPublishing, Double price) {
        try {
            if (bookName == null || bookName.trim().isEmpty()) {
                return "Error: Book name cannot be empty";
            }
            if (author == null || author.trim().isEmpty()) {
                return "Error: Author name cannot be empty";
            }
            if (yearOfPublishing == null || yearOfPublishing <= 0) {
                return "Error: Year of publishing must be a positive number";
            }
            if (price == null || price <= 0) {
                return "Error: Price must be a positive number";
            }
            
            Book book = new Book(bookName.trim(), author.trim(), yearOfPublishing, BigDecimal.valueOf(price));
            Book savedBook = bookService.addBook(book);
            
            return String.format("Successfully added book: '%s' by %s (ID: %d, Year: %d, Price: $%.2f)", 
                    savedBook.getBookName(), savedBook.getAuthor(), savedBook.getId(), 
                    savedBook.getYearOfPublishing(), savedBook.getPrice());
        } catch (Exception e) {
            return "Error adding book: " + e.getMessage();
        }
    }
    
    @Tool(name = "remove_book", description = "Remove a book from the library by ID")
    public String removeBook(Long bookId) {
        try {
            if (bookId == null || bookId <= 0) {
                return "Error: Book ID must be a positive number";
            }
            
            Optional<Book> bookOpt = bookService.getBookById(bookId);
            if (bookOpt.isEmpty()) {
                return "Error: Book with ID " + bookId + " not found";
            }
            
            Book book = bookOpt.get();
            boolean deleted = bookService.deleteBook(bookId);
            
            if (deleted) {
                return String.format("Successfully removed book: '%s' by %s (ID: %d)", 
                        book.getBookName(), book.getAuthor(), bookId);
            } else {
                return "Error: Failed to remove book with ID " + bookId;
            }
        } catch (Exception e) {
            return "Error removing book: " + e.getMessage();
        }
    }
    
    @Tool(name = "update_book", description = "Update an existing book in the library")
    public String updateBook(Long bookId, String bookName, String author, Integer yearOfPublishing, Double price) {
        try {
            if (bookId == null || bookId <= 0) {
                return "Error: Book ID must be a positive number";
            }
            if (bookName == null || bookName.trim().isEmpty()) {
                return "Error: Book name cannot be empty";
            }
            if (author == null || author.trim().isEmpty()) {
                return "Error: Author name cannot be empty";
            }
            if (yearOfPublishing == null || yearOfPublishing <= 0) {
                return "Error: Year of publishing must be a positive number";
            }
            if (price == null || price <= 0) {
                return "Error: Price must be a positive number";
            }
            
            Book updatedBook = new Book(bookName.trim(), author.trim(), yearOfPublishing, BigDecimal.valueOf(price));
            Book savedBook = bookService.updateBook(bookId, updatedBook);
            
            return String.format("Successfully updated book: '%s' by %s (ID: %d, Year: %d, Price: $%.2f)", 
                    savedBook.getBookName(), savedBook.getAuthor(), savedBook.getId(), 
                    savedBook.getYearOfPublishing(), savedBook.getPrice());
        } catch (Exception e) {
            return "Error updating book: " + e.getMessage();
        }
    }
    
    @Tool(name = "get_all_books", description = "Get all books in the library")
    public String getAllBooks() {
        try {
            List<Book> books = bookService.getAllBooks();
            
            if (books.isEmpty()) {
                return "No books found in the library";
            }
            
            StringBuilder result = new StringBuilder("Books in the library:\n");
            for (Book book : books) {
                result.append(String.format("ID: %d | '%s' by %s | Year: %d | Price: $%.2f\n", 
                        book.getId(), book.getBookName(), book.getAuthor(), 
                        book.getYearOfPublishing(), book.getPrice()));
            }
            
            result.append(String.format("\nTotal books: %d", books.size()));
            return result.toString();
        } catch (Exception e) {
            return "Error retrieving books: " + e.getMessage();
        }
    }
    
    @Tool(name = "get_book_by_id", description = "Get a specific book by its ID")
    public String getBookById(Long bookId) {
        try {
            if (bookId == null || bookId <= 0) {
                return "Error: Book ID must be a positive number";
            }
            
            Optional<Book> bookOpt = bookService.getBookById(bookId);
            
            if (bookOpt.isEmpty()) {
                return "Book with ID " + bookId + " not found";
            }
            
            Book book = bookOpt.get();
            return String.format("Book Details:\nID: %d\nTitle: '%s'\nAuthor: %s\nYear: %d\nPrice: $%.2f", 
                    book.getId(), book.getBookName(), book.getAuthor(), 
                    book.getYearOfPublishing(), book.getPrice());
        } catch (Exception e) {
            return "Error retrieving book: " + e.getMessage();
        }
    }
    
    @Tool(name = "search_books_by_name", description = "Search books by book name (partial match)")
    public String searchBooksByName(String bookName) {
        try {
            if (bookName == null || bookName.trim().isEmpty()) {
                return "Error: Book name cannot be empty";
            }
            
            List<Book> books = bookService.searchBooksByName(bookName.trim());
            
            if (books.isEmpty()) {
                return "No books found with name containing: " + bookName;
            }
            
            StringBuilder result = new StringBuilder(String.format("Books matching '%s':\n", bookName));
            for (Book book : books) {
                result.append(String.format("ID: %d | '%s' by %s | Year: %d | Price: $%.2f\n", 
                        book.getId(), book.getBookName(), book.getAuthor(), 
                        book.getYearOfPublishing(), book.getPrice()));
            }
            
            result.append(String.format("\nFound %d books", books.size()));
            return result.toString();
        } catch (Exception e) {
            return "Error searching books: " + e.getMessage();
        }
    }
    
    @Tool(name = "search_books_by_author", description = "Search books by author name (partial match)")
    public String searchBooksByAuthor(String author) {
        try {
            if (author == null || author.trim().isEmpty()) {
                return "Error: Author name cannot be empty";
            }
            
            List<Book> books = bookService.searchBooksByAuthor(author.trim());
            
            if (books.isEmpty()) {
                return "No books found by author containing: " + author;
            }
            
            StringBuilder result = new StringBuilder(String.format("Books by authors matching '%s':\n", author));
            for (Book book : books) {
                result.append(String.format("ID: %d | '%s' by %s | Year: %d | Price: $%.2f\n", 
                        book.getId(), book.getBookName(), book.getAuthor(), 
                        book.getYearOfPublishing(), book.getPrice()));
            }
            
            result.append(String.format("\nFound %d books", books.size()));
            return result.toString();
        } catch (Exception e) {
            return "Error searching books: " + e.getMessage();
        }
    }
    
    @Tool(name = "get_books_by_year", description = "Get books published in a specific year")
    public String getBooksByYear(Integer year) {
        try {
            if (year == null || year <= 0) {
                return "Error: Year must be a positive number";
            }
            
            List<Book> books = bookService.getBooksByYear(year);
            
            if (books.isEmpty()) {
                return "No books found published in year: " + year;
            }
            
            StringBuilder result = new StringBuilder(String.format("Books published in %d:\n", year));
            for (Book book : books) {
                result.append(String.format("ID: %d | '%s' by %s | Price: $%.2f\n", 
                        book.getId(), book.getBookName(), book.getAuthor(), book.getPrice()));
            }
            
            result.append(String.format("\nFound %d books", books.size()));
            return result.toString();
        } catch (Exception e) {
            return "Error retrieving books: " + e.getMessage();
        }
    }
    
    @Tool(name = "get_books_by_price_range", description = "Get books within a specific price range")
    public String getBooksByPriceRange(Double minPrice, Double maxPrice) {
        try {
            if (minPrice == null || minPrice < 0) {
                return "Error: Minimum price must be a non-negative number";
            }
            if (maxPrice == null || maxPrice < 0) {
                return "Error: Maximum price must be a non-negative number";
            }
            if (minPrice > maxPrice) {
                return "Error: Minimum price cannot be greater than maximum price";
            }
            
            List<Book> books = bookService.getBooksByPriceRange(
                    BigDecimal.valueOf(minPrice), BigDecimal.valueOf(maxPrice));
            
            if (books.isEmpty()) {
                return String.format("No books found in price range $%.2f - $%.2f", minPrice, maxPrice);
            }
            
            StringBuilder result = new StringBuilder(
                    String.format("Books in price range $%.2f - $%.2f:\n", minPrice, maxPrice));
            for (Book book : books) {
                result.append(String.format("ID: %d | '%s' by %s | Year: %d | Price: $%.2f\n", 
                        book.getId(), book.getBookName(), book.getAuthor(), 
                        book.getYearOfPublishing(), book.getPrice()));
            }
            
            result.append(String.format("\nFound %d books", books.size()));
            return result.toString();
        } catch (Exception e) {
            return "Error retrieving books: " + e.getMessage();
        }
    }
    
    @Tool(name = "get_library_stats", description = "Get statistics about the library")
    public String getLibraryStats() {
        try {
            long totalBooks = bookService.getTotalBooksCount();
            List<Book> allBooks = bookService.getAllBooks();
            
            if (totalBooks == 0) {
                return "Library is empty - no books available";
            }
            
            // Calculate some basic statistics
            int earliestYear = allBooks.stream()
                    .mapToInt(Book::getYearOfPublishing)
                    .min()
                    .orElse(0);
            
            int latestYear = allBooks.stream()
                    .mapToInt(Book::getYearOfPublishing)
                    .max()
                    .orElse(0);
            
            BigDecimal minPrice = allBooks.stream()
                    .map(Book::getPrice)
                    .min(BigDecimal::compareTo)
                    .orElse(BigDecimal.ZERO);
            
            BigDecimal maxPrice = allBooks.stream()
                    .map(Book::getPrice)
                    .max(BigDecimal::compareTo)
                    .orElse(BigDecimal.ZERO);
            
            long uniqueAuthors = allBooks.stream()
                    .map(Book::getAuthor)
                    .distinct()
                    .count();
            
            return String.format("""
                    Library Statistics:
                    Total Books: %d
                    Unique Authors: %d
                    Publication Years: %d - %d
                    Price Range: $%.2f - $%.2f
                    """, 
                    totalBooks, uniqueAuthors, earliestYear, latestYear, minPrice, maxPrice);
        } catch (Exception e) {
            return "Error retrieving library statistics: " + e.getMessage();
        }
    }
}
