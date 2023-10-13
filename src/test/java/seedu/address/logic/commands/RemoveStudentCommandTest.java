package seedu.address.logic.commands;

//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
//import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalStudents.getTypicalEduTrack;

//import org.junit.jupiter.api.Test;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.Class;
import seedu.address.model.module.ClassName;
//import seedu.address.model.student.Name;
//import seedu.address.model.student.Student;

public class RemoveStudentCommandTest {
    private static final String CLASSNAME_STUB = "cs2103t";

    final ClassName classNameStub = new ClassName(CLASSNAME_STUB);

    final Class classStub = new Class(classNameStub);

    private Model model = new ModelManager(getTypicalEduTrack(), new UserPrefs());


    //    @Test
    //    public void execute_validStudentName_success() {
    //        Student studentToDelete = model.getFilteredStudentList().get(INDEX_FIRST_PERSON.getZeroBased());
    //        Name studentName = studentToDelete.getName();
    //        RemoveCommand removeStudentCommand = new RemoveStudentCommand(INDEX_FIRST_PERSON, classNameStub);
    //        String expectedMessage = String.format(RemoveStudentCommand.MESSAGE_REMOVE_STUDENT_SUCCESS, studentName,
    //        classNameStub.toString());
    //        ModelManager expectedModel = new ModelManager(model.getEduTrack(), new UserPrefs());
    //        expectedModel.deleteStudentFromClass(studentToDelete, classStub);
    //
    //        assertCommandSuccess(removeStudentCommand, model, expectedMessage, expectedModel);
    //    }

    // MORE TESTS TO BE WRITTEN AFTER IMPLEMENTATION OF "CLASS" CLASS
}
