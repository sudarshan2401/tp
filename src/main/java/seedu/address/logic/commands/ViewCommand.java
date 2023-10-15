package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Class;
import seedu.address.model.student.Student;

/**
 * Lists all students in the specified class to the user.
 */
public class ViewCommand extends Command {
    public static final String COMMAND_WORD = "view /c";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists all students in the specified class.\n"
            + "Parameters: CLASS_INDEX\n"
            + "Example: " + COMMAND_WORD + " 2";

    public static final String MESSAGE_SUCCESS = "Listed all students in %1$s";

    public static final String MESSAGE_INVALID_CLASS_DISPLAYED_INDEX = "The class index provided is invalid";

    private final Index classIndex;

    /**
     * Creates an ViewCommand to list the students in the specified class.
     */
    public ViewCommand(Index classIndex) {
        requireNonNull(classIndex);
        this.classIndex = classIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        // get the class object and get the unique student list
        if (classIndex.getZeroBased() >= model.getClassListSize()) {
            throw new CommandException(MESSAGE_INVALID_CLASS_DISPLAYED_INDEX);
        }

        Class classToView = model.getClassByIndex(classIndex);
        model.updateFilteredStudentList(new Predicate<Student>() {
            @Override
            public boolean test(Student student) {
                return classToView.getStudentList().contains(student);
            }
        });
        return new CommandResult(String.format(MESSAGE_SUCCESS, classToView.getClassName()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewCommand // instanceof handles nulls
                && classIndex.equals(((ViewCommand) other).classIndex)); // state check
    }

    @Override
    public String toString() {
        return new ToStringBuilder("ViewCommand")
                .add("classIndex", classIndex)
                .toString();
    }
}
