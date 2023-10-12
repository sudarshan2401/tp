package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLASS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Name;
import seedu.address.model.student.Student;
import seedu.address.model.student.TempClass;

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

     private final Index studentIndex;
    private final TempClass studentClass;
    public RemoveStudentCommand(Index index, TempClass studentClass) {
        this.studentIndex= index;
        this.studentClass = studentClass;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> lastShownList = model.getFilteredStudentList();
        if (studentIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        Student studentToDelete = lastShownList.get(studentIndex.getZeroBased());
        Name studentName = studentToDelete.getName();
//        List<Class> classList = model.get?
        model.deleteStudentFromClass(studentToDelete, studentClass);
        return new CommandResult(String.format(MESSAGE_REMOVE_STUDENT_SUCCESS, studentName, studentClass.toString()));
    }

}
