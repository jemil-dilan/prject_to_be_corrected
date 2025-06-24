package com.backend.studentRecordSystem;

import com.backend.studentRecordSystem.factory.NotificationManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class StudentRecordSystemApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(StudentRecordSystemApplication.class, args);

		var notificationManager = context.getBean(NotificationManager.class);

		notificationManager.sendNotification("hello");
	}
}
