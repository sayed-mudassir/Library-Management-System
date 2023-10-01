package com.example.LibraryManagementSystem.service;

import com.example.LibraryManagementSystem.Enum.Gender;
import com.example.LibraryManagementSystem.Transformer.LibraryCardTransformer;
import com.example.LibraryManagementSystem.Transformer.StudentTransformer;
import com.example.LibraryManagementSystem.dto.requestDTO.StudentRequest;
import com.example.LibraryManagementSystem.dto.responseDTO.StudentResponse;
import com.example.LibraryManagementSystem.dto.responseDTO.getStudentResponse;
import com.example.LibraryManagementSystem.model.LibraryCard;
import com.example.LibraryManagementSystem.model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface StudentService {

    StudentResponse addStudent(StudentRequest studentRequest);
    getStudentResponse getStudent(int regNo);

    String deleteStudent(int regNo);

    String updateAge(int regNo, int age);

    List<StudentResponse> getAllStudents();

    List<String> getAllMale(Gender gender);

}
