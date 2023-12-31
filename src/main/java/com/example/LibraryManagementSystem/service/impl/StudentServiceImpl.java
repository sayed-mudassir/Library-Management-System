package com.example.LibraryManagementSystem.service.impl;

import com.example.LibraryManagementSystem.Enum.Gender;
import com.example.LibraryManagementSystem.Transformer.LibraryCardTransformer;
import com.example.LibraryManagementSystem.Transformer.StudentTransformer;
import com.example.LibraryManagementSystem.dto.requestDTO.StudentRequest;
import com.example.LibraryManagementSystem.dto.responseDTO.StudentResponse;
import com.example.LibraryManagementSystem.dto.responseDTO.getStudentResponse;
import com.example.LibraryManagementSystem.model.LibraryCard;
import com.example.LibraryManagementSystem.repository.StudentRepository;
import com.example.LibraryManagementSystem.model.Student;
import com.example.LibraryManagementSystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentRepository studentRepository;
    @Override
    public StudentResponse addStudent(StudentRequest studentRequest) {
        Student student = StudentTransformer.StudentRequestToStudent(studentRequest);
        LibraryCard libraryCard = LibraryCardTransformer.PrepareLibraryCard(student);
        libraryCard.setStudent(student);
        student.setLibraryCard(libraryCard);
        Student savedStudent = studentRepository.save(student);

        StudentResponse studentResponse =  StudentTransformer.StudentToStudentResponse(savedStudent);
        return studentResponse;
    }
    @Override
    public getStudentResponse getStudent(int regNo) {
        Optional<Student> studentOptional = studentRepository.findById(regNo);
        if(studentOptional.isPresent()) {
          return   getStudentResponse.builder()
                    .age(studentOptional.get().getAge())
                    .email(studentOptional.get().getEmail())
                    .name(studentOptional.get().getName())
                    .gender(studentOptional.get().getGender())
                    .libraryCardNo(studentOptional.get().getLibraryCard().getCardNo())
                    .build();
        }
        return null;
    }
    @Override
    public String deleteStudent(int regNo){
        Optional<Student> studentPresent = studentRepository.findById(regNo);
        if(studentPresent.isPresent()) {
            studentRepository.delete(studentPresent.get());
            return "student deleted";
        }
        return null;
    }
    @Override
    public String updateAge(int regNo, int age) {
        Optional<Student> studentPresent = studentRepository.findById(regNo);
        if(studentPresent.isPresent()){
            int prevAge = studentPresent.get().getAge();
            studentPresent.get().setAge(age);
            Student updatedAge =  studentRepository.save(studentPresent.get());
            int changedAge = updatedAge.getAge();
            String a = "age updated from "+prevAge+" to "+changedAge;
            return a;
        }
        return null;
    }
    @Override
    public List<StudentResponse> getAllStudents() {
        List<Student> studentList = studentRepository.findAll();
        List<StudentResponse> studentResponseList = new ArrayList<>();
        for (Student student : studentList){
            studentResponseList.add(StudentTransformer.StudentToStudentResponse(student));
        }
        return studentResponseList;
    }
    @Override
    public List<String> getAllMale(Gender gender){
        List<String> names = new ArrayList<>();
        List<Student> maleStudent = studentRepository.findByGender(gender);
        for(Student s : maleStudent){
            names.add(s.getName());
        }
        return names;
    }
}
