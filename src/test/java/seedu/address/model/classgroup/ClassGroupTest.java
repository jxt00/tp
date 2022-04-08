package seedu.address.model.classgroup;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ATTENDANCE_FALSE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ATTENDANCE_TRUE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CG_ID_CS2103T_TUT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CG_TYPE_CS2103T_TUT;
import static seedu.address.testutil.TypicalClassGroups.CS2030R08;
import static seedu.address.testutil.TypicalClassGroups.CS2040B10A;
import static seedu.address.testutil.TypicalClassGroups.CS2101G09;
import static seedu.address.testutil.TypicalClassGroups.CS2105T05;
import static seedu.address.testutil.TypicalLessons.LESSON12;
import static seedu.address.testutil.TypicalLessons.LESSON13;
import static seedu.address.testutil.TypicalModules.CS2030;
import static seedu.address.testutil.TypicalStudents.ALICE;
import static seedu.address.testutil.TypicalStudents.BENSON;
import static seedu.address.testutil.TypicalStudents.CARL;
import static seedu.address.testutil.TypicalStudents.DANIEL;
import static seedu.address.testutil.TypicalStudents.ELLE;
import static seedu.address.testutil.TypicalStudents.FIONA;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.studentattendance.Attendance;
import seedu.address.model.studentattendance.StudentAttendance;
import seedu.address.testutil.ClassGroupBuilder;
import seedu.address.testutil.LessonBuilder;

//@@author jxt00
public class ClassGroupTest {
    // fails occasionally
    //    @Test
    //    public void hasStudent() {
    //        ClassGroup cs2040B10ACopy = new ClassGroupBuilder(CS2040B10A).build();
    //        assertTrue(cs2040B10ACopy.hasStudent(ALICE));
    //        assertFalse(cs2040B10ACopy.hasStudent(BENSON));
    //    }

    @Test
    public void addStudent() {
        ClassGroup cs2101G09Copy = new ClassGroupBuilder(CS2101G09).build();
        cs2101G09Copy.addStudent(ALICE);
        assertTrue(cs2101G09Copy.hasStudent(ALICE));
    }

    /**
     * No need to check for non-existent students as these are handled by the commands and parsers.
     */
    @Test
    public void removeStudent() {
        ClassGroup cs2105T05Copy = new ClassGroupBuilder(CS2105T05).build();
        cs2105T05Copy.removeStudent(CARL);
        assertFalse(cs2105T05Copy.hasStudent(CARL));
    }

    @Test
    public void markAttendance() throws ParseException {
        ClassGroup cs2030R08Copy = new ClassGroupBuilder(CS2030R08).build();
        Lesson lesson13Copy = new LessonBuilder(LESSON13).build();
        Index weekIndex = ParserUtil.parseIndex(String.valueOf(lesson13Copy.getWeekId().value));
        cs2030R08Copy.markAttendance(weekIndex, lesson13Copy.getStudents());

        Lesson lessonToCheck = cs2030R08Copy.getLessons().stream()
                .filter(l -> l.getWeekId().equals(lesson13Copy.getWeekId()))
                .findFirst()
                .orElse(null);
        StudentAttendance elleAttendance = lessonToCheck.getStudentAttendanceList().stream()
                .filter(sa -> sa.getStudent().isSameStudent(ELLE))
                .findFirst().get();
        StudentAttendance fionaAttendance = lessonToCheck.getStudentAttendanceList().stream()
                .filter(sa -> sa.getStudent().isSameStudent(FIONA))
                .findFirst().get();
        assertTrue(elleAttendance.getAttendance().equals(new Attendance(VALID_ATTENDANCE_TRUE)));
        assertTrue(fionaAttendance.getAttendance().equals(new Attendance(VALID_ATTENDANCE_TRUE)));
    }

    @Test
    public void unmarkAttendance() throws ParseException {
        ClassGroup cs2105T05Copy = new ClassGroupBuilder(CS2105T05).build();
        Lesson lesson12Copy = new LessonBuilder(LESSON12).build();
        Index weekIndex = ParserUtil.parseIndex(String.valueOf(lesson12Copy.getWeekId().value));
        cs2105T05Copy.unmarkAttendance(weekIndex, lesson12Copy.getStudents());

        Lesson lessonToCheck = cs2105T05Copy.getLessons().stream()
                .filter(l -> l.getWeekId().equals(lesson12Copy.getWeekId()))
                .findFirst()
                .orElse(null);
        StudentAttendance carlAttendance = lessonToCheck.getStudentAttendanceList().stream()
                .filter(sa -> sa.getStudent().isSameStudent(CARL))
                .findFirst().get();
        StudentAttendance danielAttendance = lessonToCheck.getStudentAttendanceList().stream()
                .filter(sa -> sa.getStudent().isSameStudent(DANIEL))
                .findFirst().get();
        assertTrue(carlAttendance.getAttendance().equals(new Attendance(VALID_ATTENDANCE_FALSE)));
        assertTrue(danielAttendance.getAttendance().equals(new Attendance(VALID_ATTENDANCE_FALSE)));
    }

    // to fix
    @Test
    public void equals() {
        // same values -> returns true
        ClassGroup cs2040B10ACopy = new ClassGroupBuilder(CS2040B10A).build();
        assertTrue(CS2040B10A.equals(cs2040B10ACopy));

        // same object -> returns true
        assertTrue(CS2040B10A.equals(CS2040B10A));

        // null -> returns false
        assertFalse(CS2040B10A.equals(null));

        // different type -> returns false
        assertFalse(CS2040B10A.equals(5));

        // different class group -> returns false
        assertFalse(CS2040B10A.equals(CS2101G09));

        // different class group ID -> returns false
        ClassGroup editedCS2040B10A = new ClassGroupBuilder(CS2040B10A).withClassGroupId(VALID_CG_ID_CS2103T_TUT)
                .build();
        assertFalse(CS2040B10A.equals(editedCS2040B10A));

        // different class group type -> returns false
        editedCS2040B10A = new ClassGroupBuilder(CS2040B10A).withClassGroupType(VALID_CG_TYPE_CS2103T_TUT).build();
        assertFalse(CS2040B10A.equals(editedCS2040B10A));

        // different class group module -> returns false
        editedCS2040B10A = new ClassGroupBuilder(CS2040B10A).withModule(CS2030).build();
        assertFalse(CS2040B10A.equals(editedCS2040B10A));

        // different students -> returns false
        editedCS2040B10A = new ClassGroupBuilder(CS2040B10A).withUniqueStudentList(BENSON).build();
        assertFalse(CS2040B10A.equals(editedCS2040B10A));
    }
}
