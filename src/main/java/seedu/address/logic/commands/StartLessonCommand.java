package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.commands.RemoveClassCommand.MESSAGE_MISSING_CLASS_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLASS;

import java.util.List;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Class;
import seedu.address.model.module.ClassName;
import seedu.address.model.module.exceptions.ClassNotFoundException;
import seedu.address.model.student.Student;
import seedu.address.model.student.exceptions.StudentAlreadyMarkedAbsent;

/**
 * Starts a lesson of a Class.
 */
public class StartLessonCommand extends Command {
    public static final String COMMAND_WORD = "startlesson /c";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Starts a new lesson for a Class. This increments the total number of lessons in the Class.\n"
            + "Parameters: "
            + PREFIX_CLASS + " CLASS_NAME"
            + "\n"
            + "Example: "
            + COMMAND_WORD + " cs2103";
    public static final String MESSAGE_START_LESSON_SUCCESS = "%s started a new lesson!";
    private ClassName className;

    /**
     * Command to start a lesson of a Class. This increases total lessons conducted of the Class by one and
     * mark all the students as absent to facilitate attendance taking.
     * @param className Classname to represent the Class.
     */
    public StartLessonCommand(ClassName className) {
        requireNonNull(className);
        this.className = className;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {

        requireNonNull(model);
        Class copy = null;
        try {
            Class c = model.getClass(className);
            copy = c;
            model.startLesson(c);
            // Mark everyone absent
            List<Student> studentList = c.getStudentList();
            for (Student studentToUnmark : studentList) {
                Student editedStudent = studentToUnmark.duplicateStudent();
                try {
                    model.startLessonForStudent(studentToUnmark, c, editedStudent);
                    model.updateFilteredClassList((cl) -> cl.isSameClass(c));
                } catch (StudentAlreadyMarkedAbsent e) {
                    continue;
                }
            }
        } catch (ClassNotFoundException e) {
            throw new CommandException(String.format(MESSAGE_MISSING_CLASS_NAME, className));
        }
        return new CommandResult(String.format(MESSAGE_START_LESSON_SUCCESS,
                Messages.formatClass(copy)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof StartLessonCommand)) {
            return false;
        }

        StartLessonCommand otherStartLessonCommand = (StartLessonCommand) other;
        return this.className.equals(otherStartLessonCommand.className);
    }
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("className", this.className.toString())
                .toString();
    }
}
