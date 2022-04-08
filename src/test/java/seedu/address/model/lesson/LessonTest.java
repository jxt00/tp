package seedu.address.model.lesson;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ATTENDANCE_FALSE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ATTENDANCE_TRUE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_WEEK_ID_13;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalLessons.LESSON1;
import static seedu.address.testutil.TypicalLessons.LESSON11;
import static seedu.address.testutil.TypicalLessons.LESSON12;
import static seedu.address.testutil.TypicalLessons.LESSON13;
import static seedu.address.testutil.TypicalLessons.LESSON2;
import static seedu.address.testutil.TypicalStudentAttendances.ALICE_ATTENDANCE;
import static seedu.address.testutil.TypicalStudentAttendances.BENSON_ATTENDANCE;
import static seedu.address.testutil.TypicalStudentAttendances.CARL_ATTENDANCE;
import static seedu.address.testutil.TypicalStudentAttendances.DANIEL_ATTENDANCE;
import static seedu.address.testutil.TypicalStudentAttendances.ELLE_ATTENDANCE;
import static seedu.address.testutil.TypicalStudents.ALICE;
import static seedu.address.testutil.TypicalStudents.BENSON;
import static seedu.address.testutil.TypicalStudents.CARL;
import static seedu.address.testutil.TypicalStudents.DANIEL;
import static seedu.address.testutil.TypicalStudents.ELLE;
import static seedu.address.testutil.TypicalStudents.FIONA;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.student.Student;
import seedu.address.testutil.LessonBuilder;
import seedu.address.testutil.StudentAttendanceBuilder;

//@@author jxt00
public class LessonTest {
    @Test
    public void asObservableList_modifyList_throwsIndexOutOfBoundsException() {
        Lesson lesson = new LessonBuilder().build();
        assertThrows(IndexOutOfBoundsException.class, () -> lesson.getStudentAttendanceList().remove(0));
    }

    /**
     * No need to check for duplicate students as these are handled by the commands and parsers.
     */
    @Test
    public void getStudents() {
        Lesson lesson13Copy = new LessonBuilder(LESSON13).build();
        assertTrue(lesson13Copy.getStudents().contains(ELLE));
        assertTrue(lesson13Copy.getStudents().contains(FIONA));
    }

    /**
     * No need to check for duplicate students as these are handled by the commands and parsers.
     */
    @Test
    public void addStudent() {
        Lesson lesson1Copy = new LessonBuilder(LESSON1).build();
        lesson1Copy.addStudent(BENSON);
        assertTrue(lesson1Copy.getStudentAttendanceList().contains(
                new StudentAttendanceBuilder(BENSON_ATTENDANCE).withAttendance(VALID_ATTENDANCE_FALSE).build()));
    }

    /**
     * No need to check for non-existent students as these are handled by the commands and parsers.
     */
    @Test
    public void removeStudent() {
        Lesson lesson1Copy = new LessonBuilder(LESSON1).build();
        lesson1Copy.removeStudent(ALICE);
        assertFalse(lesson1Copy.getStudentAttendanceList().contains(
                new StudentAttendanceBuilder(ALICE_ATTENDANCE).build()));
    }

    @Test
    public void markAttendance() {
        Lesson lesson11Copy = new LessonBuilder(LESSON11).build();
        List<Student> studentsToMark = new ArrayList<>();
        studentsToMark.add(DANIEL);

        // mark 1 student with initial false attendance
        lesson11Copy.markAttendance(studentsToMark);
        assertTrue(lesson11Copy.getStudentAttendanceList().contains(
                new StudentAttendanceBuilder(DANIEL_ATTENDANCE).withAttendance(VALID_ATTENDANCE_TRUE).build()));

        Lesson lesson12Copy = new LessonBuilder(LESSON12).build();
        studentsToMark = new ArrayList<>();
        studentsToMark.add(CARL);
        studentsToMark.add(DANIEL);

        // mark 1 student with initial false attendance, 1 student with initial true attendance
        lesson12Copy.markAttendance(studentsToMark);
        assertTrue(lesson12Copy.getStudentAttendanceList().contains(
                new StudentAttendanceBuilder(CARL_ATTENDANCE).build()));
        assertTrue(lesson12Copy.getStudentAttendanceList().contains(
                new StudentAttendanceBuilder(DANIEL_ATTENDANCE).withAttendance(VALID_ATTENDANCE_TRUE).build()));

        studentsToMark = new ArrayList<>();
        studentsToMark.add(ELLE);

        // mark a student not in the list
        lesson12Copy.markAttendance(studentsToMark);
        assertFalse(lesson12Copy.getStudentAttendanceList().contains(
                new StudentAttendanceBuilder(ELLE_ATTENDANCE).build()));
    }

