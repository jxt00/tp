package seedu.address.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_DUPLICATE_CLASS_GROUP = "This class already exists in TAssist!\n"
            + "Classes of the same module are identified by their CLASS_GROUP_IDs and MODULE_INDEXs, "
            + "which are case-insensitive";
    public static final String MESSAGE_DUPLICATE_MODULE = "This module already exists in TAssist!\n"
            + "Modules in the same academic year are identified by their MODULE_CODEs and ACADEMIC_YEARs,"
            + "which are case-insensitive";
    public static final String MESSAGE_DUPLICATE_STUDENT = "This student already exists in TAssist!\n"
            + "Students are identified by their STUDENT_IDs, which are case-insensitive";
    public static final String MESSAGE_DUPLICATE_ASSESSMENT = "This assessment already exists in TAssist!\n"
            + "Assessments are identified either by their ASSESSMENT_NAMEs + MODULE_INDEXs"
            + "OR by their SIMPLE_NAMEs + MODULE_INDEXs";
    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_UNKNOWN_ENTITY = "Unknown entity!\n"
            + "Available entities: student | module | class | assessment";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_ASSESSMENT_DISPLAYED_INDEX = "The assessment index provided is invalid";
    public static final String MESSAGE_INVALID_CLASS_GROUP_DISPLAYED_INDEX = "The class index provided is invalid";
    public static final String MESSAGE_INVALID_CLASS_GROUP_TYPE = "The class group type provided is invalid!\n"
            + "Available types: lab | recitation | sectional | tutorial";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The people index provided is invalid";
    public static final String MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX = "The student index provided is invalid";
    public static final String MESSAGE_INVALID_TA_MODULE_DISPLAYED_INDEX = "The module index provided is invalid";
    public static final String MESSAGE_INVALID_WEEK_INDEX = "The week index provided is invalid.";
    public static final String MESSAGE_STUDENTS_LISTED_OVERVIEW = "%1$d students listed!";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d persons listed!";
    public static final String MESSAGE_ERROR_MESSAGE_FORMAT = "%s\n\n%s";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT_2 = "Invalid command format!";
}
