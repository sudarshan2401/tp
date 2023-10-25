package seedu.address.testutil;

import seedu.address.model.common.Memo;
import seedu.address.model.student.CurrentLessonAttendance;
import seedu.address.model.student.Id;
import seedu.address.model.student.LessonsAttended;
import seedu.address.model.student.Name;
import seedu.address.model.student.Student;

/**
 * A utility class to help with building Person objects.
 */
public class StudentBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    private static final String DEFAULT_ID = "A0000000Z";
    private static final String DEFAULT_MEMO = "";
    private static final Boolean DEFAULT_CURRENT_LESSON_ATTENDANCE = false;
    private static final Integer DEFAULT_LESSONS_ATTENDED = 5;
    private static final String DEFAULT_CLASS_PARTICIPATION = "";

    private Name name;
    private Id id;
    private Memo memo;
    private CurrentLessonAttendance currentLessonAttendance;
    private LessonsAttended lessonsAttended;
    private Memo classParticipation;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public StudentBuilder() {
        name = new Name(DEFAULT_NAME);
        id = new Id(DEFAULT_ID);
        memo = new Memo(DEFAULT_MEMO);
        currentLessonAttendance = new CurrentLessonAttendance(DEFAULT_CURRENT_LESSON_ATTENDANCE);
        lessonsAttended = new LessonsAttended(DEFAULT_LESSONS_ATTENDED);
        classParticipation = new Memo(DEFAULT_CLASS_PARTICIPATION);
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public StudentBuilder(Student personToCopy) {
        name = personToCopy.getName();
        id = personToCopy.getId();
        memo = personToCopy.getMemo();
        currentLessonAttendance = personToCopy.getCurrentAttendance();
        lessonsAttended = personToCopy.getLessonsAttended();
        classParticipation = personToCopy.getClassParticipation();
    }

    /**
     * Sets the {@code Name} of the {@code Student} that we are building.
     */
    public StudentBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Id} of the {@code Student} that we are building.
     */
    public StudentBuilder withId(String id) {
        this.id = new Id(id);
        return this;
    }

    /**
     * Sets the {@code Memo} of the {@code Student} that we are building.
     */
    public StudentBuilder withMemo(String memo) {
        this.memo = new Memo(memo);
        return this;
    }

    /**
     * Sets the {@code CurrentLessonAttendance} of the {@code Student} that we are building.
     */
    public StudentBuilder withCurrentLessonAttendance(Boolean isPresent) {
        this.currentLessonAttendance = new CurrentLessonAttendance(isPresent);
        return this;
    }

    /**
     * Sets the {@code LessonsAttended} of the {@code Student} that we are building.
     */
    public StudentBuilder withLessonsAttended(Integer lessonsAttended) {
        this.lessonsAttended = new LessonsAttended(lessonsAttended);
        return this;
    }

    /**
     * Sets the {@code ClassParticipation} of the {@code Student} that we are building.
     */
    public StudentBuilder withClassParticipation(String classParticipation) {
        this.classParticipation = new Memo(classParticipation);
        return this;
    }

    public Student build() {
        return new Student(name, id, memo, currentLessonAttendance, lessonsAttended, classParticipation);
    }

}
