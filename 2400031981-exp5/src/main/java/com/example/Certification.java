package com.example;

import org.springframework.stereotype.Component;

@Component
public class Certification {

    private int id;
    private String name;
    private String dateOfCompletion;

    public Certification() {
        this.id = 101;
        this.name = "Java Full Stack";
        this.dateOfCompletion = "10-Jan-2026";
    }

    @Override
    public String toString() {
        return "Certification [id=" + id +
               ", name=" + name +
               ", dateOfCompletion=" + dateOfCompletion + "]";
    }
}