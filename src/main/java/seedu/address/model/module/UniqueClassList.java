package seedu.address.model.module;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import seedu.address.model.module.exceptions.DuplicateClassException;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

public class UniqueClassList implements Iterable<Class> {

    private final ObservableList<Class> internalList = FXCollections.observableArrayList();
    private final ObservableList<Class> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    public boolean contains(Class toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameClass);
    }

    public void add(Class toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateClassException();
        }
        internalList.add(toAdd);
    }

    public void setClasses(List<Class> classes) {
        requireAllNonNull(classes);
        if (!classesAreUnique(classes)) {
            throw new DuplicateClassException();
        }

        internalList.setAll(classes);
    }

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

    private boolean classesAreUnique(List<Class> classes) {
        for (int i = 0; i < classes.size() - 1; i++) {
            for (int j = i + 1; j < classes.size(); j++) {
                if (classes.get(i).isSameClass(classes.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

}
