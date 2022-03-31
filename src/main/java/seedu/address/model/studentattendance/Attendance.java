package seedu.address.model.studentattendance;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

//@@author jxt00
/**
 * Represents a student's attendance for lessons in the TAssist.
 * Guarantees: immutable;
 */
public class Attendance {
    public static final String MESSAGE_CONSTRAINTS =
            "Attendance should only be false or true";

    private final Boolean value;

    /**
     * Constructs a {@code Attendance}.
     *
     * @param attendance A valid attendance value.
     */
    public Attendance(Boolean attendance) {
        requireNonNull(attendance);
        this.value = attendance;
    }

    /**
     * Constructs a {@code Attendance}.
     *
     * @param attendance A valid attendance string boolean.
     */
    public Attendance(String attendance) {
        requireNonNull(attendance);
        checkArgument(isValidAttendance(attendance), MESSAGE_CONSTRAINTS);
        this.value = Boolean.valueOf(attendance);
    }

    public Boolean getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Attendance // instanceof handles nulls
                && value.equals(((Attendance) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    /**
     * Returns true if a given string is a valid boolean.
     */
    public static boolean isValidAttendance(String attendance) {
        if (attendance.equals("false") || attendance.equals("true")) {
            return true;
        }
        return false;
    }
}