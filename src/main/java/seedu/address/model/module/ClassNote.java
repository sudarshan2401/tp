package seedu.address.model.module;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Class note in EduTrack.
 * Guarantees: immutable
 */

public class ClassNote {
    public final String classNote;

    /**
     * Constructs a {@code Name}.
     *
     * @param note A note.
     */
    public ClassNote(String note) {
        requireNonNull(note);
        classNote = note;
    }

    @Override
    public String toString() {
        return classNote;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ClassNote)) {
            return false;
        }

        ClassNote otherClassNote = (ClassNote) other;
        return classNote.equals(otherClassNote.classNote);
    }

    @Override
    public int hashCode() {
        return classNote.hashCode();
    }

}
