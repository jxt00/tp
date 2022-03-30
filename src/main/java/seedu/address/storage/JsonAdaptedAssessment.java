package seedu.address.storage;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javafx.collections.FXCollections;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.assessment.Assessment;
import seedu.address.model.assessment.AssessmentName;
import seedu.address.model.assessment.Grade;
import seedu.address.model.assessment.SimpleName;
import seedu.address.model.student.Student;
import seedu.address.model.tamodule.TaModule;

//@@author jxt00
/**
 * Jackson-friendly version of {@link Assessment}.
 */
class JsonAdaptedAssessment {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Assessment's %s field is missing!";
    public static final String NONEXISTENT_MODULE = "Module does not exist!";

    private final String assessmentName;
    private final JsonAdaptedTaModule module;
    private final String simpleName;
    private final List<JsonAdaptedAttempt> attempts;

    /**
     * Constructs a {@code JsonAdaptedAssessment} with the given assessment details.
     */
    @JsonCreator
    public JsonAdaptedAssessment(@JsonProperty("assessmentName") String assessmentName,
                                 @JsonProperty("module") JsonAdaptedTaModule module,
                                 @JsonProperty("simpleName") String simpleName,
                                 @JsonProperty("attempts") List<JsonAdaptedAttempt> attempts) {
        this.assessmentName = assessmentName;
        this.module = module;
        this.simpleName = simpleName;
        this.attempts = attempts;
    }

    /**
     * Converts a given {@code Assessment} into this class for Jackson use.
     */
    public JsonAdaptedAssessment(Assessment source) {
        assessmentName = source.getAssessmentName().value;
        simpleName = source.getSimpleName().value;
        module = new JsonAdaptedTaModule(source.getTaModule());
        attempts = source.getAttempts().entrySet().stream()
                .map(JsonAdaptedAttempt::new)
                .collect(Collectors.toList());
    }

    /**
     * Converts this Jackson-friendly adapted assessment object into the model's {@code Assessment} object.
     * Checks that the student tied to the attempt already exists and is enrolled in the module.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted assessment.
     */
    public Assessment toModelType(List<TaModule> taModuleList, List<Student> studentList) throws IllegalValueException {
        if (assessmentName == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, AssessmentName.class.getSimpleName()));
        }
        if (!AssessmentName.isValidAssessmentName(assessmentName)) {
            throw new IllegalValueException(AssessmentName.MESSAGE_CONSTRAINTS);
        }
        final AssessmentName modelAssessmentName = new AssessmentName(assessmentName);

        if (module == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, TaModule.class.getSimpleName()));
        }
        final TaModule modelModule = module.toModelType(studentList);
        if (!taModuleList.contains(modelModule)) {
            throw new IllegalValueException(NONEXISTENT_MODULE);
        }

        if (simpleName == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, SimpleName.class.getSimpleName()));
        }

        if (!SimpleName.isValidSimpleName(simpleName)) {
            throw new IllegalValueException(SimpleName.MESSAGE_CONSTRAINTS);
        }
        final SimpleName modelSimpleName = new SimpleName(simpleName);

        final Map<Student, Grade> modelAttempts = FXCollections.observableHashMap();

        for (JsonAdaptedAttempt a : attempts) {
            modelAttempts.put(a.toModelType(modelModule, studentList).getKey(),
                    a.toModelType(modelModule, studentList).getValue());
        }

        return new Assessment(modelAssessmentName, modelModule, Optional.of(modelSimpleName), modelAttempts);
    }
}
