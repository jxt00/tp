package seedu.address.logic.commands;

import seedu.address.model.AB3Model;

/**
 * Terminates the program.
 */
public class ExitCommand extends AB3Command {

    public static final String COMMAND_WORD = "exit";

    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting Address Book as requested ...";

    @Override
    public CommandResult execute(AB3Model model) {
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true);
    }

}
