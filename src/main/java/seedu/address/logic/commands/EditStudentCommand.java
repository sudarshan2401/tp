package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLASS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEMO;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.common.Memo;
import seedu.address.model.module.Class;
import seedu.address.model.student.Id;
import seedu.address.model.student.Name;
import seedu.address.model.student.Student;

/**
 * Edits the details of an existing student in EduTrack.
 */
public class EditStudentCommand extends Command {
    public static final String COMMAND_WORD = "edit /s";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the records of the student identified "
            + "by the index used in the displayed student list of a index-specified class. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: /s STUDENT_INDEX, "
            + "/c CLASS_INDEX "
            + "[" + PREFIX_NAME + " NAME] "
            + "[" + PREFIX_ID + " STUDENT_ID] "
            + "[" + PREFIX_MEMO + " NOTE]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_CLASS + " 1"
            + PREFIX_NAME + " John Doe "
            + PREFIX_ID + " A0000000Z"
            + PREFIX_MEMO + " Mischevious.";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Student: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_STUDENT = "This person already exists in the address book.";

    private final Index studentIndex;
    private final Index studentClassIndex;

    private final EditStudentCommand.EditStudentDescriptor editStudentDescriptor;

    /**
     * @param studentIndex of the person in the class's student list to edit
     * @param editStudentDescriptor details to edit the student with
     */
    public EditStudentCommand(Index studentIndex, Index studentClassIndex,
                              EditStudentCommand.EditStudentDescriptor editStudentDescriptor) {
        requireNonNull(studentIndex);
        requireNonNull(editStudentDescriptor);

        this.studentIndex = studentIndex;
        this.studentClassIndex = studentClassIndex;
        this.editStudentDescriptor = new EditStudentCommand.EditStudentDescriptor(editStudentDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> lastShownList = model.getFilteredStudentList();
        List<Class> classList = model.getFilteredClassList();

        if (studentIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        if (studentClassIndex.getZeroBased() >= classList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CLASS_DISPLAYED_INDEX);
        }

        Class studentClass = classList.get(studentClassIndex.getZeroBased());
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
        Memo updatedMemo = editStudentDescriptor.getNote().orElse(studentToEdit.getMemo());

        return new Student(updatedName, updatedId, updatedMemo);
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
                && this.studentClassIndex.equals(otherEditStudentCommand.studentClassIndex)
                && this.editStudentDescriptor.equals(otherEditStudentCommand.editStudentDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("studentIndex", studentIndex)
                .add("studentClassIndex", studentClassIndex)
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
        private Memo memo;

        public EditStudentDescriptor() {
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditStudentDescriptor(EditStudentCommand.EditStudentDescriptor toCopy) {
            setName(toCopy.name);
            setId(toCopy.id);
            setMemo(toCopy.memo);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, id, memo);
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


        public void setMemo(Memo memo) {
            this.memo = memo;
        }

        public Optional<Memo> getNote() {
            return Optional.ofNullable(memo);
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
                    && Objects.equals(memo, otherEditStudentDescriptor.memo);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("name", name)
                    .add("id", id)
                    .add("memo", memo)
                    .toString();
        }
    }
}
