package namegenerator.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import namegenerator.domain.Language;
import namegenerator.domain.exceptions.NameLengthException;
import namegenerator.ui.control.NumericField;


public class LengthSelectionController extends ChildController {

    private Language currentLanguage;

    @FXML
    private NumericField maxLengthField;

    @FXML
    private NumericField minLengthField;

    @FXML
    private Label lengthFieldError;

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
        minLengthField.setText("" + currentLanguage.getMinLength());
        maxLengthField.setText("" + currentLanguage.getMaxLength());

        lengthFieldError.setVisible(false);

        this.initializeMinLengthField();
        this.initializeMaxLengthField();
    }

    private void initializeMinLengthField() {
        minLengthField.textProperty().addListener((o, oldValue, newValue) -> {
            this.hideLengthError();

            try {
                currentLanguage.setMinLength(minLengthField.value());
            } catch (NameLengthException e) {
                this.showLengthError(e.getMessage());
            }
        });
    }

    private void initializeMaxLengthField() {
        maxLengthField.textProperty().addListener((o, oldValue, newValue) -> {
            this.hideLengthError();

            try {
                currentLanguage.setMaxLength(maxLengthField.value());
            } catch (NameLengthException e) {
                this.showLengthError(e.getMessage());
            }
        });
    }

    private void showLengthError(String message) {
        this.setError(true);

        lengthFieldError.setText(message);
        lengthFieldError.setVisible(true);
    }

    private void hideLengthError() {
        this.setError(false);

        lengthFieldError.setVisible(false);
    }
}
