package namegenerator.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import namegenerator.domain.*;
import namegenerator.domain.exceptions.*;

public class LettersController extends ChildController {

    @FXML
    private GridPane letterList;

    @Override
    public void render() {
        letterList.getChildren().clear();

        int row = 0;
        int col = 0;
        for (LetterWeight weight : parent.getLanguage().letters()) {
            letterList.add(makeLetter(weight), col, row);

            col++;
            if (col >= 4) {
                col = 0;
                row++;
            }
        }
    }

    private VBox makeLetter(LetterWeight weight) {
        VBox group = new VBox();

        Label label = new Label(weight.letter().toString().toUpperCase());
        group.getChildren().add(label);

        Slider slider = makeSlider(weight);
        group.getChildren().add(slider);

        return group;
    }

    private Slider makeSlider(LetterWeight weight) {
        Slider slider = new Slider();
        slider.setValue(weight.weight());
        slider.setMin(weight.getMinWeight());
        slider.setMax(weight.getMaxWeight());

        slider.valueProperty().addListener((o, oldValue, newValue) -> {
            try {
                weight.setWeight(newValue.intValue());
                slider.setValue(newValue.intValue());
            } catch (IntegerOutOfBoundsException e) {
                slider.setValue((Double) oldValue);
            }

            this.checkForErrors();
        });

        return slider;
    }

    private void checkForErrors() {
        if (parent.getLanguage().highestWeight() == 0) {
            this.setError(true);
        } else if (this.hasError()) {
            // Clear existing error status
            this.setError(false);
        }
    }
}
