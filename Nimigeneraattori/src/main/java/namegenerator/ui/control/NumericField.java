package namegenerator.ui.control;

import javafx.beans.NamedArg;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.TextField;

public class NumericField extends TextField {
    private int size;

    public NumericField(@NamedArg("size") int size) {
        this.size = size;

        this.textProperty().addListener(changeListener());
    }

    public int value() {
        try {
            return Integer.parseInt(this.getText());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private ChangeListener<String> changeListener() {
        return (observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d{0," + size + "}")) {
                this.setText(oldValue);
            }
        };
    }
}
