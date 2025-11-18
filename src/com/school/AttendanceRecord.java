package com.school;

public class AttendanceRecord implements Storable {
    private Student student;
    private Course course;
    private String status;

    public AttendanceRecord(Student student, Course course, String status) {
        this.student = student;
        this.course = course;
        if (status != null && (status.equalsIgnoreCase("Present") || status.equalsIgnoreCase("Absent"))) {
            this.status = status;
        } else {
            this.status = "Invalid";
            System.out.println("Warning: Invalid status provided. Setting status to 'Invalid'.");
        }
    }

    public Student getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }

    public String getStatus() {
        return status;
    }

    public void displayRecord() {
        String studentName = (student != null) ? student.getName() : "Unknown";
        int studentId = (student != null) ? student.getId() : -1;
        String courseName = (course != null) ? course.getCourseName() : "Unknown";
        int courseId = (course != null) ? course.getCourseId() : -1;
        System.out.println("Student: " + studentName + " (ID: " + studentId + ") - Course: " + courseName + " (C" + courseId + ") - Status: " + status);
    }

    @Override
    public String toDataString() {
        int sid = (student != null) ? student.getId() : -1;
        int cid = (course != null) ? course.getCourseId() : -1;
        return sid + "," + cid + "," + status;
    }
}
