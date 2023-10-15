package seedu.address.model.module;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalClasses.CS2040;
import static seedu.address.testutil.TypicalClasses.CS2102;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import seedu.address.model.module.exceptions.ClassNotFoundException;
import seedu.address.model.module.exceptions.DuplicateClassException;

import java.sql.Array;
import java.util.ArrayList;

public class UniqueClassListTest {
    private final UniqueClassList uniqueClassList = new UniqueClassList();
    private final ClassName sampleClassName1 = new ClassName("cs2103t");
    private final ClassName sampleClassName2 = new ClassName("cs2105");
    private final Class sampleClass1 = new Class(sampleClassName1);
    private final Class sampleClass2 = new Class(sampleClassName2);

    @Test
    public void contains_nullClass_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueClassList.contains(null));
    }

    @Test
    public void contains_classNotInList_returnsFalse() {
        assertFalse(uniqueClassList.contains(sampleClass1));
    }

    @Test
    public void contains_classInList_returnsTrue() {
        uniqueClassList.add(sampleClass1);
        assertTrue(uniqueClassList.contains(sampleClass1));
    }

    @Test
    public void contains_personWithSameIdentityFieldsInList_returnsTrue() {
        uniqueClassList.add(sampleClass1);
        Class c = new Class(sampleClassName1);
        assertTrue(uniqueClassList.contains(c));
    }

    @Test
    public void add_nullClass_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueClassList.add(null));
    }

    @Test
    public void add_duplicateClass_throwsDuplicateClassException() {
        uniqueClassList.add(sampleClass1);
        assertThrows(DuplicateClassException.class, () -> uniqueClassList.add(sampleClass1));
    }

    @Test
    public void remove_existingClass_success() {
        uniqueClassList.add(sampleClass1);
        Assertions.assertDoesNotThrow(() -> uniqueClassList.remove(sampleClass1));
    }

    @Test
    public void remove_notExistingClass_throwClassNotFoundException() {
        assertThrows(ClassNotFoundException.class, () -> uniqueClassList.remove(sampleClass1));
    }

    @Test
    public void setClasses_emptyClasses_success() {
        assertDoesNotThrow(() -> uniqueClassList.setClasses(new ArrayList<Class>()));
    }

    @Test
    public void setClasses_nonEmptyClasses_success() {
        ArrayList<Class> classListToAdd = new ArrayList<>();
        classListToAdd.add(sampleClass1);
        classListToAdd.add(sampleClass2);
        assertDoesNotThrow(() -> uniqueClassList.setClasses(classListToAdd));
    }

    @Test
    public void setClasses_duplicateClasses_throwDuplicateClassException() {
        ArrayList<Class> classListToAdd = new ArrayList<>();
        classListToAdd.add(sampleClass1);
        classListToAdd.add(sampleClass1);
        classListToAdd.add(sampleClass2);
        assertThrows(DuplicateClassException.class, () -> uniqueClassList.setClasses(classListToAdd));
    }

    @Test
    public void setClasses_nullUniqueClassList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueClassList.setClasses((List<Class>) null));
    }

    @Test
    public void setClasses_uniqueClassList_replacesOwnListWithProvidedUniqueClassList() {
        uniqueClassList.add(CS2040);
        UniqueClassList expectedUniqueClassList = new UniqueClassList();
        expectedUniqueClassList.add(CS2102);
        uniqueClassList.setClasses(expectedUniqueClassList);
        assertEquals(expectedUniqueClassList, uniqueClassList);
    }

    @Test
    public void setClasses_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueClassList.setClasses((List<Class>) null));
    }

    @Test
    public void setClasses_list_replacesOwnListWithProvidedList() {
        uniqueClassList.add(CS2040);
        List<Class> classList = Collections.singletonList(CS2102);
        uniqueClassList.setClasses(classList);
        UniqueClassList expectedUniqueClassList = new UniqueClassList();
        expectedUniqueClassList.add(CS2102);
        assertEquals(expectedUniqueClassList, uniqueClassList);
    }

    @Test
    public void setClasses_listWithDuplicateClasses_throwsDuplicateClassException() {
        List<Class> listWithDuplicateClasses = Arrays.asList(CS2040, CS2040);
        assertThrows(DuplicateClassException.class, () -> uniqueClassList.setClasses(listWithDuplicateClasses));
    }
    @Test
    public void toStringMethod() {
        assertEquals(uniqueClassList.asUnmodifiableObservableList().toString(), uniqueClassList.toString());
    }

}
