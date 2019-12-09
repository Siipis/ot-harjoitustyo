package namegenerator.ui;

import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import namegenerator.domain.Language;
import namegenerator.domain.exceptions.IntegerOutOfBoundsException;
import namegenerator.ui.control.NumericField;

/**
 * Name length section controller.
 */
public class LengthSelectionController extends ChildController {

    private Language currentLanguage;

    @FXML
    private NumericField maxLength;

    @FXML
    private NumericField minLength;

    @FXML
    private Label error;

    /**
     * Syncs the UI elements to match the app state.
     */
    @Override
    public void render() {
        if (!parent.getLanguage().equals(currentLanguage)) {
            this.currentLanguage = parent.getLanguage();

            this.initialize();
        }
    }

    private void initialize() {
        minLength.setText("" + currentLanguage.getMinLength());
        maxLength.setText("" + currentLanguage.getMaxLength());

        minLength.textProperty().addListener(makeListener(minLength));
        maxLength.textProperty().addListener(makeListener(maxLength));

        error.setVisible(false);
    }

    private ChangeListener<String> makeListener(NumericField field) {
        return (o, oldValue, newValue) -> {
            this.hideError();

            try {
                if (field == minLength) {
                    currentLanguage.setMinLength(minLength.value());
                    currentLanguage.setMaxLength(maxLength.value());
                }

                if (field == maxLength) {
                    currentLanguage.setMaxLength(maxLength.value());
                    currentLanguage.setMinLength(minLength.value());
                }
            } catch (IntegerOutOfBoundsException e) {
                this.showError(e.getMessage());
            }
        };
    }

    private void showError(String message) {
        this.setError(true);

        error.setText(message);
        error.setVisible(true);
    }

    private void hideError() {
        this.setError(false);

        error.setVisible(false);
    }
}
