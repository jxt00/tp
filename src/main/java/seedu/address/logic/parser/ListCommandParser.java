package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_ERROR_MESSAGE_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_CLASS_GROUP_DISPLAYED_INDEX;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_TA_MODULE_DISPLAYED_INDEX;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_ENTITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLASS_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_INDEX;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.entity.EntityType;

/**
 * Parses input arguments and creates a new ListCommand object
 */
public class ListCommandParser implements Parser<ListCommand> {

    private static final Pattern LIST_COMMAND_FORMAT = Pattern.compile("(?<entityType>\\S+)(?<arguments>.*)");
    private static final String MESSAGE_INVALID_FILTER = "Please filter by module or class group, not both!\n";

    /**
     * Parses the given {@code String} of arguments in the context of the ListCommand
     * and returns a ListCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public ListCommand parse(String args, Model model) throws ParseException {
        final Matcher matcher = LIST_COMMAND_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
        }

        final String entityType = matcher.group("entityType");
        final String arguments = matcher.group("arguments");
        EntityType parsedEntityType = ParserUtil.parseEntity(entityType);

        ArgumentMultimap argMultimap = seedu.address.logic.parser.ArgumentTokenizer.tokenize(arguments,
                PREFIX_CLASS_INDEX, PREFIX_MODULE_INDEX);
        if (ParserUtil.arePrefixesPresent(argMultimap, PREFIX_CLASS_INDEX, PREFIX_MODULE_INDEX)) {
            throw new ParseException(MESSAGE_INVALID_FILTER);
        }

        try {
            Optional<Index> optionalIndex = ParserUtil.parseOptionalIndex(argMultimap.getValue(PREFIX_MODULE_INDEX));
            Optional<EntityType> filteredEntity = argMultimap.getValue(PREFIX_MODULE_INDEX)
                    .map(x -> EntityType.TA_MODULE);
            switch(parsedEntityType) {
            case STUDENT:
                if (filteredEntity.isPresent()) {
                    if (!ParserUtil.checkValidIndex(optionalIndex.get(), model.getUnfilteredModuleList().size())) {
                        throw new ParseException(MESSAGE_INVALID_TA_MODULE_DISPLAYED_INDEX);
                    }
                } else {
                    filteredEntity = argMultimap.getValue(PREFIX_CLASS_INDEX).map(x -> EntityType.CLASS_GROUP);
                    optionalIndex = ParserUtil.parseOptionalIndex(argMultimap.getValue(PREFIX_CLASS_INDEX));
                    if (filteredEntity.isPresent()) {
                        if (!ParserUtil.checkValidIndex(optionalIndex.get(),
                                model.getUnfilteredClassGroupList().size())) {
                            throw new ParseException(MESSAGE_INVALID_CLASS_GROUP_DISPLAYED_INDEX);
                        }
                    }
                }
                break;
            case TA_MODULE:
                break;
            case CLASS_GROUP:
            case ASSESSMENT:
                if (filteredEntity.isPresent()) {
                    if (!ParserUtil.checkValidIndex(optionalIndex.get(), model.getUnfilteredModuleList().size())) {
                        throw new ParseException(MESSAGE_INVALID_TA_MODULE_DISPLAYED_INDEX);
                    }
                }
                break;
            default:
                throw new ParseException(MESSAGE_UNKNOWN_ENTITY);
            }
            return new ListCommand(parsedEntityType, filteredEntity, optionalIndex);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_ERROR_MESSAGE_FORMAT, pe.getMessage(),
                    ListCommand.MESSAGE_USAGE), pe);
        }
    }

}
