package com.example.LibraryManagementSystem.service;

import com.example.LibraryManagementSystem.Enum.Genre;
import com.example.LibraryManagementSystem.Transformer.BookTransformer;
import com.example.LibraryManagementSystem.dto.requestDTO.BookRequest;
import com.example.LibraryManagementSystem.dto.responseDTO.BookResponse;
import com.example.LibraryManagementSystem.exceptions.AuthorNotFoundException;
import com.example.LibraryManagementSystem.exceptions.BookNotFoundException;
import com.example.LibraryManagementSystem.model.Author;
import com.example.LibraryManagementSystem.model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface BookService {
    BookResponse addBook(BookRequest bookRequest);

    BookResponse getBook(int id);

    String updateGenre(int id, Genre genre);

    List<BookResponse> getByGenre(Genre genre);

    List<BookResponse> getByGenreAndCostGreaterThan(Genre genre, double cost);

    List<BookResponse> getBookByNumberOfPagesRange(int a, int b);

    List<String> getAuthorByGenre(Genre genre);
}
