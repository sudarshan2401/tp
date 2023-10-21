package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLASS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.MarkStudentAttendanceCommand;
import seedu.address.logic.commands.UnmarkStudentAttendanceCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.ClassName;

/**
 * Parses input arguments and creates a new Unmark object
 */
public class UnmarkStudentAttendanceCommandParser implements Parser<UnmarkStudentAttendanceCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddClassCommand
     * and returns an AddClassCommand object for execution.
     * @throws ParseException if the user input does not follow the expected format
     */
    public UnmarkStudentAttendanceCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_STUDENT, PREFIX_CLASS);

        if (!arePrefixesPresent(argMultimap, PREFIX_STUDENT, PREFIX_CLASS)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    MarkStudentAttendanceCommand.MESSAGE_USAGE));
        }
        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_STUDENT, PREFIX_CLASS);
        try {
            Index index = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_STUDENT).get());
            ClassName className = ParserUtil.parseClassName(argMultimap.getValue(PREFIX_CLASS).get());
            return new UnmarkStudentAttendanceCommand(index, className);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkStudentAttendanceCommand.MESSAGE_USAGE), pe);
        }
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
