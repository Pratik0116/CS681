package edu.umb.cs681.HW02;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class transcriptGenerator {

    private static double convertGradeToPoints(Grade grade) {
        switch (grade.getValue()) {
            case "A": return 4.0;
            case "-A": return 3.5;
            case "B": return 3.0;
            case "-B": return 2.5;
            case "C": return 2.0;
            case "D": return 1.0;
            default: return 0.0;
        }
    }

    public static double calculateUndergradGPA(List<Student> students) {
        return students.stream()
                .flatMap(student -> student.getGrades().entrySet().stream())
                // Assuming undergraduate courses start with "3" or "4"
                .filter(entry -> entry.getKey().getCode().startsWith("3") || entry.getKey().getCode().startsWith("4"))
                .mapToDouble(entry -> convertGradeToPoints(entry.getValue()))
                .average()
                .orElse(0.0);
    }

    public static double calculateGradGPA(List<Student> students) {
        return students.stream()
                .flatMap(student -> student.getGrades().entrySet().stream())
                // Assuming graduate courses start with "6"
                .filter(entry -> entry.getKey().getCode().startsWith("6"))
                .mapToDouble(entry -> convertGradeToPoints(entry.getValue()))
                .average()
                .orElse(0.0);
    }

    public static Map<Boolean, List<Map.Entry<Course, Grade>>> groupCoursesByLevel(List<Student> students) {
        return students.stream()
                .flatMap(student -> student.getGrades().entrySet().stream())
                // Assuming undergraduate courses start with "3" or "4"
                .collect(Collectors.partitioningBy(entry -> entry.getKey().getCode().startsWith("3") ||
                        entry.getKey().getCode().startsWith("4")));
    }

    public static double calculateMajorUndergradGPA(List<Student> students, String major) {
        return students.stream()
                .filter(student -> student.getMajor().equals(major))
                .flatMap(student -> student.getGrades().entrySet().stream())
                // Assuming undergraduate courses start with "3" or "4"
                .filter(entry -> entry.getKey().getCode().startsWith("3") ||
                        entry.getKey().getCode().startsWith("4"))
                .mapToDouble(entry -> convertGradeToPoints(entry.getValue()))
                .average()
                .orElse(0.0);
    }

    public static void calculateAllGPA(Student student, String major) {
        if (student.getMajor().equals(major)) {
            double majorUndergraduateGPA = student.getGrades().entrySet().stream()
                    .filter(entry -> entry.getKey().getCode().startsWith("3") ||
                            entry.getKey().getCode().startsWith("4"))
                    .mapToDouble(entry -> convertGradeToPoints(entry.getValue()))
                    .average()
                    .orElse(0.0);

            double cumulativeUndergraduateGPA = student.getGrades().entrySet().stream()
                    .filter(entry -> entry.getKey().getCode().startsWith("3") ||
                            entry.getKey().getCode().startsWith("4"))
                    .mapToDouble(entry -> convertGradeToPoints(entry.getValue()))
                    .average()
                    .orElse(0.0);

            double graduateGPA = student.getGrades().entrySet().stream()
                    .filter(entry -> entry.getKey().getCode().startsWith("6"))
                    .mapToDouble(entry -> convertGradeToPoints(entry.getValue()))
                    .average()
                    .orElse(0.0);

            System.out.println("Name: " + student.getName());
            System.out.println("Major Undergrad GPA: " + majorUndergraduateGPA);
            System.out.println("Cumulative Undergrad GPA: " + cumulativeUndergraduateGPA);
            System.out.println("Grad GPA: " + graduateGPA);
            System.out.println();
        }
    }

    public static List<Student> filterByHighGradGPA(List<Student> students) {
        return students.stream()
                .filter(student -> transcriptGenerator.calculateGradGPA(Collections.singletonList(student)) >= 3.0)
                .collect(Collectors.toList());
    }

    public static Map<String, Integer> getTotalCreditByStudent(List<Student> students) {
        return students.stream()
                .collect(Collectors.toMap(
                        Student::getName,
                        student -> student.getCourses().stream().mapToInt(Course::getCredit).sum()
                ));
    }


}
