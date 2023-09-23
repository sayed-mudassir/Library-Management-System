package com.example.LibraryManagementSystem.controllers;

import com.example.LibraryManagementSystem.Enum.Gender;
import com.example.LibraryManagementSystem.dto.requestDTO.StudentRequest;
import com.example.LibraryManagementSystem.dto.responseDTO.StudentResponse;
import com.example.LibraryManagementSystem.dto.responseDTO.getStudentResponse;
import com.example.LibraryManagementSystem.service.StudentService;
import com.example.LibraryManagementSystem.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("student")
public class StudentController {
    @Autowired
    StudentService studentService;
    @PostMapping("/add")
    public ResponseEntity addStudent(@RequestBody StudentRequest studentRequest){
        StudentResponse response = studentService.addStudent(studentRequest);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }
    @GetMapping("/get")
    public ResponseEntity getStudent(@RequestParam("id") int regNo){
        getStudentResponse student = studentService.getStudent(regNo);
        if(student != null) return new ResponseEntity(student,HttpStatus.FOUND);
        return new ResponseEntity("Invalid id!!!",HttpStatus.BAD_REQUEST);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteStudent(@PathVariable("id") int regNo){
        String a = studentService.deleteStudent(regNo);
        if(a!=null) return new ResponseEntity(a,HttpStatus.OK);
        return new ResponseEntity("invalid id!!",HttpStatus.BAD_REQUEST);
    }
    @PutMapping("/update-age")
    public ResponseEntity updateAge(@RequestParam("id") int regNo, @RequestParam("age") int age){
        String updateAgeStudent = studentService.updateAge(regNo,age);
        if(updateAgeStudent != null) return new ResponseEntity(updateAgeStudent,HttpStatus.OK);
        return new ResponseEntity("invalid id !!",HttpStatus.BAD_REQUEST);
    }
    @GetMapping("/get-all-students")
    public ResponseEntity getAllStudents(){
        return new ResponseEntity(studentService.getAllStudents(),HttpStatus.FOUND);
    }
    @GetMapping("/get-all-male-student")
    public ResponseEntity getAllMaleStudent(@RequestParam("g")Gender gender){
        List<String> getAllMaleStudent = studentService.getAllMale(gender);
        return new ResponseEntity(getAllMaleStudent,HttpStatus.FOUND);
    }
}
