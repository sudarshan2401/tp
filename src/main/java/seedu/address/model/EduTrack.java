package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.module.Class;
import seedu.address.model.module.UniqueClassList;
import seedu.address.model.module.exceptions.ClassNotFoundException;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class EduTrack implements ReadOnlyEduTrack {

    private final UniquePersonList persons;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
    }

    private final UniqueClassList classes;

    {
        classes = new UniqueClassList();
    }

    public EduTrack() {}

    /**
     * Creates an EduTrack using the Persons in the {@code toBeCopied}
     */
    public EduTrack(ReadOnlyEduTrack toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
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

        setPersons(newData.getPersonList());
        setClasses(newData.getClassList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addPerson(Person p) {
        persons.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        persons.setPerson(target, editedPerson);
    }

    /**
     * Removes {@code key} from this {@code EduTrack}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Person key) {
        persons.remove(key);
    }
    /**
     * Adds a class to EduTrack.
     * The person must not already exist in EduTrack.
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
     * The person must exist in EduTrack
     */
    public void removeClass(Class c) throws ClassNotFoundException {
        this.classes.remove(c);
    }

    //// util methods

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("persons", persons)
                .toString();
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
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
        return persons.equals(otherEduTrack.persons);
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }
}
