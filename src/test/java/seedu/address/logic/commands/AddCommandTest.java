package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalStudents.ALICE;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.EduTrack;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyEduTrack;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.module.Class;
import seedu.address.model.module.ClassName;
import seedu.address.model.student.Student;
import seedu.address.model.student.exceptions.StudentAlreadyMarkedAbsent;
import seedu.address.model.student.exceptions.StudentAlreadyMarkedPresent;
import seedu.address.testutil.StudentBuilder;

public class AddCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();
        Student validPerson = new StudentBuilder().build();

        CommandResult commandResult = new AddCommand(validPerson).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, Messages.formatStudent(validPerson)),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validPerson), modelStub.personsAdded);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Student validPerson = new StudentBuilder().build();
        AddCommand addCommand = new AddCommand(validPerson);
        ModelStub modelStub = new ModelStubWithPerson(validPerson);

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_PERSON, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Student alice = new StudentBuilder().withName("Alice").build();
        Student bob = new StudentBuilder().withName("Bob").build();
        AddCommand addAliceCommand = new AddCommand(alice);
        AddCommand addBobCommand = new AddCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddCommand addAliceCommandCopy = new AddCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different person -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    @Test
    public void toStringMethod() {
        AddCommand addCommand = new AddCommand(ALICE);
        String expected = AddCommand.class.getCanonicalName() + "{toAdd=" + ALICE + "}";
        assertEquals(expected, addCommand.toString());
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getEduTrackFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setEduTrackFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addStudent(Student person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setEduTrack(ReadOnlyEduTrack newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyEduTrack getEduTrack() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasStudent(Student person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteStudent(Student target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteStudentFromClass(Student student, Class studentClass) {
            // to be filled after implementation of class' delete student function
        }

        @Override
        public void addStudentToClass(Student student, Class classToAdd) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setStudent(Student target, Student editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setStudentInClass(Student target, Student editedStudent, Class targetClass) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Student> getFilteredStudentList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredStudentList(Predicate<Student> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasClass(Class c) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void markStudentPresent(Student student, Class studentClass, Student editedStudent)
                throws StudentAlreadyMarkedPresent {
            throw new AssertionError("This method should not be called.");
        };

        @Override
        public void markStudentAbsent(Student student, Class studentClass, Student editedStudent)
                throws StudentAlreadyMarkedAbsent {
            throw new AssertionError("This method should not be called.");
        };

        @Override
        public Class getClass(ClassName className) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addClass(Class c) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Class getClassByIndex(Index classIndex) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void removeClass(Class c) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public int getClassListSize() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Class> getFilteredClassList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredClassList(Predicate<Class> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Class retrieveClass(Index classListIndex) {
            throw new AssertionError("This method should not be called");
        }
        @Override
        public void setClass(Index classListIndex, Class c) {
            throw new AssertionError("This method should not be called");
        }
    }

    /**
     * A Model stub that contains a single person.
     */
    private class ModelStubWithPerson extends ModelStub {
        private final Student person;

        ModelStubWithPerson(Student person) {
            requireNonNull(person);
            this.person = person;
        }

        @Override
        public boolean hasStudent(Student person) {
            requireNonNull(person);
            return this.person.isSameStudent(person);
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingPersonAdded extends ModelStub {
        final ArrayList<Student> personsAdded = new ArrayList<>();

        @Override
        public boolean hasStudent(Student person) {
            requireNonNull(person);
            return personsAdded.stream().anyMatch(person::isSameStudent);
        }

        @Override
        public void addStudent(Student person) {
            requireNonNull(person);
            personsAdded.add(person);
        }

        @Override
        public ReadOnlyEduTrack getEduTrack() {
            return new EduTrack();
        }
    }
}
