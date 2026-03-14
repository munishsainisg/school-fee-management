package com.school.management.studentfees;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
        info = @Info(
                title = "Student Fee Management API",
                version = "1.0",
                description = "APIs for student registration, fee collection and receipt generation"
        )
)
@SpringBootApplication
public class StudentFeesApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentFeesApplication.class, args);
	}

}
