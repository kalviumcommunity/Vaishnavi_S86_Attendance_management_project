
package com.school;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Create and populate students
        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student("Alice", "10th Grade"));
        students.add(new Student("Bob", "11th Grade"));

        // Create and populate courses
        ArrayList<Course> courses = new ArrayList<>();
        courses.add(new Course("Mathematics"));
        courses.add(new Course("Physics"));

        // Create and populate attendance records
        ArrayList<AttendanceRecord> records = new ArrayList<>();
        records.add(new AttendanceRecord(students.get(0).getId(), courses.get(0).getCourseId(), "Present"));
        records.add(new AttendanceRecord(students.get(1).getId(), courses.get(1).getCourseId(), "Absent"));

        // Create teacher and staff
        Teacher teacher = new Teacher("Mr. Smith", "Mathematics");
        Staff staff = new Staff("Mrs. Brown", "Librarian");

        System.out.println("----- Person Details -----");
        for (Student s : students) {
            s.displayDetails();
            System.out.println();
        }
        teacher.displayDetails();
        System.out.println();
        staff.displayDetails();
        System.out.println();

        System.out.println("----- Course Details -----");
        for (Course c : courses) {
            c.displayDetails();
            System.out.println();
        }

        System.out.println("----- Attendance Records -----");
        for (AttendanceRecord record : records) {
            record.displayRecord();
        }

        // Save data to files
        FileStorageService storage = new FileStorageService();
        storage.saveData(students, "students.txt");
        storage.saveData(courses, "courses.txt");
        storage.saveData(records, "attendance_log.txt");
    }
}