package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ATTENDANCE_TRUE;
import static seedu.address.storage.JsonAdaptedStudentAttendance.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.student.Student;
import seedu.address.model.student.StudentId;
import seedu.address.model.studentattendance.Attendance;
import seedu.address.testutil.TypicalStudentAttendances;
import seedu.address.testutil.TypicalStudents;

//@@author jxt00
public class JsonAdaptedStudentAttendanceTest {
    private static final String INVALID_ID = "e012345a";
    private static final String INVALID_ATTTENDANCE = "tru3";

    private static final String VALID_ID = TypicalStudents.BENSON.getStudentId().toString();
    private static final List<Student> students = new ArrayList<>(Arrays.asList(TypicalStudents.BENSON));

    @Test
    public void toModelType_validStudentAttendanceDetails_returnsStudentAttendance() throws Exception {
        JsonAdaptedStudentAttendance sa = new JsonAdaptedStudentAttendance(TypicalStudentAttendances.BENSON_ATTENDANCE);
        assertEquals(TypicalStudentAttendances.BENSON_ATTENDANCE, sa.toModelType(students));
    }

    @Test
    public void toModelType_invalidStudentId_throwsIllegalValueException() {
        JsonAdaptedStudentAttendance sa =
                new JsonAdaptedStudentAttendance(INVALID_ID, VALID_ATTENDANCE_TRUE);
        String expectedMessage = StudentId.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, () -> sa.toModelType(students));
    }

    @Test
    public void toModelType_nullStudentId_throwsIllegalValueException() {
        JsonAdaptedStudentAttendance sa = new JsonAdaptedStudentAttendance(null, VALID_ATTENDANCE_TRUE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, StudentId.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, () -> sa.toModelType(students));
    }

    @Test
    public void toModelType_invalidAttendance_throwsIllegalValueException() {
        JsonAdaptedStudentAttendance sa =
                new JsonAdaptedStudentAttendance(VALID_ID, INVALID_ATTTENDANCE);
        String expectedMessage = Attendance.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, () -> sa.toModelType(students));
    }

    @Test
    public void toModelType_nullAttendance_throwsIllegalValueException() {
        JsonAdaptedStudentAttendance sa = new JsonAdaptedStudentAttendance(VALID_ID, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Attendance.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, () -> sa.toModelType(students));
    }

    @Test
    public void toModelType_nonExistentStudent_throwsIllegalValueException() {
        List<Student> studentsList = new ArrayList<>(Arrays.asList(TypicalStudents.ALICE));
        JsonAdaptedStudentAttendance sa =
                new JsonAdaptedStudentAttendance(VALID_ID, VALID_ATTENDANCE_TRUE);
        assertThrows(IllegalValueException.class, () -> sa.toModelType(studentsList));
    }
}
