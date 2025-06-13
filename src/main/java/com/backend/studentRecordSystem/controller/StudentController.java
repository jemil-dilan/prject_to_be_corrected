package com.backend.studentRecordSystem.controller;

import com.backend.studentRecordSystem.dto.student.CreateStudentDTO;
import com.backend.studentRecordSystem.dto.student.StudentDTO;
import com.backend.studentRecordSystem.service.student.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<StudentDTO>> getAllStudents(){
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable UUID id){
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable UUID id){
        studentService.deleteStudent(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping
    public ResponseEntity<StudentDTO> createStudent(@RequestBody CreateStudentDTO createStudentDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.createStudent(createStudentDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateStudent(@PathVariable UUID id, @RequestBody CreateStudentDTO createStudentDTO){
        studentService.updateStudent(id,createStudentDTO);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
