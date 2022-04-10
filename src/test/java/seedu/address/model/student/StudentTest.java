package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TELEGRAM_AMY;
import static seedu.address.testutil.TypicalStudents.BENSON;
import static seedu.address.testutil.TypicalStudents.FIONA;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.StudentBuilder;

//@@author wxliong
public class StudentTest {

    @Test
    public void isSameStudent() {
        // same object -> returns true
        assertTrue(FIONA.isSameStudent(FIONA));

        // null -> returns false
        assertFalse(FIONA.isSameStudent((Student) null));

        // same student ID, all other attributes different -> returns true
        Student editedFiona = new StudentBuilder(FIONA).withName(VALID_NAME_BOB)
                .withEmail(VALID_EMAIL_BOB).withTelegram(VALID_TELEGRAM_AMY).build();
        assertTrue(FIONA.isSameStudent(editedFiona));

        // different student ID, all other attributes same -> returns false
        editedFiona = new StudentBuilder(FIONA).withStudentId(VALID_ID_BOB).build();
        assertFalse(FIONA.isSameStudent(editedFiona));

        // student ID differs in case, all other attributes same -> returns true
        Student editedBenson = new StudentBuilder(BENSON)
                .withStudentId(BENSON.getStudentId().toString().toLowerCase()).build();
        assertTrue(BENSON.isSameStudent(editedBenson));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Student fionaCopy = new StudentBuilder(FIONA).build();
        assertTrue(FIONA.equals(fionaCopy));

        // same object -> returns true
        assertTrue(FIONA.equals(FIONA));

        // null -> returns false
        assertFalse(FIONA.equals(null));

        // different type -> returns false
        assertFalse(FIONA.equals(5));

        // different person -> returns false
        assertFalse(FIONA.equals(BENSON));

        // different student ID -> returns false
        Student editedFiona = new StudentBuilder(FIONA).withStudentId(VALID_ID_BOB).build();
        assertFalse(FIONA.equals(editedFiona));

        // different phone -> returns false
        editedFiona = new StudentBuilder(FIONA).withName(VALID_NAME_BOB).build();
        assertFalse(FIONA.equals(editedFiona));

        // different email -> returns false
        editedFiona = new StudentBuilder(FIONA).withEmail(VALID_EMAIL_AMY).build();
        assertFalse(FIONA.equals(editedFiona));

        // different address -> returns false
        editedFiona = new StudentBuilder(FIONA).withTelegram(VALID_TELEGRAM_AMY).build();
        assertFalse(FIONA.equals(editedFiona));
    }
}
