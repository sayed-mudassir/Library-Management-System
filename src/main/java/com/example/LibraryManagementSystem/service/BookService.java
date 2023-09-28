package com.example.LibraryManagementSystem.service;

import com.example.LibraryManagementSystem.Enum.Genre;
import com.example.LibraryManagementSystem.Transformer.BookTransformer;
import com.example.LibraryManagementSystem.dto.requestDTO.BookRequest;
import com.example.LibraryManagementSystem.dto.responseDTO.BookResponse;
import com.example.LibraryManagementSystem.exceptions.AuthorNotFoundException;
import com.example.LibraryManagementSystem.exceptions.BookNotFoundException;
import com.example.LibraryManagementSystem.model.Author;
import com.example.LibraryManagementSystem.model.Book;
import com.example.LibraryManagementSystem.repository.AuthorRepository;
import com.example.LibraryManagementSystem.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    AuthorRepository authorRepository ;
    @Autowired
    BookRepository bookRepository;

    public BookResponse addBook(BookRequest bookRequest) {
        Optional<Author> authorOptional = authorRepository.findById(bookRequest.getAuthorId());
        if(authorOptional.isEmpty()) throw new AuthorNotFoundException("Invalid author id !!");
        Book book = BookTransformer.BookRequestToBook(bookRequest,authorOptional.get());
        Author author = authorOptional.get();
        author.getBook().add(book);
        authorRepository.save(author);
        return BookTransformer.BookToBookResponse(book);




    }
    public BookResponse getBook(int id) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isEmpty()) throw new BookNotFoundException("invalid book Id");
        return BookTransformer.BookToBookResponse(bookOptional.get());
    }

    public String updateGenre(int id,Genre genre) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if(bookOptional.isEmpty()) throw new BookNotFoundException("invalid Book Id");
        String prev = String.valueOf(bookOptional.get().getGenre());
        bookOptional.get().setGenre(genre);
        String updatedGenre = String.valueOf((bookOptional.get().getGenre()));
        bookRepository.save(bookOptional.get());
        return "genre of book has been updated from this "+prev+" to "+updatedGenre;

    }

    public List<BookResponse> getByGenre(Genre genre) {
        List<Book> bookList = bookRepository.findByGenre(genre);
        List<BookResponse> bookResponseList = new ArrayList<>();
        for(Book book:bookList){
            BookResponse bookResponse = BookTransformer.BookToBookResponse(book);
            bookResponseList.add(bookResponse);
        }
        return bookResponseList;
    }

    public List<BookResponse> getByGenreAndCostGreaterThan(Genre genre, double cost) {
        List<Book> bookList = bookRepository.getByGenreAndCostGreaterThan(genre,cost);

        List<BookResponse> bookResponseList = new ArrayList<>();
        for (Book book: bookList){
            bookResponseList.add(BookTransformer.BookToBookResponse(book));
        }
        return bookResponseList;
    }

    public List<BookResponse> getBookByNumberOfPagesRange(int a, int b) {
        List<Book> bookList = bookRepository.getBookByNumberOfPagesRange(a,b);
        List<BookResponse> bookResponseList = new ArrayList<>();
        for (Book book : bookList){
            BookResponse bookResponse = BookTransformer.BookToBookResponse(book);
            bookResponseList.add(bookResponse);
        }
        return bookResponseList;
    }

    public List<String> getAuthorByGenre(Genre genre) {
        List<Book> bookList = bookRepository.findByGenre(genre);
        List<String> authorNames = new ArrayList<>();
        for (Book book : bookList){
            if(!authorNames.contains(book.getAuthor().getName())) authorNames.add(book.getAuthor().getName());
        }
        return authorNames;
    }
}
