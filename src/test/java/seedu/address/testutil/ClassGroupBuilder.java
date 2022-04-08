package seedu.address.testutil;

import static seedu.address.model.classgroup.ClassGroup.NUM_OF_WEEKS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import seedu.address.model.classgroup.ClassGroup;
import seedu.address.model.classgroup.ClassGroupId;
import seedu.address.model.classgroup.ClassGroupType;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.lesson.WeekId;
import seedu.address.model.student.Student;
import seedu.address.model.student.UniqueStudentList;
import seedu.address.model.studentattendance.StudentAttendance;
import seedu.address.model.tamodule.AcademicYear;
import seedu.address.model.tamodule.ModuleCode;
import seedu.address.model.tamodule.ModuleName;
import seedu.address.model.tamodule.TaModule;

/**
 * A utility class to help with building ClassGroup objects.
 */
public class ClassGroupBuilder {
    public static final String DEFAULT_CLASS_GROUP_ID = "G09";
    public static final String DEFAULT_CLASS_GROUP_TYPE = "SECTIONAL";
    public static final String DEFAULT_MODULE_NAME = "Software Engineering";
    public static final String DEFAULT_MODULE_CODE = "CS2101";
    public static final String DEFAULT_ACADEMIC_YEAR = "21S1";
    public static final String DEFAULT_WEEK_ID = "1";

    private ClassGroupId classGroupId;
    private ClassGroupType classGroupType;
    private TaModule module;
    private UniqueStudentList uniqueStudentList;
    private List<Lesson> lessons;

    /**
     * Creates a {@code ClassGroupBuilder} with the default details.
     */
    public ClassGroupBuilder() {
        classGroupId = new ClassGroupId(DEFAULT_CLASS_GROUP_ID);
        classGroupType = ClassGroupType.valueOf(DEFAULT_CLASS_GROUP_TYPE);
        module = new TaModule(new ModuleName(DEFAULT_MODULE_NAME), new ModuleCode(DEFAULT_MODULE_CODE),
                new AcademicYear(DEFAULT_ACADEMIC_YEAR));
        uniqueStudentList = new UniqueStudentList();
        lessons = IntStream.rangeClosed(1, NUM_OF_WEEKS)
                .mapToObj(entry -> new Lesson(new WeekId(String.valueOf(entry))))
                .collect(Collectors.toList());
    }

    /**
     * Initializes the ClassGroupBuilder with the data of {@code classGroupToCopy}.
     */
    public ClassGroupBuilder(ClassGroup classGroupToCopy) {
        classGroupId = classGroupToCopy.getClassGroupId();
        classGroupType = classGroupToCopy.getClassGroupType();
        module = classGroupToCopy.getModule();
        uniqueStudentList = new UniqueStudentList();
        uniqueStudentList.setStudents(classGroupToCopy.getStudents());
        lessons = new ArrayList<>(classGroupToCopy.getLessons());
    }

    /**
     * Sets the {@code ClassGroupId} of the {@code ClassGroup} that we are building.
     */
    public ClassGroupBuilder withClassGroupId(String classGroupId) {
        this.classGroupId = new ClassGroupId(classGroupId);
        return this;
    }

    /**
     * Sets the {@code ClassGroupType} of the {@code ClassGroup} that we are building.
     */
    public ClassGroupBuilder withClassGroupType(String classGroupType) {
        this.classGroupType = ClassGroupType.valueOf(classGroupType);
        return this;
    }

    /**
     * Sets the {@code Module} of the {@code ClassGroup} that we are building.
     */
    public ClassGroupBuilder withModule(String moduleName, String moduleCode, String academicYear) {
        this.module = new TaModule(new ModuleName(moduleName), new ModuleCode(moduleCode),
                new AcademicYear(academicYear));
        return this;
    }

    /**
     * Sets the {@code Module} of the {@code ClassGroup} that we are building.
     */
    public ClassGroupBuilder withModule(TaModule module) {
        this.module = module;
        return this;
    }

    //@@author jxt00
    /**
     * Sets the {@code UniqueStudentList} of the {@code ClassGroup} that we are building.
     * Initializes his/her attendance for all lessons.
     */
    public ClassGroupBuilder withUniqueStudentList(UniqueStudentList uniqueStudentList) {
        this.uniqueStudentList = new UniqueStudentList();
        this.uniqueStudentList.setStudents(uniqueStudentList);
        for (Student s : this.uniqueStudentList) {
            for (Lesson lesson : this.lessons) {
                if (!lesson.getStudents().contains(s)) {
                    lesson.addStudent(s);
                }
                lesson.getStudents().stream()
                        .filter(s1 -> !uniqueStudentList.contains(s1))
                        .forEach(s1 -> lesson.removeStudent(s1));
            }
        }
        return this;
    }

