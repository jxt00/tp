package seedu.address.model.tamodule;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Module's name in the TAssist.
 * Guarantees: immutable; is valid as declared in {@link #isValidModuleName(String)}
 */
public class ModuleName {

    public static final String MESSAGE_CONSTRAINTS =
            "Names should not be blank";

    /**
     * The first character of the Module Name must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     * A more specific VALIDATION_REGEX that works for all modules in AY 21/22 would be
     * "[\\p{L}\\p{Digit}'#“][\\p{L}\\p{Digit}‐\\-,:&.()'–\n/“”+?’!‘# ]*";
     * However, using a more general regex as newer module with other special characters maybe added.
     */
    public static final String VALIDATION_REGEX = "[^ ].*[\n]?.*";

    public final String value;

    /**
     * Constructs a {@code ModuleName}.
     *
     * @param moduleName A valid module name.
     */
    public ModuleName(String moduleName) {
        requireNonNull(moduleName);
        checkArgument(isValidModuleName(moduleName), MESSAGE_CONSTRAINTS);
        value = moduleName;
    }

    /**
     * Returns true if a given string is a valid module name.
     */
    public static boolean isValidModuleName(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ModuleName // instanceof handles nulls
                && value.equals(((ModuleName) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
