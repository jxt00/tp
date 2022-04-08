package seedu.address.testutil;

import seedu.address.model.student.Email;
import seedu.address.model.student.Name;
import seedu.address.model.student.Student;
import seedu.address.model.student.StudentId;
import seedu.address.model.studentattendance.Attendance;
import seedu.address.model.studentattendance.StudentAttendance;

//@@author jxt00
/**
 * A utility class to help with building StudentAttendance objects.
 */
public class StudentAttendanceBuilder {
    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_STUDENT_ID = "E0123456";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ATTENDANCE = "false";

    private Student student;
    private Attendance attendance;

    /**
     * Creates a {@code StudentAttendanceBuilder} with the default details.
     */
    public StudentAttendanceBuilder() {
        student = new Student(new StudentId(DEFAULT_STUDENT_ID), new Name(DEFAULT_NAME), new Email(DEFAULT_EMAIL));
        attendance = new Attendance(DEFAULT_ATTENDANCE);
    }

    /**
     * Initializes the StudentAttendanceBuilder with the data of {@code studentAttendanceToCopy}.
     */
    public StudentAttendanceBuilder(StudentAttendance studentAttendanceToCopy) {
        this.student = studentAttendanceToCopy.getStudent();
        this.attendance = studentAttendanceToCopy.getAttendance();
    }

    /**
     * Sets the {@code Student} of the {@code StudentAttendance} that we are building.
     */
    public StudentAttendanceBuilder withStudent(String id, String name, String email) {
        this.student = new Student(new StudentId(id), new Name(name), new Email(email));
        return this;
    }

    /**
     * Sets the {@code Student} of the {@code StudentAttendance} that we are building.
     */
    public StudentAttendanceBuilder withStudent(Student student) {
        this.student = student;
        return this;
    }

    /**
     * Sets the {@code Attendance} of the {@code StudentAttendance} that we are building.
     */
    public StudentAttendanceBuilder withAttendance(String attendance) {
        this.attendance = new Attendance(attendance);
        return this;
    }

    public StudentAttendance build() {
        return new StudentAttendance(student, attendance);
    }
}
