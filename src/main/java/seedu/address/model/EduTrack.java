package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.module.Class;
import seedu.address.model.module.ClassName;
import seedu.address.model.module.UniqueClassList;
import seedu.address.model.module.exceptions.ClassNotFoundException;
import seedu.address.model.student.Student;
import seedu.address.model.student.UniqueStudentList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class EduTrack implements ReadOnlyEduTrack {



    /*
     * The 'unusual' code block below is a non-static initialization block,
     * sometimes used to avoid duplication
     * between constructors. See
     * https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other
     * ways to avoid duplication
     * among constructors.
     */
    private final UniqueStudentList students;

    {
        students = new UniqueStudentList();
    }


    private final UniqueClassList classes;

    {
        classes = new UniqueClassList();
    }

    public EduTrack() {}

    /**
     * Creates an EduTrack using the Students in the {@code toBeCopied}
     */
    public EduTrack(ReadOnlyEduTrack toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the student list with {@code students}.
     * {@code students} must not contain duplicate students.
     */
    public void setStudents(List<Student> students) {
        this.students.setStudents(students);
    }

    /**
     * Replaces the contents of the class list with {@code classes}.
     * {@code classess} must not contain duplicate class.
     */
    public void setClasses(List<Class> classes) {
        this.classes.setClasses(classes);
    }

    /**
     * Resets the existing data of this {@code EduTrack} with {@code newData}.
     */
    public void resetData(ReadOnlyEduTrack newData) {
        requireNonNull(newData);
        setStudents(newData.getStudentList());
        setClasses(newData.getClassList());
    }

    //// student-level operations

    /**
     * Returns true if a student with the same identity as {@code student} exists in
     * the address book.
     */
    public boolean hasStudent(Student student) {
        requireNonNull(student);
        return students.contains(student);
    }

    /**
     * Adds a student to the address book.
     * The student must not already exist in the address book.
     */
    public void addStudent(Student p) {
        students.add(p);
    }

    /**
     * Replaces the given student {@code target} in the list with
     * {@code editedStudent}.
     * {@code target} must exist in the address book.
     * The student identity of {@code editedStudent} must not be the same as another
     * existing student in the address book.
     */
    public void setStudent(Student target, Student editedStudent) {
        requireNonNull(editedStudent);

        students.setStudent(target, editedStudent);
    }

    /**
     * Removes {@code key} from this {@code EduTrack}.
     * {@code key} must exist in the address book.
     */
    public void removeStudent(Student key) {
        students.remove(key);
    }
    /**
     * Adds a class to EduTrack.
     * The student must not already exist in EduTrack.
     */
    public void addClass(Class c) {
        classes.add(c);
    }

    /**
     * Returns true if a class with the same identity as {@code c} exists in EduTrack.
     */
    public boolean hasClass(Class c) {
        requireNonNull(c);
        return classes.contains(c);
    }

    /**
     * Removes a class from EduTrack.
     * The class must exist in EduTrack
     */
    public void removeClass(Class c) throws ClassNotFoundException {
        this.classes.remove(c);
    }

    public Class getClass(ClassName className) {
        for (Class c : classes) {
            System.out.println(c.toString());
            if (c.getClassName().equals(className)) {
                return c;
            }
        }
        // if no matching className, class do not exist in unique class list
        return null;
    }

    public Class getClassByIndex(Index classIndex) {
        requireNonNull(classIndex);
        return classes.get(classIndex);
    }

    public int getClassListSize() {
        return classes.size();
    }
    //// util methods

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("students", students)
                .toString();
    }

    @Override
    public ObservableList<Student> getStudentList() {
        return students.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Class> getClassList() {
        return classes.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EduTrack)) {
            return false;
        }

        EduTrack otherEduTrack = (EduTrack) other;
        return students.equals(otherEduTrack.students);
    }

    @Override
    public int hashCode() {
        return students.hashCode();
    }
}
