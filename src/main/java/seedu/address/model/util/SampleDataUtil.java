package seedu.address.model.util;

import seedu.address.commons.core.index.Index;
import seedu.address.model.EduTrack;
import seedu.address.model.ReadOnlyEduTrack;
import seedu.address.model.student.Name;
import seedu.address.model.student.Student;


/**
 * Contains utility methods for populating {@code EduTrack} with sample data.
 */
public class SampleDataUtil {
    public static Student[] getSamplePersons() {
        return new Student[] {
            new Student(new Name("Alex Yeoh"), Index.fromZeroBased(0)),
            new Student(new Name("Bernice Yu"), Index.fromZeroBased(0)),
            new Student(new Name("Charlotte Oliveiro"), Index.fromZeroBased(0)),
            new Student(new Name("David Li"), Index.fromZeroBased(0)),
            new Student(new Name("Irfan Ibrahim"), Index.fromZeroBased(0)),
            new Student(new Name("Roy Balakrishnan"), Index.fromZeroBased(0))
        };
    }

    public static ReadOnlyEduTrack getSampleEduTrack() {
        EduTrack sampleAb = new EduTrack();
        for (Student samplePerson : getSamplePersons()) {
            sampleAb.addStudent(samplePerson);
        }
        return sampleAb;
    }

}
