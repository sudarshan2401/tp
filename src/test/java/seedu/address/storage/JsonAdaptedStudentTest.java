package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedStudent.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalStudents.BENSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.student.Name;

public class JsonAdaptedStudentTest {
    private static final String INVALID_NAME = "R@chel";
    private static final Boolean VALID_CURRENT_ATTENDANCE = true;
    private static final Integer VALID_LESSONS_ATTENDED = 5;
    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_ID = "A0000000Z";
    private static final String VALID_NOTE = "Gets distracted easily.";


    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedStudent person = new JsonAdaptedStudent(VALID_NAME,
                VALID_ID, VALID_NOTE, VALID_CURRENT_ATTENDANCE, VALID_LESSONS_ATTENDED);
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedStudent person = new JsonAdaptedStudent(INVALID_NAME,
                VALID_ID, VALID_NOTE, VALID_CURRENT_ATTENDANCE, VALID_LESSONS_ATTENDED);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedStudent person = new JsonAdaptedStudent((String) null,
                VALID_ID, VALID_NOTE, VALID_CURRENT_ATTENDANCE, VALID_LESSONS_ATTENDED);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

}
