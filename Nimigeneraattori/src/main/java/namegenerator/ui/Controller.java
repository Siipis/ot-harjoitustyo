package namegenerator.ui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.*;
import namegenerator.domain.*;

public class Controller implements Initializable {

    private Language language;

    @FXML
    private LengthSelectionController lengthSelectionController;

    @FXML
    private GeneratorController generatorController;

    @FXML
    private LettersController lettersController;

    @FXML
    private LetterConfigController letterConfigController;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Bind main controller to child controllers
        for (ChildController child : this.children()) {
            child.setParent(this);
        }

        this.setLanguage(new DefaultLanguage());
    }

    public void setLanguage(Language language) {
        this.language = language;

        this.renderChildren();
    }

    public Language getLanguage() {
        return language;
    }

    public void renderChildren() {
        for (ChildController child : this.children()) {
            child.render();
        }
    }

    public boolean hasError() {
        for (ChildController child : this.children()) {
            if (child.hasError()) {
                return true;
            }
        }

        return false;
    }

    /**
     * Returns all nested controllers for easier access
     *
     * @return array
     */
    private ChildController[] children() {
        return new ChildController[] {
            lengthSelectionController,
            generatorController,
            lettersController,
            letterConfigController,
        };
    }
}