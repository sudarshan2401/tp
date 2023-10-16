package seedu.address.logic.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.EduTrack;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.Class;
import seedu.address.model.module.ClassName;
import seedu.address.testutil.TypicalClasses;
import seedu.address.testutil.TypicalStudents;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalClasses.getTypicalEduTrack;

public class ViewCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(TypicalClasses.getTypicalEduTrack(), new UserPrefs());
        expectedModel = new ModelManager(model.getEduTrack(), new UserPrefs());
    }

    @Test
    public void execute_viewClass_success() {
        ClassName className = new ClassName("cs2103");
        Class classToView = new Class(className);
        model.addClass(classToView);
        ViewCommand viewCommand = new ViewCommand(Index.fromOneBased(1));
        String expectedMessage = String.format(ViewCommand.MESSAGE_SUCCESS, className);
        assertCommandSuccess(viewCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_viewClass_failure() {
        ViewCommand viewCommand = new ViewCommand(Index.fromOneBased(1));
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
        assertFalse(viewCommand.equals(new AddClassCommand(new Class(className))));
    }
}
