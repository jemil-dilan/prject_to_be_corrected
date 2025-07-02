package com.backend.studentRecordSystem.config;

import com.backend.studentRecordSystem.domain.classroom.ClassroomRepository;
import com.backend.studentRecordSystem.domain.classroom.ClassroomRepositoryImpl;
import com.backend.studentRecordSystem.domain.classroom.SchoolClassFactory;
import com.backend.studentRecordSystem.domain.classroom.SchoolClassFactoryImpl;
import com.backend.studentRecordSystem.repository.ClassroomSpringRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClassroomConfig {

    @Bean
    public SchoolClassFactory schoolClassFactory(ClassroomSpringRepository classroomSpringRepository) {
        return new SchoolClassFactoryImpl(classroomSpringRepository);
    }

    @Bean
    public ClassroomRepository classroomRepository(ClassroomSpringRepository classroomSpringRepository){
        return new ClassroomRepositoryImpl(classroomSpringRepository);
    }
}
