package com.school.management.School;

import org.springframework.stereotype.Component;

@Component("student")
public class Student {

    private String name;
    private int age;

    public Student() {
        this.name = "Logesh";
        this.age = 21;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name=" + name +
                ", age=" + age +
                '}';
    }
}
