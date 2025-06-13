package com.backend.studentRecordSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface SpringRepository <T,ID> extends JpaRepository<T,ID> {
}
