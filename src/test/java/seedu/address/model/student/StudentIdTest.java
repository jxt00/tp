package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

//@@author wxliong
public class StudentIdTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new StudentId(null));
    }

    @Test
    public void constructor_invalidStudentId_throwsIllegalArgumentException() {
        String invalidStudentId = "";
        assertThrows(IllegalArgumentException.class, () -> new StudentId(invalidStudentId));
    }

    @Test
    public void isValidStudentId() {
        // null student ID
        assertThrows(NullPointerException.class, () -> StudentId.isValidStudentId(null));

        // invalid student ID
        assertFalse(StudentId.isValidStudentId("")); // empty string
        assertFalse(StudentId.isValidStudentId(" ")); // spaces only
        assertFalse(StudentId.isValidStudentId("0101212")); // does not start with an 'e'
        assertFalse(StudentId.isValidStudentId("e")); // is not followed by 7 digits
        assertFalse(StudentId.isValidStudentId("e011_023")); // no special characters allowed
        assertFalse(StudentId.isValidStudentId("e011 0232")); // spaces within student ID

        // valid student ID
        assertTrue(StudentId.isValidStudentId("e0123456")); // correct format, 'e' is lowercase
        assertTrue(StudentId.isValidStudentId("E0011223")); // correct format, 'e' is uppercase
    }
}
