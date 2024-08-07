package com.school.management.School;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component()
@Scope(value="prototype")
public class StudentClass {

    private String name;
    @Autowired
    @Qualifier("teacher")
    private Teacher Teacher;
    @Autowired
    @Qualifier("student")
    private Student Student;

    public StudentClass() {
        this.name = "Class 10";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name + "{"
                + Teacher +
                ", " + Student +
                "}";
    }
}
