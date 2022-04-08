package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ENTITY;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.entity.EntityType;

//@@author jxt00
/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteCommandParserTest {

    private DeleteCommandParser parser = new DeleteCommandParser();
    private Model model = new ModelManager();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, "assessment 1", model, new DeleteCommand(INDEX_FIRST_ENTITY, EntityType.ASSESSMENT));
        assertParseSuccess(parser, "class 1", model, new DeleteCommand(INDEX_FIRST_ENTITY, EntityType.CLASS_GROUP));
        assertParseSuccess(parser, "module 1", model, new DeleteCommand(INDEX_FIRST_ENTITY, EntityType.TA_MODULE));
        assertParseSuccess(parser, "student 1", model, new DeleteCommand(INDEX_FIRST_ENTITY, EntityType.STUDENT));

        // shorthand input
        assertParseSuccess(parser, "a 1", model, new DeleteCommand(INDEX_FIRST_ENTITY, EntityType.ASSESSMENT));
        assertParseSuccess(parser, "c 1", model, new DeleteCommand(INDEX_FIRST_ENTITY, EntityType.CLASS_GROUP));
        assertParseSuccess(parser, "m 1", model, new DeleteCommand(INDEX_FIRST_ENTITY, EntityType.TA_MODULE));
        assertParseSuccess(parser, "s 1", model, new DeleteCommand(INDEX_FIRST_ENTITY, EntityType.STUDENT));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        // missing index
        assertParseFailure(parser, "a", model,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
        // invalid indexes
        assertParseFailure(parser, "a 0", model,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "a abc", model,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
        // invalid entity type
        assertParseFailure(parser, "b", model, Messages.MESSAGE_UNKNOWN_ENTITY);
    }
}
