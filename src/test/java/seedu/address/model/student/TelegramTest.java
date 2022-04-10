package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

//@@author wxliong
public class TelegramTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Telegram(null));
    }

    @Test
    public void constructor_invalidTelegram_throwsIllegalArgumentException() {
        String invalidTelegram = "";
        assertThrows(IllegalArgumentException.class, () -> new Telegram(invalidTelegram));
    }

    @Test
    public void isValidTelegram() {
        // null telegram
        assertThrows(NullPointerException.class, () -> Telegram.isValidTelegram(null));

        // invalid telegram handles
        assertFalse(Telegram.isValidTelegram("")); // empty string
        assertFalse(Telegram.isValidTelegram(" ")); // spaces only
        assertFalse(Telegram.isValidTelegram("amy@2000")); // special character '@' is not allowed
        assertFalse(Telegram.isValidTelegram("bob")); // should be at least 5 characters long
        assertFalse(Telegram.isValidTelegram("alice-_-!")); // special character '-' and '!' is not allowed

        // valid telegram handles
        assertTrue(Telegram.isValidTelegram("amy_2000"));
        assertTrue(Telegram.isValidTelegram("bobby"));
        assertTrue(Telegram.isValidTelegram("_alice_"));
    }
}
