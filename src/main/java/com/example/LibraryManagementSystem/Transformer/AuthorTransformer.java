package com.example.LibraryManagementSystem.Transformer;

import com.example.LibraryManagementSystem.dto.responseDTO.AuthorResponse;
import com.example.LibraryManagementSystem.dto.responseDTO.AuthorWithBooklist;
import com.example.LibraryManagementSystem.model.Author;

import java.util.List;

public class AuthorTransformer {
    public static Author PrepareAuthor (String name,int age, String email){
        return Author.builder()
                .age(age)
                .name(name)
                .email(email)
                .build();
    }
    public static AuthorResponse AuthorToAuthorResponse(Author author){
        return AuthorResponse.builder()
                .age(author.getAge())
                .name(author.getName())
                .email(author.getEmail())
                .build();
    }

}
