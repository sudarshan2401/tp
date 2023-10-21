package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.module.Class;
import seedu.address.model.module.ClassName;
import seedu.address.model.module.exceptions.ClassNotFoundException;
import seedu.address.model.student.Student;
import seedu.address.model.student.exceptions.StudentAlreadyMarkedAbsent;
import seedu.address.model.student.exceptions.StudentAlreadyMarkedPresent;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Student> PREDICATE_SHOW_ALL_PERSONS = unused -> true;
    /** {@code Predicate} that always evaluate to true */
    Predicate<Class> PREDICATE_SHOW_ALL_CLASSES = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getEduTrackFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setEduTrackFilePath(Path eduTrackFilePath);

    /**
     * Replaces address book data with the data in {@code eduTrack}.
     */
    void setEduTrack(ReadOnlyEduTrack eduTrack);

    /** Returns the EduTrack */
    ReadOnlyEduTrack getEduTrack();

    /**
     * Returns true if a person with the same identity as {@code person} exists in
     * the address book.
     */
    boolean hasStudent(Student person);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deleteStudent(Student target);

    /**
     * Deletes the given student from class.
     * The person must exist in the class.
     */
    void deleteStudentFromClass(Student student, Class studentClass);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addStudent(Student person);

    /**
     * Adds the given student to the class.
     * The person must not already exist in the class.
     */
    void addStudentToClass(Student student, Class studentClass);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another
     * existing person in the address book.
     */
    void setStudent(Student target, Student editedPerson);

    /** Returns an unmodifiable view of the filtered student list */
    ObservableList<Student> getFilteredStudentList();

    /** Returns an unmodifiable view of the filtered class list */
    ObservableList<Class> getFilteredClassList();

    /**
     * Updates the filter of the filtered person list to filter by the given
     * {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredStudentList(Predicate<Student> predicate);

    void addClass(Class c);

    boolean hasClass(Class c);

    void removeClass(Class c) throws ClassNotFoundException;

    Class retrieveClass(Index classListIndex) throws CommandException;
    Student retrieveStudent(Index studentListIndex) throws CommandException;
    void markStudentPresent(Student student, Class studentClass, Student editedStudent) throws StudentAlreadyMarkedPresent;
    void markStudentAbsent(Student student, Class studentClass, Student editedStudent) throws StudentAlreadyMarkedAbsent;

    Class getClass(ClassName className);

    Class getClassByIndex(Index classIndex);

    int getClassListSize();

    void updateFilteredClassList(Predicate<Class> predicate);
}
