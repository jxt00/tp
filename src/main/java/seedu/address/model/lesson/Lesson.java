package seedu.address.model.lesson;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import seedu.address.model.student.Student;
import seedu.address.model.studentattendance.Attendance;
import seedu.address.model.studentattendance.StudentAttendance;

//@@author jxt00
/**
 * Represents a Lesson in the TAssist.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Lesson {
    // Identity field
    private final WeekId weekId;
    // Data field
    private final List<StudentAttendance> studentAttendanceList;

    /**
     * Constructs a {@code Lesson}.
     * {@code weekId} must be present and not null.
     *
     * @param weekId A valid week ID.
     */
    public Lesson(WeekId weekId) {
        this(weekId, new ArrayList<>());
    }

    /**
     * Constructs a {@code Lesson}.
     * {@code weekId} must be present and not null.
     *
     * @param weekId A valid week ID.
     * @param studentAttendanceList A list of {@code StudentAttendance} objects.
     */
    public Lesson(WeekId weekId, List<StudentAttendance> studentAttendanceList) {
        requireAllNonNull(weekId);
        this.weekId = weekId;
        this.studentAttendanceList = studentAttendanceList;
    }

    public WeekId getWeekId() {
        return weekId;
    }

    public List<StudentAttendance> getStudentAttendanceList() {
        return studentAttendanceList;
    }

    public List<Student> getStudents() {
        return studentAttendanceList.stream()
                .map(entry -> entry.getStudent())
                .collect(Collectors.toList());
    }

    public void addStudent(Student student) {
        studentAttendanceList.add(new StudentAttendance(student, new Attendance(false)));
    }

    /**
     * Removes a student from the lesson as well as all the related attendance information.
     */
    public void removeStudent(Student student) {
        StudentAttendance objToDelete = studentAttendanceList.stream().filter(sa -> sa.getStudent().equals(student))
                .findFirst().get();
        studentAttendanceList.remove(objToDelete);
    }

    //@@author EvaderFati
    /**
     * Iterate through all the student in the specified student list and update the studentAttendanceList
     * if there is a {@code StudentAttendance} representing the attendance for the specified student.
     *
     * @param students A list of students to mark attendance
     */
    public List<Student> markAttendance(List<Student> students) {
        List<Student> studentsCopy = new ArrayList<>(students);
        for (StudentAttendance sa : studentAttendanceList) {
            for (Student s : students) {
                if (sa.getStudent().equals(s)) {
                    studentAttendanceList.set(studentAttendanceList.indexOf(sa), sa.markAttendance());
                    studentsCopy.remove(s);
                    break;
                }
            }
        }
        return studentsCopy;
    }

    //@@author EvaderFati
    /**
     * Iterate through all the student in the specified student list and update the studentAttendanceList
     * if there is a {@code StudentAttendance} representing the attendance for the specified student.
     *
     * @param students A list of students to unmark attendance
     */
    public List<Student> unmarkAttendance(List<Student> students) {
        List<Student> studentsCopy = new ArrayList<>(students);
        for (StudentAttendance sa : studentAttendanceList) {
            for (Student s : students) {
                if (sa.getStudent().isSameStudent(s)) {
                    studentAttendanceList.set(studentAttendanceList.indexOf(sa), sa.unmarkAttendance(s));
                    studentsCopy.remove(s);
                    break;
                }
            }
        }
        return studentsCopy;
    }

    /**
     * Returns true if both lessons have the same week ID.
     * This defines a weaker notion of equality between two lessons.
     */
    public boolean isSameLesson(Lesson otherLesson) {
        if (otherLesson == this) {
            return true;
        }

        return otherLesson != null
                && otherLesson.getWeekId().equals(getWeekId());
    }

    /**
     * Returns true if both lessons have the same identity and data fields.
     * This defines a stronger notion of equality between two lessons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Lesson)) {
            return false;
        }

        Lesson otherLesson = (Lesson) other;
        return otherLesson.getWeekId().equals(getWeekId())
                && otherLesson.getStudentAttendanceList().equals(getStudentAttendanceList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(weekId, studentAttendanceList);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getWeekId());

        List<StudentAttendance> studentAttendances = getStudentAttendanceList();
        if (!studentAttendances.isEmpty()) {
            builder.append("; Student Attendances: ");
            studentAttendances.forEach(builder::append);
        }
        return builder.toString();
    }
}
