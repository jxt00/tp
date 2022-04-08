package seedu.address.model.assessment;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an Assessment grade in TAssist.
 * Guarantees: immutable; is valid as declared in {@link #isValidGrade(String)}
 */
public class Grade {


    public static final String MESSAGE_CONSTRAINTS =
            "Grade should only contain numbers, and it should be within the range -2147483648 to 2147483647";
    public static final String VALIDATION_REGEX = "[-]?\\d{0,10}";
    public final Integer value;

    /**
     * Constructs a {@code Grade}.
     *
     * @param grade A valid grade integer String.
     */
    public Grade(String grade) {
        requireNonNull(grade);
        checkArgument(isValidGrade(grade), MESSAGE_CONSTRAINTS);
        value = Integer.valueOf(grade);
    }

    /**
     * Constructs a {@code Grade}.
     *
     * @param value A valid grade integer.
     */
    public Grade(int value) {
        this.value = value;
    }

    /**
     * Constructs a {@code Grade} with value 1.
     */
    public Grade() {
        this(1);
    }

    /**
     * Returns true if a given string is a valid grade integer.
     */
    public static boolean isValidGrade(String test) {
        if (test.matches(VALIDATION_REGEX)) {
            try {
                Integer.valueOf(test);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return false;
    }

    /**
     * Returns a new grade with value increased by 1.
     */
    public Grade increment() {
        return new Grade(Math.addExact(this.value, 1));
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Grade // instanceof handles nulls
                && value.equals(((Grade) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
