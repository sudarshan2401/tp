package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.EduTrack;
import seedu.address.model.ReadOnlyEduTrack;
import seedu.address.model.module.Class;
import seedu.address.model.person.Person;

/**
 * An Immutable EduTrack that is serializable to JSON format.
 */
@JsonRootName(value = "edutrack")
class JsonSerializableEduTrack {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";

    public static final String MESSAGE_DUPLICATE_CLASS = "Class list contains duplicate classes.";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();

    private final List<JsonAdaptedClass> classes = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableEduTrack} with the given persons.
     */
    @JsonCreator
    public JsonSerializableEduTrack(@JsonProperty("persons") List<JsonAdaptedPerson> persons,
                                    @JsonProperty("classes") List<JsonAdaptedClass> classes) {
        this.persons.addAll(persons);
        this.classes.addAll(classes);
    }

    /**
     * Converts a given {@code ReadOnlyEduTrack} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableEduTrack}.
     */
    public JsonSerializableEduTrack(ReadOnlyEduTrack source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
        classes.addAll(source.getClassList().stream().map(JsonAdaptedClass::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code EduTrack} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public EduTrack toModelType() throws IllegalValueException {
        EduTrack eduTrack = new EduTrack();
        for (JsonAdaptedClass jsonAdaptedClass : classes) {
            Class c = jsonAdaptedClass.toModelType();
            if (eduTrack.hasClass(c)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_CLASS);
            }
            eduTrack.addClass(c);
        }
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (eduTrack.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            eduTrack.addPerson(person);
        }
        return eduTrack;
    }

}
