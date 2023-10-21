package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.MarkAllStudentPresentCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLASS;

public class MarkAllStudentPresentCommandParser {

    public MarkAllStudentPresentCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_CLASS);

        if (!arePrefixesPresent(argMultimap, PREFIX_CLASS)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    MarkAllStudentPresentCommand.MESSAGE_USAGE));
        }
        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_CLASS);
        try {
            Index index = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_CLASS).get());
            return new MarkAllStudentPresentCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkAllStudentPresentCommand.MESSAGE_USAGE), pe);
        }
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
