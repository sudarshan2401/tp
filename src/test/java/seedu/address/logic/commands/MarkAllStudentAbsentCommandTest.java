package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.EduTrack;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.common.Memo;
import seedu.address.model.module.Class;
import seedu.address.model.module.ClassName;
import seedu.address.model.module.Schedule;
import seedu.address.model.student.Student;
import seedu.address.model.student.UniqueStudentList;
import seedu.address.testutil.StudentBuilder;
import seedu.address.testutil.TypicalClasses;

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
    public void execute_userIsMarked_success() {
        // Set up test case
        model = new ModelManager(TypicalClasses.getTypicalEduTrack(), new UserPrefs());
        Class CS2109 = new Class(new ClassName("cs2109"), new UniqueStudentList(),
                new Memo(" "), new Schedule());
        CS2109.setTotalLessons(10);
        Student studentToRemove1 = new StudentBuilder().withName("Amy Bee")
                .withCurrentLessonAttendance(true).withLessonsAttended(4).build();
        model.addStudent(studentToRemove1);
        model.addClass(CS2109);
        model.addStudentToClass(studentToRemove1, CS2109);
        Student studentToRemove2 = new StudentBuilder().withName("John")
                .withCurrentLessonAttendance(true).withLessonsAttended(2).build();
        model.addStudent(studentToRemove2);
        model.addStudentToClass(studentToRemove2, CS2109);

        // Set up expected model
        Student editedStudent1 = new StudentBuilder().withName("Amy Bee")
                .withCurrentLessonAttendance(false).withLessonsAttended(4).build();
        Student editedStudent2 = new StudentBuilder().withName("John")
                .withCurrentLessonAttendance(false).withLessonsAttended(2).build();
        Model expectedModel = new ModelManager(new EduTrack(model.getEduTrack()), new UserPrefs());
        expectedModel.setStudent(model.getFilteredStudentList().get(0), editedStudent1);
        expectedModel.setStudent(model.getFilteredStudentList().get(1), editedStudent2);

        MarkAllStudentAbsentCommand command = new MarkAllStudentAbsentCommand(Index.fromOneBased(1));
        String expectedMessage = String.format(MarkAllStudentAbsentCommand.MESSAGE_UNMARK_STUDENT_ATTENDANCE_SUCCESS,
                CS2109.getClassName());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
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


