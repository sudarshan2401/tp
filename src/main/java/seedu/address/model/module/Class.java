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

    /**
     * Constructs a {@code Class} object.
     *
     * @param className The name of the class. Must not be null.
     */
    public Class(ClassName className, UniqueStudentList students) {
        requireNonNull(className);
        this.className = className;
        this.students = students;
    }


    public ClassName getClassName() {
        return className;
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
     *
     * @param student The student.
     * @return True if student is in class, false otherwise.
     */
    public boolean hasStudentInClass(Student student) {
        requireNonNull(student);
        return students.contains(student);
    }

    /**
     * Replaces the given student {@code target} in the list with
     * {@code editedStudent}.
     * {@code target} must exist in the class
     * The student identity of {@code editedStudent} must not be the same as another
     * existing student in the class
     */
    public void setStudent(Student target, Student editedStudent) {
        requireNonNull(editedStudent);

        students.setStudent(target, editedStudent);
    }



    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("class name", className)
                .toString();
    }

}
