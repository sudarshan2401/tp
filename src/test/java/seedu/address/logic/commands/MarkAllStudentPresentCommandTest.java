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

public class MarkAllStudentPresentCommandTest {

    private Model model;

    @Test
    public void constructor_nullClass_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new MarkAllStudentPresentCommand(null));
    }

    @Test
    public void constructor_validIndex_success() {
        assertDoesNotThrow(() -> new MarkAllStudentPresentCommand(Index.fromOneBased(1)));
    }

    @Test
    public void execute_userIsUnmarked_success() {
        // Set up test case
        model = new ModelManager(TypicalClasses.getTypicalEduTrack(), new UserPrefs());
        Class CS2109 = new Class(new ClassName("cs2109"), new UniqueStudentList(),
                new Memo(" "), new Schedule());
        CS2109.setTotalLessons(10);
        Student studentToRemove1 = new StudentBuilder().withName("Amy Bee")
                .withCurrentLessonAttendance(false).withLessonsAttended(4).build();
        model.addStudent(studentToRemove1);
        model.addClass(CS2109);
        model.addStudentToClass(studentToRemove1, CS2109);
        Student studentToRemove2 = new StudentBuilder().withName("John")
                .withCurrentLessonAttendance(true).withLessonsAttended(2).build();
        model.addStudent(studentToRemove2);
        model.addStudentToClass(studentToRemove2, CS2109);

        // Set up expected model
        Student editedStudent1 = new StudentBuilder().withName("Amy Bee")
                .withCurrentLessonAttendance(true).withLessonsAttended(5).build();
        Student editedStudent2 = new StudentBuilder().withName("John")
                .withCurrentLessonAttendance(true).withLessonsAttended(2).build();
        MarkAllStudentPresentCommand command = new MarkAllStudentPresentCommand(Index.fromOneBased(4));
        Model expectedModel = new ModelManager(new EduTrack(model.getEduTrack()), new UserPrefs());
        expectedModel.setStudent(model.getFilteredStudentList().get(0), editedStudent1);
        expectedModel.setStudent(model.getFilteredStudentList().get(1), editedStudent2);

        String expectedMessage = String.format(MarkAllStudentPresentCommand.MESSAGE_MARK_STUDENT_ATTENDANCE_SUCCESS,
                "CS2109");
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        MarkAllStudentPresentCommand firstMarkAllStudentPresentCommand = new MarkAllStudentPresentCommand((
                Index.fromOneBased(1)));
        MarkAllStudentPresentCommand secondMarkAllStudentPresentCommand = new MarkAllStudentPresentCommand((
                Index.fromOneBased(2)));

        // Same object -> returns true
        assertTrue(firstMarkAllStudentPresentCommand.equals(firstMarkAllStudentPresentCommand));

        // same values -> return true
        MarkAllStudentPresentCommand firstDuplicateMarkAllStudentPresentCommand = new MarkAllStudentPresentCommand(
                Index.fromOneBased(1));
        assertTrue(firstMarkAllStudentPresentCommand.equals(firstDuplicateMarkAllStudentPresentCommand));

        // different values -> return false
        assertFalse(firstMarkAllStudentPresentCommand.equals(secondMarkAllStudentPresentCommand));

        // different instance -> return false
        assertFalse(firstMarkAllStudentPresentCommand.equals(new Integer(1)));
    }

    @Test
    public void toString_test() {
        Index classIndex = Index.fromOneBased(1);
        MarkAllStudentPresentCommand markAllStudentPresentCommand = new MarkAllStudentPresentCommand(classIndex);
        String expected = MarkAllStudentPresentCommand.class.getCanonicalName() + "{classIndex="
                + classIndex.toString() + "}";
        assertEquals(markAllStudentPresentCommand.toString(), expected);
    }
}
