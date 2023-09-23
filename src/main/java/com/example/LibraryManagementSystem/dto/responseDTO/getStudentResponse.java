package com.example.LibraryManagementSystem.dto.responseDTO;

import com.example.LibraryManagementSystem.Enum.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class getStudentResponse {
    String name;
    int age;

    String email;

    Gender gender;

    String libraryCardNo;
}
