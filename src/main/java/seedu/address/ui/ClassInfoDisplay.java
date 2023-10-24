package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;
import seedu.address.model.module.Class;

import static java.util.Objects.requireNonNull;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;

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
