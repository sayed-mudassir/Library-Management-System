package com.example.LibraryManagementSystem.controllers;

import com.example.LibraryManagementSystem.exceptions.AuthorNotFoundException;
import com.example.LibraryManagementSystem.model.Book;
import com.example.LibraryManagementSystem.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("book")
public class BookController {
    @Autowired
    BookService bookService;

    @PostMapping("/add")
    public String addBook (@RequestBody Book book){
        try{
            String addedBook = bookService.addBook(book);
            return addedBook;
        }
        catch (Exception e){
        return e.getMessage();
        }

    }
}
