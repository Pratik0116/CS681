package edu.umb.cs681.HW02;

import java.util.*;

public class Main {

    private static void assignRandomGrades(Student student, List<Course> courses) {
        Random random = new Random();
        for (Course course : courses) {
            String[] grades = {"A", "-A", "B", "-B", "C", "D", "F"};
            String randomGrade = grades[random.nextInt(grades.length)];
            Grade grade = new Grade(randomGrade);
            student.giveGrade(course, grade);
        }
    }
    public static void main(String[] args) {

        Course course1 = new Course("Software Development Lab","681",3);
        Course course2 = new Course("Object Oriented Software Development","680",3);
        Course course3 = new Course("Theory of Computation","630",3);
        Course course4 = new Course("Database Application Development","430",3);
        Course course5 = new Course("Introduction to Computer Security","371",3);

        Student student = new Student("Pratik Dhameliya","Computer Science","02073004");
        Student student2 = new Student("Nirav Savani","Computer Science","02073005");

        student.courseEnrollment(course5);
        student.courseEnrollment(course4);
        student.courseEnrollment(course3);
        student.courseEnrollment(course2);
        student.courseEnrollment(course1);

        student2.courseEnrollment(course1);
        student2.courseEnrollment(course3);
        student2.courseEnrollment(course4);


        assignRandomGrades(student, Arrays.asList(course1,course2,course3,course4,course5));
        assignRandomGrades(student2, Arrays.asList(course1,course3,course4));


        List<Student> students = new ArrayList<>();
        students.add(student);
        students.add(student2);

        for (Student student1 : students) {
            System.out.println("GPA for " + student1.getName() + ":");
            System.out.println("Undergraduate GPA: " + transcriptGenerator.calculateUndergradGPA(Collections.singletonList(student1)));
            System.out.println("Graduate GPA: " + transcriptGenerator.calculateGradGPA(Collections.singletonList(student1)));
            System.out.println("Major Undergraduate GPA: " + transcriptGenerator.calculateMajorUndergradGPA(Collections.singletonList(student1), "Computer Science"));
            System.out.println();
        }

        System.out.println("\nCalculating All GPA in a Single Data Processing...");
        for (Student student3 : students) {
            transcriptGenerator.calculateAllGPA(student3, "Computer Science");
        }

        Map<Boolean, List<Map.Entry<Course, Grade>>> groupedCourses = transcriptGenerator.groupCoursesByLevel(students);
        List<Map.Entry<Course, Grade>> undergradCourses = groupedCourses.get(true);
        List<Map.Entry<Course, Grade>> gradCourses = groupedCourses.get(false);

        System.out.println("\nUndergraduate Courses:");
        undergradCourses.forEach(entry -> {
            System.out.println("Course: " + entry.getKey().getName() + ", Grade: " + entry.getValue().getValue());
        });

        System.out.println("\nGraduate Courses:");
        gradCourses.forEach(entry -> {
            System.out.println("Course: " + entry.getKey().getName() + ", Grade: " + entry.getValue().getValue());
        });

        List<Student> highGradGPAStudents = transcriptGenerator.filterByHighGradGPA(students);
        System.out.println("Students with GPA more than or equal to 3.0: " + highGradGPAStudents);

        Map<String, Integer> totalCredit = transcriptGenerator.getTotalCreditByStudent(students);
        System.out.println(totalCredit);

    }
}