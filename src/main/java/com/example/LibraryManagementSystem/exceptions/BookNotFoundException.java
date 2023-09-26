package com.example.LibraryManagementSystem.exceptions;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(String message){
        super(message);
    }
}
