package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLASS;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.MarkAllStudentAbsentCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new MarkAllStudentAbsentCommand object.
 */
public class MarkAllStudentAbsentCommandParser implements Parser<MarkAllStudentAbsentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the MarkAllStudentPresentCommand
     * and returns an MarkAllStudentPresentCommand object for execution.
     * @throws ParseException if the user input does not follow the expected format
     */
    public MarkAllStudentAbsentCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_CLASS);

        if (!arePrefixesPresent(argMultimap, PREFIX_CLASS)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    MarkAllStudentAbsentCommand.MESSAGE_USAGE));
        }
        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_CLASS);
        try {
            Index index = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_CLASS).get());
            return new MarkAllStudentAbsentCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkAllStudentAbsentCommand.MESSAGE_USAGE), pe);
        }
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
