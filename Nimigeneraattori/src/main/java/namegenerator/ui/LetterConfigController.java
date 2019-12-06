package namegenerator.ui;

import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import namegenerator.domain.Language;
import namegenerator.domain.exceptions.IntegerOutOfBoundsException;
import namegenerator.ui.control.NumericField;

/**
 * Language spelling section controller.
 */
public class LetterConfigController extends ChildController {

    private Language currentLanguage;

    @FXML
    private NumericField vowelGroupSize;

    @FXML
    private CheckBox allowDoubleVowels;

    @FXML
    private NumericField consonantGroupSize;

    @FXML
    private CheckBox allowDoubleConsonants;

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
        vowelGroupSize.setText("" + currentLanguage.getVowelGroupSize());
        vowelGroupSize.textProperty().addListener(makeFieldListener(vowelGroupSize));

        allowDoubleVowels.setSelected(currentLanguage.hasDoubleVowels());
        allowDoubleVowels.selectedProperty().addListener(makeCheckboxListener(allowDoubleVowels));

        consonantGroupSize.setText("" + currentLanguage.getConsonantGroupSize());
        consonantGroupSize.textProperty().addListener(makeFieldListener(consonantGroupSize));

        allowDoubleConsonants.setSelected(currentLanguage.hasDoubleConsonants());
        allowDoubleConsonants.selectedProperty().addListener(makeCheckboxListener(allowDoubleConsonants));

        error.setVisible(false);
    }

    private ChangeListener<String> makeFieldListener(NumericField field) {
        return (o, oldValue, newValue) -> {
            this.hideError();

            try {
                int value = field.value();

                if (field == vowelGroupSize) {
                    currentLanguage.setVowelGroupSize(value);
                }

                if (field == consonantGroupSize) {
                    currentLanguage.setConsonantGroupSize(value);
                }

                CheckBox checkBox = field == vowelGroupSize ? allowDoubleVowels : allowDoubleConsonants;
                checkBox.setSelected(value != 1 && checkBox.isSelected());
                checkBox.setDisable(value == 1);

            } catch (IntegerOutOfBoundsException e) {
                this.showError(e.getMessage());
            }
        };
    }

    private ChangeListener<Boolean> makeCheckboxListener(CheckBox checkBox) {
        return (o, oldValue, newValue) -> {
            if (checkBox == allowDoubleVowels) {
                currentLanguage.setDoubleVowels(newValue);
            }

            if (checkBox == allowDoubleConsonants) {
                currentLanguage.setDoubleConsonants(newValue);
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
