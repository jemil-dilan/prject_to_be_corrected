package com.backend.studentRecordSystem.controller;

import com.backend.studentRecordSystem.dto.parent.CreateParentDTO;
import com.backend.studentRecordSystem.dto.parent.ParentDTO;
import com.backend.studentRecordSystem.service.parent.ParentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parents")
public class ParentController {
    private final ParentService parentService;

    public ParentController(ParentService parentService) {
        this.parentService = parentService;
    }

    @PostMapping
    public ResponseEntity<ParentDTO> createParent(@RequestBody CreateParentDTO createParentDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(parentService.createParent(createParentDTO));
    }

    @GetMapping
    public ResponseEntity<List<ParentDTO>> getAllParents() {
        return ResponseEntity.ok(parentService.getAllParents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParentDTO> getParentById(@PathVariable Long id) {
        return ResponseEntity.ok(parentService.getParentById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateParent(@PathVariable Long id, @RequestBody CreateParentDTO createParentDTO) {
        parentService.updateParent(id, createParentDTO);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParent(@PathVariable Long id) {
        parentService.deleteParent(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

