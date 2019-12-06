package namegenerator.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import namegenerator.dao.LanguageDao;
import namegenerator.domain.*;

import java.util.*;
import java.util.stream.Collectors;

public class MenuController extends ChildController {

    private LanguageDao db;

    @FXML
    private MenuItem saveOption;

    public MenuController() {
        this.db = new LanguageDao();
    }

    @Override
    public void render() {
        saveOption.setDisable(parent.hasError());
    }

    public void handleNewClick(ActionEvent event) {
        parent.setLanguage(new DefaultLanguage());
    }

    public void handleOpenClick(ActionEvent event) {
        ArrayList<Language> languages = db.findAll();
        List<String> names = languages.stream().map(Language::getName).collect(Collectors.toList());

        String placeholder = "Select...";

        ChoiceDialog<String> dialog = new ChoiceDialog<>(placeholder, names);
        dialog.setTitle("Open language");
        dialog.setHeaderText("Open a language");
        dialog.setContentText("Choose language:");

        Optional<String> result = dialog.showAndWait();

        result.ifPresent(name -> {
            if (name.equals(placeholder)) {
                return;
            }

            parent.setLanguage(db.findOne(name));
        });
    }

    public void handleSaveClick(ActionEvent event) {
        Language language = parent.getLanguage();

        TextInputDialog dialog = new TextInputDialog(language.getName().length() > 0 ? language.getName() : "");
        dialog.setTitle("Save language");
        dialog.setHeaderText("Enter a language name");
        dialog.setContentText("Language name:");

        Optional<String> result = dialog.showAndWait();

        result.ifPresent(name -> {
            language.setName(name);

            db.saveOrUpdate(language);
        });
    }

    public void handleDeleteClick(ActionEvent event) {
        ArrayList<Language> languages = db.findAll();
        List<String> names = languages.stream().map(Language::getName).collect(Collectors.toList());

        String placeholder = "Select...";

        ChoiceDialog<String> dialog = new ChoiceDialog<>(placeholder, names);
        dialog.setTitle("Delete language");
        dialog.setHeaderText("Delete a language");
        dialog.setContentText("Delete language:");

        Optional<String> result = dialog.showAndWait();

        result.ifPresent(name -> {
            if (name.equals(placeholder)) {
                return;
            }

            db.delete(name);
        });
    }
}
