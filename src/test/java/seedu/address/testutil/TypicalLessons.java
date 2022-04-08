package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.lesson.Lesson;

//@@author jxt00
/**
 * A utility class containing a list of {@code Lesson} objects to be used in tests.
 */
public class TypicalLessons {
    public static final Lesson LESSON1 = new LessonBuilder()
            .withStudentAttendances(TypicalStudentAttendances.ALICE_ATTENDANCE)
            .withWeekId("1").build();
    public static final Lesson LESSON2 = new LessonBuilder()
            .withStudentAttendances(TypicalStudentAttendances.BENSON_ATTENDANCE)
            .withWeekId("2").build();
    public static final Lesson LESSON3 = new LessonBuilder()
            .withStudentAttendances(TypicalStudentAttendances.CARL_ATTENDANCE)
            .withWeekId("3").build();
    public static final Lesson LESSON11 = new LessonBuilder()
            .withStudentAttendances(TypicalStudentAttendances.DANIEL_ATTENDANCE)
            .withWeekId("11").build();
    public static final Lesson LESSON12 = new LessonBuilder()
            .withStudentAttendances(TypicalStudentAttendances.CARL_ATTENDANCE,
                    TypicalStudentAttendances.DANIEL_ATTENDANCE)
            .withWeekId("12").build();
    public static final Lesson LESSON13 = new LessonBuilder()
            .withStudentAttendances(TypicalStudentAttendances.ELLE_ATTENDANCE,
                    TypicalStudentAttendances.FIONA_ATTENDANCE)
            .withWeekId("13").build();

    private TypicalLessons() {} // prevents instantiation

    public static List<Lesson> getTypicalLessons() {
        return new ArrayList<>(Arrays.asList(LESSON1, LESSON2, LESSON3, LESSON11, LESSON12, LESSON13));
    }
}
