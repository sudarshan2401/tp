package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLASS;

import java.util.stream.Stream;

import seedu.address.logic.commands.RemoveClassCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.Class;
import seedu.address.model.module.ClassName;

/**
 * Parses input from the user and creates a RemoveClassCommand.
 */
public class RemoveClassCommandParser implements Parser<RemoveClassCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the RemoveClassCommand
     * and returns a RemoveClassCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public RemoveClassCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_CLASS);
        if (!arePrefixesPresent(argMultimap, PREFIX_CLASS)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemoveClassCommand.MESSAGE_USAGE));
        }
        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_CLASS);
        Class c = null;
        try {
            ClassName classname = ParserUtil.parseClassName(argMultimap.getValue(PREFIX_CLASS).get());
            c = new Class(classname);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemoveClassCommand.MESSAGE_USAGE), pe);
        }
        return new RemoveClassCommand(c);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
