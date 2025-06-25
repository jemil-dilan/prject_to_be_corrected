package com.backend.studentRecordSystem.config;

import com.backend.studentRecordSystem.domain.student.StudentFactory;
import com.backend.studentRecordSystem.domain.student.StudentFactoryImpl;
import com.backend.studentRecordSystem.repository.StudentJpaRepository;
import com.backend.studentRecordSystem.repository.StudentRepository;
import com.backend.studentRecordSystem.repository.StudentRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {

    @Bean
    public StudentFactory studentFactory(StudentRepository studentStudentRepository){
        return new StudentFactoryImpl(studentStudentRepository);
    }

    @Bean
    public StudentRepository studentRepository(StudentJpaRepository studentJpaRepository) {
        return new StudentRepositoryImpl(studentJpaRepository);
    }

}