    //@@author jxt00
    /**
     * Sets the {@code UniqueStudentList} of the {@code ClassGroup} that we are building.
     * Initializes his/her attendance for all lessons.
     */
    public ClassGroupBuilder withUniqueStudentList(Student... students) {
        this.uniqueStudentList = new UniqueStudentList();
        for (Student s : students) {
            this.uniqueStudentList.add(s);
            for (Lesson lesson : this.lessons) {
                if (!lesson.getStudents().contains(s)) {
                    lesson.addStudent(s);
                }
                lesson.getStudents().stream()
                        .filter(s1 -> !uniqueStudentList.contains(s1))
                        .forEach(s1 -> lesson.removeStudent(s1));
            }
        }
        return this;
    }

    //@@author jxt00
    /**
     * Sets the {@code Lesson} of the {@code ClassGroup} that we are building.
     * Adds the students to the uniqueStudentList as well.
     */
    public ClassGroupBuilder withLessons(List<Lesson> lessons) {
        for (Lesson lessonToCopy : lessons) {
            Lesson lesson = this.lessons.stream()
                    .filter(l -> l.getWeekId().equals(lessonToCopy.getWeekId()))
                    .findFirst()
                    .orElse(null);

            for (StudentAttendance sa : lessonToCopy.getStudentAttendanceList()) {
                // update attendance
                if (lesson.getStudents().contains(sa.getStudent())) {
                    if (sa.getAttendance().value) {
                        lesson.markAttendance(new ArrayList<>(Arrays.asList(sa.getStudent())));
                    } else {
                        lesson.unmarkAttendance(new ArrayList<>(Arrays.asList(sa.getStudent())));
                    }
                }
                // add students to lessons
                List<Lesson> lessonsToChange = this.lessons.stream()
                        .filter(l -> !l.getStudents().contains(sa.getStudent()))
                        .collect(Collectors.toList());
                for (Lesson l : lessonsToChange) {
                    l.addStudent(sa.getStudent());
                }

                // add students to uniqueStudentList
                if (!this.uniqueStudentList.contains(sa.getStudent())) {
                    this.uniqueStudentList.add(sa.getStudent());
                }
            }
        }
        // remove students from lessons if they don't exist in the uniqueStudentList
        this.lessons.stream()
                .forEach(l -> {
                    l.getStudents().stream().forEach(student -> {
                        if (!this.uniqueStudentList.contains(student)) {
                            l.removeStudent(student);
                        }
                    });
                });
        return this;
    }

    //@@author jxt00
    /**
     * Sets the {@code Lesson} of the {@code ClassGroup} that we are building.
     * Adds the students to the uniqueStudentList as well.
     */
    public ClassGroupBuilder withLessons(Lesson... lessons) {
        for (Lesson lessonToCopy : lessons) {
            Lesson lesson = this.lessons.stream()
                    .filter(l -> l.getWeekId().equals(lessonToCopy.getWeekId()))
                    .findFirst()
                    .orElse(null);

            for (StudentAttendance sa : lessonToCopy.getStudentAttendanceList()) {
                // update attendance
                if (lesson.getStudents().contains(sa.getStudent())) {
                    if (sa.getAttendance().value) {
                        lesson.markAttendance(new ArrayList<>(Arrays.asList(sa.getStudent())));
                    } else {
                        lesson.unmarkAttendance(new ArrayList<>(Arrays.asList(sa.getStudent())));
                    }
                }
                // add students to lessons
                List<Lesson> lessonsToChange = this.lessons.stream()
                        .filter(l -> !l.getStudents().contains(sa.getStudent()))
                        .collect(Collectors.toList());
                for (Lesson l : lessonsToChange) {
                    l.addStudent(sa.getStudent());
                }

                // add students to uniqueStudentList
                if (!this.uniqueStudentList.contains(sa.getStudent())) {
                    this.uniqueStudentList.add(sa.getStudent());
                }
            }
        }
        // remove students from lessons if they don't exist in the uniqueStudentList
        this.lessons.stream()
                .forEach(l -> {
                    l.getStudents().stream().forEach(student -> {
                        if (!this.uniqueStudentList.contains(student)) {
                            l.removeStudent(student);
                        }
                    });
                });
        return this;
    }

    public ClassGroup build() {
        return new ClassGroup(classGroupId, classGroupType, module, uniqueStudentList, lessons);
    }
}
