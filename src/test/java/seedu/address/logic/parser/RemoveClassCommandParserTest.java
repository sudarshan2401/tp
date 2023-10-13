package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.RemoveClassCommand;
import seedu.address.model.module.Class;
import seedu.address.model.module.ClassName;

public class RemoveClassCommandParserTest {
    private RemoveClassCommandParser parser = new RemoveClassCommandParser();
    private final String missingClassName = "cs0000";
    private final String validClassName = "cs2102";

    @Test
    public void parse_validArgs_returnRemoveClassCommand() {
        ClassName className = new ClassName(validClassName);
        Class expectedClass = new Class(className);
        RemoveClassCommand expectedCommand = new RemoveClassCommand(expectedClass);
        assertParseSuccess(parser, " /c " + validClassName, expectedCommand);
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "/c" + missingClassName,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemoveClassCommand.MESSAGE_USAGE));
    }
}
