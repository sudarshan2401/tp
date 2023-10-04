package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.*;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

public class RemoveCommand extends Command {

    public static final String COMMAND_WORD = "remove";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Removes a specified type of object from its respective storage"
            // Removing a student from a class
            + "\n"
            + "To remove a student from a class:\n"
            + "Parameters: "
            + PREFIX_STUDENT + " STUDENT_NAME"
            + PREFIX_CLASS + " CLASS_NAME"
            + "\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_STUDENT + " John Doe"
            + PREFIX_CLASS + " cs2103t"
            + "\n\n"

            // Removing a class
            + "To remove a class: \n"
            + "Parameters:"
            + PREFIX_CLASS + "CLASS_NAME"
            + "\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_CLASS + "cs2103t";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }

}
