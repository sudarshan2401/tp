package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_CLASS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * A command that informs user what specific objects they can remove.
 */
public class RemoveCommand extends Command {

    public static final String COMMAND_WORD = "remove";

    public static final String MESSAGE_USAGE =
            "Remove : Removes either a Class from list or a Student from a Class"
            // Removing a student from a class
            + "\n\n"
            + "To remove a student from a class:\n"
            + "Parameters: "
            + PREFIX_STUDENT + " STUDENT_INDEX "
            + PREFIX_CLASS + " CLASS_NAME"
            + "\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_STUDENT + " 1 "
            + PREFIX_CLASS + " cs2103t"
            + "\n\n"

            // Removing a class
            + "To remove a class: \n"
            + "Parameters: "
            + PREFIX_CLASS + " CLASS_NAME"
            + "\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_CLASS + " cs2103t";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return new CommandResult(String.format(MESSAGE_USAGE));

    }

}
