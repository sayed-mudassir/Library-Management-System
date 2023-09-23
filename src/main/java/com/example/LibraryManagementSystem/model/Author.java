package com.example.LibraryManagementSystem.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.UpdateTimestamp;

import javax.annotation.processing.Generated;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int Id;

    String name;

    int age;

    @Column(unique = true,nullable = false)
    String email;

    @UpdateTimestamp
    Date lastActivity;

    @OneToMany(mappedBy = "author",cascade = CascadeType.ALL)
    List<Book> book = new ArrayList<>();
}
