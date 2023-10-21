package seedu.address.model.module;

import seedu.address.commons.core.index.Index;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Objects;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
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

    /**
     * Obtains the student based on the index in the UniqueStudentList of this class.
     *
     * @param targetStudentIndex The index of the student to retrieve
     * @return Student The student object corresponding to the indexStudentIndex
     * @throws CommandException If an error occurs during command execution.
     */
    public Student getStudentInClass(Index targetStudentIndex) throws CommandException {
        List<Student> studentList = this.getStudentList();

        if (studentList.size() == 0) {
            throw new CommandException(Messages.MESSAGE_EMPTY_STUDENT_LIST);
        }
        if (targetStudentIndex.getZeroBased() >= studentList.size()) {
            throw new CommandException(Messages.MESSAGE_INDEX_INPUT_TOO_LARGE);
        }
        return studentList.get(targetStudentIndex.getZeroBased());
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
                .toString();
    }

}
