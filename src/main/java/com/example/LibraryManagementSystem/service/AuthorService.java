package com.example.LibraryManagementSystem.service;

import com.example.LibraryManagementSystem.Transformer.AuthorTransformer;
import com.example.LibraryManagementSystem.dto.responseDTO.AuthorResponse;
import com.example.LibraryManagementSystem.exceptions.AuthorNotFoundException;
import com.example.LibraryManagementSystem.model.Author;
import com.example.LibraryManagementSystem.model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface AuthorService {

    String addAuthor(String name , int age ,String email);

    AuthorResponse getAuthor(int id);

    List<String> getAuthorWithBooklist(int id);

    String updateEmail(int id, String email);

    List<String> authorWithNNumberOfBooks(int n);
}
