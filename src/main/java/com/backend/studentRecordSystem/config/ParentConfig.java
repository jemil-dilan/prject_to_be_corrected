package com.backend.studentRecordSystem.config;


import com.backend.studentRecordSystem.domain.parent.ParentFactory;
import com.backend.studentRecordSystem.domain.parent.ParentFactoryImpl;
import com.backend.studentRecordSystem.repository.ParentJpaRepository;
import com.backend.studentRecordSystem.repository.ParentRepository;
import com.backend.studentRecordSystem.repository.ParentRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ParentConfig {
    @Bean
    public ParentRepository parentRepository(ParentJpaRepository parentJpaRepository){
        return new ParentRepositoryImpl(parentJpaRepository);
    }

    @Bean
    public ParentFactory parentFactory(ParentRepository parentRepository){
        return new ParentFactoryImpl(parentRepository);
    }
}
