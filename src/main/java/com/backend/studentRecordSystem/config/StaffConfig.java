package com.backend.studentRecordSystem.config;

import com.backend.studentRecordSystem.domain.staff.StaffRepository;
import com.backend.studentRecordSystem.domain.staff.StaffRepositoryImpl;
import com.backend.studentRecordSystem.repository.StaffJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StaffConfig {
    @Bean
    public StaffRepository staffRepository(StaffJpaRepository staffJpaRepository){
        return new StaffRepositoryImpl(staffJpaRepository);
    }
}
