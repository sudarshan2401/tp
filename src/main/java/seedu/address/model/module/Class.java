package seedu.address.model.module;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Objects;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.student.Student;
import seedu.address.model.student.UniqueStudentList;

/**
 * Represents a Class in EduTrack.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Class {
    private final ClassName className;
    private final UniqueStudentList students;

    // private final ClassNote classNote;
    // total lessons

    /**
     * Constructs a {@code Class} object.
     *
     * @param className The name of the class. Must not be null.
     */
    public Class(ClassName className, UniqueStudentList students) {
        requireNonNull(className);
        this.className = className;
        this.students = students;
        //this.classNote = new ClassNote("");
    }


    public ClassName getClassName() {
        return className;
    }
//    public ClassNote getClassNote() {
//        return classNote;
//    }
    public UniqueStudentList getUniqueStudentList() {
        return students;
    }

    /**
     * Checks if the given class is the same as this class.
     *
     * @param otherClass The other class to compare with.
     * @return True if the classes have the same class name, false otherwise.
     */
    public boolean isSameClass(Class otherClass) {
        if (otherClass == this) {
            return true;
        }

        return otherClass != null
                && otherClass.getClassName().equals(getClassName());
    }

    public void addStudentToClass(Student toAdd) {
        students.add(toAdd);
    }

    public void removeStudentFromClass(Student s) {
        students.remove(s);
    }
    public ObservableList<Student> getStudentList() {
        return students.asUnmodifiableObservableList();
    }

    public void setStudents(List<Student> students) {
        this.students.setStudents(students);
    }
    /**
     * Checks if this class is equal to another object.
     *
     * @param other The object to compare with.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Class)) {
            return false;
        }

        Class otherClass = (Class) other;
        return className.equals(otherClass.className);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(className);
    }

    /**
     * Returns true if a student with the same identity as {@code student} exists in
     * the class.
     */
    public boolean hasStudentInClass(Student student) {
        requireNonNull(student);
        return students.contains(student);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("class name", className)
                //.add("class note", classNote)
                .toString();
    }

}
