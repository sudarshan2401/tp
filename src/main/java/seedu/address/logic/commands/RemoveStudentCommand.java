package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLASS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Name;
import seedu.address.model.student.TempClass;

/**
 * Removes a Student identified by Name  from a Class.
 */
public class RemoveStudentCommand extends RemoveCommand {

    public static final String COMMAND_WORD = RemoveCommand.COMMAND_WORD + " /s";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Removes a student from a class.\n"
            + "Parameters: "
            + PREFIX_STUDENT + "STUDENT_NAME"
            + PREFIX_CLASS + "CLASS_NAME"
            + "\n"
            + "Example: "
            + COMMAND_WORD + " John Doe "
            + PREFIX_CLASS + " cs2103t";

    public static final String MESSAGE_REMOVE_STUDENT_SUCCESS = "%s has been removed from %s ";

    private final Name studentName;
    private final TempClass studentClass;

    /**
     * Constructor of RemoveStudentCommand
     * @param studentName Name of Student to be deleted
     * @param studentClass Class of Student to be deleted
     */
    public RemoveStudentCommand(Name studentName, TempClass studentClass) {
        this.studentName = studentName;
        this.studentClass = studentClass;
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        //        List<Class> classList = model.get?
        model.deleteStudentFromClass(studentName, studentClass);
        return new CommandResult(String.format(MESSAGE_REMOVE_STUDENT_SUCCESS, studentName, studentClass.toString()));
    }

}
