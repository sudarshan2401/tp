package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.EduTrack;
import seedu.address.model.module.Class;
import seedu.address.model.module.ClassName;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalClasses {

    public static final Class CS2102 = new Class(new ClassName("cs2102"));
    public static final Class CS2105 = new Class(new ClassName("cs2105"));
    public static final Class CS2040 = new Class(new ClassName("cs2040"));

    private TypicalClasses() {} // prevents instantiation

    /**
     * Returns an {@code EduTrack} with all the typical persons.
     */
    public static EduTrack getTypicalEduTrack() {
        EduTrack et = new EduTrack();
        for (Class c : getTypicalClasses()) {
            et.addClass(c);
        }
        return et;
    }

    public static List<Class> getTypicalClasses() {
        return new ArrayList<>(Arrays.asList(CS2102, CS2105, CS2040));
    }
}
