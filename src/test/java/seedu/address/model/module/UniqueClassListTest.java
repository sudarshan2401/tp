package seedu.address.model.module;

import org.junit.jupiter.api.Test;
import seedu.address.model.module.exceptions.DuplicateClassException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

public class UniqueClassListTest {
    private final UniqueClassList uniqueClassList = new UniqueClassList();
    private final Class sampleClass = new Class(new ClassName("cs2103t"));

    @Test
    public void contains_nullClass_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueClassList.contains(null));
    }

    @Test
    public void contains_classNotInList_returnsFalse() {
        assertFalse(uniqueClassList.contains(sampleClass));
    }

    @Test
    public void contains_classInList_returnsTrue() {
        uniqueClassList.add(sampleClass);
        assertTrue(uniqueClassList.contains(sampleClass));
    }

    @Test
    public void add_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueClassList.add(null));
    }

    @Test
    public void add_duplicateClass_throwsDuplicateClassException() {
        uniqueClassList.add(sampleClass);
        assertThrows(DuplicateClassException.class, () -> uniqueClassList.add(sampleClass));
    }

    @Test
    public void setClasses_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueClassList.setClasses(null));
    }

}
