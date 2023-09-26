package com.example.LibraryManagementSystem.dto.responseDTO;

import com.example.LibraryManagementSystem.model.Book;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AuthorWithBooklist extends AuthorResponse {
    List<String> books = new ArrayList<>();

}
