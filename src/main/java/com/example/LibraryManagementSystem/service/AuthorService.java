package com.example.LibraryManagementSystem.service;

import com.example.LibraryManagementSystem.Transformer.AuthorTransformer;
import com.example.LibraryManagementSystem.dto.responseDTO.AuthorResponse;
import com.example.LibraryManagementSystem.dto.responseDTO.AuthorWithBooklist;
import com.example.LibraryManagementSystem.exceptions.AuthorNotFoundException;
import com.example.LibraryManagementSystem.model.Author;
import com.example.LibraryManagementSystem.model.Book;
import com.example.LibraryManagementSystem.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    @Autowired
    AuthorRepository authorRepository;

    public String addAuthor(String name , int age ,String email) {
        Author author = AuthorTransformer.PrepareAuthor(name,age,email);
        authorRepository.save(author);
        return "author added !!!!!";
    }

    public AuthorResponse getAuthor(int id) {
        Optional<Author> optionalAuthor = authorRepository.findById(id);
        if(optionalAuthor.isEmpty()) throw new AuthorNotFoundException("invalid ID !!!!");

        return AuthorTransformer.AuthorToAuthorResponse(optionalAuthor.get());
    }

    public AuthorWithBooklist getAuthorWithBooklist(int id) {
        Optional<Author> authorOptional = authorRepository.findById(id);
        if(authorOptional.isEmpty()) throw new AuthorNotFoundException("Invalid ID !!!!!");
        AuthorWithBooklist authorWithBooklist = new AuthorWithBooklist();
        authorWithBooklist.setName(authorOptional.get().getName());
        authorWithBooklist.setAge(authorOptional.get().getAge());
        authorWithBooklist.setEmail(authorOptional.get().getEmail());
        List<String> books = new ArrayList<>();
        List<Book> bookList = authorOptional.get().getBook();
        for (Book b :bookList){
            books.add(b.getTittle());
        }
        authorWithBooklist.setBooks(books);
        return authorWithBooklist;
    }

    public String updateEmail(int id, String email) {
        Optional<Author> authorOptional = authorRepository.findById(id);
        if (authorOptional.isEmpty()) throw new AuthorNotFoundException("Invalid Author Id!!!!!");
        String prevEmail = authorOptional.get().getEmail();
        authorOptional.get().setEmail(email);
        authorRepository.save(authorOptional.get());
        return "email updated from "+prevEmail+" to "+authorOptional.get().getEmail();
    }

    public List<String> authorWithNNumberOfBooks(int n) {
        List<Author> authorList = authorRepository.findAll();
        List<String> authorNameList = new ArrayList<>();
        for (Author a : authorList)
            if(a.getBook().size()>=n) authorNameList.add(a.getName());
        return authorNameList;
    }
}
