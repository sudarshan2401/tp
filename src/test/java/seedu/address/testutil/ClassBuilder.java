package seedu.address.testutil;


import seedu.address.model.common.Memo;
import seedu.address.model.module.Class;
import seedu.address.model.module.ClassName;
import seedu.address.model.module.Schedule;
import seedu.address.model.student.UniqueStudentList;

/**
 * A utility class to help with building Class objects.
 */
public class ClassBuilder {

    public static final String DEFAULT_CLASS_NAME = "cs2103t";
    public static final String DEFAULT_MEMO = "prepare materials";
    public static final String DEFAULT_SCHEDULE = "thu, 15:00-16:00";

    private ClassName className;
    private Memo memo;
    private Schedule schedule;

    /**
     * Creates a {@code ClassBuilder} with the default details.
     */
    public ClassBuilder() {
        className = new ClassName(DEFAULT_CLASS_NAME);
        memo = new Memo(DEFAULT_MEMO);
        schedule = new Schedule(DEFAULT_SCHEDULE);
    }

    /**
     * Initializes the ClassBuilder with the data of {@code classToCopy}.
     */
    public ClassBuilder(Class classToCopy) {
        className = classToCopy.getClassName();
    }

    /**
     * Sets the {@code ClassName} of the {@code Class} that we are building.
     */
    public ClassBuilder withClassName(String className) {
        this.className = new ClassName(className);
        return this;
    }


    /**
     * Sets the {@code Memo} of the {@code Class} that we are building.
     */
    public ClassBuilder withMemo(String m) {
        this.memo = new Memo(m);
        return this;
    }

    /**
     * Sets the {@code Memo} of the {@code Class} that we are building.
     */
    public ClassBuilder withSchedule(String s) {
        this.schedule = new Schedule(s);
        return this;
    }

    public Class build() {
        return new Class(className, new UniqueStudentList(), memo, schedule);
    }

}
