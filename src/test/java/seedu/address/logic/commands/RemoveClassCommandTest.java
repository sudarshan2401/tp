package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalClasses.getTypicalEduTrack;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.Class;
import seedu.address.model.module.ClassName;

class RemoveClassCommandTest {

    private static final String CLASSNAME_STUB = "cs2102";

    final ClassName validClassName = new ClassName(CLASSNAME_STUB);

    final Class validClass = new Class(validClassName);

    private Model model = new ModelManager(getTypicalEduTrack(), new UserPrefs());

    @Test
    public void constructor_nullClass_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new RemoveClassCommand(null));
    }

    @Test
    public void execute_nonEmptyClassList_success() {
        RemoveClassCommand removeClassCommand = new RemoveClassCommand(validClass);
        String expectedOutcomeMessage = String.format(RemoveClassCommand.MESSAGE_REMOVE_CLASS_SUCCESS,
                CLASSNAME_STUB.toUpperCase());
        Model expectedModel = new ModelManager(model.getEduTrack(), new UserPrefs());

        assertCommandSuccess(removeClassCommand, model, expectedOutcomeMessage, expectedModel);
    }

    @Test
    public void execute_emptyClassList_throwsCommandException() {
        String name = "cs0000";
        Class classToDelete = new Class(new ClassName(name));
        RemoveClassCommand removeClassCommand = new RemoveClassCommand(classToDelete);

        assertCommandFailure(removeClassCommand, model, String.format(RemoveClassCommand.MESSAGE_MISSING_CLASS,
                name.toUpperCase()));
    }

    @Test
    public void equals() {
        RemoveClassCommand firstRemoveClassCommand = new RemoveClassCommand(validClass);
        RemoveClassCommand secondRemoveClassCommand = new RemoveClassCommand(
                new Class(new ClassName("secondClassname")));
        // same object -> returns true
        assertTrue(firstRemoveClassCommand.equals(firstRemoveClassCommand));

        // same values -> returns true
        RemoveClassCommand removeFirstClassCommand = new RemoveClassCommand(validClass);
        assertTrue(firstRemoveClassCommand.equals(removeFirstClassCommand));

        // different types -> returns false
        assertFalse(firstRemoveClassCommand.equals(1));

        // null -> returns false
        assertFalse(firstRemoveClassCommand.equals(null));

        // different person -> returns false
        assertFalse(firstRemoveClassCommand.equals(secondRemoveClassCommand));
    }

    @Test
    public void toStringMethod() {
        RemoveClassCommand removeClassCommand = new RemoveClassCommand(validClass);
        String expected = RemoveClassCommand.class.getCanonicalName() + "{class=" + validClass.toString() + "}";
        assertEquals(expected, removeClassCommand.toString());
    }
}
