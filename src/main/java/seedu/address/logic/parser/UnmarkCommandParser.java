package seedu.address.logic.parser;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.UnmarkCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;

//@@author jxt00
/**
 * Parses input arguments and creates a new UnmarkCommand object
 */
public class UnmarkCommandParser implements Parser<Command> {
    /**
     * Parses the given {@code String} of arguments in the context of the UnmarkCommand
     * and returns a UnmarkCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    @Override
    public Command parse(String args, Model model) throws ParseException {
        return GenericMarkCommandParser.parse(UnmarkCommand.COMMAND_WORD, args, model, UnmarkCommand.MESSAGE_USAGE);
    }
}
