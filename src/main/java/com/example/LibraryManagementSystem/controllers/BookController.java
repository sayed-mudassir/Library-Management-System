package com.example.LibraryManagementSystem.controllers;

import com.example.LibraryManagementSystem.Enum.Genre;
import com.example.LibraryManagementSystem.dto.requestDTO.BookRequest;
import com.example.LibraryManagementSystem.dto.responseDTO.BookResponse;
import com.example.LibraryManagementSystem.service.impl.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("book")
public class BookController {
    @Autowired
    BookServiceImpl bookServiceImpl;

    @PostMapping("/add")
    public ResponseEntity addBook (@RequestBody BookRequest book){
        try{
            BookResponse addedBook = bookServiceImpl.addBook(book);
            return new ResponseEntity(addedBook, HttpStatus.CREATED);
        }
        catch (Exception e){
        return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }
    @GetMapping("/get")
    public ResponseEntity getBook (@RequestParam("id") int id){
        try{
            BookResponse bookResponse = bookServiceImpl.getBook(id);
            return new ResponseEntity(bookResponse,HttpStatus.FOUND);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
        @PutMapping("/update-genre/{id}")
    public ResponseEntity updateGenre (@PathVariable("id") int id ,@RequestParam("genre") Genre genre){
        try{
            String updateMessage = bookServiceImpl.updateGenre(id,genre);
            return new ResponseEntity(updateMessage,HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/get-book-by-genre")
    public ResponseEntity getByGenre(@RequestParam("genre") Genre genre){
        return new ResponseEntity(bookServiceImpl.getByGenre(genre),HttpStatus.FOUND);
    }
    @GetMapping("/get-genre-by-cost")
    public ResponseEntity getByGenreAndCostGreaterThan(@RequestParam("genre") Genre genre, @RequestParam("cost") double cost){
        return new ResponseEntity(bookServiceImpl.getByGenreAndCostGreaterThan(genre,cost),HttpStatus.FOUND);
    }
    @GetMapping("/get-books-by-pages")
    public ResponseEntity getBookByNumberOfPagesRange(@RequestParam("a") int a, @RequestParam("b") int b){
        return new ResponseEntity(bookServiceImpl.getBookByNumberOfPagesRange(a,b),HttpStatus.FOUND);
    }
    @GetMapping("/get-author-by-genre")
    public ResponseEntity getAuthorByGenre(@RequestParam("genre") Genre genre){
        return new ResponseEntity(bookServiceImpl.getAuthorByGenre(genre),HttpStatus.FOUND);
    }
}
