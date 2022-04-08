package seedu.address.model.studentattendance;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ATTENDANCE_FALSE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ATTENDANCE_TRUE;
import static seedu.address.testutil.TypicalStudentAttendances.ALICE_ATTENDANCE;
import static seedu.address.testutil.TypicalStudentAttendances.BENSON_ATTENDANCE;
import static seedu.address.testutil.TypicalStudentAttendances.DANIEL_ATTENDANCE;
import static seedu.address.testutil.TypicalStudents.BENSON;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.StudentAttendanceBuilder;

//@@author jxt00
public class StudentAttendanceTest {
    @Test
    public void markAttendance() {
        StudentAttendance danielAttendanceCopy = new StudentAttendanceBuilder(DANIEL_ATTENDANCE).build();
        assertTrue(danielAttendanceCopy.markAttendance().equals(
                new StudentAttendanceBuilder(DANIEL_ATTENDANCE).withAttendance(VALID_ATTENDANCE_TRUE).build()));

        // same object -> returns true
        StudentAttendance aliceAttendanceCopy = new StudentAttendanceBuilder(ALICE_ATTENDANCE).build();
        assertTrue(aliceAttendanceCopy.markAttendance().equals(aliceAttendanceCopy));
    }

    @Test
    public void unmarkAttendance() {
        StudentAttendance aliceAttendanceCopy = new StudentAttendanceBuilder(ALICE_ATTENDANCE).build();
        assertTrue(aliceAttendanceCopy.unmarkAttendance(aliceAttendanceCopy.getStudent()).equals(
                new StudentAttendanceBuilder(ALICE_ATTENDANCE).withAttendance(VALID_ATTENDANCE_FALSE).build()));

        // same object -> returns true
        StudentAttendance danielAttendanceCopy = new StudentAttendanceBuilder(DANIEL_ATTENDANCE).build();
        assertTrue(danielAttendanceCopy.unmarkAttendance(danielAttendanceCopy.getStudent())
                .equals(danielAttendanceCopy));
    }

    @Test
    public void isSameStudentAttendance() {
        // same object -> returns true
        assertTrue(ALICE_ATTENDANCE.isSameStudentAttendance(ALICE_ATTENDANCE));

        // null -> returns false
        assertFalse(ALICE_ATTENDANCE.isSameStudentAttendance(null));

        // same student, different attendance -> returns true
        StudentAttendance editedAliceAttendance = new StudentAttendanceBuilder(ALICE_ATTENDANCE)
                .withAttendance(VALID_ATTENDANCE_FALSE).build();
        assertTrue(ALICE_ATTENDANCE.isSameStudentAttendance(editedAliceAttendance));

        // different student, same attendance -> returns false
        editedAliceAttendance = new StudentAttendanceBuilder(ALICE_ATTENDANCE).withStudent(BENSON).build();
        assertFalse(ALICE_ATTENDANCE.isSameStudentAttendance(editedAliceAttendance));
    }

    @Test
    public void equals() {
        // same values -> returns true
        StudentAttendance aliceAttendanceCopy = new StudentAttendanceBuilder(ALICE_ATTENDANCE).build();
        assertTrue(ALICE_ATTENDANCE.equals(aliceAttendanceCopy));

        // same object -> returns true
        assertTrue(ALICE_ATTENDANCE.equals(ALICE_ATTENDANCE));

        // null -> returns false
        assertFalse(ALICE_ATTENDANCE.equals(null));

        // different type -> returns false
        assertFalse(ALICE_ATTENDANCE.equals(5));

        // different student attendance -> returns false
        assertFalse(ALICE_ATTENDANCE.equals(BENSON_ATTENDANCE));

        // different attendance -> returns false
        StudentAttendance editedAliceAttendance = new StudentAttendanceBuilder(ALICE_ATTENDANCE)
                .withAttendance(VALID_ATTENDANCE_FALSE).build();
        assertFalse(ALICE_ATTENDANCE.equals(editedAliceAttendance));

        // different student -> returns false
        editedAliceAttendance = new StudentAttendanceBuilder(ALICE_ATTENDANCE).withStudent(BENSON).build();
        assertFalse(ALICE_ATTENDANCE.equals(editedAliceAttendance));
    }
}
