package edu.umb.cs681.HW02;

public class Course {

    private String name;
    private String code;
    private int credit;

    public Course(String name, String code, int creditHours) {
        this.name = name;
        this.code = code;
        this.credit = creditHours;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public int getCredit() {
        return credit;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setCredit(int creditHours) {
        this.credit = creditHours;
    }
}
