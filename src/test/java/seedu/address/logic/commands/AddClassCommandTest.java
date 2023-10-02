package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.Class;
import seedu.address.model.module.ClassName;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

class AddClassCommandTest {

    private static final String CLASSNAME_STUB = "cs2103t";

    final ClassName className = new ClassName(CLASSNAME_STUB);

    final Class c = new Class(className);

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullClass_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddClassCommand(null));
    }

    @Test
    public void execute_addClass_success() {
        AddClassCommand addClassCommand = new AddClassCommand(c);
        String expectedMessage = String.format(AddClassCommand.MESSAGE_SUCCESS, CLASSNAME_STUB.toUpperCase());
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(addClassCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateClass_throwsCommandException() {
        AddClassCommand addClassCommand1 = new AddClassCommand(c);

        try {
            addClassCommand1.execute(model);
        } catch (CommandException e) {
            e.printStackTrace();
        }

        AddClassCommand addClassCommand2 = new AddClassCommand(c);

        assertThrows(CommandException.class, String.format(AddClassCommand.MESSAGE_DUPLICATE_CLASS,
                CLASSNAME_STUB.toUpperCase()), () -> addClassCommand2.execute(model));
    }

    @Test
    public void equals() {
        ClassName className = new ClassName("CS2103T");
        Class classToAdd = new Class(className);

        AddClassCommand command1 = new AddClassCommand(classToAdd);
        AddClassCommand command2 = new AddClassCommand(classToAdd);

        assertEquals(command1, command2);
    }

}