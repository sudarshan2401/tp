package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedClass.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalClasses.CS2105;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.module.ClassName;

public class JsonAdaptedClassTest {

    private static final String INVALID_CLASS_NAME = "cs 2103t";

    @Test
    public void toModelType_validClassDetails_returnsClass() throws Exception {
        JsonAdaptedClass c = new JsonAdaptedClass(CS2105);
        assertEquals(CS2105, c.toModelType());
    }

    @Test
    public void toModelType_invalidClassName_throwsIllegalValueException() {
        JsonAdaptedClass c =
                new JsonAdaptedClass(INVALID_CLASS_NAME);
        String expectedMessage = ClassName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, c::toModelType);
    }

    @Test
    public void toModelType_nullClassName_throwsIllegalValueException() {
        JsonAdaptedClass c = new JsonAdaptedClass((String) null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ClassName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, c::toModelType);
    }
}
