package namegenerator.ui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import namegenerator.domain.*;
import namegenerator.domain.exceptions.LettersNotFoundException;
import namegenerator.domain.exceptions.NameLengthException;
import namegenerator.ui.control.NumericField;

public class Controller implements Initializable {

    private Language language;
    private Generator generator;

    @FXML
    private Label generatedName;

    @FXML
    private GridPane letterList;

    @FXML
    public NumericField maxLengthField;

    @FXML
    public NumericField minLengthField;

    @FXML
    public Label lengthFieldError;

    public Controller() {
        generator = new Generator();

        language = new DefaultLanguage();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        generatedName.setText("No name generated yet");

        this.initializeLengthFields();
        this.initializeLetters();
    }

    private void initializeLengthFields() {
        minLengthField.setText("" + language.getMinLength());
        maxLengthField.setText("" + language.getMaxLength());

        lengthFieldError.setVisible(false);

        this.initializeMinLengthField();
        this.initializeMaxLengthField();
    }

    private void initializeMinLengthField() {
        minLengthField.textProperty().addListener((o, oldValue, newValue) -> {
            this.hideLengthError();

            try {
                language.setMinLength(minLengthField.value());
            } catch (NameLengthException e) {
                this.showLengthError(e.getMessage());
            }
        });
    }

    private void initializeMaxLengthField() {
        maxLengthField.textProperty().addListener((o, oldValue, newValue) -> {
            this.hideLengthError();

            try {
                language.setMaxLength(maxLengthField.value());
            } catch (NameLengthException e) {
                this.showLengthError(e.getMessage());
            }
        });
    }

    private void initializeLetters() {
        int row = 0;
        int col = 0;
        for (LetterWeight weight : language.letters()) {
            letterList.add(makeLetter(weight), col, row);

            col++;
            if (col >= 4) {
                col = 0;
                row++;
            }
        }
    }

    @FXML
    private void clickedGenerateButton(ActionEvent event) {
        try {
            Name name = generator.generate(language);
            generatedName.setText(name.toString());
            System.out.println("Generated new name: " + name);
        } catch (LettersNotFoundException e) {
            System.out.println(e.getMessage());
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
            weight.setWeight(oldValue.intValue());
        });

        return slider;
    }

    private void showLengthError(String message) {
        lengthFieldError.setText(message);
        lengthFieldError.setVisible(true);
    }

    private void hideLengthError() {
        lengthFieldError.setVisible(false);
    }
}