package seedu.address.model.util;

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
//            new Student(new Name("Alex Yeoh")),
//            new Student(new Name("Bernice Yu")),
//            new Student(new Name("Charlotte Oliveiro")),
//            new Student(new Name("David Li")),
//            new Student(new Name("Irfan Ibrahim")),
//            new Student(new Name("Roy Balakrishnan"))
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
