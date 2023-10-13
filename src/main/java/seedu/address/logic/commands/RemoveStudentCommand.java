package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLASS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Class;
import seedu.address.model.module.ClassName;
import seedu.address.model.student.Name;
import seedu.address.model.student.Student;

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


    private final Index studentIndex;
    private final ClassName studentClassName;

    /**
     * Command to remove Student based on its index in the UniqueStudentList in Class with ClassName
     * @param index Index of student in List
     * @param studentClassName ClassName of the Student's Class
     */
    public RemoveStudentCommand(Index index, ClassName studentClassName) {
        this.studentIndex = index;
        this.studentClassName = studentClassName;
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Class studentClass = model.getClass(studentClassName);
        List<Student> studentList = studentClass.getStudentList();
        if (studentIndex.getZeroBased() >= studentList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        Student studentToDelete = studentList.get(studentIndex.getZeroBased());

        model.deleteStudentFromClass(studentToDelete, studentClass);

        Name studentName = studentToDelete.getName();
        return new CommandResult(String.format(MESSAGE_REMOVE_STUDENT_SUCCESS, studentName, studentClass.toString()));
    }

}
