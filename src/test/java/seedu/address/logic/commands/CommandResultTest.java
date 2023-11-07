package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CommandResultTest {
    @Test
    public void equals() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns true
        assertTrue(commandResult.equals(new CommandResult("feedback")));
        assertTrue(commandResult.equals(new CommandResult("feedback", false, false)));

        // same object -> returns true
        assertTrue(commandResult.equals(commandResult));

        // null -> returns false
        assertFalse(commandResult.equals(null));

        // different types -> returns false
        assertFalse(commandResult.equals(0.5f));

        // different feedbackToUser value -> returns false
        assertFalse(commandResult.equals(new CommandResult("different")));

        // different showHelp value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", true, false)));

        // different exit value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", false, true)));
    }

    @Test
    public void hashcode() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns same hashcode
        assertEquals(commandResult.hashCode(), new CommandResult("feedback").hashCode());

        // different feedbackToUser value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("different").hashCode());

        // different showHelp value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", true, false).hashCode());

        // different exit value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", false, true).hashCode());
    }

    @Test
    public void toStringMethod() {
        CommandResult commandResult = new CommandResult("feedback");
        String expected = CommandResult.class.getCanonicalName() + "{feedbackToUser="
                + commandResult.getFeedbackToUser() + ", showHelp=" + commandResult.isShowHelp()
                + ", exit=" + commandResult.isExit() + "}";
        assertEquals(expected, commandResult.toString());
    }

    @Test
    public void testViewFeedback() {
        CommandResult commandResult = new CommandResult("Listed all students in CS2103T");
        assertEquals(commandResult.isView(), true);
        assertEquals(commandResult.isList(), false);
        assertEquals(commandResult.isExit(), false);
    }

    @Test
    public void testListFeedback() {
        CommandResult commandResult = new CommandResult("Listed all classes");
        assertEquals(commandResult.isView(), false);
        assertEquals(commandResult.isList(), true);
        assertEquals(commandResult.isExit(), false);
    }

    // Test for edit class feedback "Edited Class: "
    @Test
    public void testEditFeedback() {
        CommandResult commandResult = new CommandResult("Edited Class: CS2103T");
        assertEquals(commandResult.isView(), false);
        assertEquals(commandResult.isList(), false);
        assertEquals(commandResult.isExit(), false);
        assertEquals(commandResult.isEditClass(), true);
    }

    // Test for updated students
    @Test
    public void testUpdatedStudentFeedback() {
        CommandResult commandResultEdit = new CommandResult("Edited Student: CS2103T");
        assertEquals(commandResultEdit.isView(), false);
        assertEquals(commandResultEdit.isList(), false);
        assertEquals(commandResultEdit.isExit(), false);
        assertEquals(commandResultEdit.isEditClass(), false);
        assertEquals(commandResultEdit.isUpdatedStudent(), true);
    }

    @Test
    public void testAddStudentFeedback() {
        CommandResult commandResultAdd = new CommandResult("Added New Student: John");
        assertEquals(commandResultAdd.isView(), false);
        assertEquals(commandResultAdd.isList(), false);
        assertEquals(commandResultAdd.isExit(), false);
        assertEquals(commandResultAdd.isEditClass(), false);
        assertEquals(commandResultAdd.isUpdatedStudent(), true);
    }

    @Test
    public void testRemoveStudentFeedback() {
        CommandResult commandResultRemove = new CommandResult("John has been removed from CS2103T");
        assertEquals(commandResultRemove.isView(), false);
        assertEquals(commandResultRemove.isList(), false);
        assertEquals(commandResultRemove.isExit(), false);
        assertEquals(commandResultRemove.isEditClass(), false);
        assertEquals(commandResultRemove.isUpdatedStudent(), true);
    }

    @Test
    public void testSetLessonFeedback() {
        CommandResult commandResultSetLesson = new CommandResult("Successfully set the number of lessons in CS2103T");
        assertEquals(commandResultSetLesson.isView(), false);
        assertEquals(commandResultSetLesson.isList(), false);
        assertEquals(commandResultSetLesson.isExit(), false);
        assertEquals(commandResultSetLesson.isEditClass(), false);
        assertEquals(commandResultSetLesson.isUpdatedStudent(), true);
    }

    @Test
    public void testMarkAllPresentFeedback() {
        CommandResult commandResultMarkAllPresent = new CommandResult("Successfully marked all students in CS2103T");
        assertEquals(commandResultMarkAllPresent.isView(), false);
        assertEquals(commandResultMarkAllPresent.isList(), false);
        assertEquals(commandResultMarkAllPresent.isExit(), false);
        assertEquals(commandResultMarkAllPresent.isEditClass(), false);
        assertEquals(commandResultMarkAllPresent.isUpdatedStudent(), true);
    }

    @Test
    public void testMarkAllAbsentFeedback() {
        CommandResult commandResultMarkAllAbsent = new CommandResult("Successfully unmarked all students in CS2103T");
        assertEquals(commandResultMarkAllAbsent.isView(), false);
        assertEquals(commandResultMarkAllAbsent.isList(), false);
        assertEquals(commandResultMarkAllAbsent.isExit(), false);
        assertEquals(commandResultMarkAllAbsent.isEditClass(), false);
        assertEquals(commandResultMarkAllAbsent.isUpdatedStudent(), true);
    }

    @Test
    public void testMarkStudentAbsentFeedback() {
        CommandResult commandResultMarkStudentAbsent = new CommandResult("John has been marked absent!");
        assertEquals(commandResultMarkStudentAbsent.isView(), false);
        assertEquals(commandResultMarkStudentAbsent.isList(), false);
        assertEquals(commandResultMarkStudentAbsent.isExit(), false);
        assertEquals(commandResultMarkStudentAbsent.isEditClass(), false);
        assertEquals(commandResultMarkStudentAbsent.isUpdatedStudent(), true);
    }

    @Test
    public void testMarkStudentPresentFeedback() {
        CommandResult commandResultMarkStudentPresent = new CommandResult("John sucessfully marked present!");
        assertEquals(commandResultMarkStudentPresent.isView(), false);
        assertEquals(commandResultMarkStudentPresent.isList(), false);
        assertEquals(commandResultMarkStudentPresent.isExit(), false);
        assertEquals(commandResultMarkStudentPresent.isEditClass(), false);
        assertEquals(commandResultMarkStudentPresent.isUpdatedStudent(), true);
    }

    @Test
    public void testStartLessonFeedback() {
        CommandResult commandResultStartLesson = new CommandResult("CS2103T started a new lesson!");
        assertEquals(commandResultStartLesson.isView(), false);
        assertEquals(commandResultStartLesson.isList(), false);
        assertEquals(commandResultStartLesson.isExit(), false);
        assertEquals(commandResultStartLesson.isEditClass(), false);
        assertEquals(commandResultStartLesson.isUpdatedStudent(), true);
    }
}
