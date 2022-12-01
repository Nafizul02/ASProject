package com.example.schedulab;

import java.util.List;
import java.util.Map;

public class TestEntry {

    private String name;
    private String code;
    List<String> prereqs;

    public TestEntry(){

    }

    public TestEntry(String cName, String cCode, List<String> cP){
        name = cName;
        code = cCode;
        prereqs = cP;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public List<String> getPrereqs() {
        return prereqs;
    }
    public void setPrereqs(List<String> prereqs) {
        this.prereqs = prereqs;
    }

}
