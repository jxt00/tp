package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.model.assessment.Assessment;
import seedu.address.model.classgroup.ClassGroup;
import seedu.address.model.entity.Entity;
import seedu.address.model.entity.EntityConverter;
import seedu.address.model.entity.exceptions.DifferentEntityException;
import seedu.address.model.entity.exceptions.UnknownEntityException;
import seedu.address.model.student.Student;
import seedu.address.model.student.StudentId;
import seedu.address.model.tamodule.TaModule;
import seedu.address.model.tamodule.exceptions.ModuleNotFoundException;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final TAssist tAssist;
    private final UserPrefs userPrefs;
    private final FilteredList<Student> filteredStudents;
    private final FilteredList<TaModule> filteredModules;
    private final FilteredList<ClassGroup> filteredClassGroups;
    private final FilteredList<Assessment> filteredAssessments;

    /**
     * Initializes a ModelManager with the given tAssist and userPrefs.
     */
    public ModelManager(ReadOnlyTAssist tAssist, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(tAssist, userPrefs);

        logger.fine("Initializing with TAssist: " + tAssist + " and user prefs " + userPrefs);

        this.tAssist = new TAssist(tAssist);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredStudents = new FilteredList<>(this.tAssist.getStudentList());
        filteredModules = new FilteredList<>(this.tAssist.getModuleList());
        filteredClassGroups = new FilteredList<>(this.tAssist.getClassGroupList());
        filteredAssessments = new FilteredList<>(this.tAssist.getAssessmentList());
    }

    public ModelManager() {
        this(new TAssist(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }


    @Override
    public Path getTAssistFilePath() {
        return userPrefs.getTAssistFilePath();
    }

    @Override
    public void setTAssistFilePath(Path tAssistFilePath) {
        requireNonNull(tAssistFilePath);
        userPrefs.setTAssistFilePath(tAssistFilePath);
    }

    //=========== TAssist ================================================================================

    @Override
    public void setTAssist(ReadOnlyTAssist tAssist) {
        this.tAssist.resetData(tAssist);
    }

    @Override
    public ReadOnlyTAssist getTAssist() {
        return tAssist;
    }

    @Override
    public boolean hasEntity(Entity entity) {
        requireNonNull(entity);
        switch (entity.getEntityType()) {
        case STUDENT:
            return tAssist.hasStudent(EntityConverter.entityToStudent(entity));
        case TA_MODULE:
            return tAssist.hasModule(EntityConverter.entityToTaModule(entity));
        case CLASS_GROUP:
            return tAssist.hasClassGroup(EntityConverter.entityToClassGroup(entity));
        case ASSESSMENT:
            return tAssist.hasAssessment(EntityConverter.entityToAssessment(entity));
        default:
            throw new UnknownEntityException();
        }
    }

    @Override
    public void deleteEntity(Entity target) {
        requireNonNull(target);
        switch (target.getEntityType()) {
        case STUDENT:
            tAssist.removeStudent(EntityConverter.entityToStudent(target));
            break;
        case TA_MODULE:
            tAssist.removeModule(EntityConverter.entityToTaModule(target));
            break;
        case CLASS_GROUP:
            tAssist.removeClassGroup(EntityConverter.entityToClassGroup(target));
            break;
        case ASSESSMENT:
            tAssist.removeAssessment(EntityConverter.entityToAssessment(target));
            break;
        default:
            throw new UnknownEntityException();
        }
    }

    @Override
    public void addEntity(Entity entity) {
        requireNonNull(entity);
        switch (entity.getEntityType()) {
        case STUDENT:
            tAssist.addStudent(EntityConverter.entityToStudent(entity));
            updateFilteredStudentList(PREDICATE_SHOW_ALL);
            break;
        case TA_MODULE:
            tAssist.addModule(EntityConverter.entityToTaModule(entity));
            updateFilteredModuleList(PREDICATE_SHOW_ALL);
            break;
        case CLASS_GROUP:
            tAssist.addClassGroup(EntityConverter.entityToClassGroup(entity));
            updateFilteredClassGroupList(PREDICATE_SHOW_ALL);
            break;
        case ASSESSMENT:
            tAssist.addAssessment(EntityConverter.entityToAssessment(entity));
            updateFilteredAssessmentList(PREDICATE_SHOW_ALL);
            break;
        default:
            throw new UnknownEntityException();
        }
    }

    @Override
    public void setEntity(Entity target, Entity editedEntity) {
        requireAllNonNull(target, editedEntity);
        switch (target.getEntityType()) {
        case STUDENT:
            Student targetStudent = EntityConverter.entityToStudent(target);
            Student editedStudent = EntityConverter.entityToStudent(editedEntity);
            tAssist.setStudent(targetStudent, editedStudent);
            break;
        case TA_MODULE:
            TaModule targetModule = EntityConverter.entityToTaModule(target);
            TaModule editedModule = EntityConverter.entityToTaModule(editedEntity);
            tAssist.setModule(targetModule, editedModule);
            break;
        case CLASS_GROUP:
            ClassGroup targetClassGroup = EntityConverter.entityToClassGroup(target);
            ClassGroup editedClassGroup = EntityConverter.entityToClassGroup(editedEntity);
            tAssist.setClassGroup(targetClassGroup, editedClassGroup);
            break;
        case ASSESSMENT:
            Assessment targetAssessment = EntityConverter.entityToAssessment(target);
            Assessment editedAssessment = EntityConverter.entityToAssessment(editedEntity);
            tAssist.setAssessment(targetAssessment, editedAssessment);
            break;
        default:
            throw new DifferentEntityException(target.getEntityType(), editedEntity.getEntityType());
        }
    }

    //=========== Filtered List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of the various entities backed by the internal list of
     * {@code versionedTAssist}.
     */


    @Override
    public ObservableList<Student> getFilteredStudentList() {
        return filteredStudents;
    }

    @Override
    public ObservableList<TaModule> getFilteredModuleList() {
        return filteredModules;
    }

    @Override
    public ObservableList<ClassGroup> getFilteredClassGroupList() {
        return filteredClassGroups;
    }

    @Override
    public ObservableList<Assessment> getFilteredAssessmentList() {
        return filteredAssessments;
    }

    @Override
    public ObservableList<Student> getUnfilteredStudentList() {
        return tAssist.getStudentList();
    }

    @Override
    public ObservableList<TaModule> getUnfilteredModuleList() {
        return tAssist.getModuleList();
    }

    @Override
    public ObservableList<ClassGroup> getUnfilteredClassGroupList() {
        return tAssist.getClassGroupList();
    }

    @Override
    public ObservableList<Assessment> getUnfilteredAssessmentList() {
        return tAssist.getAssessmentList();
    }

    @Override
    public TaModule getModule(TaModule module) {
        return getUnfilteredModuleList().stream().filter(mod -> mod.isSameModule(module)).findFirst()
                .orElseThrow(() -> new ModuleNotFoundException());
    }

    @Override
    public boolean hasStudent(StudentId studentId) {
        return tAssist.hasStudent(studentId);
    }

    @Override
    public void removeStudentFromAssessments(Student student) {
        tAssist.removeStudentFromAssessments(student);
    }

    @Override
    public ObservableList<Student> getStudentListByIndexes(List<Index> indexes) {
        return tAssist.getStudentListByIndexes(indexes);
    }

    @Override
    public ObservableList<Student> getStudentListByStudentIds(List<StudentId> studentIds) {
        return tAssist.getStudentListByStudentIds(studentIds);
    }

    @Override
    public void updateFilteredStudentList(Predicate<? super Student> predicate) {
        requireNonNull(predicate);
        filteredStudents.setPredicate(predicate);
    }

    @Override
    public void updateFilteredModuleList(Predicate<? super TaModule> predicate) {
        requireNonNull(predicate);
        filteredModules.setPredicate(predicate);
    }

    @Override
    public void updateFilteredClassGroupList(Predicate<? super ClassGroup> predicate) {
        requireNonNull(predicate);
        filteredClassGroups.setPredicate(predicate);
    }

    @Override
    public void updateFilteredAssessmentList(Predicate<? super Assessment> predicate) {
        requireNonNull(predicate);
        filteredAssessments.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return tAssist.equals(other.tAssist)
                && userPrefs.equals(other.userPrefs)
                && filteredStudents.equals(other.filteredStudents)
                && filteredModules.equals(other.filteredModules)
                && filteredClassGroups.equals(other.filteredClassGroups)
                && filteredAssessments.equals(other.filteredAssessments);
    }

}
