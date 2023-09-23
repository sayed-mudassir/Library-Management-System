package com.example.LibraryManagementSystem.service;

import com.example.LibraryManagementSystem.Enum.CardStatus;
import com.example.LibraryManagementSystem.Enum.Gender;
import com.example.LibraryManagementSystem.dto.requestDTO.StudentRequest;
import com.example.LibraryManagementSystem.dto.responseDTO.StudentResponse;
import com.example.LibraryManagementSystem.dto.responseDTO.getStudentResponse;
import com.example.LibraryManagementSystem.model.LibraryCard;
import com.example.LibraryManagementSystem.repository.StudentRepository;
import com.example.LibraryManagementSystem.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;
    public StudentResponse addStudent(StudentRequest studentRequest) {
        Student student = new Student();
        student.setAge(studentRequest.getAge());
        student.setName(studentRequest.getName());
        student.setEmail(studentRequest.getEmail());
        student.setGender(studentRequest.getGender());

        LibraryCard libraryCard = new LibraryCard();
        libraryCard.setCardNo(String.valueOf(UUID.randomUUID()));
        libraryCard.setCardStatus(CardStatus.ACTIVE);
        libraryCard.setStudent(student);
        student.setLibraryCard(libraryCard);
        Student savedStudent = studentRepository.save(student);

        StudentResponse studentResponse = new StudentResponse();
        studentResponse.setName(savedStudent.getName());
        studentResponse.setEmail(savedStudent.getEmail());
        studentResponse.setMessaage("you have been registered");
        studentResponse.setCardIssuedNo(savedStudent.getLibraryCard().getCardNo());
        return studentResponse;
    }

    public getStudentResponse getStudent(int regNo) {
        Optional<Student> studentOptional = studentRepository.findById(regNo);
        if(studentOptional.isPresent()) {
            getStudentResponse getStudentResponse = new getStudentResponse();
            getStudentResponse.setName(studentOptional.get().getName());
            getStudentResponse.setEmail(studentOptional.get().getEmail());
            getStudentResponse.setGender(studentOptional.get().getGender());
            getStudentResponse.setAge(studentOptional.get().getAge());
            getStudentResponse.setLibraryCardNo(studentOptional.get().getLibraryCard().getCardNo());
            return getStudentResponse;
        }
        return null;
    }
    public String deleteStudent(int regNo){
        Optional<Student> studentPresent = studentRepository.findById(regNo);
        if(studentPresent.isPresent()) {
            studentRepository.delete(studentPresent.get());
            return "student deleted";
        }
        return null;
    }

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

    public Object getAllStudents() {
        return studentRepository.findAll();
    }

    public List<String> getAllMale(Gender gender){
        List<String> names = new ArrayList<>();
        List<Student> maleStudent = studentRepository.findByGender(gender);
        for(Student s : maleStudent){
            names.add(s.getName());
        }
        return names;
    }
}
