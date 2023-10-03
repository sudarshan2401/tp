package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLASS;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Class;

/**
 * Changes the remark of an existing person in the address book.
 */
public class AddClassCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a new class to the list of classes.\n"
            + "Parameters: "
            + PREFIX_CLASS + " CLASS_NAME ";

    public static final String MESSAGE_SUCCESS = "%1$s has been added";

    public static final String MESSAGE_DUPLICATE_CLASS = "%1$s already exists";

    private final Class c;

    public AddClassCommand(Class c) {
        requireAllNonNull(c);
        this.c = c;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasClass(c)) {
            throw new CommandException(String.format(MESSAGE_DUPLICATE_CLASS, c.getClassName()));
        }

        model.addClass(c);
        return new CommandResult(String.format(MESSAGE_SUCCESS, c.getClassName()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddClassCommand)) {
            return false;
        }

        AddClassCommand e = (AddClassCommand) other;
        return c.equals(e.c);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("class", c)
                .toString();
    }
}
