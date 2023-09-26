package com.example.LibraryManagementSystem.repository;

import com.example.LibraryManagementSystem.Enum.Genre;
import com.example.LibraryManagementSystem.dto.responseDTO.BookResponse;
import com.example.LibraryManagementSystem.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Integer> {
    List<Book> findByGenre(Genre genre);

    @Query(value = "select b from Book b where b.genre = :genre and b.cost > :cost",nativeQuery = false)
    List<Book> getByGenreAndCostGreaterThan(Genre genre, double cost);

    @Query(value = "select b from Book b where b.noOfPages <= :b and b.noOfPages >= :a")
    List<Book> getBookByNumberOfPagesRange(int a , int b);
}
