package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import seedu.address.model.lesson.Lesson;
import seedu.address.model.lesson.WeekId;
import seedu.address.model.studentattendance.StudentAttendance;

//@@author jxt00
/**
 * A utility class to help with building Lesson objects.
 */
public class LessonBuilder {
    public static final String DEFAULT_WEEK_ID = "1";

    private WeekId weekId;
    private List<StudentAttendance> studentAttendances;

    /**
     * Creates a {@code LessonBuilder} with the default details.
     */
    public LessonBuilder() {
        weekId = new WeekId(DEFAULT_WEEK_ID);
        studentAttendances = new ArrayList<>();
    }

    /**
     * Initializes the LessonBuilder with the data of {@code lessonToCopy}.
     */

    public LessonBuilder(Lesson lessonToCopy) {
        this.weekId = lessonToCopy.getWeekId();
        this.studentAttendances = new ArrayList<>(lessonToCopy.getStudentAttendanceList());
    }

    /**
     * Sets the {@code WeekId} of the {@code Lesson} that we are building.
     */
    public LessonBuilder withWeekId(String weekId) {
        this.weekId = new WeekId(weekId);
        return this;
    }

    /**
     * Parses the {@code studentAttendances} into a {@code ArrayList<StudentAttendance>}
     * and set it to the {@code Lesson} that we are building.
     */
    public LessonBuilder withStudentAttendances(StudentAttendance ... studentAttendances) {
        this.studentAttendances = Arrays.stream(studentAttendances)
                .map(entry -> new StudentAttendance(entry.getStudent(), entry.getAttendance()))
                .collect(Collectors.toList());
        return this;
    }

    public Lesson build() {
        return new Lesson(weekId, studentAttendances);
    }
}
