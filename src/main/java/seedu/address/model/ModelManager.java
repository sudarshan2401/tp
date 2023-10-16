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
import seedu.address.model.module.Class;
import seedu.address.model.module.ClassName;
import seedu.address.model.student.Student;

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

        // IMPORTANT!! to be removed after `add student` is implemented
        // current classStub share the same file as EduTrack.json under data folder
        // this automatically always a create a class called classStub
        //        ClassName classNameStub = new ClassName("classStub");
        //        Class classStub = new Class(classNameStub);
        //        classStub.setStudents(eduTrack.getStudentList());
        //        this.addClass(classStub);
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
    }

    @Override
    public void addStudent(Student person) {
        eduTrack.addStudent(person);
        updateFilteredStudentList(PREDICATE_SHOW_ALL_PERSONS);
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

    public Class getClass(ClassName className) {
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

    @Override
    public void setStudent(Student target, Student editedPerson) {
        requireAllNonNull(target, editedPerson);

        eduTrack.setStudent(target, editedPerson);
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
