package com.backend.studentRecordSystem.controller;

import com.backend.studentRecordSystem.domain.teacher.CreateTeacherRequest;
import com.backend.studentRecordSystem.domain.teacher.TeacherResponse;
import com.backend.studentRecordSystem.service.teacher.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teachers")
public class TeacherController {
    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @PostMapping
    public ResponseEntity<TeacherResponse> createTeacher(@RequestBody CreateTeacherRequest createTeacherRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(teacherService.createTeacher(createTeacherRequest));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<TeacherResponse> getTeacherByEmail(@PathVariable String email){
        return ResponseEntity.ok(teacherService.getTeacherByEmail(email));
    }
}
