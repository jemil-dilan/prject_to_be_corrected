package com.backend.studentRecordSystem.config;

import com.backend.studentRecordSystem.domain.staff.StaffRepository;
import com.backend.studentRecordSystem.domain.teacher.TeacherFactory;
import com.backend.studentRecordSystem.domain.teacher.TeacherFactoryImpl;
import com.backend.studentRecordSystem.domain.teacher.TeacherRepository;
import com.backend.studentRecordSystem.domain.teacher.TeacherRepositoryImpl;
import com.backend.studentRecordSystem.repository.TeacherJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TeacherConfig {
    @Bean
    public TeacherRepository teacherRepository(TeacherJpaRepository teacherJpaRepository){
        return new TeacherRepositoryImpl(teacherJpaRepository);
    }

    @Bean
    public TeacherFactory teacherFactory(TeacherRepository teacherRepository, StaffRepository staffRepository){
        return new TeacherFactoryImpl(teacherRepository, staffRepository);
    }
}
