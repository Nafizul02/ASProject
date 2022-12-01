package com.example.schedulab;

import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.Map;

public class Course {
    String code;
    String name;
    ArrayList<String> prereqs;
    Map<String, Boolean> sessions;

    public Course(String code, String name, ArrayList<String> prereqs, Map<String, Boolean> sessions) {
        this.code = code;
        this.name = name;
        this.prereqs = prereqs;
        this.sessions = sessions;
    }

    public Course(){}

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getPrereqs() {
        return prereqs;
    }

    public Map<String, Boolean> getSessions() {
        return sessions;
    }

    @Override
    public String toString() {
        return "Course{" +
                "code='" + code + '\'' +
                '}';
    }
}
