package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.studentattendance.StudentAttendance;

//@@author jxt00
/**
 * A utility class containing a list of {@code StudentAttendance} objects to be used in tests.
 */
public class TypicalStudentAttendances {
    public static final StudentAttendance ALICE_ATTENDANCE = new StudentAttendanceBuilder()
            .withStudent(TypicalStudents.ALICE)
            .withAttendance("true").build();
    public static final StudentAttendance BENSON_ATTENDANCE = new StudentAttendanceBuilder()
            .withStudent(TypicalStudents.BENSON)
            .withAttendance("true").build();
    public static final StudentAttendance CARL_ATTENDANCE = new StudentAttendanceBuilder()
            .withStudent(TypicalStudents.CARL)
            .withAttendance("true").build();
    public static final StudentAttendance DANIEL_ATTENDANCE = new StudentAttendanceBuilder()
            .withStudent(TypicalStudents.DANIEL)
            .withAttendance("false").build();
    public static final StudentAttendance ELLE_ATTENDANCE = new StudentAttendanceBuilder()
            .withStudent(TypicalStudents.ELLE)
            .withAttendance("false").build();
    public static final StudentAttendance FIONA_ATTENDANCE = new StudentAttendanceBuilder()
            .withStudent(TypicalStudents.FIONA)
            .withAttendance("false").build();

    private TypicalStudentAttendances() {} // prevents instantiation

    public static List<StudentAttendance> getTypicalStudentAttendances() {
        return new ArrayList<>(Arrays.asList(ALICE_ATTENDANCE, BENSON_ATTENDANCE, CARL_ATTENDANCE,
                DANIEL_ATTENDANCE, ELLE_ATTENDANCE, FIONA_ATTENDANCE));
    }
}
