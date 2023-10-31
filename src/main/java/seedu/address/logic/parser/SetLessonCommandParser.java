package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLASS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LESSONQUANTITY;

import java.util.stream.Stream;

import seedu.address.logic.commands.SetLessonCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.ClassName;

/**
 * Parses input arguments and create a new SetLessonCommand.
 */
public class SetLessonCommandParser {
    /**
     * Parses the input arguments and creates a SetLessonCommand based on input arguments.
     * @param args Inputs by user to determine Class that SetLessonCommand should work on.
     * @return SetLessonCommand with user's chosen Class.
     */
    public SetLessonCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_CLASS, PREFIX_LESSONQUANTITY);

        if (!arePrefixesPresent(argMultimap, PREFIX_CLASS, PREFIX_LESSONQUANTITY)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    SetLessonCommand.MESSAGE_USAGE));
        }
        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_CLASS, PREFIX_LESSONQUANTITY);
        ClassName className = ParserUtil.parseClassName(argMultimap.getValue(PREFIX_CLASS).get());
        int numLessons = ParserUtil.parseNumLessons(argMultimap.getValue(PREFIX_LESSONQUANTITY).get());
        return new SetLessonCommand(className, numLessons);
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
