package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ACADEMIC_YEAR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SIMPLE_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TELEGRAM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;
import static seedu.address.logic.parser.CliSyntax.TYPE_ASSESSMENT;
import static seedu.address.logic.parser.CliSyntax.TYPE_CLASS;
import static seedu.address.logic.parser.CliSyntax.TYPE_MODULE;
import static seedu.address.logic.parser.CliSyntax.TYPE_STUDENT;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.entity.Entity;

//@@author EvaderFati
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a student/module/class group/assessment to "
            + "TAssist.\nFor each entity, type the command to find out more about the fields required:\n"
            + "\t1. Adds a student: add student\n"
            + "\t2. Adds a module: add module\n"
            + "\t3. Adds a class group: add class\n"
            + "\t4. Adds an assessment: add assessment\n";
    public static final String MESSAGE_STUDENT_USAGE = COMMAND_WORD + ": Adds a student to TAssist\n"
            + "\tParameters: " + TYPE_STUDENT + " "
            + PREFIX_ID + "STUDENT_ID "
            + PREFIX_NAME + "NAME "
            + PREFIX_EMAIL + "EMAIL ["
            + PREFIX_TELEGRAM + "TELEGRAM_ID] "
            + "\n\tExample: " + COMMAND_WORD + " "
            + TYPE_STUDENT + " "
            + PREFIX_ID + "E0123456 "
            + PREFIX_NAME + "John Doe "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_TELEGRAM + "john_doe\n";
    public static final String MESSAGE_MODULE_USAGE = COMMAND_WORD + ": Adds a module to TAssist\n"
            + "\tParameters: " + TYPE_MODULE + " "
            + PREFIX_NAME + "MODULE_NAME "
            + PREFIX_MODULE_CODE + "MODULE_CODE "
            + PREFIX_ACADEMIC_YEAR + "ACADEMIC_YEAR "
            + "\n\tExample: " + COMMAND_WORD + " "
            + TYPE_MODULE + " "
            + PREFIX_NAME + "Software Engineering Project "
            + PREFIX_MODULE_CODE + "CS2103T "
            + PREFIX_ACADEMIC_YEAR + "21S1\n";
    public static final String MESSAGE_CLASS_USAGE = COMMAND_WORD + ": Adds a class group to TAssist\n"
            + "\tParameters: " + TYPE_CLASS + " "
            + PREFIX_ID + "CLASS_ID "
            + PREFIX_TYPE + "CLASS_TYPE "
            + PREFIX_MODULE_INDEX + "MODULE_INDEX "
            + "\n\tExample: " + COMMAND_WORD + " "
            + TYPE_CLASS + " "
            + PREFIX_ID + "T13 "
            + PREFIX_TYPE + "tutorial "
            + PREFIX_MODULE_INDEX + "1 ";
    public static final String MESSAGE_ASSESSMENT_USAGE = COMMAND_WORD + ": Adds an assessment to TAssist\n"
            + "\tParameters: " + TYPE_ASSESSMENT + " "
            + PREFIX_NAME + "ASSESSMENT_NAME "
            + PREFIX_MODULE_INDEX + "MODULE_INDEX ["
            + PREFIX_SIMPLE_NAME + "SIMPLE_NAME] "
            + "\n\tExample: " + COMMAND_WORD + " "
            + TYPE_ASSESSMENT + " "
            + PREFIX_NAME + "Participation "
            + PREFIX_MODULE_INDEX + "1 "
            + PREFIX_SIMPLE_NAME + "part ";

    public static final String MESSAGE_SUCCESS = "New entity added: %1$s";

    private final Entity toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Entity}
     */
    public AddCommand(Entity entity) {
        requireNonNull(entity);
        toAdd = entity;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasEntity(toAdd)) {
            switch (toAdd.getEntityType()) {
            case STUDENT:
                throw new CommandException(Messages.MESSAGE_DUPLICATE_STUDENT);
            case TA_MODULE:
                throw new CommandException(Messages.MESSAGE_DUPLICATE_MODULE);
            case CLASS_GROUP:
                throw new CommandException(Messages.MESSAGE_DUPLICATE_CLASS_GROUP);
            case ASSESSMENT:
                throw new CommandException(Messages.MESSAGE_DUPLICATE_ASSESSMENT);
            default:
                throw new CommandException(Messages.MESSAGE_UNKNOWN_ENTITY);
            }
        }

        model.addEntity(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd), toAdd.getEntityType());
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof AddCommand
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
