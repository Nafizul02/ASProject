package com.example.schedulab;

import java.util.ArrayList;

public class User {
    String fullName;
    String age;
    String email;
    String studentID;
    ArrayList<String> coursesTaken;

    public User(String fullName, String age, String email, String studentID, ArrayList<String> coursesTaken) {
        this.fullName = fullName;
        this.age = age;
        this.email = email;
        this.studentID = studentID;
        this.coursesTaken = coursesTaken;
    }

    public User(){this.coursesTaken = new ArrayList<>();}

    public String getFullName() {
        return fullName;
    }

    public String getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getStudentID() {
        return studentID;
    }

    public ArrayList<String> getCoursesTaken() {
        return coursesTaken;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public void setCoursesTaken(ArrayList<String> coursesTaken) {
        this.coursesTaken = coursesTaken;
    }
}
