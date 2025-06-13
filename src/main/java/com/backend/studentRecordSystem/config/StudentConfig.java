package com.backend.studentRecordSystem.config;

import com.backend.studentRecordSystem.factory.student.StudentFactory;
import com.backend.studentRecordSystem.factory.student.StudentFactoryImpl;
import com.backend.studentRecordSystem.repository.StudentRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {

    @Bean
    public StudentFactory studentFactory(StudentRepository studentStudentRepository){
        return new StudentFactoryImpl(studentStudentRepository);
    }

}
