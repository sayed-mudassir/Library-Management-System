package com.example.LibraryManagementSystem.service;

import com.example.LibraryManagementSystem.Transformer.TransactionTransformer;
import com.example.LibraryManagementSystem.dto.responseDTO.IssueBookResponse;
import com.example.LibraryManagementSystem.exceptions.BookNotAvailableException;
import com.example.LibraryManagementSystem.exceptions.BookNotFoundException;
import com.example.LibraryManagementSystem.exceptions.StudentNotFoundException;
import com.example.LibraryManagementSystem.model.Book;
import com.example.LibraryManagementSystem.model.Student;
import com.example.LibraryManagementSystem.model.Transaction;
import com.example.LibraryManagementSystem.repository.BookRepository;
import com.example.LibraryManagementSystem.repository.StudentRepository;
import com.example.LibraryManagementSystem.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class TransactionService {
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    StudentRepository studentRepository;

    public IssueBookResponse getTransaction(int bookId, int studentId) {
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        if(bookOptional.isEmpty()) throw new BookNotFoundException("Invalid Book Id!!!!!");
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        if (studentOptional.isEmpty()) throw new StudentNotFoundException("Invalid Student Id!!!!!");
        Book book = bookOptional.get();
        if (book.isIssued()) throw new BookNotAvailableException("book already issued");
        Student student = studentOptional.get();

//       create Transaction
        Transaction transaction = TransactionTransformer.PrepareTransaction(book,student);
        Transaction savedTransaction = transactionRepository.save(transaction);
        book.setIssued(true);
        book.getTransactions().add(savedTransaction);
        student.getLibraryCard().getTransactions().add(savedTransaction);
        Book savedBook = bookRepository.save(book);
        Student savedStudent = studentRepository.save(student);

        return TransactionTransformer.PrepareIssueBookResponse(savedBook,savedStudent,savedTransaction);
    }
}
