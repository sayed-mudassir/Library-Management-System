package com.example.LibraryManagementSystem.dto.responseDTO;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentResponse {
    String name;

    String email;

    String messaage;

    String cardIssuedNo;

}
