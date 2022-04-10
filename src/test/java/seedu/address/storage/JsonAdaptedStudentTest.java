package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedStudent.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalStudents.BENSON;
import static seedu.address.testutil.TypicalStudents.CARL;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.student.Email;
import seedu.address.model.student.Name;
import seedu.address.model.student.StudentId;
import seedu.address.model.student.Telegram;

//@@author wxliong
public class JsonAdaptedStudentTest {
    private static final String INVALID_STUDENT_ID = "a0213212";
    private static final String INVALID_NAME = "R!chel";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TELEGRAM = "rachel#yas";

    private static final String VALID_STUDENT_ID = BENSON.getStudentId().toString();
    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_TELEGRAM = BENSON.getTelegram().toString();

    @Test
    public void toModelType_validStudentDetails_returnsStudent() throws Exception {
        JsonAdaptedStudent student = new JsonAdaptedStudent(BENSON);
        assertEquals(BENSON, student.toModelType());
    }

    @Test
    public void toModelType_validStudentDetailsWithoutTelegram_returnsStudent() throws Exception {
        JsonAdaptedStudent student = new JsonAdaptedStudent(CARL);
        assertEquals(CARL, student.toModelType());
    }

    @Test
    public void toModelType_invalidStudentId_throwsIllegalValueException() {
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(INVALID_STUDENT_ID, VALID_NAME, VALID_EMAIL, VALID_TELEGRAM);
        String expectedMessage = StudentId.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_nullStudentId_throwsIllegalValueException() {
        JsonAdaptedStudent student = new JsonAdaptedStudent(null, VALID_NAME, VALID_EMAIL, VALID_TELEGRAM);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, StudentId.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(VALID_STUDENT_ID, INVALID_NAME, VALID_EMAIL, VALID_TELEGRAM);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedStudent student = new JsonAdaptedStudent(VALID_STUDENT_ID, null, VALID_EMAIL, VALID_TELEGRAM);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(VALID_STUDENT_ID, VALID_NAME, INVALID_EMAIL, VALID_TELEGRAM);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(VALID_STUDENT_ID, VALID_NAME, null, VALID_TELEGRAM);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_invalidTelegram_throwsIllegalValueException() {
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(VALID_STUDENT_ID, VALID_NAME, VALID_EMAIL, INVALID_TELEGRAM);
        String expectedMessage = Telegram.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

}
