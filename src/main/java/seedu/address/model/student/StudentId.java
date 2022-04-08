package seedu.address.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

//@@author wxliong
/**
 * Represents a Student's student ID in the TAssist.
 * Guarantees: immutable; is valid as declared in {@link #isValidStudentId(String)}
 */
public class StudentId {

    public static final String MESSAGE_CONSTRAINTS = "Student ID should adhere to these requirements:"
           + "\n\t1. No special characters"
           + "\n\t2. Should not be blank"
           + "\n\t3. It should start with an 'E/e' and followed by 7 digits";
    /*
     * The first character of the student ID must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "^[Ee][0-9]{7}$";

    public final String value;

    /**
     * Constructs an {@code StudentId}.
     *
     * @param studentId A valid student ID.
     */
    public StudentId(String studentId) {
        requireNonNull(studentId);
        checkArgument(isValidStudentId(studentId), MESSAGE_CONSTRAINTS);
        value = studentId.toUpperCase();
    }

    /**
     * Returns true if a given string is a valid student ID.
     */
    public static boolean isValidStudentId(String test) {
        return test.toUpperCase().matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StudentId // instanceof handles nulls
                && value.equals(((StudentId) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
