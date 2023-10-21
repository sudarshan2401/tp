package seedu.address.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.common.Memo;

/**
 * Represents a Student in the address book.
 * Guarantees: details are present and not null, field values are validated,
 * immutable.
 */
public class Student {
    // Default fields
    private final Id DEFAULT_ID = new Id("A0000000Z");
    private final Memo DEFAULT_MEMO = new Memo("");

    // Identity fields
    private final Name name;
    private final Id id;

    // Data fields
    private final Memo memo;


    /**
     * Every field must be present and not null.
     */
    public Student(Name name, Id id, Memo memo) {
        requireAllNonNull(name);
        this.name = name;
        this.id = id;
        this.memo = memo;
    }

    /**
     * If only name is provided.
     */
    public Student(Name name) {
        requireNonNull(name);
        this.name = name;
        this.id = DEFAULT_ID;
        this.memo = DEFAULT_MEMO;
    }

    public Name getName() {
        return name;
    }

    public Id getId() {
        return id;
    }

    public Memo getMemo() {
        return memo;
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
                .add("id", id)
                .add("memo", memo)
                .toString();
    }

}
