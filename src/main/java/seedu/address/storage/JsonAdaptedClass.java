package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.module.Class;
import seedu.address.model.module.ClassName;

/**
 * Jackson-friendly version of {@link Class}.
 */
class JsonAdaptedClass {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Class' %s field is missing!";

    private final String className;

    /**
     * Constructs a {@code JsonAdaptedClass} with the given class details.
     */
    @JsonCreator
    public JsonAdaptedClass(@JsonProperty("className") String className) {
        this.className = className;
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedClass(Class source) {
        className = source.getClassName().className;
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Class toModelType() throws IllegalValueException {

        if (className == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ClassName.class.getSimpleName()));
        }
        if (!ClassName.isValidClassName(className)) {
            throw new IllegalValueException(ClassName.MESSAGE_CONSTRAINTS);
        }
        final ClassName modelClassName = new ClassName(className);

        return new Class(modelClassName);
    }

}
