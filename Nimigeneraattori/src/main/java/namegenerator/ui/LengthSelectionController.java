package namegenerator.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import namegenerator.domain.Language;
import namegenerator.domain.exceptions.NameLengthException;
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

        error.setVisible(false);

        this.initializeMinLengthField();
        this.initializeMaxLengthField();
    }

    private void initializeMinLengthField() {
        minLength.textProperty().addListener((o, oldValue, newValue) -> {
            this.hideLengthError();

            try {
                currentLanguage.setMinLength(minLength.value());
            } catch (NameLengthException e) {
                this.showLengthError(e.getMessage());
            }
        });
    }

    private void initializeMaxLengthField() {
        maxLength.textProperty().addListener((o, oldValue, newValue) -> {
            this.hideLengthError();

            try {
                currentLanguage.setMaxLength(maxLength.value());
            } catch (NameLengthException e) {
                this.showLengthError(e.getMessage());
            }
        });
    }

    private void showLengthError(String message) {
        this.setError(true);

        error.setText(message);
        error.setVisible(true);
    }

    private void hideLengthError() {
        this.setError(false);

        error.setVisible(false);
    }
}
