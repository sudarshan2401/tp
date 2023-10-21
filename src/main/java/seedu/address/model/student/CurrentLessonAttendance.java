package seedu.address.model.student;

import seedu.address.model.student.exceptions.StudentAlreadyMarkedPresent;
import seedu.address.model.student.exceptions.StudentAlreadyMarkedAbsent;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Student's current lesson's attendance in the EduTrack.
 * current lesson - Active lesson at the moment as the TA is marking attendance
 */
public class CurrentLessonAttendance {

    public static final String MESSAGE_CONSTRAINTS = "Attendance should be of the format of True or False";

    public Boolean isPresent;

    /**
     * Constructs an {@code CurrentLessonAttendance}.
     *
     * @param isPresent True if the Student is present for the current lesson.
     */
    public CurrentLessonAttendance(Boolean isPresent) {
        requireNonNull(isPresent);
        this.isPresent = isPresent;
    }

    /**
     * Sets the attendance of the current student to be present.
     *
     * @throws StudentAlreadyMarkedPresent - Student already marked present
     */
    public void setPresent() throws StudentAlreadyMarkedPresent {
        if (this.isPresent) {
            throw new StudentAlreadyMarkedPresent();
        }
        this.isPresent = true;
    }

    /**
     * Sets the attendance of the current student to be absent.
     *
     * @throws StudentAlreadyMarkedAbsent - Student already marked absent
     */
    public void setAbsent() throws StudentAlreadyMarkedAbsent {
        if (!this.isPresent) {
            throw new StudentAlreadyMarkedAbsent();
        }
        this.isPresent = false;
    }

    @Override
    public String toString() {
        return isPresent ? "Y" : "N";
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CurrentLessonAttendance)) {
            return false;
        }

        CurrentLessonAttendance otherAttendance = (CurrentLessonAttendance) other;
        return this.isPresent == otherAttendance.isPresent;
    }

    @Override
    public int hashCode() {
        return this.isPresent.hashCode();
    }

}
