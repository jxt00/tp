package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLASS_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEEK;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.classgroup.ClassGroup;
import seedu.address.model.entity.EntityType;
import seedu.address.model.student.Student;

//@@author EvaderFati
public class MarkCommand extends Command {

    public static final String COMMAND_WORD = "mark";
    public static final String NONEXISTENT_CG = "Class Group %d does not exists.";
    public static final String NONEXISTENT_WEEK = "Week %d does not exists.";
    public static final String MESSAGE_STUDENT_NOT_ENROLLED = "Command failed for student(s) who do not exist"
            + " in given class group:\n%s";
    public static final String MESSAGE_MARK_OTHERS = "Successfully marked some enrolled student(s) from %s(%s).\n";
    public static final String MESSAGE_MARK_SUCCESS = "Successfully marked given student(s) from %s(%s).";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Marks the attendance(s) of the specified student(s)"
            + "belonging to the class group at the specified CLASS_GROUP_INDEX for the specified week.\n"
            + "\tParameters: " + PREFIX_CLASS_INDEX + "CLASS_GROUP_INDEX "
            + PREFIX_WEEK + "WEEK_INDEX "
            + PREFIX_STUDENT + "all|STUDENT_INDEXES|STUDENT_IDS "
            + "\n\tExamples: "
            + "\n\t\t1. " + COMMAND_WORD + " "
            + PREFIX_CLASS_INDEX + "1 "
            + PREFIX_WEEK + "3 "
            + PREFIX_STUDENT + "all "
            + "\n\t\t2. " + COMMAND_WORD + " "
            + PREFIX_CLASS_INDEX + "1 "
            + PREFIX_WEEK + "3 "
            + PREFIX_STUDENT + "1,2,3 "
            + "\n\t\t3. " + COMMAND_WORD + " "
            + PREFIX_CLASS_INDEX + "1 "
            + PREFIX_WEEK + "3 "
            + PREFIX_STUDENT + "e0123456,e0234567 \n";

    private Index classGroupIndex;
    private Index weekIndex;
    private List<Student> students;

    /**
     * Creates a MarkCommand to mark the attendance for the students in the specified class and week.
     */
    public MarkCommand(Index classGroupIndex, Index weekIndex, List<Student> students) {
        this.classGroupIndex = classGroupIndex;
        this.weekIndex = weekIndex;
        this.students = students;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        String result = "";
        List<ClassGroup> cgList = model.getUnfilteredClassGroupList();

        if (classGroupIndex.getZeroBased() >= cgList.size()) {
            throw new CommandException(String.format(NONEXISTENT_CG, classGroupIndex.getOneBased()));
        }

        ClassGroup classGroupToEdit = cgList.get(classGroupIndex.getZeroBased());
        ClassGroup classGroup = new ClassGroup(classGroupToEdit);
        if (weekIndex.getZeroBased() >= classGroup.getLessons().size()) {
            throw new CommandException(String.format(NONEXISTENT_WEEK, weekIndex.getOneBased()));
        }

        List<Student> notMarkedStudents = classGroup.markAttendance(weekIndex, students);
        model.setEntity(classGroupToEdit, classGroup);
        if (notMarkedStudents.size() == 0) {
            return new CommandResult(String.format(MESSAGE_MARK_SUCCESS,
                    classGroup.getClassGroupId(), classGroup.getClassGroupType()));
        }
        if (notMarkedStudents.size() != students.size()) {
            result += String.format(MESSAGE_MARK_OTHERS, classGroup.getClassGroupId(), classGroup.getClassGroupType());
        }
        String notEnrolledStudents = notMarkedStudents.stream().map(student ->
                        String.format("\t%s (%s)\n", student.getName(), student.getStudentId()))
                .reduce("", (x, y) -> x + y);
        result += String.format(MESSAGE_STUDENT_NOT_ENROLLED, notEnrolledStudents);
        return new CommandResult(result, EntityType.CLASS_GROUP);

    }
}
