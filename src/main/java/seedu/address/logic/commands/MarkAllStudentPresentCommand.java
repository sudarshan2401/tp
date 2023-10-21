package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLASS;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Class;
import seedu.address.model.student.Student;
import seedu.address.model.student.exceptions.StudentAlreadyMarkedPresent;

/**
 * Marks all the student in a class as present.
 */
public class MarkAllStudentPresentCommand extends Command {
    public static final String COMMAND_WORD = "markall" + " /c";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks all students in this class as being present.\n"
            + "Parameters: "
            + PREFIX_CLASS + " CLASS_NAME"
            + "\n"
            + "Example: "
            + COMMAND_WORD + " 1";
    public static final String MESSAGE_MARK_STUDENT_ATTENDANCE_SUCCESS = "Successfully marked all students in %s!";
    private Index targetClassIndex;

    /**
     * Creates a MarkAllStudentPresentCommand to mark all {@code Student}
     */
    public MarkAllStudentPresentCommand(Index classIndex) {
        requireNonNull(classIndex);
        this.targetClassIndex = classIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Class studentClass = model.retrieveClass(targetClassIndex);
        List<Student> studentList = studentClass.getStudentList();
        for (Student studentToMark : studentList) {
            Student editedStudent = studentToMark.duplicateStudent();
            try {
                model.markStudentPresent(studentToMark, studentClass, editedStudent);
            } catch (StudentAlreadyMarkedPresent e) {
                continue;
            }
        }
        return new CommandResult(String.format(MESSAGE_MARK_STUDENT_ATTENDANCE_SUCCESS,
                Messages.formatClass(studentClass)));
    }
}
