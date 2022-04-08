package seedu.address.model;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.model.assessment.Assessment;
import seedu.address.model.classgroup.ClassGroup;
import seedu.address.model.entity.Entity;
import seedu.address.model.student.Student;
import seedu.address.model.student.StudentId;
import seedu.address.model.tamodule.TaModule;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true. */
    Predicate<Entity> PREDICATE_SHOW_ALL = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' TAssist file path.
     */
    Path getTAssistFilePath();

    /**
     * Sets the user prefs' TAssist file path.
     */
    void setTAssistFilePath(Path tAssistFilePath);

    /**
     * Replaces TAssist data with the data in {@code TAssist}.
     */
    void setTAssist(ReadOnlyTAssist tAssist);

    /** Returns the TAssist. */
    ReadOnlyTAssist getTAssist();

    /**
     * Returns true if an entity with the same identity as {@code entity} exists in the TAssist.
     */
    boolean hasEntity(Entity entity);

    /**
     * Deletes the given entity.
     * The entity must exist in the TAssist.
     */
    void deleteEntity(Entity target);

    /**
     * Adds the given entity.
     * {@code entity} must not already exist in the TAssist.
     */
    void addEntity(Entity entity);

    /**
     * Replaces the given entity {@code target} with {@code editedEntity}.
     * {@code target} must exist in the TAssist.
     * The entity identity of {@code editedEntity} must not be the same as another existing entity in the TAssist.
     */
    void setEntity(Entity target, Entity editedEntity);

    /** Returns an unmodifiable view of the filtered student list. */
    ObservableList<Student> getFilteredStudentList();

    /** Returns an unmodifiable view of the filtered module list. */
    ObservableList<TaModule> getFilteredModuleList();

    /** Returns an unmodifiable view of the filtered class group list. */
    ObservableList<ClassGroup> getFilteredClassGroupList();

    /** Returns an unmodifiable view of the filtered assessment list. */
    ObservableList<Assessment> getFilteredAssessmentList();

    /** Returns an unmodifiable view of the unfiltered student list. */
    ObservableList<Student> getUnfilteredStudentList();

    /** Returns an unmodifiable view of the unfiltered module list. */
    ObservableList<TaModule> getUnfilteredModuleList();

    /** Returns an unmodifiable view of the unfiltered class group list. */
    ObservableList<ClassGroup> getUnfilteredClassGroupList();

    /** Returns an unmodifiable view of the unfiltered assessment list. */
    ObservableList<Assessment> getUnfilteredAssessmentList();

    /** Returns the updated module in tassist. */
    TaModule getModule(TaModule module);

    /** Returns true if a student with the same studentId exists in tassist. */
    boolean hasStudent(StudentId studentId);

    /** Removes all instances of student from the assessment list. */
    void removeStudentFromAssessments(Student student);

    /** Returns an unmodifiable view of the student list given by the list of indexes. */
    ObservableList<Student> getStudentListByIndexes(List<Index> indexes);

    /** Returns an unmodifiable view of the student list given by the list of studentIds. */
    ObservableList<Student> getStudentListByStudentIds(List<StudentId> studentIds);

    /**
     * Updates the filter of the filtered student list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredStudentList(Predicate<? super Student> predicate);

    /**
     * Updates the filter of the filtered module list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredModuleList(Predicate<? super TaModule> predicate);

    /**
     * Updates the filter of the filtered class group list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredClassGroupList(Predicate<? super ClassGroup> predicate);

    /**
     * Updates the filter of the filtered assessment list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredAssessmentList(Predicate<? super Assessment> predicate);
}
