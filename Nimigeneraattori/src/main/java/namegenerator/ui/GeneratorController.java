package namegenerator.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import namegenerator.domain.*;
import namegenerator.domain.exceptions.*;

import java.net.URL;
import java.util.ResourceBundle;

public class GeneratorController extends ChildController implements Initializable {
    private Generator generator;

    @FXML
    private Button button;

    @FXML
    private Label result;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        generator = new Generator();

        result.setText("No name generated yet");
    }

    @Override
    public void render() {
        button.setDisable(parent.hasError());
    }

    @FXML
    private void clickedGenerateButton(ActionEvent event) {
        try {
            Name name = generator.generate(parent.getLanguage());
            result.setText(name.toString());
            System.out.println("Generated new name: " + name);
        } catch (LettersNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
