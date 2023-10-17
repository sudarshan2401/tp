package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.Class;
import seedu.address.model.module.ClassName;
import seedu.address.model.student.UniqueStudentList;
import seedu.address.testutil.TypicalClasses;

public class ViewCommandTest {
    private Model model = new ModelManager(TypicalClasses.getTypicalEduTrack(), new UserPrefs());
    private Model expectedModel = new ModelManager(TypicalClasses.getTypicalEduTrack(), new UserPrefs());

    @Test
    public void execute_viewClass_success() {
        ViewCommand viewCommand = new ViewCommand(Index.fromOneBased(1));
        String expectedClass = model.getClassByIndex(Index.fromOneBased(1)).getClassName().toString();
        String expectedMessage = String.format(ViewCommand.MESSAGE_SUCCESS, expectedClass);
        assertCommandSuccess(viewCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_viewClass_failure() {
        ViewCommand viewCommand = new ViewCommand(Index.fromOneBased(100));
        assertThrows(CommandException.class, () -> viewCommand.execute(model));
    }

    @Test
    public void equals() {
        ClassName className = new ClassName("cs2103");
        ViewCommand viewCommand = new ViewCommand(Index.fromOneBased(1));
        ViewCommand viewCommandCopy = new ViewCommand(Index.fromOneBased(1));
        ViewCommand viewCommandDiff = new ViewCommand(Index.fromOneBased(2));
        assertTrue(viewCommand.equals(viewCommand));
        assertTrue(viewCommand.equals(viewCommandCopy));
        assertFalse(viewCommand.equals(viewCommandDiff));
        assertFalse(viewCommand.equals(null));
        assertFalse(viewCommand.equals(new AddClassCommand(new Class(className, new UniqueStudentList()))));
    }

    @Test
    public void toStringTest() {
        ViewCommand viewCommand = new ViewCommand(Index.fromOneBased(1));
        // expected : ViewCommand{classIndex=seedu.address.commons.core.index.Index{zeroBasedIndex=0}}
        String expected = ViewCommand.class.getSimpleName() + "{classIndex=" + Index.fromOneBased(1) + "}";
        assertEquals(expected, viewCommand.toString());
    }
}
