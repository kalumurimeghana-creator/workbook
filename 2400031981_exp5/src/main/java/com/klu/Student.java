package com.klu;


	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Component;

	@Component
	public class Student {

	    private int id;
	    private String name;
	    private String gender;

	    @Autowired
	    private Certification certification;

	    public Student() {
	        this.id = 1;
	        this.name = "Meghana";
	        this.gender = "female";
	    }

	    public void displayDetails() {
	        System.out.println("Student ID   : " + id);
	        System.out.println("Student Name : " + name);
	        System.out.println("Gender       : " + gender);
	        System.out.println("Certification: " + certification);
	    }
	}

