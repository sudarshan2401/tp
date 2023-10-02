package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.AddClassCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.Class;
import seedu.address.model.module.ClassName;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

public class AddClassCommandParserTest {
    private AddClassCommandParser parser = new AddClassCommandParser();

    private final String INVALID_CLASSNAME = "cs 2103t";

    private final String VALID_CLASSNAME = "cs2103t";

    @Test
    public void parse_allFieldsPresent_success_success() {
        ClassName className = new ClassName("cs2103t");
        Class expectedClass = new Class(className);
        AddClassCommand expectedCommand = new AddClassCommand(expectedClass);
        assertParseSuccess(parser, " /c " + VALID_CLASSNAME, expectedCommand);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, " add /c " + INVALID_CLASSNAME, String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddClassCommand.MESSAGE_USAGE));
    }
}
