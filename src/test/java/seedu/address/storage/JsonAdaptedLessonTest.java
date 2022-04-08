package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ATTENDANCE_TRUE;
import static seedu.address.storage.JsonAdaptedLesson.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.lesson.WeekId;
import seedu.address.model.student.Student;
import seedu.address.model.student.StudentId;
import seedu.address.model.studentattendance.Attendance;
import seedu.address.testutil.TypicalLessons;
import seedu.address.testutil.TypicalStudentAttendances;
import seedu.address.testutil.TypicalStudents;

//@@author jxt00
public class JsonAdaptedLessonTest {
    private static final String INVALID_WEEK_ID = "1a";
    private static final String INVALID_ID = "e012345a";
    private static final String INVALID_ATTTENDANCE = "tru3";

    private static final String VALID_WEEK_ID = "2";
    private static final String VALID_ID = TypicalStudents.BENSON.getStudentId().toString();
    private static final List<Student> students = new ArrayList<>(Arrays.asList(TypicalStudents.BENSON));
    private static final List<JsonAdaptedStudentAttendance> studentAttendances = new ArrayList<>(
            Arrays.asList(new JsonAdaptedStudentAttendance(TypicalStudentAttendances.BENSON_ATTENDANCE)));

    @Test
    public void toModelType_validLessonDetails_returnsLesson() throws Exception {
        JsonAdaptedLesson lesson = new JsonAdaptedLesson(VALID_WEEK_ID, studentAttendances);
        assertEquals(TypicalLessons.LESSON2, lesson.toModelType(students));
    }

    @Test
    public void toModelType_invalidWeekId_throwsIllegalValueException() {
        JsonAdaptedLesson lesson =
                new JsonAdaptedLesson(INVALID_WEEK_ID, studentAttendances);
        String expectedMessage = WeekId.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, () -> lesson.toModelType(students));
    }

    @Test
    public void toModelType_nullWeekId_throwsIllegalValueException() {
        JsonAdaptedLesson lesson = new JsonAdaptedLesson(null, studentAttendances);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, WeekId.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, () -> lesson.toModelType(students));
    }

    @Test
    public void toModelType_invalidStudentId_throwsIllegalValueException() {
        JsonAdaptedStudentAttendance invalidSA =
                new JsonAdaptedStudentAttendance(INVALID_ID, VALID_ATTENDANCE_TRUE);
        JsonAdaptedLesson lesson =
                new JsonAdaptedLesson(VALID_WEEK_ID, new ArrayList<>(Arrays.asList(invalidSA)));
        String expectedMessage = StudentId.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, () -> lesson.toModelType(students));
    }

    @Test
    public void toModelType_nullStudentId_throwsIllegalValueException() {
        JsonAdaptedStudentAttendance invalidSA = new JsonAdaptedStudentAttendance(null, VALID_ATTENDANCE_TRUE);
        JsonAdaptedLesson lesson =
                new JsonAdaptedLesson(VALID_WEEK_ID, new ArrayList<>(Arrays.asList(invalidSA)));
        String expectedMessage = String.format(
                JsonAdaptedStudentAttendance.MISSING_FIELD_MESSAGE_FORMAT, StudentId.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, () -> lesson.toModelType(students));
    }

    @Test
    public void toModelType_invalidAttendance_throwsIllegalValueException() {
        JsonAdaptedStudentAttendance invalidSA =
                new JsonAdaptedStudentAttendance(VALID_ID, INVALID_ATTTENDANCE);
        JsonAdaptedLesson lesson =
                new JsonAdaptedLesson(VALID_WEEK_ID, new ArrayList<>(Arrays.asList(invalidSA)));
        String expectedMessage = Attendance.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, () -> lesson.toModelType(students));
    }

    @Test
    public void toModelType_nullAttendance_throwsIllegalValueException() {
        JsonAdaptedStudentAttendance invalidSA = new JsonAdaptedStudentAttendance(VALID_ID, null);
        JsonAdaptedLesson lesson =
                new JsonAdaptedLesson(VALID_WEEK_ID, new ArrayList<>(Arrays.asList(invalidSA)));
        String expectedMessage = String.format(
                JsonAdaptedStudentAttendance.MISSING_FIELD_MESSAGE_FORMAT, Attendance.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, () -> lesson.toModelType(students));
    }

    @Test
    public void toModelType_nonExistentStudent_throwsIllegalValueException() {
        List<Student> studentsList = new ArrayList<>(Arrays.asList(TypicalStudents.ALICE));
        JsonAdaptedStudentAttendance sa =
                new JsonAdaptedStudentAttendance(VALID_ID, VALID_ATTENDANCE_TRUE);
        JsonAdaptedLesson lesson =
                new JsonAdaptedLesson(VALID_WEEK_ID, new ArrayList<>(Arrays.asList(sa)));
        assertThrows(IllegalValueException.class, () -> lesson.toModelType(studentsList));
    }
}
