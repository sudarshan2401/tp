package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.UpdateClassCommand;
import seedu.address.logic.commands.UpdateClassCommand.EditClassDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new UpdateClassCommand object
 */
public class UpdateClassCommandParser implements Parser<UpdateClassCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the UpdateClassCommand
     * and returns an UpdateClassCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public UpdateClassCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_CLASS, PREFIX_NAME);

        Index index;
        //Index index = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_CLASS).get());
        System.out.println(argMultimap.getValue(PREFIX_CLASS).get());

        try {
            index = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_CLASS).get());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, UpdateClassCommand.MESSAGE_USAGE), pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME);

        EditClassDescriptor editClassDescriptor = new EditClassDescriptor();

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editClassDescriptor.setClassName((ParserUtil.parseClassName(argMultimap.getValue(PREFIX_NAME).get())));
        }

//        if (argMultimap.getValue(PREFIX_NOTE).isPresent()) {
//            editClassDescriptor.setClassNote((ParserUtil.parseClassNote(argMultimap.getValue(PREFIX_NOTE).get())));
//        }

        if (!editClassDescriptor.isAnyFieldEdited()) {
            throw new ParseException(UpdateClassCommand.MESSAGE_NOT_EDITED);
        }

        return new UpdateClassCommand(index, editClassDescriptor);
    }
}
