package com.example.LibraryManagementSystem.service;

import com.example.LibraryManagementSystem.Enum.Genre;
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
        Book book = new Book();
        Author author = authorOptional.get();
        book.setCost(bookRequest.getCost());
        book.setGenre(bookRequest.getGenre());
        book.setTittle(bookRequest.getTittle());
        book.setNoOfPages(bookRequest.getNoOfPages());
        book.setAuthor(author);
        author.getBook().add(book);
        authorRepository.save(author);
        BookResponse bookResponse = new BookResponse();
        bookResponse.setTittle(book.getTittle());
        bookResponse.setGenre(book.getGenre());
        bookResponse.setAuthorName(book.getAuthor().getName());
        bookResponse.setNoOfPages(book.getNoOfPages());
        bookResponse.setCost(book.getCost());
        return bookResponse;



    }
    public BookResponse getBook(int id) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isEmpty()) throw new BookNotFoundException("invalid book Id");
        BookResponse bookResponse = new BookResponse();
        bookResponse.setTittle(bookOptional.get().getTittle());
        bookResponse.setCost(bookOptional.get().getCost());
        bookResponse.setGenre(bookOptional.get().getGenre());
        bookResponse.setAuthorName(bookOptional.get().getAuthor().getName());
        bookResponse.setNoOfPages(bookOptional.get().getNoOfPages());
        return bookResponse;
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
            BookResponse bookResponse = new BookResponse();
            bookResponse.setTittle(book.getTittle());
            bookResponse.setCost(book.getCost());
            bookResponse.setGenre(book.getGenre());
            bookResponse.setAuthorName(book.getAuthor().getName());
            bookResponse.setNoOfPages(book.getNoOfPages());
            bookResponseList.add(bookResponse);
        }
        return bookResponseList;
    }

    public List<BookResponse> getByGenreAndCostGreaterThan(Genre genre, double cost) {
        List<Book> bookList= bookRepository.getByGenreAndCostGreaterThan(genre,cost);
        List<BookResponse> bookResponseList = new ArrayList<>();
        for (Book b : bookList){
            BookResponse bookResponse = new BookResponse();
            bookResponse.setTittle(b.getTittle());
            bookResponse.setCost(b.getCost());
            bookResponse.setGenre(b.getGenre());
            bookResponse.setAuthorName(b.getAuthor().getName());
            bookResponse.setNoOfPages(b.getNoOfPages());
            bookResponseList.add(bookResponse);
        }
        return bookResponseList;
    }

    public List<BookResponse> getBookByNumberOfPagesRange(int a, int b) {
        List<Book> bookList = bookRepository.getBookByNumberOfPagesRange(a,b);
        List<BookResponse> bookResponseList = new ArrayList<>();
        for (Book book : bookList){
            BookResponse bookResponse = new BookResponse();
            bookResponse.setTittle(book.getTittle());
            bookResponse.setCost(book.getCost());
            bookResponse.setGenre(book.getGenre());
            bookResponse.setAuthorName(book.getAuthor().getName());
            bookResponse.setNoOfPages(book.getNoOfPages());
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
