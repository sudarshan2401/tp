package seedu.address.model.module;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class ClassTest {
    private final Class c = new Class(new ClassName("abc"));

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Class(null));
    }

    @Test
    public void equals() {
        // same values -> returns true
        assertTrue(c.equals(new Class(new ClassName("abc"))));

        // same object -> returns true
        assertTrue(c.equals(c));

        // null -> returns false
        assertFalse(c.equals(null));

        // different types -> returns false
        assertFalse(c.equals(5.0f));

        // different values -> returns false
        assertFalse(c.equals(new Class(new ClassName("def"))));
    }

    @Test
    public void toStringMethod() {
        String expected = Class.class.getCanonicalName() + "{class name=" + c.getClassName() + "}";
        assertEquals(expected, c.toString());
    }
}
