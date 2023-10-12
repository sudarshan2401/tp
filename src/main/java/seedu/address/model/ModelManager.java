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
import seedu.address.model.module.Class;
import seedu.address.model.person.Person;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final EduTrack eduTrack;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;

    /**
     * Initializes a ModelManager with the given eduTrack and userPrefs.
     */
    public ModelManager(ReadOnlyEduTrack eduTrack, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(eduTrack, userPrefs);

        logger.fine("Initializing with address book: " + eduTrack + " and user prefs " + userPrefs);

        this.eduTrack = new EduTrack(eduTrack);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.eduTrack.getPersonList());
    }

    public ModelManager() {
        this(new EduTrack(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

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
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return eduTrack.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        eduTrack.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        eduTrack.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        eduTrack.setPerson(target, editedPerson);
    }

    @Override
    public void addClass(Class c) {
        eduTrack.addClass(c);
    }

    @Override
    public boolean hasClass(Class c) {
        requireNonNull(c);
        return eduTrack.hasClass(c);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedEduTrack}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
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
                && filteredPersons.equals(otherModelManager.filteredPersons);
    }

}
