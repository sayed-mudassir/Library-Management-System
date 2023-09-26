package com.example.LibraryManagementSystem.dto.responseDTO;

import com.example.LibraryManagementSystem.Enum.Genre;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookResponse {
    String tittle;

    int noOfPages;

    Genre genre;

    double cost;

    String authorName;
}
