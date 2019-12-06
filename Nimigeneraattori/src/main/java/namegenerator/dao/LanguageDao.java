package namegenerator.dao;

import namegenerator.domain.*;
import namegenerator.domain.exceptions.*;
import org.json.*;

import java.sql.*;
import java.util.ArrayList;

public class LanguageDao implements Dao<Language, String> {

    private Database db;

    public LanguageDao() {
        this("jdbc:sqlite:database.db");
    }

    public LanguageDao(String db) {
        try {
            this.db = new Database(db);

            this.createTable();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void createTable() throws SQLException {
        Connection c = db.connection();
        PreparedStatement stmt = c.prepareStatement("CREATE TABLE IF NOT EXISTS language (" +
                " name varchar(200) PRIMARY KEY," +
                " letters text," +
                " minLength integer," +
                " maxLength integer," +
                " vowelGroupSize integer," +
                " consonantGroupSize integer," +
                " doubleVowels boolean," +
                " doubleConsonants boolean" +
                ")");

        stmt.execute();

        stmt.close();
        c.close();
    }

    @Override
    public Language findOne(String name) {
        try {
            Connection c = db.connection();
            PreparedStatement stmt = c.prepareStatement("SELECT * FROM language WHERE name = ?");
            stmt.setString(1, name);

            ResultSet result = stmt.executeQuery();

            if (!result.next()) {
                return null;
            }

            Language language = languageFromResult(result);

            stmt.close();
            result.close();
            c.close();

            return language;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    @Override
    public ArrayList<Language> findAll() {
        ArrayList<Language> languages = new ArrayList<>();

        try {
            Connection c = db.connection();
            PreparedStatement stmt = c.prepareStatement("SELECT * FROM language");

            ResultSet result = stmt.executeQuery();

            while (result.next()) {
                languages.add(languageFromResult(result));
            }

            stmt.close();
            result.close();
            c.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return languages;
    }

    @Override
    public Language saveOrUpdate(Language language) {
        try {
            Language existing = this.findOne(language.getName());

            if (existing == null) {
                this.save(language);
            } else {
                this.update(language);
            }

            return language;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    private void save(Language language) throws SQLException {
        Connection c = db.connection();

        PreparedStatement stmt = c.prepareStatement("INSERT INTO language" +
                " (letters, minLength, maxLength, vowelGroupSize, consonantGroupSize, doubleVowels, doubleConsonants, name)" +
                " VALUES (?, ?, ?, ?, ?, ?, ?, ?)");


        stmt = bindLanguageToStatement(stmt, language);

        stmt.execute();

        stmt.close();
        c.close();
    }

    private void update(Language language) throws SQLException {
        Connection c = db.connection();

        PreparedStatement stmt = c.prepareStatement("UPDATE language SET" +
                " letters = ?," +
                " minLength = ?," +
                " maxLength = ?," +
                " vowelGroupSize = ?," +
                " consonantGroupSize = ?," +
                " doubleVowels = ?," +
                " doubleConsonants = ?" +
                " WHERE name = ?");

        stmt = bindLanguageToStatement(stmt, language);

        stmt.execute();

        stmt.close();
        c.close();
    }

    @Override
    public void delete(String name) {
        try {
            Connection c = db.connection();
            PreparedStatement stmt = c.prepareStatement("DELETE FROM language WHERE name = ?");

            stmt.setString(1, name);
            stmt.executeUpdate();

            stmt.close();
            c.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private PreparedStatement bindLanguageToStatement(PreparedStatement stmt, Language language) throws SQLException {
        stmt.setString(1, this.lettersToJson(language).toString());
        stmt.setInt(2, language.getMinLength());
        stmt.setInt(3, language.getMaxLength());
        stmt.setInt(4, language.getVowelGroupSize());
        stmt.setInt(5, language.getConsonantGroupSize());
        stmt.setBoolean(6, language.hasDoubleVowels());
        stmt.setBoolean(7, language.hasDoubleConsonants());
        stmt.setString(8, language.getName());

        return stmt;
    }

    private Language languageFromResult(ResultSet result) throws SQLException, IntegerOutOfBoundsException {
        Language language = new Language();

        language.setLetters(this.lettersFromJson(result.getString("letters")));
        language.setName(result.getString("name"));
        language.setMinLength(result.getInt("minLength"));
        language.setMaxLength(result.getInt("maxLength"));
        language.setVowelGroupSize(result.getInt("VowelGroupSize"));
        language.setConsonantGroupSize(result.getInt("consonantGroupSize"));
        language.setDoubleVowels(result.getBoolean("doubleVowels"));
        language.setDoubleConsonants(result.getBoolean("doubleConsonants"));

        return language;
    }

    private JSONArray lettersToJson(Language language) {
        JSONArray letters = new JSONArray();

        for (LetterWeight l : language.letters()) {
            JSONObject letter = new JSONObject();
            letter.put("char", l.letter());
            letter.put("type", l.letter().getType());
            letter.put("weight", l.weight());

            letters.put(letter);
        }

        return letters;
    }

    private ArrayList<LetterWeight> lettersFromJson(String jsonString) {
        ArrayList<LetterWeight> letters = new ArrayList<>();
        JSONArray json = new JSONArray(jsonString);

        for (int i = 0; i < json.length(); i++) {
            try {
                letters.add(letterFromJson((JSONObject) json.get(i)));
            } catch (IntegerOutOfBoundsException e) {
                System.out.println(e.getMessage());
            }
        }

        return letters;
    }

    private LetterWeight letterFromJson(JSONObject json) throws IntegerOutOfBoundsException {
        String stringChar = (String) json.get("char");
        char c = stringChar.charAt(0);

        LetterType type;

        switch ((String) json.get("type")) {
            case "VOWEL":
                type = LetterType.VOWEL;
                break;
            case "CONSONANT":
                type = LetterType.CONSONANT;
                break;
            default:
                type = LetterType.BOTH;
        }

        int weight = (int) json.get("weight");

        return new LetterWeight(new Letter(c, type), weight);
    }
}
