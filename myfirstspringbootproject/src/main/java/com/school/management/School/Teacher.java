package com.school.management.School;


//@Component("teacher")
public class Teacher {

    private String name;

    public Teacher() {
        this.name = "kanna";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "name=" + name +
                '}';
    }

}
