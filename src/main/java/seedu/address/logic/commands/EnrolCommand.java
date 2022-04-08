package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLASS_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.classgroup.ClassGroup;
import seedu.address.model.entity.EntityType;
import seedu.address.model.student.Student;
import seedu.address.model.tamodule.TaModule;

//@@author wxliong
/**
 * Enrols given student(s) into the specified class group and automatically into the module.
 */
public class EnrolCommand extends Command {

    public static final String STUDENT_EXISTS_CG = "Command failed for student(s)"
            + " who already exist in given class group:\n%s";
    public static final String NONEXISTENT_CG = "Class Group index %d does not exists\n\n%s";
    public static final String MESSAGE_ENROL_SOME = "Successfully enrolled some student(s) into %s(%s)\n";
    public static final String MESSAGE_ENROL_SUCCESS = "Successfully enrolled all given student(s) into %s(%s)";
    public static final String MESSAGE_ENROL_FAILURE = "No given student(s) were successfully enrolled into %s(%s)\n";
    public static final String COMMAND_WORD = "enrol";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Enrols the specified students to "
            + "the given class group.\n"
            + "\tParameters: " + PREFIX_CLASS_INDEX + "CLASS_GROUP_INDEX "
            + PREFIX_STUDENT + "all | STUDENT_INDEXES | STUDENT_IDS "
            + "\n\tExamples: "
            + "\n\t\t1. " + COMMAND_WORD + " "
            + PREFIX_CLASS_INDEX + "1 "
            + PREFIX_STUDENT + "all "
            + "\n\t\t2. " + COMMAND_WORD + " "
            + PREFIX_CLASS_INDEX + "1 "
            + PREFIX_STUDENT + "1,2,3 "
            + "\n\t\t3. " + COMMAND_WORD + " "
            + PREFIX_CLASS_INDEX + "1 "
            + PREFIX_STUDENT + "e0123456,e0234567 \n";
    private Index classGroupIndex;
    private List<Student> students;

    /**
     * Creates an EnrolCommand to enrol given students into class group and module.
     *
     * @param classGroupIndex A class group index.
     * @param students A list of students.
     */
    public EnrolCommand(Index classGroupIndex, List<Student> students) {
        this.classGroupIndex = classGroupIndex;
        this.students = students;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        String result = "";
        String fail = "";
        List<ClassGroup> cgList = model.getUnfilteredClassGroupList();

        if (classGroupIndex.getZeroBased() >= cgList.size()) {
            throw new CommandException(String.format(NONEXISTENT_CG, classGroupIndex.getOneBased(), MESSAGE_USAGE));
        }

        ClassGroup cgToEdit = cgList.get(classGroupIndex.getZeroBased());
        TaModule moduleToEdit = cgToEdit.getModule();
        TaModule newModule = new TaModule(moduleToEdit);
        ClassGroup newCg = new ClassGroup(cgToEdit, newModule);
        int notEnrolled = 0;
        for (Student s : students) {
            if (!newCg.hasStudent(s)) {
                newCg.addStudent(s);
                if (!newModule.hasStudent(s)) {
                    newModule.addStudent(s);
                }
            } else {
                result += String.format("\t%s (%s)\n", s.getName(), s.getStudentId());
                notEnrolled++;
            }
        }

        if (notEnrolled == 0) {
            result = String.format(MESSAGE_ENROL_SUCCESS,
                    newCg.getClassGroupId(), newCg.getClassGroupType());
        } else {
            result = String.format(STUDENT_EXISTS_CG, result);
            if (notEnrolled != students.size()) {
                fail = String.format(MESSAGE_ENROL_SOME,
                        newCg.getClassGroupId(), newCg.getClassGroupType());
            } else if (notEnrolled == students.size()) {
                fail = String.format(MESSAGE_ENROL_FAILURE,
                        newCg.getClassGroupId(), newCg.getClassGroupType());
            }
            result = fail + result;
        }

        model.setEntity(cgToEdit, newCg);
        model.setEntity(moduleToEdit, newModule);
        model.updateFilteredStudentList(student -> newCg.hasStudent(student));
        return new CommandResult(result, EntityType.STUDENT);
    }
}
