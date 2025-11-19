
package com.school;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void displaySchoolDirectory(RegistrationService regService) {
        System.out.println("----- School Directory -----");
        List<Person> people = regService.getAllPeople();
        for (Person p : people) {
            p.displayDetails();
            System.out.println();
        }
    }

    public static void main(String[] args) {

        // Create storage, registration service & attendance service
        FileStorageService storage = new FileStorageService();
        RegistrationService regService = new RegistrationService(storage);

        // Register students
        Student s1 = regService.registerStudent("Alice", "10th Grade");
        Student s2 = regService.registerStudent("Bob", "11th Grade");

        // Create courses with capacities
        Course c1 = regService.createCourse("Mathematics", 1); // capacity 1
        Course c2 = regService.createCourse("Physics", 2); // capacity 2

        // Create and populate attendance records (pass Student and Course objects)
        // (AttendanceService will manage the records now)

        // Register teacher and staff via registration service
        Teacher teacher = regService.registerTeacher("Mr. Smith", "Mathematics");
        Staff staff = regService.registerStaff("Mrs. Brown", "Librarian");

        System.out.println("----- Person Details -----");
        // Display directory from registration service
        displaySchoolDirectory(regService);

        System.out.println("----- Course Details -----");
        for (Course c : regService.getCourses()) {
            c.displayDetails();
            System.out.println();
        }

        // Create attendance service, injected with registration service
        AttendanceService attendanceService = new AttendanceService(storage, regService);

        // Enroll students (including a failing over-capacity attempt)
        regService.enrollStudentInCourse(s1, c1); // should succeed
        regService.enrollStudentInCourse(s2, c1); // should fail (capacity 1)
        regService.enrollStudentInCourse(s2, c2); // should succeed

        // Show updated course details after enrollment
        System.out.println("----- Course Details After Enrollment -----");
        for (Course c : regService.getCourses()) {
            c.displayDetails();
            System.out.println();
        }

        // Use AttendanceService markAttendance overload (object-based) only if enrolled
        if (c1.getEnrolledStudents().contains(s1)) {
            attendanceService.markAttendance(s1, c1, "Present");
        }
        if (c2.getEnrolledStudents().contains(s2)) {
            attendanceService.markAttendance(s2, c2, "Absent");
        }

        // Use id-based overload which looks up objects via RegistrationService
        attendanceService.markAttendance(s1.getId(), c2.getCourseId(), "Present");

        System.out.println("----- Attendance Records (via AttendanceService) -----");
        attendanceService.displayAttendanceLog();
        System.out.println();

        // Display filtered logs
        attendanceService.displayAttendanceLog(s1);
        System.out.println();
        attendanceService.displayAttendanceLog(c2);
        System.out.println();

        // Save attendance via service
        attendanceService.saveAttendanceData();

        // Save registrations via registration service
        regService.saveAllRegistrations();
    }
}