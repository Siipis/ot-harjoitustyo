package namegenerator.ui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.*;
import namegenerator.domain.*;

/**
 * Main UI controller.
 */
public class Controller implements Initializable {

    private Language language;

    @FXML
    private MenuController menuController;

    @FXML
    private LengthSelectionController lengthSelectionController;

    @FXML
    private GeneratorController generatorController;

    @FXML
    private LettersController lettersController;

    @FXML
    private LetterConfigController letterConfigController;

    /**
     * Initializes the controller, used by JavaFX.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Bind main controller to child controllers
        for (ChildController child : this.children()) {
            child.setParent(this);
        }

        this.setLanguage(new DefaultLanguage());
    }

    /**
     * Sets the current language.
     *
     * @param language current language
     */
    public void setLanguage(Language language) {
        this.language = language;

        this.renderChildren();
    }

    /**
     * Returns the current language
     *
     * @return current language
     */
    public Language getLanguage() {
        return language;
    }

    /**
     * Tells all child controllers to update the UI.
     */
    public void renderChildren() {
        for (ChildController child : this.children()) {
            child.render();
        }
    }

    /**
     * Check whether any child controller has errors.
     *
     * @return if any controller has an error
     */
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
            menuController,
            lengthSelectionController,
            generatorController,
            lettersController,
            letterConfigController,
        };
    }
}