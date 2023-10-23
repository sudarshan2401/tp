package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Class;
import seedu.address.model.module.exceptions.ClassNotFoundException;

/**
 * Deletes a Class identified using index from the EduTrack.
 */
public class RemoveClassCommand extends Command {

    public static final String COMMAND_WORD = "remove /c";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the class identified by the index number used in the displayed class list.\n"
            + "Parameters: CLASSINDEX \n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_REMOVE_CLASS_SUCCESS = "Deleted Class: %1$s";
    public static final String MESSAGE_MISSING_CLASS_INDEX = "The index (%1$s) you provided does not exist!";
    public static final String MESSAGE_MISSING_CLASS_NAME = "The Class name (%s) you provided does not exist!";

    private final Index targetClassIndex;

    /**
     * Constructor for the Remove Class Command.
     *
     * @param classIndex Index to remove from the Class unique list
     */
    public RemoveClassCommand(Index classIndex) {
        requireNonNull(classIndex);
        this.targetClassIndex = classIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Class classToRemove = model.retrieveClass(targetClassIndex);

        try {
            model.removeClass(classToRemove);
        } catch (ClassNotFoundException e) {
            throw new CommandException(MESSAGE_MISSING_CLASS_INDEX);
        }
        return new CommandResult(String.format(MESSAGE_REMOVE_CLASS_SUCCESS, Messages.formatClass(classToRemove)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RemoveClassCommand)) {
            return false;
        }

        RemoveClassCommand otherRemoveClassCommand = (RemoveClassCommand) other;
        return this.targetClassIndex.equals(otherRemoveClassCommand.targetClassIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("classIndex", this.targetClassIndex)
                .toString();
    }
}
