package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLASS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Class;
import seedu.address.model.module.ClassName;
import seedu.address.model.student.Student;
import seedu.address.model.student.exceptions.StudentAlreadyMarkedPresent;

/**
 * Marks a student in a Class in teh EduTrack as present.
 */
public class MarkStudentPresentCommand extends Command {
    public static final String COMMAND_WORD = "mark" + " /s";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks a student as being present.\n"
            + "Parameters: "
            + PREFIX_STUDENT + " STUDENT_INDEX"
            + "\n"
            + PREFIX_CLASS + " CLASS_NAME"
            + "\n"
            + "Example: "
            + COMMAND_WORD + " 1 " + PREFIX_CLASS + " cs2103";
    public static final String MESSAGE_STUDENT_ALREADY_MARKED = "%s has already been marked present!";
    public static final String MESSAGE_MARK_STUDENT_ATTENDANCE_SUCCESS = "%s has been marked present!";
    public final Index targetStudentIndex;
    private ClassName className;

    /**
     * Command to mark a student present for lesson based on its index in the UniqueStudentList
     * @param index Index of student in List
     */
    public MarkStudentPresentCommand(Index index, ClassName className) {
        requireNonNull(index);
        requireNonNull(className);
        this.targetStudentIndex = index;
        this.className = className;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {

        requireNonNull(model);
        Class studentClass = model.getClass(className);
        Student studentToMark = studentClass.getStudentInClass(targetStudentIndex);
        Student editedStudent = studentToMark.duplicateStudent();

        try {
            model.markStudentPresent(studentToMark, studentClass, editedStudent);
        } catch (StudentAlreadyMarkedPresent e) {
            throw new CommandException(String.format(MESSAGE_STUDENT_ALREADY_MARKED, studentToMark.toString()));
        }
        return new CommandResult(String.format(MESSAGE_MARK_STUDENT_ATTENDANCE_SUCCESS,
                Messages.formatStudent(studentToMark)));
    }
}
