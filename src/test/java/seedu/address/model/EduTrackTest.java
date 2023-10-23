package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalStudents.ALICE;
import static seedu.address.testutil.TypicalStudents.getTypicalEduTrack;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.module.Class;
import seedu.address.model.module.ClassName;
import seedu.address.model.student.Name;
import seedu.address.model.student.Student;
import seedu.address.model.student.exceptions.DuplicateStudentException;
import seedu.address.testutil.StudentBuilder;

public class EduTrackTest {

    private final EduTrack eduTrack = new EduTrack();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), eduTrack.getStudentList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> eduTrack.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        EduTrack newData = getTypicalEduTrack();
        eduTrack.resetData(newData);
        assertEquals(newData, eduTrack);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Student editedAlice = new StudentBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();

        List<Student> newPersons = Arrays.asList(ALICE, editedAlice);
        EduTrackStub newData = new EduTrackStub(newPersons);

        assertThrows(DuplicateStudentException.class, () -> eduTrack.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> eduTrack.hasStudent(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(eduTrack.hasStudent(ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        eduTrack.addStudent(ALICE);
        assertTrue(eduTrack.hasStudent(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInAddressBook_returnsTrue() {

        eduTrack.addStudent(ALICE);
        Student editedAlice = new StudentBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(eduTrack.hasStudent(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> eduTrack.getStudentList().remove(0));
    }

    @Test
    public void toStringMethod() {
        String expected = EduTrack.class.getCanonicalName() + "{students=" + eduTrack.getStudentList() + "}";
        assertEquals(expected, eduTrack.toString());
    }

    @Test
    public void addStudent_listSizeIncreasesByOne_returnsTrue() {
        EduTrack newData = getTypicalEduTrack();
        eduTrack.resetData(newData);
        int originalSize = eduTrack.getStudentList().size();
        Name studentNameStub = new Name("studentNameStub");
        Student studentStub = new Student(studentNameStub);
        eduTrack.addStudent(studentStub);
        int newSize = eduTrack.getStudentList().size();
        int diff = newSize - originalSize;
        assertEquals(diff, 1);
    }

    // This test needs to be refined but just putting here for now to increase coverage to push PR
    // for others to start working.
    @Test
    public void removeStudent_listSizeDecreasesByOne_returnsTrue() {
        EduTrack newData = getTypicalEduTrack();
        eduTrack.resetData(newData);
        int originalSize = eduTrack.getStudentList().size();
        Name studentNameStub = new Name("studentNameStub");
        Student studentStub = new Student(studentNameStub);
        eduTrack.addStudent(studentStub);
        eduTrack.removeStudent(studentStub);
        int newSize = eduTrack.getStudentList().size();
        int diff = newSize - originalSize;
        assertEquals(diff, 0);
    }

    @Test
    public void getClass_eduTrackWithNoClass_returnsFalse() {
        EduTrack newData = getTypicalEduTrack();
        eduTrack.resetData(newData);
        ClassName classNameStub = new ClassName("classNameStub");
        assertEquals(eduTrack.getClass(classNameStub), null);
    }
    /**
     * A stub ReadOnlyAddressBook whose persons list can violate interface
     * constraints.
     */

    private static class EduTrackStub implements ReadOnlyEduTrack {
        private final ObservableList<Student> students = FXCollections.observableArrayList();

        private final ObservableList<Class> classes = FXCollections.observableArrayList();

        EduTrackStub(Collection<Student> students) {
            this.students.setAll(students);
        }

        @Override
        public ObservableList<Student> getStudentList() {
            return students;
        }

        @Override
        public ObservableList<Class> getClassList() {
            return classes;
        }
    }

}
