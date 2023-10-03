package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.*;

public abstract class RemoveCommand {

    public static final String COMMAND_WORD = "remove";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Removes a specified type of object from its respective storage"
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



}
