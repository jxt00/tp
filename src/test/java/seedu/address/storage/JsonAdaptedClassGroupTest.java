package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedClassGroup.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.classgroup.ClassGroupId;
import seedu.address.model.classgroup.ClassGroupType;
import seedu.address.model.student.Student;
import seedu.address.model.tamodule.TaModule;
import seedu.address.testutil.TypicalClassGroups;
import seedu.address.testutil.TypicalLessons;
import seedu.address.testutil.TypicalModules;
import seedu.address.testutil.TypicalStudents;

//@@author jxt00
public class JsonAdaptedClassGroupTest {
    private static final String INVALID_CG_ID = "CS2101!";
    private static final String INVALID_CG_TYPE = "LECTURE";

    private static final String VALID_CG_ID = TypicalClassGroups.CS2101G09.getClassGroupId().toString();
    private static final String VALID_CG_TYPE = TypicalClassGroups.CS2101G09.getClassGroupType().toString();
    private static final String VALID_MODULE_CODE = TypicalModules.CS2101.getModuleCode().toString();
    private static final String VALID_ACAD_YEAR = TypicalModules.CS2101.getAcademicYear().toString();

    private static final List<TaModule> modules = new ArrayList<>(Arrays.asList(TypicalModules.CS2101));
    private static final List<String> studentIds =
            new ArrayList<>(Arrays.asList(TypicalStudents.BENSON.getStudentId().toString()));
    private static final List<Student> students = new ArrayList<>(Arrays.asList(TypicalStudents.BENSON));
    private static final List<JsonAdaptedLesson> lessons =
            new ArrayList<>(Arrays.asList(new JsonAdaptedLesson(TypicalLessons.LESSON2)));

    @Test
    public void toModelType_validClassGroupDetails_returnsClassGroup() throws Exception {
        JsonAdaptedClassGroup classGroup = new JsonAdaptedClassGroup(
                VALID_CG_ID, VALID_CG_TYPE, VALID_MODULE_CODE, VALID_ACAD_YEAR, studentIds, lessons);
        assertEquals(TypicalClassGroups.CS2101G09, classGroup.toModelType(modules, students));
    }

    @Test
    public void toModelType_invalidClassGroupId_throwsIllegalValueException() {
        JsonAdaptedClassGroup classGroup = new JsonAdaptedClassGroup(
                INVALID_CG_ID, VALID_CG_TYPE, VALID_MODULE_CODE, VALID_ACAD_YEAR, studentIds, lessons);
        String expectedMessage = ClassGroupId.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, () -> classGroup.toModelType(modules, students));
    }

    @Test
    public void toModelType_nullClassGroupId_throwsIllegalValueException() {
        JsonAdaptedClassGroup classGroup = new JsonAdaptedClassGroup(
                null, VALID_CG_TYPE, VALID_MODULE_CODE, VALID_ACAD_YEAR, studentIds, lessons);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ClassGroupId.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, () -> classGroup.toModelType(modules, students));
    }

    @Test
    public void toModelType_invalidClassGroupType_throwsIllegalValueException() {
        JsonAdaptedClassGroup classGroup = new JsonAdaptedClassGroup(
                VALID_CG_ID, INVALID_CG_TYPE, VALID_MODULE_CODE, VALID_ACAD_YEAR, studentIds, lessons);
        String expectedMessage = ClassGroupType.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, () -> classGroup.toModelType(modules, students));
    }

    @Test
    public void toModelType_nullClassGroupType_throwsIllegalValueException() {
        JsonAdaptedClassGroup classGroup = new JsonAdaptedClassGroup(
                VALID_CG_ID, null, VALID_MODULE_CODE, VALID_ACAD_YEAR, studentIds, lessons);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ClassGroupType.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, () -> classGroup.toModelType(modules, students));
    }

    @Test
    public void toModelType_nonExistentModule_throwsIllegalValueException() {
        List<TaModule> modulesList = new ArrayList<>(Arrays.asList(TypicalModules.CS2105));
        JsonAdaptedClassGroup classGroup = new JsonAdaptedClassGroup(
                VALID_CG_ID, VALID_CG_TYPE, VALID_MODULE_CODE, VALID_ACAD_YEAR, studentIds, lessons);
        assertThrows(IllegalValueException.class, () -> classGroup.toModelType(modulesList, students));
    }

    @Test
    public void toModelType_nonExistentStudent_throwsIllegalValueException() {
        List<Student> studentsList = new ArrayList<>(Arrays.asList(TypicalStudents.ALICE));
        JsonAdaptedClassGroup classGroup = new JsonAdaptedClassGroup(
                VALID_CG_ID, VALID_CG_TYPE, VALID_MODULE_CODE, VALID_ACAD_YEAR, studentIds, lessons);
        assertThrows(IllegalValueException.class, () -> classGroup.toModelType(modules, studentsList));
    }
}
