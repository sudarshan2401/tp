package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLASS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT;
public class RemoveStudentCommand  extends RemoveCommand{
    public static final String COMMAND_WORD = RemoveCommand.COMMAND_WORD + PREFIX_STUDENT;

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Removes a student from a class.\n"
            + "Parameters: "
            + PREFIX_STUDENT + "STUDENT_NAME"
            + PREFIX_CLASS + "CLASS_NAME"
            + "\n"
            + "Example: "
            + COMMAND_WORD + " John Doe "
            + PREFIX_CLASS + " cs2103t";

    public static final String MESSAGE_REMOVE_STUDENT_SUCCESS = "Removed Student: %s";

    private final Student studentToDelete;
    private final Class studentClass;

    public RemoveStudentCommand(Student student, Class class) {
        this.studentToDelete = student;
        this.studentClass = class;
    }

    public CommandResult execute (Model model) {
        requireNonNull(model);

        // code logic to remove student from class
    }
}
