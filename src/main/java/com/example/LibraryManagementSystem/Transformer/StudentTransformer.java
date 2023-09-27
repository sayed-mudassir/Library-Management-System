package com.example.LibraryManagementSystem.Transformer;

import com.example.LibraryManagementSystem.dto.requestDTO.StudentRequest;
import com.example.LibraryManagementSystem.dto.responseDTO.StudentResponse;
import com.example.LibraryManagementSystem.model.Student;

public class StudentTransformer {
    public static Student StudentRequestToStudent(StudentRequest studentRequest){
        return Student.builder()
                .age(studentRequest.getAge())
                .email(studentRequest.getEmail())
                .gender(studentRequest.getGender())
                .build();
    }
    public static StudentResponse StudentToStudentResponse(Student student){
        return StudentResponse.builder()
                .name(student.getName())
                .email(student.getName())
                .build();
    }
}
