package com.example.LibraryManagementSystem.Transformer;

import com.example.LibraryManagementSystem.Enum.TransactionStatus;
import com.example.LibraryManagementSystem.dto.responseDTO.IssueBookResponse;
import com.example.LibraryManagementSystem.model.Book;
import com.example.LibraryManagementSystem.model.Student;
import com.example.LibraryManagementSystem.model.Transaction;

import java.util.UUID;

public class TransactionTransformer {
    public static Transaction PrepareTransaction(Book book, Student student){
        return Transaction.builder()
                .transactionNumber(String.valueOf(UUID.randomUUID()))
                .transactionStatus(TransactionStatus.FAILURE)
                .book(book)
                .libraryCard(student.getLibraryCard())
                .build();
    }
    public static IssueBookResponse PrepareIssueBookResponse(Book book, Student student, Transaction transaction){
        return IssueBookResponse.builder()
                .authorName(book.getAuthor().getName())
                .bookName(book.getTittle())
                .libraryCardNumber(student.getLibraryCard().getCardNo())
                .studentName(student.getName())
                .transactionTime(transaction.getTransactionTime())
                .transactionNumber(transaction.getTransactionNumber())
                .transactionStatus(transaction.getTransactionStatus())
                .build();
    }
}
