package seedu.address.ui;

import static java.util.Objects.requireNonNull;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;

/**
 * A ui for the display of class information.
 */
public class ClassInfoDisplay extends UiPart<Region> {
    private static final String FXML = "ClassInfoDisplay.fxml";

    @FXML
    private TextArea classInfoDisplay;

    public ClassInfoDisplay() {
        super(FXML);
    }

    public void setClassInfo(String classInfo) {
        requireNonNull(classInfo);
        classInfoDisplay.setText(classInfo);
    }
}
