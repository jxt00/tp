package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.classgroup.ClassGroup;
import seedu.address.model.classgroup.ClassGroupId;
import seedu.address.model.classgroup.ClassGroupType;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.student.Student;
import seedu.address.model.student.UniqueStudentList;
import seedu.address.model.tamodule.TaModule;

//@@author jxt00
/**
 * Jackson-friendly version of {@link ClassGroup}.
 */
class JsonAdaptedClassGroup {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "ClassGroup's %s field is missing!";

    private final String classGroupId;
    private final String classGroupType;
    private final String moduleCode;
    private final String academicYear;
    private final List<String> classGroupStudentIds = new ArrayList<>();
    private final List<JsonAdaptedLesson> lessons = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedClassGroup} with the given class group details.
     */
    @JsonCreator
    public JsonAdaptedClassGroup(@JsonProperty("classGroupId") String classGroupId,
            @JsonProperty("classGroupType") String classGroupType,
            @JsonProperty("moduleCode") String moduleCode,
            @JsonProperty("academicYear") String academicYear,
            @JsonProperty("classGroupStudentIds") List<String> classGroupStudentIds,
            @JsonProperty("lessons") List<JsonAdaptedLesson> lessons) {
        this.classGroupId = classGroupId;
        this.classGroupType = classGroupType;
        this.moduleCode = moduleCode;
        this.academicYear = academicYear;
        if (!classGroupStudentIds.isEmpty()) {
            this.classGroupStudentIds.addAll(classGroupStudentIds);
        }
        if (!lessons.isEmpty()) {
            this.lessons.addAll(lessons);
        }
    }

    /**
     * Converts a given {@code ClassGroup} into this class for Jackson use.
     */
    public JsonAdaptedClassGroup(ClassGroup source) {
        classGroupId = source.getClassGroupId().value;
        classGroupType = source.getClassGroupType().toString();
        moduleCode = source.getModule().getModuleCode().value;
        academicYear = source.getModule().getAcademicYear().value;
        classGroupStudentIds.addAll(source.getStudents().stream()
                .map(student -> student.getStudentId().value)
                .collect(Collectors.toList()));
        lessons.addAll(source.getLessons().stream()
                .map(JsonAdaptedLesson::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted class group object into the model's {@code ClassGroup} object.
     * Checks that the module the class group is added to already exists.
     * Checks that the student tied to the module already exists.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted class group.
     */
    public ClassGroup toModelType(List<TaModule> taModuleList, List<Student> studentList) throws IllegalValueException {
        if (classGroupId == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, ClassGroupId.class.getSimpleName()));
        }
        if (!ClassGroupId.isValidClassGroupId(classGroupId)) {
            throw new IllegalValueException(ClassGroupId.MESSAGE_CONSTRAINTS);
        }
        final ClassGroupId modelClassGroupId = new ClassGroupId(classGroupId);

        if (classGroupType == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, ClassGroupType.class.getSimpleName()));
        }
        final ClassGroupType modelClassGroupType;
        try {
            modelClassGroupType = ClassGroupType.valueOf(classGroupType.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalValueException(ClassGroupType.MESSAGE_CONSTRAINTS);
        }

        final TaModule modelModule = StorageUtil.getModuleByCodeAndAcadYear(taModuleList, moduleCode, academicYear,
                MISSING_FIELD_MESSAGE_FORMAT);

        final UniqueStudentList modelStudents = new UniqueStudentList();
        for (String s : classGroupStudentIds) {
            Student sObj = StorageUtil.getStudentByStudentId(studentList, s, MISSING_FIELD_MESSAGE_FORMAT);
            modelStudents.add(sObj);
        }

        final List<Lesson> modelLessons = new ArrayList<Lesson>();
        for (JsonAdaptedLesson l : lessons) {
            modelLessons.add(l.toModelType(studentList));
        }

        return new ClassGroup(modelClassGroupId, modelClassGroupType, modelModule, modelStudents, modelLessons);
    }
}
