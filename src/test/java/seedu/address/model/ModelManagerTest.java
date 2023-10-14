package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.module.Class;
import seedu.address.model.module.ClassName;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.testutil.EduTrackBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new EduTrack(), new EduTrack(modelManager.getEduTrack()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setEduTrackFilePath(Paths.get("edu/track/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setEduTrackFilePath(Paths.get("new/edu/track/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void setEduTrackFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setEduTrackFilePath(null));
    }

    @Test
    public void setEduTrackFilePath_validPath_setsEduTrackFilePath() {
        Path path = Paths.get("edu/track/file/path");
        modelManager.setEduTrackFilePath(path);
        assertEquals(path, modelManager.getEduTrackFilePath());
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInEduTrack_returnsFalse() {
        assertFalse(modelManager.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInEduTrack_returnsTrue() {
        modelManager.addPerson(ALICE);
        assertTrue(modelManager.hasPerson(ALICE));
    }

    @Test
    public void retrieveClass_emptyClassList_throwsCommandException() {
        assertThrows(CommandException.class, () -> modelManager.retrieveClass(Index.fromOneBased(1)));
    }

    @Test
    public void retrieveClass_indexLargerThanClassList_throwsCommandException() {
        modelManager.addClass(new Class(new ClassName("class1")));
        modelManager.addClass(new Class(new ClassName("class2")));
        assertThrows(CommandException.class, () -> modelManager.retrieveClass(Index.fromOneBased(3)));
    }

    @Test
    public void retrieveClass_validIndexInClassList_success() {
        modelManager.addClass(new Class(new ClassName("class1")));
        modelManager.addClass(new Class(new ClassName("class2")));
        assertDoesNotThrow(() -> modelManager.retrieveClass(Index.fromOneBased(1)));
    }

    @Test
    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredPersonList().remove(0));
    }

    @Test
    public void equals() {
        EduTrack eduTrack = new EduTrackBuilder().withPerson(ALICE).withPerson(BENSON).build();
        EduTrack differentEduTrack = new EduTrack();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(eduTrack, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(eduTrack, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different eduTrack -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentEduTrack, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(eduTrack, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setEduTrackFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(eduTrack, differentUserPrefs)));
    }
}
