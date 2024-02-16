package edu.umb.cs681.HW02;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Student {

    private String name, major;
    private String id;
    private List<Course> courses;
    private Map<Course, Grade> grades;

    public Student(String name, String major, String id) {
        this.name = name;
        this.major = major;
        this.id = id;
        this.courses = new ArrayList<>();
        this.grades = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public Map<Course, Grade> getGrades() {
        return grades;
    }

    public void setGrades(Map<Course, Grade> grades) {
        this.grades = grades;
    }

    public void courseEnrollment(Course course){
        courses.add(course);
    }

    public void giveGrade(Course course, Grade grade){
        if(courses.contains(course)){
            grades.put(course,grade);
        } else {
            System.out.println("You are not enrolled in this course: " + course.getName());
        }
    }
}
