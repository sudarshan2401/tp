package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class LessonsAttendedTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new LessonsAttended(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        Integer invalidAttendance = -5;
        assertThrows(IllegalArgumentException.class, () -> new LessonsAttended(invalidAttendance));
    }

    @Test
    public void isValidLessonsAttended() {
        assertThrows(NullPointerException.class, () -> LessonsAttended.isValidLessonsAttended(null));
        assertFalse(LessonsAttended.isValidLessonsAttended(-1));
        assertFalse(LessonsAttended.isValidLessonsAttended(-100));

        assertTrue(LessonsAttended.isValidLessonsAttended(0));
        assertTrue(LessonsAttended.isValidLessonsAttended(1));
        assertTrue(LessonsAttended.isValidLessonsAttended(50));
    }

    @Test
    public void getTotalLessons() {
        LessonsAttended defaultLessonsAttended = new LessonsAttended();
        Integer expectedDefault = 0;
        assertEquals(expectedDefault, defaultLessonsAttended.getTotalLessons());
        LessonsAttended lessonsAttended = new LessonsAttended(50);
        Integer expected = 50;
        assertEquals(expected, lessonsAttended.getTotalLessons());
    }

    @Test
    public void incrementLessons() {
        LessonsAttended lessonsAttended = new LessonsAttended(50);
        lessonsAttended.incrementLessons();
        Integer expected = 51;
        assertEquals(expected, lessonsAttended.getTotalLessons());
    }

    @Test
    public void decrementLessons() {
        LessonsAttended lessonsAttended = new LessonsAttended(50);
        lessonsAttended.decrementLessons();
        Integer expected = 49;
        assertEquals(expected, lessonsAttended.getTotalLessons());
    }

    @Test
    public void equals() {
        LessonsAttended lessonsAttended = new LessonsAttended(10);
        LessonsAttended lessonsAttended_default = new LessonsAttended();

        assertTrue(lessonsAttended.equals(new LessonsAttended(10)));
        assertTrue(lessonsAttended_default.equals(new LessonsAttended()));
        assertTrue(lessonsAttended.equals(lessonsAttended));

        assertFalse(lessonsAttended.equals(lessonsAttended_default));
        assertFalse(lessonsAttended.equals(new LessonsAttended(50)));
        assertFalse(lessonsAttended.equals(null));
    }
}
