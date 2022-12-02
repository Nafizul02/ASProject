package com.example.schedulab;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.io.Serializable;

public class User implements Serializable {
    //
    // Map<String, String> coursesTaken;
    public List<String> coursesTaken;
    public String age;
    public String email;
    public String fullName;
    public String studentId;
    public String type;

    public User() {
        this.coursesTaken = new ArrayList<>();
    }
}