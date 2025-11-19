package com.school;

import java.util.ArrayList;
import java.util.List;

public class RegistrationService {
    private List<Student> students;
    private List<Teacher> teachers;
    private List<Staff> staffMembers;
    private List<Course> courses;
    private FileStorageService storageService;

    public RegistrationService(FileStorageService storageService) {
        this.storageService = storageService;
        this.students = new ArrayList<>();
        this.teachers = new ArrayList<>();
        this.staffMembers = new ArrayList<>();
        this.courses = new ArrayList<>();
    }

    public Student registerStudent(String name, String gradeLevel) {
        Student s = new Student(name, gradeLevel);
        students.add(s);
        return s;
    }

    public Teacher registerTeacher(String name, String subjectTaught) {
        Teacher t = new Teacher(name, subjectTaught);
        teachers.add(t);
        return t;
    }

    public Staff registerStaff(String name, String role) {
        Staff st = new Staff(name, role);
        staffMembers.add(st);
        return st;
    }

    public Course createCourse(String courseName, int capacity) {
        Course c = new Course(courseName, capacity);
        courses.add(c);
        return c;
    }

    public boolean enrollStudentInCourse(Student student, Course course) {
        boolean added = course.addStudent(student);
        if (added) {
            System.out.println("Enrolled student " + student.getName() + " (ID:" + student.getId() + ") in course " + course.getCourseName());
        } else {
            System.out.println("Failed to enroll student " + student.getName() + " (ID:" + student.getId() + ") in course " + course.getCourseName() + " (may be full or already enrolled)");
        }
        return added;
    }

    public List<Student> getStudents() {
        return students;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public List<Staff> getStaffMembers() {
        return staffMembers;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public Student findStudentById(int id) {
        for (Student s : students) {
            if (s.getId() == id) return s;
        }
        return null;
    }

    public Course findCourseById(int id) {
        for (Course c : courses) {
            if (c.getCourseId() == id) return c;
        }
        return null;
    }

    public List<Person> getAllPeople() {
        List<Person> people = new ArrayList<>();
        people.addAll(students);
        people.addAll(teachers);
        people.addAll(staffMembers);
        return people;
    }

    public void saveAllRegistrations() {
        storageService.saveData(students, "students.txt");
        storageService.saveData(teachers, "teachers.txt");
        storageService.saveData(staffMembers, "staff.txt");
        storageService.saveData(courses, "courses.txt");
    }
}
