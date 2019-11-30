package namegenerator.ui;

import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import namegenerator.domain.Language;
import namegenerator.domain.exceptions.IntegerOutOfBoundsException;
import namegenerator.ui.control.NumericField;


public class LengthSelectionController extends ChildController {

    private Language currentLanguage;

    @FXML
    private NumericField maxLength;

    @FXML
    private NumericField minLength;

    @FXML
    private Label error;

    @Override
    public void render() {
        if (this.languageHasChanged()) {
            this.setCurrentLanguage(parent.getLanguage());
        }
    }

    private boolean languageHasChanged() {
        return !parent.getLanguage().equals(currentLanguage);
    }

    public void setCurrentLanguage(Language language) {
        this.currentLanguage = language;

        this.initialize();
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
                }

                if (field == maxLength) {
                    currentLanguage.setMaxLength(maxLength.value());
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
