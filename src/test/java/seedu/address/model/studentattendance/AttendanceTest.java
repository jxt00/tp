package seedu.address.model.studentattendance;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ATTENDANCE_FALSE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ATTENDANCE_TRUE;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

//@@author jxt00
public class AttendanceTest {
    @Test
    public void constructor_invalidAttendance_throwsIllegalArgumentException() {
        String invalidAttendance = "";
        assertThrows(IllegalArgumentException.class, () -> new Attendance(invalidAttendance));
    }

    @Test
    public void isValidAttendance() {
        // null attendance
        assertThrows(NullPointerException.class, () -> Attendance.isValidAttendance(null));

        // invalid attendances
        assertFalse(Attendance.isValidAttendance("")); // empty string
        assertFalse(Attendance.isValidAttendance(" ")); // spaces only
        assertFalse(Attendance.isValidAttendance("Tru3")); // contains digits
        assertFalse(Attendance.isValidAttendance("Tru!")); // contains symbol
        assertFalse(Attendance.isValidAttendance("TRUE")); // upper-case

        // valid attendances
        assertTrue(Attendance.isValidAttendance("true")); // lower-case
        assertTrue(Attendance.isValidAttendance("false"));
        assertTrue(Attendance.isValidAttendance(String.valueOf(true)));
        assertTrue(Attendance.isValidAttendance(String.valueOf(false)));
    }

    @Test
    public void equals() {
        Attendance attendanceT = new Attendance(VALID_ATTENDANCE_TRUE);
        Attendance attendanceF = new Attendance(VALID_ATTENDANCE_FALSE);

        // same object
        assertTrue(attendanceT.equals(attendanceT));
        // different objects but same value
        assertTrue(attendanceT.equals(new Attendance(Boolean.TRUE)));
        assertTrue(attendanceF.equals(new Attendance(Boolean.FALSE)));

        // different objects
        assertFalse(attendanceT.equals(attendanceF));
        assertFalse(attendanceT.equals(new Attendance(Boolean.FALSE)));
        assertFalse(attendanceF.equals(new Attendance(Boolean.TRUE)));
    }
}
