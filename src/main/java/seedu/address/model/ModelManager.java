package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.module.Class;
import seedu.address.model.module.ClassName;
import seedu.address.model.module.exceptions.ClassNotFoundException;
import seedu.address.model.student.Student;
import seedu.address.model.student.exceptions.StudentAlreadyMarkedAbsent;
import seedu.address.model.student.exceptions.StudentAlreadyMarkedPresent;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final EduTrack eduTrack;
    private final UserPrefs userPrefs;
    private final FilteredList<Student> filteredStudents;
    private final FilteredList<Class> filteredClasses;

    /**
     * Initializes a ModelManager with the given eduTrack and userPrefs.
     */
    public ModelManager(ReadOnlyEduTrack eduTrack, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(eduTrack, userPrefs);

        logger.fine("Initializing with address book: " + eduTrack + " and user prefs " + userPrefs);

        this.eduTrack = new EduTrack(eduTrack);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredStudents = new FilteredList<>(this.eduTrack.getStudentList());
        filteredClasses = new FilteredList<>(this.eduTrack.getClassList());
    }

    public ModelManager() {
        this(new EduTrack(), new UserPrefs());
    }

    // =========== UserPrefs
    // ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getEduTrackFilePath() {
        return userPrefs.getEduTrackFilePath();
    }

    @Override
    public void setEduTrackFilePath(Path eduTrackFilePath) {
        requireNonNull(eduTrackFilePath);
        userPrefs.setEduTrackFilePath(eduTrackFilePath);
    }

    //=========== EduTrack ================================================================================

    @Override
    public void setEduTrack(ReadOnlyEduTrack eduTrack) {
        this.eduTrack.resetData(eduTrack);
    }

    @Override
    public ReadOnlyEduTrack getEduTrack() {
        return eduTrack;
    }

    @Override
    public boolean hasStudent(Student person) {
        requireNonNull(person);
        return eduTrack.hasStudent(person);
    }

    @Override
    public void deleteStudent(Student target) {
        eduTrack.removeStudent(target);
    }

    @Override
    public void deleteStudentFromClass(Student student, Class studentClass) {
        studentClass.removeStudentFromClass(student);
        updateFilteredStudentList((s) -> studentClass.getStudentList().contains(s));
    }

    @Override
    public void addStudent(Student person) {
        eduTrack.addStudent(person);
    }

    @Override
    public void addStudentToClass(Student student, Class studentClass) {
        studentClass.addStudentToClass(student);
        updateFilteredStudentList((s) -> studentClass.getStudentList().contains(s));
    }

    @Override
    public void addClass(Class c) {
        eduTrack.addClass(c);
        updateFilteredClassList(PREDICATE_SHOW_ALL_CLASSES);
    }

    @Override
    public boolean hasClass(Class c) {
        requireNonNull(c);
        return eduTrack.hasClass(c);
    }

    @Override
    public void setClass(Index index, Class editedClass) {
        requireAllNonNull(index, editedClass);
        eduTrack.setClass(index, editedClass);
    }

    @Override
    public void removeClass(Class c) {
        requireNonNull(c);
        eduTrack.removeClass(c);
    }

    @Override
    public Class retrieveClass(Index targetClassIndex) throws CommandException {
        ObservableList<Class> classList = this.eduTrack.getClassList();
        if (classList.size() == 0) {
            throw new CommandException(Messages.MESSAGE_EMPTY_CLASS_LIST);
        }
        if (targetClassIndex.getZeroBased() >= classList.size()) {
            throw new CommandException(Messages.MESSAGE_INDEX_INPUT_TOO_LARGE);
        }

        return classList.get(targetClassIndex.getZeroBased());
    }

    @Override
    public void markStudentPresent(Student student, Class studentClass, Student editedStudent)
            throws StudentAlreadyMarkedPresent {
        editedStudent.markStudentPresent();
        eduTrack.setStudent(student, editedStudent);

        // Changes the original Student object
        student.markStudentPresent();
        updateFilteredStudentList((s) -> studentClass.getStudentList().contains(s));
    }

    @Override
    public void markStudentAbsent(Student student, Class studentClass, Student editedStudent)
            throws StudentAlreadyMarkedAbsent {
        editedStudent.markStudentAbsent();
        eduTrack.setStudent(student, editedStudent);

        // Changes the original Student object
        student.markStudentAbsent();
        updateFilteredStudentList((s) -> studentClass.getStudentList().contains(s));
    }

    public Class getClass(ClassName className) throws ClassNotFoundException {
        requireNonNull(className);
        return eduTrack.getClass(className);
    }

    public Class getClassByIndex(Index classIndex) {
        requireNonNull(classIndex);
        return eduTrack.getClassByIndex(classIndex);
    }

    public int getClassListSize() {
        return eduTrack.getClassListSize();
    }
    //=========== Filtered Person List Accessors =============================================================

    @Override
    public void setStudent(Student target, Student editedPerson) {
        requireAllNonNull(target, editedPerson);

        eduTrack.setStudent(target, editedPerson);
    }

    @Override
    public void setStudentInClass(Student target, Student editedStudent, Class targetClass) {
        requireAllNonNull(target, editedStudent, targetClass);

        targetClass.setStudent(target, editedStudent);
    }

    // =========== Filtered Person List Accessors
    // =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedEduTrack}
     */
    @Override
    public ObservableList<Student> getFilteredStudentList() {
        return filteredStudents;
    }

    @Override
    public void updateFilteredStudentList(Predicate<Student> predicate) {
        requireNonNull(predicate);
        filteredStudents.setPredicate(predicate);
    }

    @Override
    public ObservableList<Class> getFilteredClassList() {
        return filteredClasses;
    }

    @Override
    public void updateFilteredClassList(Predicate<Class> predicate) {
        requireNonNull(predicate);
        filteredClasses.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ModelManager)) {
            return false;
        }

        ModelManager otherModelManager = (ModelManager) other;
        return eduTrack.equals(otherModelManager.eduTrack)
                && userPrefs.equals(otherModelManager.userPrefs)
                && filteredStudents.equals(otherModelManager.filteredStudents);
    }

}
