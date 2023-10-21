package seedu.address.logic.commands;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Class;
import seedu.address.model.module.ClassName;
import seedu.address.model.student.Student;
import seedu.address.model.student.exceptions.StudentAlreadyMarkedPresent;

import java.util.List;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLASS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT;

public class MarkStudentAttendanceCommand extends Command {
    public static final String COMMAND_WORD = "mark" + " /s";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks a student as being present.\n"
            + "Parameters: "
            + PREFIX_STUDENT + " STUDENT_INDEX"
            + "\n"
            + PREFIX_CLASS + " CLASS_NAME"
            + "\n"
            + "Example: "
            + COMMAND_WORD + " 1";

    public static final String MESSAGE_STUDENT_ALREADY_MARKED = "%s has already been marked present!";
    public static final String MESSAGE_MARK_STUDENT_ATTENDANCE_SUCCESS = "%s has been marked present!";

    public final Index targetStudentIndex;
    private ClassName className;

    /**
     * Command to mark a student present for lesson based on its index in the UniqueStudentList
     * @param index Index of student in List
     */
    public MarkStudentAttendanceCommand(Index index, ClassName className) {
        requireNonNull(index);
        requireNonNull(className);
        this.targetStudentIndex = index;
        this.className = className;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {

        requireNonNull(model);
        Class studentClass = model.getClass(className);
        List<Student> studentList = studentClass.getStudentList();

        if (studentList.size() == 0) {
            throw new CommandException(Messages.MESSAGE_EMPTY_STUDENT_LIST);
        }
        if (targetStudentIndex.getZeroBased() >= studentList.size()) {
            throw new CommandException(Messages.MESSAGE_INDEX_INPUT_TOO_LARGE);
        }
        Student studentToMark = studentList.get(targetStudentIndex.getZeroBased());
        Student editedStudent = studentToMark.duplicateStudent();

        try {
            model.markStudentPresent(studentToMark, studentClass, editedStudent);
        } catch (StudentAlreadyMarkedPresent e) {
            throw new CommandException(MESSAGE_STUDENT_ALREADY_MARKED);
        }
        return new CommandResult(String.format(MESSAGE_MARK_STUDENT_ATTENDANCE_SUCCESS, Messages.formatStudent(studentToMark)));
    }
}
