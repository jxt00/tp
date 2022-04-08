package seedu.address.model.lesson;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_WEEK_ID_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_WEEK_ID_13;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

//@@author jxt00
public class WeekIdTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new WeekId(null));
    }

    @Test
    public void constructor_invalidWeekId_throwsIllegalArgumentException() {
        String invalidWeekId = "";
        assertThrows(IllegalArgumentException.class, () -> new WeekId(invalidWeekId));
    }

    @Test
    public void isValidWeekId() {
        // null week ID
        assertThrows(NullPointerException.class, () -> WeekId.isValidWeekId(null));

        // invalid week IDs
        assertFalse(WeekId.isValidWeekId("")); // empty string
        assertFalse(WeekId.isValidWeekId(" ")); // spaces only
        assertFalse(WeekId.isValidWeekId("123")); // more than 2 digits
        assertFalse(WeekId.isValidWeekId("1!")); // contains symbol
        assertFalse(WeekId.isValidWeekId("week")); // non-numeric
        assertFalse(WeekId.isValidWeekId("a1")); // starts with an alphabetic character
        assertFalse(WeekId.isValidWeekId("0")); // out-of-bounds
        assertFalse(WeekId.isValidWeekId("14")); // out-of-bounds

        // valid week IDs
        assertTrue(WeekId.isValidWeekId("01"));
        assertTrue(WeekId.isValidWeekId("1"));
        assertTrue(WeekId.isValidWeekId("13"));
    }

    @Test
    public void equals() {
        WeekId week1 = new WeekId(VALID_WEEK_ID_1);
        // same object
        assertTrue(week1.equals(week1));

        WeekId week13 = new WeekId(VALID_WEEK_ID_13);
        // different objects
        assertFalse(week1.equals(week13));
    }
}
