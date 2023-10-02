package seedu.address.model.module;

import org.junit.jupiter.api.Test;
import seedu.address.model.person.Name;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

public class ClassNameTest {

    @Test
    public void isValidClassName() {
        // null name
        assertThrows(NullPointerException.class, () -> ClassName.isValidClassName(null));

        // invalid name
        assertFalse(ClassName.isValidClassName("")); // empty string
        assertFalse(ClassName.isValidClassName(" ")); // spaces only
        assertFalse(ClassName.isValidClassName("^")); // only non-alphanumeric characters
        assertFalse(ClassName.isValidClassName("cs2103*t")); // contains non-alphanumeric characters
        assertFalse(ClassName.isValidClassName("cs 2103t")); // contains spaces

        // valid name
        assertTrue(ClassName.isValidClassName("abc")); // alphabets only
        assertTrue(ClassName.isValidClassName("123")); // numbers only
        assertTrue(ClassName.isValidClassName("cs2103t")); // alphanumeric characters
    }

    @Test
    public void equals() {
        ClassName className = new ClassName("cs2103t");

        // same values -> returns true
        assertTrue(className.equals(new ClassName("cs2103t")));

        // same object -> returns true
        assertTrue(className.equals(className));

        // null -> returns false
        assertFalse(className.equals(null));

        // different types -> returns false
        assertFalse(className.equals(5.0f));

        // different values -> returns false
        assertFalse(className.equals(new Name("cs2100")));
    }
}
