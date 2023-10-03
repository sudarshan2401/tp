package seedu.address.model.module;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class ClassName {
    public static final String MESSAGE_CONSTRAINTS =
            "Class name is compulsory.\n"
            + "Class name should only contains alphanumeric characters, and it should not contain spaces.";

    public static final String VALIDATION_REGEX = "^[a-zA-Z0-9]*$";

    public final String className;

    public ClassName(String className) {
        requireNonNull(className);
        checkArgument(isValidClassName(className), MESSAGE_CONSTRAINTS);
        this.className = className.toUpperCase();
    }

    public static boolean isValidClassName(String test) {
        return !test.trim().isEmpty() && test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return className;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ClassName)) {
            return false;
        }

        ClassName otherName = (ClassName) other;
        return className.equals(otherName.className);
    }

    @Override
    public int hashCode() {
        return className.hashCode();
    }

}
