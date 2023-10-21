package seedu.address.logic.commands;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Class;
import seedu.address.model.module.ClassName;
import seedu.address.model.module.ClassNote;
import seedu.address.model.student.UniqueStudentList;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLASS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE;

import static seedu.address.model.Model.PREDICATE_SHOW_ALL_CLASSES;

public class UpdateClassCommand extends Command {
    public static final String COMMAND_WORD = "edit /c";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the class identified "
            + "by the index number used in the displayed class list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_NOTE + "NOTE] "
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_NAME + "91234567 "
            + PREFIX_NOTE + "johndoe@example.com";

    public static final String MESSAGE_EDIT_CLASS_SUCCESS = "Edited Class: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_CLASS = "Class already exists";

    private final Index index;
    private final EditClassDescriptor editClassDescriptor;

    /**
     * @param index                of the person in the filtered person list to edit
     * @param editClassDescriptor details to edit the person with
     */
    public UpdateClassCommand(Index index, EditClassDescriptor editClassDescriptor) {
        requireNonNull(index);
        requireNonNull(editClassDescriptor);

        this.index = index;
        this.editClassDescriptor = new EditClassDescriptor(editClassDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Class> lastShownList = model.getFilteredClassList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CLASS_DISPLAYED_INDEX);
        }

        Class classToEdit = lastShownList.get(index.getZeroBased());
        UniqueStudentList currentStudentList = classToEdit.getUniqueStudentList();

        Class editedClass = createEditedClass(classToEdit, editClassDescriptor, currentStudentList);

        if (!classToEdit.isSameClass(editedClass) && model.hasClass(editedClass)) {
            throw new CommandException(MESSAGE_DUPLICATE_CLASS);
        }

        model.setClass(index, editedClass);
        model.updateFilteredClassList(PREDICATE_SHOW_ALL_CLASSES);
        return new CommandResult(String.format(MESSAGE_EDIT_CLASS_SUCCESS, Messages.formatClass(editedClass)));
    }

    /**
     * Creates and returns a {@code Class} with the details of {@code classToEdit}
     * edited with {@code editClassDescriptor}.
     */
    private static Class createEditedClass(Class classToEdit, EditClassDescriptor editClassDescriptor, UniqueStudentList studentList) {
        assert classToEdit != null;

        ClassName updatedClassName = editClassDescriptor.getClassName().orElse(classToEdit.getClassName());

        return new Class(updatedClassName, studentList);
    }

    /**
     * Stores the details to edit the class with. Each non-empty field value will
     * replace the corresponding field value of the class.
     */
    public static class EditClassDescriptor {
        private ClassName className;
        //private ClassNote classNote;

        public EditClassDescriptor() {
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditClassDescriptor(EditClassDescriptor toCopy) {
            setClassName(toCopy.className);
            //setClassNote(toCopy.classNote);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(className);
        }

        public void setClassName(ClassName className) {
            this.className = className;
        }

        public Optional<ClassName> getClassName() {
            return Optional.ofNullable(className);
        }


        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditClassDescriptor)) {
                return false;
            }

            EditClassDescriptor otherEditClassDescriptor = (EditClassDescriptor) other;
            return Objects.equals(className, otherEditClassDescriptor.className);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("className", className)
                    //.add("classNote", classNote)
                    .toString();
        }
    }
}
