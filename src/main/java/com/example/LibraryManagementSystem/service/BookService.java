package com.example.LibraryManagementSystem.service;

import com.example.LibraryManagementSystem.exceptions.AuthorNotFoundException;
import com.example.LibraryManagementSystem.model.Author;
import com.example.LibraryManagementSystem.model.Book;
import com.example.LibraryManagementSystem.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService {
    @Autowired
    AuthorRepository authorRepository ;

    public String addBook(Book book) {
        Optional<Author> authorOptional = authorRepository.findById(book.getAuthor().getId());
        if(authorOptional.isEmpty()) throw new AuthorNotFoundException("Invalid author id !!");
        Author author = authorOptional.get();
        book.setAuthor(author);
        author.getBook().add(book);
        authorRepository.save(author);

        return "book added !!!!";



    }
}
