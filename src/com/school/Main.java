
package com.school;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void displaySchoolDirectory(List<Person> people) {
        System.out.println("----- School Directory -----");
        for (Person p : people) {
            p.displayDetails();
            System.out.println();
        }
    }

    public static void main(String[] args) {
        // Create and populate students
        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student("Alice", "10th Grade"));
        students.add(new Student("Bob", "11th Grade"));

        // Create and populate courses
        ArrayList<Course> courses = new ArrayList<>();
        courses.add(new Course("Mathematics"));
        courses.add(new Course("Physics"));

        // Create and populate attendance records (pass Student and Course objects)
        ArrayList<AttendanceRecord> records = new ArrayList<>();
        records.add(new AttendanceRecord(students.get(0), courses.get(0), "Present"));
        records.add(new AttendanceRecord(students.get(1), courses.get(1), "Absent"));

        // Create teacher and staff
        Teacher teacher = new Teacher("Mr. Smith", "Mathematics");
        Staff staff = new Staff("Mrs. Brown", "Librarian");

        System.out.println("----- Person Details -----");
        // Build a polymorphic list and display via the helper
        List<Person> schoolPeople = new ArrayList<>();
        schoolPeople.addAll(students);
        schoolPeople.add(teacher);
        schoolPeople.add(staff);
        displaySchoolDirectory(schoolPeople);

        System.out.println("----- Course Details -----");
        for (Course c : courses) {
            c.displayDetails();
            System.out.println();
        }

        // Create storage & service
        FileStorageService storage = new FileStorageService();
        AttendanceService attendanceService = new AttendanceService(storage);

        // Use AttendanceService markAttendance overload (object-based)
        attendanceService.markAttendance(students.get(0), courses.get(0), "Present");
        attendanceService.markAttendance(students.get(1), courses.get(1), "Absent");

        // Use id-based overload which looks up objects
        attendanceService.markAttendance(students.get(0).getId(), courses.get(1).getCourseId(), "Present", students, courses);

        System.out.println("----- Attendance Records (via AttendanceService) -----");
        attendanceService.displayAttendanceLog();
        System.out.println();

        // Display filtered logs
        attendanceService.displayAttendanceLog(students.get(0));
        System.out.println();
        attendanceService.displayAttendanceLog(courses.get(1));
        System.out.println();

        // Save attendance via service
        attendanceService.saveAttendanceData();

        // Also save other entity files directly for completeness
        storage.saveData(students, "students.txt");
        storage.saveData(courses, "courses.txt");
    }
}