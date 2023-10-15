package seedu.address.model.module;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalClasses.CS2040;
import static seedu.address.testutil.TypicalClasses.CS2102;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.module.exceptions.DuplicateClassException;
import seedu.address.model.student.UniqueStudentList;

public class UniqueClassListTest {
    private final UniqueClassList uniqueClassList = new UniqueClassList();
    private final ClassName sampleClassName = new ClassName("cs2103t");
    private final Class sampleClass = new Class(sampleClassName, new UniqueStudentList());

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
    public void contains_classWithSameIdentityFieldsInList_returnsTrue() {
        uniqueClassList.add(sampleClass);
        Class c = new Class(sampleClassName, new UniqueStudentList());
        assertTrue(uniqueClassList.contains(c));
    }

    @Test
    public void add_nullClass_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueClassList.add(null));
    }

    @Test
    public void add_duplicateClass_throwsDuplicateClassException() {
        uniqueClassList.add(sampleClass);
        assertThrows(DuplicateClassException.class, () -> uniqueClassList.add(sampleClass));
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
