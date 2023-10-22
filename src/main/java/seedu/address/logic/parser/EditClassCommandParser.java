package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLASS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCHEDULE;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditClassCommand;
import seedu.address.logic.commands.EditClassCommand.EditClassDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditClassCommand object
 */
public class EditClassCommandParser implements Parser<EditClassCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the EditClassCommand
     * and returns an EditClassCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditClassCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_CLASS, PREFIX_NAME, PREFIX_NOTE, PREFIX_SCHEDULE);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_CLASS).get());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditClassCommand.MESSAGE_USAGE), pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_CLASS, PREFIX_NAME, PREFIX_NOTE, PREFIX_SCHEDULE);

        EditClassDescriptor editClassDescriptor = new EditClassDescriptor();

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editClassDescriptor.setClassName((ParserUtil.parseClassName(argMultimap.getValue(PREFIX_NAME).get())));
        }

        if (argMultimap.getValue(PREFIX_NOTE).isPresent()) {
            editClassDescriptor.setClassNote((ParserUtil.parseClassNote(argMultimap.getValue(PREFIX_NOTE).get())));
        }

        if (argMultimap.getValue(PREFIX_SCHEDULE).isPresent()) {
            editClassDescriptor.setClassSchedule((ParserUtil.parseClassSchedule(argMultimap
                    .getValue(PREFIX_SCHEDULE).get())));
        }

        if (!editClassDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditClassCommand.MESSAGE_NOT_EDITED);
        }

        return new EditClassCommand(index, editClassDescriptor);
    }
}
