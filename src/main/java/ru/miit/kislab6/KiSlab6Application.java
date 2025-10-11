package ru.miit.kislab6;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "KISlab6 API", version = "v1", description = "API для лабораторной работы №7"))
public class KiSlab6Application {

    public static void main(String[] args) {
        SpringApplication.run(KiSlab6Application.class, args);
    }

}
