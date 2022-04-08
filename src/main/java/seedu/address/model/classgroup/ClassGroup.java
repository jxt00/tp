package seedu.address.model.classgroup;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.model.entity.Entity;
import seedu.address.model.entity.EntityType;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.lesson.WeekId;
import seedu.address.model.lesson.exceptions.LessonNotFoundException;
import seedu.address.model.student.Student;
import seedu.address.model.student.UniqueStudentList;
import seedu.address.model.tamodule.TaModule;

//@@author jxt00
/**
 * Represents a ClassGroup in the TAssist.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class ClassGroup implements Entity {
    // Identity fields
    public static final int NUM_OF_WEEKS = 13;
    private final ClassGroupId classGroupId;
    private final ClassGroupType classGroupType;
    private final TaModule taModule;
    private final UniqueStudentList uniqueStudentList;
    private final List<Lesson> lessons;

    /**
     * Constructs a {@code ClassGroup}.
     * Every field must be present and not null.
     * Used to initialize a new class group with no students.
     *
     * @param classGroupId A valid class group ID.
     * @param classGroupType A valid class group type.
     * @param taModule A valid module.
     */
    public ClassGroup(ClassGroupId classGroupId, ClassGroupType classGroupType, TaModule taModule) {
        this(classGroupId, classGroupType, taModule, new UniqueStudentList());
    }

    /**
     * Constructs a {@code ClassGroup}.
     * Every field must be present and not null.
     * Used to initialize a class group from storage file.
     *
     * @param classGroupId A valid class group ID.
     * @param classGroupType A valid class group type.
     * @param taModule A valid module.
     */
    public ClassGroup(ClassGroupId classGroupId, ClassGroupType classGroupType, TaModule taModule,
                      UniqueStudentList uniqueStudentList) {
        this.classGroupId = classGroupId;
        this.classGroupType = classGroupType;
        this.taModule = taModule;
        this.uniqueStudentList = uniqueStudentList;

        // initialize the 13 lessons
        this.lessons = IntStream.rangeClosed(1, NUM_OF_WEEKS)
                .mapToObj(entry -> new Lesson(new WeekId(String.valueOf(entry))))
                .collect(Collectors.toList());
    }

    /**
     * Construct a {@code ClassGroup} by copying all the provided fields.
     */
    public ClassGroup(ClassGroupId classGroupId, ClassGroupType classGroupType, TaModule taModule,
                      UniqueStudentList uniqueStudentList, List<Lesson> lessons) {
        this.classGroupId = classGroupId;
        this.classGroupType = classGroupType;
        this.taModule = taModule;
        this.uniqueStudentList = uniqueStudentList;
        this.lessons = lessons;
    }

    /**
     * Constructs a {@code ClassGroup} with a new module.
     * Every field must be present and not null.
     * Used to initialize a class group for enrollment.
     *
     * @param toCopy A valid class group.
     */
    public ClassGroup(ClassGroup toCopy, TaModule newModule) {
        this(toCopy.getClassGroupId(), toCopy.getClassGroupType(), newModule, new UniqueStudentList(),
                toCopy.lessons);
        uniqueStudentList.setStudents(toCopy.uniqueStudentList);
    }

    /**
     * Constructs a {@code ClassGroup}.
     * Every field must be present and not null.
     * Used to initialize a class group from storage file.
     *
     * @param toCopy A valid class group.
     */
    public ClassGroup(ClassGroup toCopy) {
        this(toCopy.getClassGroupId(), toCopy.getClassGroupType(), toCopy.getModule(), new UniqueStudentList(),
                toCopy.lessons);
        uniqueStudentList.setStudents(toCopy.uniqueStudentList);
    }

    public ClassGroupId getClassGroupId() {
        return classGroupId;
    }

    public ClassGroupType getClassGroupType() {
        return classGroupType;
    }

    public TaModule getModule() {
        return taModule;
    }

    /**
     * Adds a student to the {@code uniqueStudentList} and initialize his/her
     * attendance for all lessons.
     */
    public void addStudent(Student s) {
        uniqueStudentList.add(s);
        for (Lesson lesson : lessons) {
            lesson.addStudent(s);
        }
    }

    public boolean hasStudent(Student s) {
        return uniqueStudentList.contains(s);
    }

    /**
     * Removes a student from the {@code uniqueStudentList} as well as
     * all the lessons under the current class group.
     */
    public void removeStudent(Student s) {
        uniqueStudentList.remove(s);
        for (Lesson lesson : lessons) {
            lesson.removeStudent(s);
        }
    }

    public ObservableList<Student> getStudents() {
        return uniqueStudentList.asUnmodifiableObservableList();
    }

    //@@author EvaderFati
    /**
     * Finds the lesson according to the specified weekIndex and marks attendance
     * for all students in the given student list.
     */
    public List<Student> markAttendance(Index weekIndex, List<Student> students) {
        Lesson toMark = findLessonByIndex(weekIndex);
        return toMark.markAttendance(students);
    }

    //@@author EvaderFati
    /**
     * Finds the lesson according to the specified weekIndex and unmarks attendance
     * for all students in the given student list.
     */
    public List<Student> unmarkAttendance(Index weekIndex, List<Student> students) {
        Lesson toUnmark = findLessonByIndex(weekIndex);
        return toUnmark.unmarkAttendance(students);
    }

    //@@author jxt00
    /**
     * Finds the lesson in the lesson list by the provided weekIndex.
     * weekIndex will always be correct.
     */
    private Lesson findLessonByIndex(Index weekIndex) {
        return lessons.stream()
                .filter(lesson -> lesson.getWeekId().value.equals(weekIndex.getOneBased()))
                .findFirst()
                .orElseThrow(() -> new LessonNotFoundException());
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    @Override
    public EntityType getEntityType() {
        return EntityType.CLASS_GROUP;
    }

    /**
     * Returns true if both class groups have the same identity and fields.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof ClassGroup)) {
            return false;
        }

        ClassGroup otherClassGroup = (ClassGroup) other;
        return otherClassGroup.getClassGroupId().equals(getClassGroupId())
                && otherClassGroup.getClassGroupType().equals(getClassGroupType())
                && otherClassGroup.getModule().equals(getModule())
                && otherClassGroup.getStudents().equals(getStudents());
    }

    @Override
    public int hashCode() {
        return Objects.hash(classGroupId, classGroupType, taModule, uniqueStudentList);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getClassGroupId())
                .append("; Type: ")
                .append(getClassGroupType())
                .append("; Module Code: ")
                .append(getModule().getModuleCode())
                .append("; Academic Year: ")
                .append(getModule().getAcademicYear());
        return builder.toString();
    }
}
