package com.school;

import java.util.ArrayList;
import java.util.List;

public class AttendanceService {
    private List<AttendanceRecord> attendanceLog;
    private FileStorageService storageService;
    private RegistrationService registrationService;

    public AttendanceService(FileStorageService storageService, RegistrationService registrationService) {
        this.storageService = storageService;
        this.registrationService = registrationService;
        this.attendanceLog = new ArrayList<>();
    }

    public void markAttendance(Student student, Course course, String status) {
        AttendanceRecord record = new AttendanceRecord(student, course, status);
        attendanceLog.add(record);
    }

    public void markAttendance(int studentId, int courseId, String status) {
        Student s = registrationService.findStudentById(studentId);
        Course c = registrationService.findCourseById(courseId);
        if (s != null && c != null) {
            markAttendance(s, c, status);
        } else {
            System.out.println("Warning: Could not find student or course for IDs: " + studentId + ", " + courseId);
        }
    }

    public void displayAttendanceLog() {
        System.out.println("----- Attendance Log (All) -----");
        for (AttendanceRecord r : attendanceLog) {
            r.displayRecord();
        }
    }

    public void displayAttendanceLog(Student student) {
        System.out.println("----- Attendance Log (Student: " + student.getName() + ") -----");
        for (AttendanceRecord r : attendanceLog) {
            if (r.getStudent() != null && r.getStudent().getId() == student.getId()) {
                r.displayRecord();
            }
        }
    }

    public void displayAttendanceLog(Course course) {
        System.out.println("----- Attendance Log (Course: " + course.getCourseName() + ") -----");
        for (AttendanceRecord r : attendanceLog) {
            if (r.getCourse() != null && r.getCourse().getCourseId() == course.getCourseId()) {
                r.displayRecord();
            }
        }
    }

    public void saveAttendanceData() {
        storageService.saveData(this.attendanceLog, "attendance_log.txt");
    }
}
