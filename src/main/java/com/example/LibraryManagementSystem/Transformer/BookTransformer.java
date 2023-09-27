package com.example.LibraryManagementSystem.Transformer;

import com.example.LibraryManagementSystem.dto.requestDTO.BookRequest;
import com.example.LibraryManagementSystem.dto.responseDTO.BookResponse;
import com.example.LibraryManagementSystem.model.Author;
import com.example.LibraryManagementSystem.model.Book;

public class BookTransformer {
    public static Book BookRequestToBook (BookRequest bookRequest, Author author){
        return Book.builder()
                .author(author)
                .cost(bookRequest.getCost())
                .genre(bookRequest.getGenre())
                .noOfPages(bookRequest.getNoOfPages())
                .tittle(bookRequest.getTittle())
                .build();
    }
    public static BookResponse BookToBookResponse (Book book){
        return BookResponse.builder()
                .tittle(book.getTittle())
                .cost(book.getCost())
                .genre(book.getGenre())
                .noOfPages(book.getNoOfPages())
                .authorName(book.getAuthor().getName())
                .build();
    }
}
