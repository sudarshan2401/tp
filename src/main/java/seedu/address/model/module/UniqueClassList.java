package seedu.address.model.module;

import static java.util.Objects.requireNonNull;

import java.util.Iterator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.module.exceptions.ClassNotFoundException;
import seedu.address.model.module.exceptions.DuplicateClassException;

/**
 * A list of classes that enforces uniqueness between its elements and does not allow nulls.
 * A class is considered unique by comparing using {@code Class#isSameClass(Class)}. As such, adding and updating of
 * classes uses Class#isSameClass(Class) for equality to ensure that the class being added or updated is
 * unique in terms of identity in the UniqueClassList. However, the removal of a class uses Class#equals(Object)
 * to ensure that the class with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Class#isSameClass(Class)
 */
public class UniqueClassList implements Iterable<Class> {

    private final ObservableList<Class> internalList = FXCollections.observableArrayList();
    private final ObservableList<Class> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent class as the given argument.
     *
     * @param toCheck The class to check for in the list.
     * @return True if the list contains an equivalent class, false otherwise.
     */
    public boolean contains(Class toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameClass);
    }

    /**
     * Adds a class to the list.
     * The class must not already exist in the list.
     *
     * @param toAdd The class to add.
     * @throws DuplicateClassException If adding the class would result in duplicate classes.
     */
    public void add(Class toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateClassException();
        }
        internalList.add(toAdd);
    }

    /**
     * Removes a class from the list
     * The Class must already exist in the list.
     *
     * @param toRemove The class to remove.
     * @throws ClassNotFoundException If the class to be removed is not found in the existing list.
     */
    public void remove(Class toRemove) {
        requireNonNull(toRemove);
        if (!this.contains(toRemove)) {
            throw new ClassNotFoundException();
        }
        this.internalList.remove(toRemove);
    }

    /**
     * Returns an unmodifiable view of the list of classes.
     *
     * @return An unmodifiable view of the list of classes.
     */
    public ObservableList<Class> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Class> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UniqueClassList)) {
            return false;
        }

        UniqueClassList otherUniqueClassList = (UniqueClassList) other;
        return internalList.equals(otherUniqueClassList.internalList);
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    @Override
    public String toString() {
        return internalList.toString();
    }
}
