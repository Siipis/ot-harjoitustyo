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
    @FXML
    private Button button;

    @FXML
    private Label result;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        result.setText("No name generated yet");
    }

    @Override
    public void render() {
        button.setDisable(parent.hasError());
    }

    @FXML
    private void handleClick(ActionEvent event) {
        try {
            Generator generator = new Generator(parent.getLanguage());

            Name name = generator.generate();
            result.setText(name.toString());
        } catch (LettersNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
