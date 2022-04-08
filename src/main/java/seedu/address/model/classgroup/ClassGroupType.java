package seedu.address.model.classgroup;

//@@author jxt00
/**
 * Represents a ClassGroupType in the TAssist.
 * Only these 4 types are acceptable.
 */
public enum ClassGroupType {
    LAB("Lab"),
    RECITATION("Recitation"),
    SECTIONAL("Sectional"),
    TUTORIAL("Tutorial");

    public static final String MESSAGE_CONSTRAINTS =
            "Class type should only be Lab, Recitation, Sectional or Tutorial.";
    private final String value;

    ClassGroupType(String classGroupType) {
        this.value = classGroupType;
    }

    @Override
    public String toString() {
        return value;
    }
}
