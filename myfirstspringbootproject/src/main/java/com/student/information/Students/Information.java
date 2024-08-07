package com.student.information.Students;

import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class Information {

     public static class Information1 {
        private Integer id;
        private String name;
        private Integer age;
        private String address;

        public Information1(Integer id, String name, Integer age, String address) {
            this.id = id;
            this.name = name;
            this.age = age;
            this.address = address;
        }

        public Integer getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public Integer getAge() {
            return age;
        }

        public String getAddress() {
            return address;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Information1 that = (Information1) o;
            return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(age, that.age) && Objects.equals(address, that.address);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name, age, address);
        }

        @Override
        public String toString() {
            return "Information{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", age=" + age +
                    ", address='" + address + '\'' +
                    '}';
        }
    }

}
