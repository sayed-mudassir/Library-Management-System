package com.example.LibraryManagementSystem.dto.requestDTO;

import com.example.LibraryManagementSystem.Enum.Genre;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookRequest {
    String tittle;

    int noOfPages;

    Genre genre;

    double cost;

    int authorId;
}
