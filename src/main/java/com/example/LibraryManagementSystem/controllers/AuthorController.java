package com.example.LibraryManagementSystem.controllers;

import com.example.LibraryManagementSystem.dto.responseDTO.AuthorResponse;
//import com.example.LibraryManagementSystem.dto.responseDTO.AuthorWithBooklist;
import com.example.LibraryManagementSystem.service.impl.AuthorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("author")
public class AuthorController {
    @Autowired
    AuthorServiceImpl authorServiceImpl;
    @PostMapping("/add")
    public ResponseEntity addAuthor(@RequestParam("name") String name , @RequestParam("age") int age, @RequestParam("email") String email){
        String response = authorServiceImpl.addAuthor(name,age,email);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity getAuthor(@PathVariable("id") int id){
        try {
            AuthorResponse authorResponse = authorServiceImpl.getAuthor(id);
            return new ResponseEntity(authorResponse,HttpStatus.FOUND);
        }
        catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/get-author-With-BookList/{id}")
    public ResponseEntity getAuthorWithBooklist(@PathVariable("id") int id){
        try {
            List<String> Booklist = authorServiceImpl.getAuthorWithBooklist(id);
            return new ResponseEntity(Booklist,HttpStatus.FOUND);
        }
        catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/update-email/{id}")
    public ResponseEntity updateEmail(@PathVariable("id") int id,@RequestParam("email") String email){
        try {
            String updatedEmail = authorServiceImpl.updateEmail(id,email);
            return new ResponseEntity(updatedEmail,HttpStatus.FOUND);
        }
        catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/autthor-with-n-books")
    public ResponseEntity authorWithNNumberOfBooks(@RequestParam("n")int n){
        List<String> authorList = authorServiceImpl.authorWithNNumberOfBooks(n);
        return new ResponseEntity(authorList,HttpStatus.FOUND);
    }
}
