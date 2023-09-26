package com.example.LibraryManagementSystem.model;

import com.example.LibraryManagementSystem.Enum.CardStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class LibraryCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int Id;
    String cardNo;
    @Enumerated(EnumType.STRING)
    CardStatus cardStatus;
    @CreationTimestamp
    Date issudeDate;
    @OneToOne
    @JoinColumn
    Student student;

    @OneToMany(mappedBy = "libraryCard",cascade = CascadeType.ALL)
    List<Transaction> transactions = new ArrayList<>();

}
