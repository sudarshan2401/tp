package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;

public class MarkAllStudentAbsentCommandTest {
    private Model model;

    @Test
    public void constructor_nullClass_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new MarkAllStudentAbsentCommand(null));
    }

    @Test
    public void constructor_validIndex_success() {
        assertDoesNotThrow(() -> new MarkAllStudentAbsentCommand(Index.fromOneBased(1)));
    }

    @Test
    public void equals() {
        MarkAllStudentAbsentCommand firstMarkAllStudentAbsentCommand = new MarkAllStudentAbsentCommand(
                Index.fromOneBased(1));
        MarkAllStudentAbsentCommand secondMarkAllStudentAbsentCommand = new MarkAllStudentAbsentCommand(
                Index.fromOneBased(2));

        // same object -> returns true
        assertTrue(firstMarkAllStudentAbsentCommand.equals(firstMarkAllStudentAbsentCommand));

        // same values -> returns true
        MarkAllStudentAbsentCommand firstMarkAllStudentAbsentCommandCopy = new MarkAllStudentAbsentCommand(
                Index.fromOneBased(1));
        assertTrue(firstMarkAllStudentAbsentCommand.equals(firstMarkAllStudentAbsentCommandCopy));

        // different types -> returns false
        assertFalse(firstMarkAllStudentAbsentCommand.equals(1));

        // different instance -> returns false
        assertFalse(firstMarkAllStudentAbsentCommand.equals(secondMarkAllStudentAbsentCommand));
    }
}


