package seedu.address.testutil;

import seedu.address.model.EduTrack;
import seedu.address.model.person.Person;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private EduTrack eduTrack;

    public AddressBookBuilder() {
        eduTrack = new EduTrack();
    }

    public AddressBookBuilder(EduTrack eduTrack) {
        this.eduTrack = eduTrack;
    }

    /**
     * Adds a new {@code Person} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withPerson(Person person) {
        eduTrack.addPerson(person);
        return this;
    }

    public EduTrack build() {
        return eduTrack;
    }
}
