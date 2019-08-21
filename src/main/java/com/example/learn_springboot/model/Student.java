package com.example.learn_springboot.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="STUDENT")
public class Student  implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String passportNumber;
	

	public Student(Object object, Object object2, Object object3) {
		this.id = id;
		this.name = name;
		this.passportNumber = passportNumber;
	}

	public Student(Long id, String name, String passportNumber) {
		this.id = id;
		this.name = name;
		this.passportNumber = passportNumber;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassportNumber() {
		return this.passportNumber;
	}

	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}

	public Student id(Long id) {
		this.id = id;
		return this;
	}

	public Student name(String name) {
		this.name = name;
		return this;
	}

	public Student passportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
		return this;
	}

	@Override
	public String toString() {
		return "{" +
			" id='" + getId() + "'" +
			", name='" + getName() + "'" +
			", passportNumber='" + getPassportNumber() + "'" +
			"}";
	}

}