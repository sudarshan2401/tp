package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddClassCommand;
import seedu.address.logic.commands.ViewCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.Class;
import seedu.address.model.module.ClassName;
import seedu.address.testutil.TypicalClasses;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

public class ViewCommandParserTest {
    private ViewCommandParser parser = new ViewCommandParser();

//    private final Index invalidClassIndex = Index.fromOneBased(0);

    private final Index validClassIndex = Index.fromOneBased(1);


    private Model model = new ModelManager(TypicalClasses.getTypicalEduTrack(), new UserPrefs());

    @Test
    public void parse_allFieldsPresent_success() {
        ClassName className = new ClassName("cs2103t");
        model.addClass(new Class(className));
        ViewCommand expectedCommand = new ViewCommand(validClassIndex);
        assertParseSuccess(parser, " /c " + "1", expectedCommand);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, " /c " + "0",
                String.format(ViewCommand.MESSAGE_INVALID_CLASS_DISPLAYED_INDEX));
    }
}
