package com.example.LibraryManagementSystem.model;

import com.example.LibraryManagementSystem.Enum.Gender;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int regNo;
    String name;
    int age;
    @Column(unique = true,nullable = false)
    String email;
    @Enumerated(EnumType.STRING)
    Gender gender;
    @OneToOne(mappedBy = "student",cascade = CascadeType.ALL)
    LibraryCard libraryCard;

}
