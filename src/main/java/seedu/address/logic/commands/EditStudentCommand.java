package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLASS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.common.Note;
import seedu.address.model.module.Class;
import seedu.address.model.student.Address;
import seedu.address.model.student.Email;
import seedu.address.model.student.Id;
import seedu.address.model.student.Name;
import seedu.address.model.student.Phone;
import seedu.address.model.student.Student;

/**
 * Edits the details of an existing student in EduTrack.
 */
public class EditStudentCommand extends Command {
    public static final String COMMAND_WORD = "edit /s";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the records of the student identified "
            + "by the index number used in the displayed student list of a specific class. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: /s INDEX (must be a positive integer), "
            + "/c CLASS_NAME (must be the exact class name)"
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_NOTE + "NOTE] "
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_CLASS + "CS2103T"
            + PREFIX_NAME + "John Doe "
            + PREFIX_NOTE + "Mischevious.";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Student: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_STUDENT = "This person already exists in the address book.";

    private final Index studentIndex;
    private final Class studentClass;

    private final EditStudentCommand.EditStudentDescriptor editStudentDescriptor;

    /**
     * @param studentIndex of the person in the class's student list to edit
     * @param editStudentDescriptor details to edit the student with
     */
    public EditStudentCommand(Index studentIndex, Class studentClass,
                              EditStudentCommand.EditStudentDescriptor editStudentDescriptor) {
        requireNonNull(studentIndex);
        requireNonNull(editStudentDescriptor);

        this.studentIndex = studentIndex;
        this.studentClass = studentClass;
        this.editStudentDescriptor = new EditStudentCommand.EditStudentDescriptor(editStudentDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> lastShownList = model.getFilteredStudentList();

        if (studentIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Student studentToEdit = lastShownList.get(studentIndex.getZeroBased());
        Student editedStudent = createEditedStudent(studentToEdit, editStudentDescriptor);

        if (!studentToEdit.isSameStudent(editedStudent) && studentClass.hasStudentInClass(editedStudent)) {
            throw new CommandException(MESSAGE_DUPLICATE_STUDENT);
        }

        model.setStudent(studentToEdit, editedStudent);
        model.setStudentInClass(studentToEdit, editedStudent, studentClass);
        model.updateFilteredStudentList((s) -> studentClass.getStudentList().contains(s));
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, Messages.formatStudent(editedStudent)));
    }

    /**
     * Creates and returns a {@code Student} with the details of {@code studentToEdit}
     * edited with {@code editStudentDescriptor}.
     */
    private static Student createEditedStudent(Student studentToEdit,
                                               EditStudentCommand.EditStudentDescriptor editStudentDescriptor) {
        assert studentToEdit != null;

        Name updatedName = editStudentDescriptor.getName().orElse(studentToEdit.getName());
        Id updatedId = editStudentDescriptor.getId().orElse(studentToEdit.getId());
        Note updatedNote = editStudentDescriptor.getNote().orElse(studentToEdit.getNote());

        return new Student(updatedName, updatedId, updatedNote);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditStudentCommand)) {
            return false;
        }

        EditStudentCommand otherEditStudentCommand = (EditStudentCommand) other;
        return this.studentIndex.equals(otherEditStudentCommand.studentIndex)
                && this.studentClass.equals(otherEditStudentCommand.studentClass)
                && this.editStudentDescriptor.equals(otherEditStudentCommand.editStudentDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("studentIndex", studentIndex)
                .add("studentClass", studentClass)
                .add("editStudentDescriptor", editStudentDescriptor)
                .toString();
    }


    /**
     * Stores the details to edit the person with. Each non-empty field value will
     * replace the
     * corresponding field value of the person.
     */
    public static class EditStudentDescriptor {
        private Name name;
        private Id id;
        private Note note;

        public EditStudentDescriptor() {
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditStudentDescriptor(EditStudentCommand.EditStudentDescriptor toCopy) {
            setName(toCopy.name);
            setId(toCopy.id);
            setNote(toCopy.note);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, id, note);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setId(Id id) {
            this.id = id;
        }

        public Optional<Id> getId() {
            return Optional.ofNullable(id);
        }


        public void setNote(Note note) {
            this.note = note;
        }

        public Optional<Note> getNote() {
            return Optional.ofNullable(note);
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditStudentCommand.EditStudentDescriptor)) {
                return false;
            }

            EditStudentCommand.EditStudentDescriptor otherEditStudentDescriptor = (EditStudentCommand.EditStudentDescriptor) other;
            return Objects.equals(name, otherEditStudentDescriptor.name)
                    && Objects.equals(id, otherEditStudentDescriptor.id)
                    && Objects.equals(note, otherEditStudentDescriptor.note);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("name", name)
                    .add("id", id)
                    .add("note", note)
                    .toString();
        }
    }
}
