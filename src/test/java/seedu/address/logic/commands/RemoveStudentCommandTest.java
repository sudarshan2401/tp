package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.student.Name;
import seedu.address.model.student.Student;
import seedu.address.model.student.TempClass;

public class RemoveStudentCommandTest {
    private static final TempClass CLASSNAME_STUB = new TempClass();

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());


    @Test
    public void execute_validStudentName_success() {
        Student studentToDelete = model.getFilteredStudentList().get(INDEX_FIRST_PERSON.getZeroBased());
        Name studentName = studentToDelete.getName();
        RemoveCommand removeStudentCommand = new RemoveStudentCommand(INDEX_FIRST_PERSON, CLASSNAME_STUB);
        String expectedMessage = String.format(RemoveStudentCommand.MESSAGE_REMOVE_STUDENT_SUCCESS, studentName, CLASSNAME_STUB.toString());
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteStudentFromClass(studentToDelete, CLASSNAME_STUB);

        assertCommandSuccess(removeStudentCommand, model, expectedMessage, expectedModel);
    }

    // MORE TESTS TO BE WRITTEN AFTER IMPLEMENTATION OF "CLASS" CLASS
}