    @Test
    public void unmarkAttendance() {
        Lesson lesson1Copy = new LessonBuilder(LESSON1).build();
        List<Student> studentsToUnmark = new ArrayList<>();
        studentsToUnmark.add(ALICE);

        // unmark 1 student with initial true attendance
        lesson1Copy.unmarkAttendance(studentsToUnmark);
        assertTrue(lesson1Copy.getStudentAttendanceList().contains(
                new StudentAttendanceBuilder(ALICE_ATTENDANCE).withAttendance(VALID_ATTENDANCE_FALSE).build()));

        Lesson lesson12Copy = new LessonBuilder(LESSON12).build();
        studentsToUnmark = new ArrayList<>();
        studentsToUnmark.add(CARL);
        studentsToUnmark.add(DANIEL);

        // unmark 1 student with initial false attendance, 1 student with initial true attendance
        lesson12Copy.unmarkAttendance(studentsToUnmark);
        assertTrue(lesson12Copy.getStudentAttendanceList().contains(
                new StudentAttendanceBuilder(CARL_ATTENDANCE).withAttendance(VALID_ATTENDANCE_FALSE).build()));
        assertTrue(lesson12Copy.getStudentAttendanceList().contains(
                new StudentAttendanceBuilder(DANIEL_ATTENDANCE).build()));

        studentsToUnmark = new ArrayList<>();
        studentsToUnmark.add(ELLE);

        // unmark a student not in the list
        lesson12Copy.unmarkAttendance(studentsToUnmark);
        assertFalse(lesson12Copy.getStudentAttendanceList().contains(
                new StudentAttendanceBuilder(ELLE_ATTENDANCE).build()));
    }

    @Test
    public void isSameLesson() {
        // same object -> returns true
        assertTrue(LESSON1.isSameLesson(LESSON1));

        // null -> returns false
        assertFalse(LESSON1.isSameLesson(null));

        // same week ID, different student attendance -> returns true
        Lesson editedLesson1 = new LessonBuilder(LESSON1).withStudentAttendances(BENSON_ATTENDANCE).build();
        assertTrue(LESSON1.isSameLesson(editedLesson1));

        // different week ID, same student attendance -> returns false
        editedLesson1 = new LessonBuilder(LESSON1).withWeekId(VALID_WEEK_ID_13).build();
        assertFalse(LESSON1.isSameLesson(editedLesson1));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Lesson lesson1Copy = new LessonBuilder(LESSON1).build();
        assertTrue(LESSON1.equals(lesson1Copy));

        // same object -> returns true
        assertTrue(LESSON1.equals(LESSON1));

        // null -> returns false
        assertFalse(LESSON1.equals(null));

        // different type -> returns false
        assertFalse(LESSON1.equals(5));

        // different lesson -> returns false
        assertFalse(LESSON1.equals(LESSON2));

        // different week ID -> returns false
        Lesson editedLesson1 = new LessonBuilder(LESSON1).withWeekId(VALID_WEEK_ID_13).build();
        assertFalse(LESSON1.equals(editedLesson1));

        // different student attendance -> returns false
        editedLesson1 = new LessonBuilder(LESSON1).withStudentAttendances(BENSON_ATTENDANCE).build();
        assertFalse(LESSON1.equals(editedLesson1));
    }
}
