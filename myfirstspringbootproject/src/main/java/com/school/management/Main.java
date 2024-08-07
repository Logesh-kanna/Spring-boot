package com.school.management;

import com.school.management.School.StudentClass;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Main {
    public static void main(String [] args) {
        ConfigurableApplicationContext bean = SpringApplication.run(Main.class, args);

        StudentClass studentClass = bean.getBean(StudentClass.class);
        System.out.println(studentClass);
    }
}
