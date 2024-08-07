package com.spring.Uben.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class HomeModel {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private long id;

		@Column(name = "name")
		private String name;

		@Column(name = "age")
		private int age;

		@Column(name = "course")
		private String course;

		public HomeModel(String name, int age, String course) {
			this.name = name;
			this.age = age;
			this.course = course;
		}

		public HomeModel() {
		}

		public long getId() {
			return id;
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

		public String getCourse() {
			return course;
		}

		public void getCourse(String course) {
			this.course = course;
		}

		@Override
		public String toString() {
			return "User [id=" + id + ", name=" + name + ", age=" + age + ", course=" + course + "]";
		}
}
