package com.example.examplemcpserver.repository;

import com.example.examplemcpserver.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    
    /**
     * Find books by book name (case-insensitive)
     */
    List<Book> findByBookNameContainingIgnoreCase(String bookName);
    
    /**
     * Find books by author (case-insensitive)
     */
    List<Book> findByAuthorContainingIgnoreCase(String author);
    
    /**
     * Find books by year of publishing
     */
    List<Book> findByYearOfPublishing(Integer year);
    
    /**
     * Find books by year range
     */
    List<Book> findByYearOfPublishingBetween(Integer startYear, Integer endYear);
    
    /**
     * Find books by exact book name and author (case-insensitive)
     */
    Optional<Book> findByBookNameIgnoreCaseAndAuthorIgnoreCase(String bookName, String author);
    
    /**
     * Custom query to find books ordered by year of publishing
     */
    @Query("SELECT b FROM Book b ORDER BY b.yearOfPublishing DESC")
    List<Book> findAllOrderByYearDesc();
    
    /**
     * Custom query to find books by price range
     */
    @Query("SELECT b FROM Book b WHERE b.price BETWEEN :minPrice AND :maxPrice")
    List<Book> findByPriceRange(@Param("minPrice") java.math.BigDecimal minPrice, 
                               @Param("maxPrice") java.math.BigDecimal maxPrice);
}
