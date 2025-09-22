
package com.school;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Create objects
        Student student = new Student("Alice", "10th Grade");
        Teacher teacher = new Teacher("Mr. Smith", "Mathematics");
        Staff staff = new Staff("Mrs. Brown", "Librarian");

        System.out.println("----- Person Details -----");
        student.displayDetails();
        System.out.println();
        teacher.displayDetails();
        System.out.println();
        staff.displayDetails();
        System.out.println();

        // Attendance log
        Course course = new Course("Mathematics");
        List<AttendanceRecord> attendanceLog = new ArrayList<>();
        attendanceLog.add(new AttendanceRecord(student.getId(), course.getCourseId(), "Present"));

        System.out.println("----- Attendance Records -----");
        for (AttendanceRecord record : attendanceLog) {
            record.displayRecord();
        }
    }
}