package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INDEX_IS_NEGATIVE;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.RemoveClassCommand;
import seedu.address.logic.commands.RemoveStudentCommand;
import seedu.address.model.module.ClassName;

public class RemoveStudentCommandParserTest {
    private RemoveStudentCommandParser parser = new RemoveStudentCommandParser();
    private final String missingClassName = "cs0000";


    @Test
    public void parse_validArgs_returnRemoveStudentCommand() {
        int studentIndex = 1;
        Index studentIndexToRemove = Index.fromOneBased(studentIndex);
        ClassName className = new ClassName("cs2103");
        RemoveStudentCommand expectedCommand = new RemoveStudentCommand(studentIndexToRemove, className);
        assertParseSuccess(parser, " /s 1 /c cs2103", expectedCommand);
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        // Invalid argument: input negative student index
        assertParseFailure(parser, " /s -1 /c cs2103",
                String.format(MESSAGE_INDEX_IS_NEGATIVE, RemoveStudentCommand.MESSAGE_USAGE));

    }
}
