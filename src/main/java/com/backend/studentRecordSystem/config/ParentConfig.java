package com.backend.studentRecordSystem.config;


import com.backend.studentRecordSystem.factory.parent.ParentFactory;
import com.backend.studentRecordSystem.factory.parent.ParentFactoryImpl;
import com.backend.studentRecordSystem.repository.ParentRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ParentConfig {
    @Bean
    public ParentFactory parentFactory(ParentRepository parentRepository){
        return new ParentFactoryImpl(parentRepository);
    }
}
