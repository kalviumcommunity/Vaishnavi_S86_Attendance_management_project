
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

        System.out.println("----- Attendance Records -----");
        for (AttendanceRecord record : records) {
            record.displayRecord();
        }

        // Save data to files
        FileStorageService storage = new FileStorageService();

        // If saving directly from students list (easy):
        storage.saveData(students, "students.txt");

        // Or, if you had only the polymorphic schoolPeople list, filter Students:
        ArrayList<Student> studentsToSave = new ArrayList<>();
        for (Person p : schoolPeople) {
            if (p instanceof Student) {
                studentsToSave.add((Student) p);
            }
        }
        // studentsToSave now contains Student instances from schoolPeople
        storage.saveData(studentsToSave, "students_from_directory.txt");

        storage.saveData(courses, "courses.txt");
        storage.saveData(records, "attendance_log.txt");
    }
}