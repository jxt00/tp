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
 * Disenrols given student(s) from the specified class group and automatically from the module.
 */
public class DisenrolCommand extends Command {

    public static final String NONEXISTENT_STUDENT_CG = "Command failed for student(s)"
            + " who do not exist in given class group:\n%s";
    public static final String NONEXISTENT_CG = "Class Group index %s does not exists\n\n%s";
    public static final String MESSAGE_DISENROL_SOME = "Successfully disenrolled some student(s) from %s(%s)\n";
    public static final String MESSAGE_DISENROL_SUCCESS = "Successfully disenrolled all"
            + " given students(s) from %s(%s)";
    public static final String MESSAGE_DISENROL_FAILURE = "No given student(s)"
            + " were successfully disenrolled from %s(%s)\n";
    public static final String COMMAND_WORD = "disenrol";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Disenrols the specified students from "
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
     * Creates an DisenrolCommand to disenrol given students from class group and module.
     *
     * @param classGroupIndex A class group index.
     * @param students A list of students.
     */
    public DisenrolCommand(Index classGroupIndex, List<Student> students) {
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
        ClassGroup newCg = new ClassGroup(cgToEdit);
        TaModule newModule = new TaModule(moduleToEdit);
        int notDisenrolled = 0;
        for (Student s : students) {
            if (newCg.hasStudent(s)) {
                newCg.removeStudent(s);
            } else {
                result += String.format("\t%s (%s)\n", s.getName(), s.getStudentId());
                notDisenrolled++;
            }
        }

        model.setEntity(cgToEdit, newCg);

        for (Student s : students) {
            if (!model.getUnfilteredClassGroupList().stream().filter(cg -> cg.getModule().isSameModule(newModule))
                    .anyMatch(cg -> cg.hasStudent(s))) {
                if (newModule.hasStudent(s)) {
                    newModule.removeStudent(s);
                    model.removeStudentFromAssessments(s);
                }
            }
        }

        if (notDisenrolled == 0) {
            result = String.format(MESSAGE_DISENROL_SUCCESS,
                    newCg.getClassGroupId(), newCg.getClassGroupType());
        } else {
            result = String.format(NONEXISTENT_STUDENT_CG, result);
            if (notDisenrolled != students.size()) {
                fail = String.format(MESSAGE_DISENROL_SOME,
                        newCg.getClassGroupId(), newCg.getClassGroupType());
            } else if (notDisenrolled == students.size()) {
                fail = String.format(MESSAGE_DISENROL_FAILURE,
                        newCg.getClassGroupId(), newCg.getClassGroupType());
            }
            result = fail + result;
        }

        model.setEntity(moduleToEdit, newModule);
        model.updateFilteredStudentList(student -> newCg.hasStudent(student));
        return new CommandResult(result, EntityType.STUDENT);
    }
}
