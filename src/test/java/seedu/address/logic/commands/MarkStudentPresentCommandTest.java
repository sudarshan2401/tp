package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
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

public class MarkStudentPresentCommandTest {
    private Model model = new ModelManager(TypicalClasses.getTypicalEduTrack(), new UserPrefs());
    @Test
    public void execute_userIsMarked_throwsStudentAlreadyMarkedPresentException() {
        model = new ModelManager(TypicalClasses.getTypicalEduTrack(), new UserPrefs());
        Class cs2109 = new Class(new ClassName("cs2109"), new UniqueStudentList(),
                new Memo(" "), new Schedule());
        cs2109.setTotalLessons(10);
        Student studentToRemove = new StudentBuilder().withName("Amy Bee")
                .withCurrentLessonAttendance(true).withLessonsAttended(4).build();
        model.addStudent(studentToRemove);
        model.addClass(cs2109);
        model.addStudentToClass(studentToRemove, cs2109);

        ClassName className = new ClassName("CS2109");
        Index index = Index.fromOneBased(1);
        MarkStudentPresentCommand command = new MarkStudentPresentCommand(index, className);
        String expectedMessage = String.format(MarkStudentPresentCommand.MESSAGE_STUDENT_ALREADY_MARKED,
                Messages.formatStudent(studentToRemove));
        assertCommandFailure(command, model, expectedMessage);
    }

    @Test
    public void execute_userIsMarked_throwsAttendanceDiscrepancyException() {
        model = new ModelManager(TypicalClasses.getTypicalEduTrack(), new UserPrefs());
        Class cs2109 = new Class(new ClassName("cs2109"), new UniqueStudentList(),
                new Memo(" "), new Schedule());
        cs2109.setTotalLessons(0);
        Student studentToRemove = new StudentBuilder().withName("Amy Bee")
                .withCurrentLessonAttendance(true).withLessonsAttended(4).build();
        model.addStudent(studentToRemove);
        model.addClass(cs2109);
        model.addStudentToClass(studentToRemove, cs2109);

        ClassName className = new ClassName("CS2109");
        Index index = Index.fromOneBased(1);
        MarkStudentPresentCommand command = new MarkStudentPresentCommand(index, className);
        String expectedMessage = String.format(MarkStudentPresentCommand.MESSAGE_EXISTING_ATTENDANCE_LARGER_THAN_TOTAL,
                Messages.formatClass(cs2109));
        assertCommandFailure(command, model, expectedMessage);
    }

    @Test
    public void execute_userIsUnmarked_success() {
        // set up SUT
        model = new ModelManager(TypicalClasses.getTypicalEduTrack(), new UserPrefs());
        Class cs2109 = new Class(new ClassName("cs2109"), new UniqueStudentList(),
                new Memo(" "), new Schedule());
        cs2109.setTotalLessons(10);
        Student studentToRemove = new StudentBuilder().withName("Amy Bee")
                .withCurrentLessonAttendance(false).withLessonsAttended(4).build();
        model.addStudent(studentToRemove);
        model.addClass(cs2109);
        model.addStudentToClass(studentToRemove, cs2109);

        // set up expected model
        Student editedStudent = new StudentBuilder().withName("Amy Bee")
                .withCurrentLessonAttendance(true).withLessonsAttended(5).build();
        ClassName className = new ClassName("CS2109");
        MarkStudentPresentCommand command = new MarkStudentPresentCommand(Index.fromOneBased(1),
                className);
        Model expectedModel = new ModelManager(new EduTrack(model.getEduTrack()), new UserPrefs());
        expectedModel.setStudent(model.getFilteredStudentList().get(0), editedStudent);

        String expectedMessage = String.format(MarkStudentPresentCommand.MESSAGE_MARK_STUDENT_ATTENDANCE_SUCCESS,
                Messages.formatStudent(editedStudent));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidClass_throwsClassNotFoundException() {
        model = new ModelManager(TypicalClasses.getTypicalEduTrack(), new UserPrefs());
        Class cs2109 = new Class(new ClassName("cs2109"), new UniqueStudentList(),
                new Memo(" "), new Schedule());
        Student studentToRemove = new StudentBuilder().withName("Amy Bee")
                .withCurrentLessonAttendance(false).withLessonsAttended(5).build();
        model.addStudent(studentToRemove);
        model.addClass(cs2109);
        model.addStudentToClass(studentToRemove, cs2109);

        ClassName className = new ClassName("CS1101S");
        // Actual test
        String studentStubName = "Amy Bee";
        Index index = Index.fromOneBased(1);
        MarkStudentPresentCommand command = new MarkStudentPresentCommand(index, className);
        String expectedMessage = String.format(MarkStudentPresentCommand.MESSAGE_MISSING_CLASS_NAME,
                "CS1101S");
        assertCommandFailure(command, model, expectedMessage);
    }

    @Test
    public void equalsMethod() {
        Index index1 = Index.fromOneBased(1);
        ClassName className1 = new ClassName("testclass1");
        Index index2 = Index.fromOneBased(2);
        ClassName className2 = new ClassName("testclass2");
        Index dupIndex1 = Index.fromOneBased(1);
        ClassName dupClassName1 = new ClassName("testclass1");
        MarkStudentPresentCommand command1 = new MarkStudentPresentCommand(index1, className1);
        MarkStudentPresentCommand command2 = new MarkStudentPresentCommand(index2, className2);
        MarkStudentPresentCommand dupCommand1 = new MarkStudentPresentCommand(dupIndex1, dupClassName1);

        // same instance
        assertTrue(command1.equals(command1));
        // null -> false
        assertFalse(command1.equals(null));
        // different values
        assertFalse(command1.equals(command2));
        // different instance but same values
        assertTrue(command1.equals(dupCommand1));
    }

    @Test
    public void toStringMethod() {
        Index index = Index.fromOneBased(1);
        ClassName className = new ClassName("testclass");
        MarkStudentPresentCommand command = new MarkStudentPresentCommand(index, className);
        String expected = MarkStudentPresentCommand.class.getCanonicalName() + "{studentIndex=" + index + ", className="
                + className.toString() + "}";
        assertEquals(expected, command.toString());
    }
}
