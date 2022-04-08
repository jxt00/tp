package seedu.address.storage;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.student.Student;
import seedu.address.model.studentattendance.Attendance;
import seedu.address.model.studentattendance.StudentAttendance;

/**
 * Jackson-friendly version of {@link StudentAttendance}.
 */
class JsonAdaptedStudentAttendance {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "StudentAttendance's %s field is missing!";
    private final String studentId;
    private final String isPresent;

    /**
     * Constructs a {@code JsonAdaptedStudentAttendance} with the given student attendance details.
     */
    @JsonCreator
    public JsonAdaptedStudentAttendance(@JsonProperty("studentId") String studentId,
                                        @JsonProperty("isPresent") String isPresent) {
        this.studentId = studentId;
        this.isPresent = isPresent;
    }

    /**
     * Converts a given {@code StudentAttendance} into this class for Jackson use.
     */
    public JsonAdaptedStudentAttendance(StudentAttendance source) {
        studentId = source.getStudent().getStudentId().value;
        isPresent = source.getAttendance().toString();
    }

    /**
     * Converts this Jackson-friendly adapted class group object into the model's {@code StudentAttendance} object.
     * Checks that the student the attendance is tied to already exists.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted student attendance.
     */
    public StudentAttendance toModelType(List<Student> studentList) throws IllegalValueException {

        final Student modelStudent = StorageUtil.getStudentByStudentId(studentList, studentId,
                MISSING_FIELD_MESSAGE_FORMAT);

        if (isPresent == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Attendance.class.getSimpleName()));
        }
        if (!Attendance.isValidAttendance(isPresent)) {
            throw new IllegalValueException(Attendance.MESSAGE_CONSTRAINTS);
        }
        final Attendance modelAttendance = new Attendance(isPresent);

        return new StudentAttendance(modelStudent, modelAttendance);
    }
}
