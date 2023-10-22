package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.MarkAllStudentPresentCommand;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

public class MarkAllStudentPresentCommandParserTest {
    private MarkAllStudentPresentCommandParser parser = new MarkAllStudentPresentCommandParser();
    @Test
    public void parse_validArgs_returnMarkStudentPresentCommand() {
        int index = 1;
        MarkAllStudentPresentCommand expectedCommand = new MarkAllStudentPresentCommand(Index.fromOneBased(index));
        assertParseSuccess(parser, " /c " + index, expectedCommand);
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        // Argument should be numbers
        int index = 1;
        assertParseFailure(parser, "/c " + index,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkAllStudentPresentCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_wrongIdentifier_throwsParseException() {
        // Argument should be numbers
        int index = 1;
        assertParseFailure(parser, "/s " + index,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkAllStudentPresentCommand.MESSAGE_USAGE));
    }
}
