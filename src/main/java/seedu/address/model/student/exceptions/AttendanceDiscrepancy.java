package seedu.address.model.student.exceptions;

/**
 * Signals that the operation will result in Student's current total attendance becoming larger than class total
 * attendance.
 */
public class AttendanceDiscrepancy extends RuntimeException {
    public AttendanceDiscrepancy() {
        super("Operation would result in current attendance being larger than total attendance");
    }
}
