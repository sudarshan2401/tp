package seedu.address.testutil;

import seedu.address.model.student.Address;
import seedu.address.model.student.CurrentLessonAttendance;
import seedu.address.model.student.Email;
import seedu.address.model.student.LessonsAttended;
import seedu.address.model.student.Name;
import seedu.address.model.student.Phone;
import seedu.address.model.student.Student;

/**
 * A utility class to help with building Person objects.
 */
public class StudentBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final Boolean DEFAULT_CURRENT_LESSON_ATTENDANCE = false;
    public static final Integer DEFAULT_LESSONS_ATTENDED = 5;

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private CurrentLessonAttendance currentLessonAttendance;
    private LessonsAttended lessonsAttended;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public StudentBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        currentLessonAttendance = new CurrentLessonAttendance(DEFAULT_CURRENT_LESSON_ATTENDANCE);
        lessonsAttended = new LessonsAttended(DEFAULT_LESSONS_ATTENDED);
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public StudentBuilder(Student personToCopy) {
        name = personToCopy.getName();
    }

    /**
     * Sets the {@code Name} of the {@code Student} that we are building.
     */
    public StudentBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the
     * {@code Person} that we are building.
     */
    public StudentBuilder withTags(String... tags) {
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Student} that we are building.
     */
    public StudentBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Student} that we are building.
     */
    public StudentBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Student} that we are building.
     */
    public StudentBuilder withEmail(String email) {
        this.email = new Email(email);
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

    public Student build() {
        return new Student(name);
    }

}
