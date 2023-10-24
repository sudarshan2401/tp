package seedu.address.model.student;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;

/**
 * Represents a Student in the address book.
 * Guarantees: details are present and not null, field values are validated,
 * immutable.
 */
public class Student {

    // Identity fields
    private final Name name;
    private final Index classIndex;

    // Data fields



    /**
     * Every field must be present and not null.
     */
    public Student(Name name, Index classIndex) {
        requireAllNonNull(name);
        this.name = name;
        this.classIndex = classIndex;
    }

    /**
     * Returns the Student's name.
     * @return Name of Student.
     */
    public Name getName() {
        return name;
    }
    public Index getClassIndex() {
        return classIndex;
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
        return name.equals(otherStudent.name);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .toString();
    }

}
