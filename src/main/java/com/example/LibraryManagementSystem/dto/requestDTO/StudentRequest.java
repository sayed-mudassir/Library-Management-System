package com.example.LibraryManagementSystem.dto.requestDTO;

import com.example.LibraryManagementSystem.Enum.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentRequest {
    String name;
    int age;
    String email;
    Gender gender;
}
