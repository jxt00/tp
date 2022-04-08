package seedu.address.model.classgroup;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CG_ID_CS2103T_TUT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CG_ID_CS2106_LAB;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

//@@author jxt00
public class ClassGroupIdTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ClassGroupId(null));
    }

    @Test
    public void constructor_invalidClassGroupId_throwsIllegalArgumentException() {
        String invalidClassGroupId = "";
        assertThrows(IllegalArgumentException.class, () -> new ClassGroupId(invalidClassGroupId));
    }

    @Test
    public void isValidClassGroupId() {
        // null class group ID
        assertThrows(NullPointerException.class, () -> ClassGroupId.isValidClassGroupId(null));

        // invalid class group IDs
        assertFalse(ClassGroupId.isValidClassGroupId("")); // empty string
        assertFalse(ClassGroupId.isValidClassGroupId(" ")); // spaces only
        assertFalse(ClassGroupId.isValidClassGroupId("B23TT")); // more than 4 characters
        assertFalse(ClassGroupId.isValidClassGroupId("T0!")); // contains symbol
        assertFalse(ClassGroupId.isValidClassGroupId("01")); // starts with a non-alphabetic character

        // valid class group IDs
        assertTrue(ClassGroupId.isValidClassGroupId("B01"));
        assertTrue(ClassGroupId.isValidClassGroupId("T13"));
        assertTrue(ClassGroupId.isValidClassGroupId("T13A"));
    }

    @Test
    public void equals() {
        ClassGroupId cs2103tTutId = new ClassGroupId(VALID_CG_ID_CS2103T_TUT);
        // same object
        assertTrue(cs2103tTutId.equals(cs2103tTutId));

        ClassGroupId cs2106LabId = new ClassGroupId(VALID_CG_ID_CS2106_LAB);
        // different objects
        assertFalse(cs2103tTutId.equals(cs2106LabId));
    }
}
