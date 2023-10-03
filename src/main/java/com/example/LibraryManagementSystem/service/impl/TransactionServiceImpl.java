package com.example.LibraryManagementSystem.service.impl;

import com.example.LibraryManagementSystem.Enum.TransactionStatus;
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
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class TransactionServiceImpl {
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    JavaMailSender javaMailSender;

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

        //  send an email
        String text = "Dear, "+ student.getName()+"\n\n"+"This is to notify you that the book: "+ book.getTittle() +" by "+book.getAuthor().getName()
                +",has been issued to you.The transaction number is: "+savedTransaction.getTransactionNumber()+"\n\n"
                +"Book Details:\n"+"Title: "+book.getTittle()+"\n"+"Author: "+book.getAuthor().getName()+"\n"+"Issue Date: "+savedTransaction.getTransactionTime()+"\n\n"
                +"Please take note of the due date and ensure the book is returned by then to avoid any late fees.\n\n"+"Happy reading!";

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("springlibraryapp@gmail.com");
        simpleMailMessage.setTo(student.getEmail());
        simpleMailMessage.setSubject("Congrats!! Book Issued");
        simpleMailMessage.setText(text);

        javaMailSender.send(simpleMailMessage);

        return TransactionTransformer.PrepareIssueBookResponse(savedBook,savedStudent,savedTransaction);
    }

    public IssueBookResponse returnBookTransaction(int bookId, int studentId) {
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        if(bookOptional.isEmpty()) throw new BookNotFoundException("Invalid Book Id!!!!!");
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        if (studentOptional.isEmpty()) throw new StudentNotFoundException("Invalid Student Id!!!!!");
        Book book = bookOptional.get();
        if (!book.isIssued()) throw new BookNotAvailableException("book has not been issued yet");
        Student student = studentOptional.get();

//       create Transaction
        Transaction transaction = TransactionTransformer.PrepareTransaction(book,student);
        Transaction savedTransaction = transactionRepository.save(transaction);
        book.setIssued(false);
        book.getTransactions().add(savedTransaction);
        student.getLibraryCard().getTransactions().add(savedTransaction);
        Book savedBook = bookRepository.save(book);
        Student savedStudent = studentRepository.save(student);

        //  send an email
        String text = "Dear, "+ student.getName()+"\n\n"+"This is to notify you that the book: "+ book.getTittle() +" by "+book.getAuthor().getName()
                +",has been returned by you.The transaction number is: "+savedTransaction.getTransactionNumber()+"\n\n"
                +"Book Details:\n"+"Title: "+book.getTittle()+"\n"+"Author: "+book.getAuthor().getName()+"\n"+"Issue Date: "+savedTransaction.getTransactionTime()+"\n\n"
                +"Please visit again\n\n"+"Happy reading!";

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("springlibraryapp@gmail.com");
        simpleMailMessage.setTo(student.getEmail());
        simpleMailMessage.setSubject("Thanks!! Book Returned");
        simpleMailMessage.setText(text);

        javaMailSender.send(simpleMailMessage);


        return TransactionTransformer.PrepareIssueBookResponse(savedBook,savedStudent,savedTransaction);
    }
}
