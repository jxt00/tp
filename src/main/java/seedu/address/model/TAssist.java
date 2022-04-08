package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.model.assessment.Assessment;
import seedu.address.model.assessment.UniqueAssessmentList;
import seedu.address.model.classgroup.ClassGroup;
import seedu.address.model.classgroup.UniqueClassGroupList;
import seedu.address.model.student.Student;
import seedu.address.model.student.StudentId;
import seedu.address.model.student.UniqueStudentList;
import seedu.address.model.student.exceptions.StudentNotFoundException;
import seedu.address.model.tamodule.TaModule;
import seedu.address.model.tamodule.UniqueModuleList;

/**
 * Wraps all data at the TAssist level.
 * Duplicates are not allowed (by .isSameX comparison, where X is the corresponding entity).
 */
public class TAssist implements ReadOnlyTAssist {

    private final UniqueStudentList students;
    private final UniqueModuleList modules;
    private final UniqueClassGroupList classGroups;
    private final UniqueAssessmentList assessments;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        students = new UniqueStudentList();
        modules = new UniqueModuleList();
        classGroups = new UniqueClassGroupList();
        assessments = new UniqueAssessmentList();
    }

    public TAssist() {
    }

    /**
     * Creates a TAssist using the lists in the {@code toBeCopied}.
     */
    public TAssist(ReadOnlyTAssist toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the student list with {@code students}.
     * {@code students} must not contain duplicate students.
     */
    public void setStudents(List<Student> students) {
        this.students.setStudents(students);
    }

    /**
     * Replaces the contents of the module list with {@code modules}.
     * {@code modules} must not contain duplicate modules.
     */
    public void setModules(List<TaModule> taModules) {
        this.modules.setModules(taModules);
    }

    /**
     * Replaces the contents of the class group list with {@code classGroups}.
     * {@code classGroups} must not contain duplicate class groups.
     */
    public void setClassGroups(List<ClassGroup> classGroups) {
        this.classGroups.setClassGroups(classGroups);
    }

    /**
     * Replaces the contents of the assessment list with {@code assessments}.
     * {@code assessments} must not contain duplicate assessments.
     */
    public void setAssessments(List<Assessment> assessments) {
        this.assessments.setAssessments(assessments);
    }

    /**
     * Resets the existing data of this {@code TAssist} with {@code newData}.
     */
    public void resetData(ReadOnlyTAssist newData) {
        requireNonNull(newData);

        setStudents(newData.getStudentList());
        setModules(newData.getModuleList());
        setClassGroups(newData.getClassGroupList());
        setAssessments(newData.getAssessmentList());
    }

    //// student-level operations

    /**
     * Returns true if a student with the same identity as {@code student} exists in the TAssist.
     */
    public boolean hasStudent(Student student) {
        requireNonNull(student);
        return students.contains(student);
    }

    /**
     * Returns true if a student with the same StudentId exists in the TAssist.
     */
    public boolean hasStudent(StudentId studentId) {
        requireNonNull(studentId);
        return students.contains(studentId);
    }

    /**
     * Adds a student to the TAssist.
     * The student must not already exist in the TAssist.
     */
    public void addStudent(Student student) {
        students.add(student);
    }

    /**
     * Replaces the given student {@code target} in the list with {@code editedStudent}.
     * {@code target} must exist in the TAssist.
     * The student identity of {@code editedStudent} must not be the same as another existing student in the TAssist.
     */
    public void setStudent(Student target, Student editedStudent) {
        requireNonNull(editedStudent);

        students.setStudent(target, editedStudent);
    }

    /**
     * Removes {@code key} from this {@code TAssist}.
     * {@code key} must exist in the TAssist.
     */
    public void removeStudent(Student key) {
        removeStudentFromClassGroups(key);
        removeStudentFromAssessments(key);
        removeStudentFromModules(key);
        students.remove(key);

    }

    //// module-level operations

    /**
     * Returns true if a module with the same identity as {@code module} exists in the TAssist.
     */
    public boolean hasModule(TaModule module) {
        requireNonNull(module);
        return modules.contains(module);
    }

    /**
     * Adds a module to the TAssist.
     * The module must not already exist in the TAssist.
     */
    public void addModule(TaModule module) {
        modules.add(module);
    }

    /**
     * Replaces the given module {@code target} in the list with {@code editedTaModule}.
     * {@code target} must exist in the TAssist.
     * The module identity of {@code editedTaModule} must not be the same as another existing module in the TAssist.
     */
    public void setModule(TaModule target, TaModule editedTaModule) {
        requireNonNull(editedTaModule);

        modules.setModule(target, editedTaModule);
    }

    /**
     * Removes {@code key} and all the class groups that tied to the {@code TaModule} from this {@code TAssist}.
     * {@code key} must exist in the TAssist.
     */
    public void removeModule(TaModule key) {
        List<ClassGroup> lst = classGroups.findClassesOfModule(key);
        for (ClassGroup classGroup : lst) {
            this.removeClassGroup(classGroup);
        }
        List<Assessment> assessmentList = assessments.findAssessmentsOfModule(key);
        for (Assessment assessment : assessmentList) {
            this.removeAssessment(assessment);
        }
        modules.remove(key);
    }

    //// classGroup-level operations

    /**
     * Returns true if a classGroup with the same identity as {@code classGroup} exists in the TAssist.
     */
    public boolean hasClassGroup(ClassGroup classGroup) {
        requireNonNull(classGroup);
        return classGroups.contains(classGroup);
    }

    /**
     * Adds a classGroup to the TAssist.
     * The classGroup must not already exist in the TAssist.
     */
    public void addClassGroup(ClassGroup classGroup) {
        classGroups.add(classGroup);
    }

    /**
     * Replaces the given classGroup {@code target} in the list with {@code editedClassGroup}.
     * {@code target} must exist in the TAssist.
     * The classGroup identity of {@code editedClassGroup} must not be the same as another
     * existing classGroup in the TAssist.
     */
    public void setClassGroup(ClassGroup target, ClassGroup editedClassGroup) {
        requireNonNull(editedClassGroup);

        classGroups.setClassGroup(target, editedClassGroup);
    }

    /**
     * Removes {@code key} from this {@code TAssist}.
     * {@code key} must exist in the TAssist.
     */
    public void removeClassGroup(ClassGroup key) {
        classGroups.remove(key);
    }


    //// assessment-level operations

    /**
     * Returns true if an assessment with the same identity as {@code assessment} exists in the TAssist.
     */
    public boolean hasAssessment(Assessment assessment) {
        requireNonNull(assessment);
        return assessments.contains(assessment);
    }

    /**
     * Adds an assessment to the TAssist.
     * The assessment must not already exist in the TAssist.
     */
    public void addAssessment(Assessment assessment) {
        assessments.add(assessment);
    }

    /**
     * Replaces the given assessment {@code target} in the list with {@code editedAssessment}.
     * {@code target} must exist in the TAssist.
     * The assessment identity of {@code editedAssessment} must not be the same as another
     * existing assessment in the TAssist.
     */
    public void setAssessment(Assessment target, Assessment editedAssessment) {
        requireNonNull(editedAssessment);

        assessments.setAssessment(target, editedAssessment);
    }

    /**
     * Removes {@code key} from this {@code TAssist}.
     * {@code key} must exist in the TAssist.
     */
    public void removeAssessment(Assessment key) {
        assessments.remove(key);
    }

    /**
     * Removes the student from all the assessments.
     * @param student The student to remove.
     */
    public void removeStudentFromAssessments(Student student) {
        List<Assessment> assessmentsToModify = new ArrayList<>(assessments.asUnmodifiableObservableList());
        assessmentsToModify.stream().forEach(assessment -> assessment.removeStudent(student));
        assessments.setAssessments(assessmentsToModify);
    }

    /**
     * Removes the student from all the modules.
     * @param student The student to remove.
     */
    public void removeStudentFromModules(Student student) {
        List<TaModule> modulesToModify = new ArrayList<>(modules.asUnmodifiableObservableList());
        modulesToModify.stream().filter(module -> module.hasStudent(student))
                .forEach(module -> module.removeStudent(student));
        modules.setModules(modulesToModify);
    }

    /**
     * Removes the student from all the class groups.
     * @param student The student to remove.
     */
    public void removeStudentFromClassGroups(Student student) {
        List<ClassGroup> classGroupsToModify = new ArrayList<>(classGroups.asUnmodifiableObservableList());
        classGroupsToModify.stream().filter(classGroup -> classGroup.hasStudent(student))
                .forEach(classGroup -> classGroup.removeStudent(student));
        classGroups.setClassGroups(classGroupsToModify);
    }

    //// util methods

    @Override
    public String toString() {
        return students.asUnmodifiableObservableList().size() + " students"
                + modules.asUnmodifiableObservableList().size() + " modules"
                + classGroups.asUnmodifiableObservableList().size() + " class groups"
                + assessments.asUnmodifiableObservableList().size() + " assessments";
        // TODO: refine later
    }

    @Override
    public ObservableList<Student> getStudentList() {
        return students.asUnmodifiableObservableList();
    }

    public ObservableList<Student> getStudentListByIndexes(List<Index> indexes) {
        if (indexes.stream().anyMatch(x -> x.getZeroBased() >= getStudentList().size())) {
            throw new StudentNotFoundException();
        }
        return FXCollections.unmodifiableObservableList(
                FXCollections.observableArrayList(indexes.stream()
                .map(index -> getStudentList().get(index.getZeroBased()))
                .collect(Collectors.toList())));
    }

    public ObservableList<Student> getStudentListByStudentIds(List<StudentId> studentIds) {
        if (studentIds.stream().anyMatch(x -> !students.contains(x))) {
            throw new StudentNotFoundException();
        }
        return FXCollections.unmodifiableObservableList(
                FXCollections.observableArrayList(getStudentList().stream()
                .filter(student -> studentIds.contains(student.getStudentId())).collect(Collectors.toList())));
    }

    @Override
    public ObservableList<TaModule> getModuleList() {
        return modules.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<ClassGroup> getClassGroupList() {
        return classGroups.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Assessment> getAssessmentList() {
        return assessments.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TAssist // instanceof handles nulls
                && students.equals(((TAssist) other).students)
                && modules.equals(((TAssist) other).modules)
                && classGroups.equals(((TAssist) other).classGroups)
                && assessments.equals(((TAssist) other).assessments));
    }

    @Override
    public int hashCode() {
        return Objects.hash(students, modules, classGroups, assessments);
    }
}
