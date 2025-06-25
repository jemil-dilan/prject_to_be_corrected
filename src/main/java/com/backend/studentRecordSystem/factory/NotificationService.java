package com.backend.studentRecordSystem.factory;

import org.springframework.stereotype.Service;

@Service
public interface NotificationService {

    void send(String message);
}
