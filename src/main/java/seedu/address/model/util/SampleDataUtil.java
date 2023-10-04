package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.student.Address;
import seedu.address.model.student.Email;
import seedu.address.model.student.Name;
import seedu.address.model.student.Phone;
import seedu.address.model.student.Student;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
        public static Student[] getSamplePersons() {
                return new Student[] {
                                new Student(new Name("Alex Yeoh"),
                                                getTagSet("friends")),
                                new Student(new Name("Bernice Yu"),
                                                getTagSet("colleagues", "friends")),
                                new Student(new Name("Charlotte Oliveiro"),
                                                getTagSet("neighbours")),
                                new Student(new Name("David Li"),
                                                getTagSet("family")),
                                new Student(new Name("Irfan Ibrahim"),
                                                getTagSet("classmates")),
                                new Student(new Name("Roy Balakrishnan"),
                                                getTagSet("colleagues"))
                };
        }

        public static ReadOnlyAddressBook getSampleAddressBook() {
                AddressBook sampleAb = new AddressBook();
                for (Student samplePerson : getSamplePersons()) {
                        sampleAb.addPerson(samplePerson);
                }
                return sampleAb;
        }

        /**
         * Returns a tag set containing the list of strings given.
         */
        public static Set<Tag> getTagSet(String... strings) {
                return Arrays.stream(strings)
                                .map(Tag::new)
                                .collect(Collectors.toSet());
        }

}
