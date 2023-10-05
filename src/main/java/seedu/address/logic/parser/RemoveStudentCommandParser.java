package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLASS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT;

import java.util.stream.Stream;

import seedu.address.logic.commands.RemoveCommand;
import seedu.address.logic.commands.RemoveStudentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.Name;
import seedu.address.model.student.TempClass;
/**
 * Parses input arguments and creates a new AddCommand object
 */
public class RemoveStudentCommandParser implements Parser<RemoveCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the
     * RemoveCommand and returns a RemoveStudentCommand object
     * for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public RemoveStudentCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_STUDENT, PREFIX_CLASS);

        // ensures that student name and class name is provided
        if (!arePrefixesPresent(argMultimap, PREFIX_STUDENT, PREFIX_CLASS)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemoveCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_STUDENT, PREFIX_CLASS);
        Name studentName = ParserUtil.parseName(argMultimap.getValue(PREFIX_STUDENT).get());
        //        TempClass studentClass = ParserUtil.parseClass(argMultimap.getValue(PREFIX_CLASS).get());
        TempClass studentClassPlaceholder = new TempClass();
        return new RemoveStudentCommand(studentName, studentClassPlaceholder);

    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
