package seedu.address.model.module;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.module.exceptions.DuplicateClassException;

public class UniqueClassListTest {
    private final UniqueClassList uniqueClassList = new UniqueClassList();
    private final ClassName sampleClassName = new ClassName("cs2103t");
    private final Class sampleClass = new Class(sampleClassName);

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
    public void contains_personWithSameIdentityFieldsInList_returnsTrue() {
        uniqueClassList.add(sampleClass);
        Class c = new Class(sampleClassName);
        assertTrue(uniqueClassList.contains(c));
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
    public void toStringMethod() {
        assertEquals(uniqueClassList.asUnmodifiableObservableList().toString(), uniqueClassList.toString());
    }

}
