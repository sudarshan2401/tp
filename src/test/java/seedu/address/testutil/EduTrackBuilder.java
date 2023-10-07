package seedu.address.testutil;

import seedu.address.model.EduTrack;
import seedu.address.model.person.Person;

/**
 * A utility class to help with building EduTrack objects.
 * Example usage: <br>
 *     {@code EduTrack ab = new EduTrackBuilder().withPerson("John", "Doe").build();}
 */
public class EduTrackBuilder {

    private EduTrack eduTrack;

    public EduTrackBuilder() {
        eduTrack = new EduTrack();
    }

    public EduTrackBuilder(EduTrack eduTrack) {
        this.eduTrack = eduTrack;
    }

    /**
     * Adds a new {@code Person} to the {@code EduTrack} that we are building.
     */
    public EduTrackBuilder withPerson(Person person) {
        eduTrack.addPerson(person);
        return this;
    }

    public EduTrack build() {
        return eduTrack;
    }
}
