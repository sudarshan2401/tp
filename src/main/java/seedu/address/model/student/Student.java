package seedu.address.model.student;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.student.exceptions.StudentAlreadyMarkedAbsent;
import seedu.address.model.student.exceptions.StudentAlreadyMarkedPresent;


/**
 * Represents a Student in the address book.
 * Guarantees: details are present and not null, field values are validated,
 * immutable.
 */
public class Student {

    // Identity fields
    private final Name name;

    // Data fields
    // The current lesson's attendance (Present/Absent)
    private CurrentLessonAttendance currentLessonAttendance;
    // Cumulative number of lessons attended
    private LessonsAttended lessonsAttended;

    /**
     * Every field must be present and not null.
     */
    public Student(Name name) {
        requireAllNonNull(name);
        this.name = name;
        this.currentLessonAttendance = new CurrentLessonAttendance(false);
        this.lessonsAttended = new LessonsAttended();
    }

    /**
     * Every field must be present and not null.
     *
     * Mainly used for retrieving data from storage
     */
    public Student(Name name, CurrentLessonAttendance currentLessonAttendance,
                   LessonsAttended lessonsAttended) {
        requireAllNonNull(name);
        this.name = name;
        this.currentLessonAttendance = currentLessonAttendance;
        this.lessonsAttended = lessonsAttended;
    }

    public Name getName() {
        return name;
    }
    public CurrentLessonAttendance getCurrentAttendance() {
        return this.currentLessonAttendance;
    }
    public LessonsAttended getLessonsAttended() {
        return this.lessonsAttended;
    }

    /**
     * Returns true if both students have the same name.
     * This defines a weaker notion of equality between two students.
     */
    public boolean isSameStudent(Student otherStudent) {
        if (otherStudent == this) {
            return true;
        }

        return otherStudent != null
                && otherStudent.getName().equals(getName());
    }

    /**
     * Creates a duplicate object of this student.
     *
     * @return Student - Duplicate student that lives on a different part of memory
     */
    public Student duplicateStudent() {
        return new Student(new Name(this.name.fullName),
                new CurrentLessonAttendance(this.currentLessonAttendance.getIsPresent()),
                new LessonsAttended(this.lessonsAttended.getTotalLessons()));
    }

    /**
     * Marks a student as present for the current lesson.
     *
     * @throws StudentAlreadyMarkedPresent If the Student has already been marked present
     */
    public void markStudentPresent() throws StudentAlreadyMarkedPresent {
        this.currentLessonAttendance.setPresent();
        this.lessonsAttended.incrementLessons();
    }

    /**
     * Marks a student as absent for the current lesson.
     *
     * @throws StudentAlreadyMarkedAbsent If the Student has already been marked absent
     */
    public void markStudentAbsent() throws StudentAlreadyMarkedAbsent {
        this.currentLessonAttendance.setAbsent();
        this.lessonsAttended.decrementLessons();
    }

    /**
     * Returns true if both students have the same identity and data fields.
     * This defines a stronger notion of equality between two students.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Student)) {
            return false;
        }

        Student otherStudent = (Student) other;
        return name.equals(otherStudent.name)
                && currentLessonAttendance.equals(otherStudent.currentLessonAttendance)
                && lessonsAttended.equals(otherStudent.lessonsAttended);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name);
    }

    /**
     * Obtain String representation of the attendance of this Student.
     *
     * @return String - Y - Present current class. N - Absent for current class
     */
    public String getAttendanceStringRep() {
        return this.currentLessonAttendance.toString();
    }

    /**
     * Obtain String representation of the total number of lessons attended this Student attended.
     *
     * @return String - String representation of a integer
     */
    public String getTotalAttendanceStringRep() {
        return this.lessonsAttended.toString();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .toString();
    }

}
