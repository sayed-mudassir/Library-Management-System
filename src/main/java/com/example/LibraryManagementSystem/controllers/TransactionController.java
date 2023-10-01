package com.example.LibraryManagementSystem.controllers;

import com.example.LibraryManagementSystem.dto.responseDTO.IssueBookResponse;
import com.example.LibraryManagementSystem.service.impl.TransactionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("transaction")
public class TransactionController {

    @Autowired
    TransactionServiceImpl transactionServiceImpl;
    @GetMapping("/issue-book/book-id/{book-id}/student-id/{student-id}")
    public ResponseEntity getTransaction(@PathVariable("book-id")int bookId, @PathVariable("student-id") int studentId){

        try {
            IssueBookResponse issueBookResponse = transactionServiceImpl.getTransaction(bookId, studentId);
            return new ResponseEntity(issueBookResponse,HttpStatus.FOUND);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
