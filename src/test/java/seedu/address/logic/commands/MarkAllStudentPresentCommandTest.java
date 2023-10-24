package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CLASS;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.Class;
import seedu.address.model.module.ClassName;
import seedu.address.model.student.Student;
import seedu.address.model.student.exceptions.StudentNotFoundException;
import seedu.address.testutil.StudentBuilder;
import seedu.address.testutil.TypicalClasses;

public class MarkAllStudentPresentCommandTest {

    private Model model;
    private Class classStub;
    private Student studentStub;
    private ClassName classStubName;
    @BeforeEach
    public void setUp() {
        model = new ModelManager(TypicalClasses.getTypicalEduTrack(), new UserPrefs());
        classStub = model.getFilteredClassList().get(INDEX_FIRST_CLASS.getZeroBased());
        studentStub = new StudentBuilder().build();
        model.addStudent(studentStub);
        if (!classStub.hasStudentInClass(studentStub)) {
            classStub.addStudentToClass(studentStub);
        }
        classStubName = classStub.getClassName();
    }

    @AfterEach
    public void cleanUp() {
        try {
            model.deleteStudentFromClass(studentStub, classStub);
        } catch (StudentNotFoundException e) {
            System.out.println("Cleaning up after test: No student to be removed");
        }
    }

    @Test
    public void constructor_nullClass_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new MarkAllStudentPresentCommand(null));
    }

    @Test
    public void constructor_validIndex_success() {
        assertDoesNotThrow(() -> new MarkAllStudentPresentCommand(Index.fromOneBased(1)));
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
