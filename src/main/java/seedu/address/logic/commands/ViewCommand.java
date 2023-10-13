package seedu.address.logic.commands;

import seedu.address.model.Model;
import seedu.address.model.person.Person;

import java.util.function.Predicate;

import static java.util.Objects.requireNonNull;

/**
 * Lists all students in the specified class to the user.
 */
public class ViewCommand extends Command {

        public static final String COMMAND_WORD = "view";

        public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists all students in the specified class.\n"
                + "Parameters: CLASS_NAME\n"
                + "Example: " + COMMAND_WORD + " CS2103T";

        public static final String MESSAGE_SUCCESS = "Listed all students in %1$s";

        private final String className;

        /**
        * Creates an ViewCommand to list the students in the specified class.
        */
        public ViewCommand(String className) {
            this.className = className;
        }

        @Override
        public CommandResult execute(Model model) {
            requireNonNull(model);
            return new CommandResult(String.format(MESSAGE_SUCCESS, className));
        }

}
