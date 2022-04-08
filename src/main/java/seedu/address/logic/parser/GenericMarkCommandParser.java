package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_ERROR_MESSAGE_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_CLASS_GROUP_DISPLAYED_INDEX;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT_2;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_WEEK_INDEX;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLASS_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEEK;
import static seedu.address.model.classgroup.ClassGroup.NUM_OF_WEEKS;

import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.MarkCommand;
import seedu.address.logic.commands.UnmarkCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.student.Student;

//@@author jxt00
public class GenericMarkCommandParser {
    /**
     * Parses the given {@code String} of arguments in the context of the MarkCommand or UnmarkCommand
     * and returns a MarkCommand or UnmarkCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public static Command parse(String command, String args, Model model, String usage) throws ParseException {
        ArgumentMultimap argMultimap = seedu.address.logic.parser.ArgumentTokenizer.tokenize(args,
                PREFIX_CLASS_INDEX, PREFIX_WEEK, PREFIX_STUDENT);

        if (!ParserUtil.arePrefixesPresent(argMultimap, PREFIX_CLASS_INDEX, PREFIX_WEEK, PREFIX_STUDENT)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_ERROR_MESSAGE_FORMAT, MESSAGE_INVALID_COMMAND_FORMAT_2, usage));
        }

        try {
            Index classGroupIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_CLASS_INDEX).get());
            if (!ParserUtil.checkValidIndex(classGroupIndex, model.getUnfilteredClassGroupList().size())) {
                throw new ParseException(MESSAGE_INVALID_CLASS_GROUP_DISPLAYED_INDEX);
            }

            Index weekIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_WEEK).get());
            if (!ParserUtil.checkValidIndex(weekIndex, NUM_OF_WEEKS)) {
                throw new ParseException(MESSAGE_INVALID_WEEK_INDEX);
            }

            ObservableList<Student> students = ParserUtil.parseStudents(
                    argMultimap.getValue(PREFIX_STUDENT).get(), model);
            switch (command) {
            case MarkCommand.COMMAND_WORD:
                return new MarkCommand(classGroupIndex, weekIndex, students);
            case UnmarkCommand.COMMAND_WORD:
                return new UnmarkCommand(classGroupIndex, weekIndex, students);
            default:
                throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
            }
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_ERROR_MESSAGE_FORMAT, pe.getMessage(), usage), pe);
        }
    }
}
