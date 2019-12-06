package namegenerator.dao;

import namegenerator.domain.DefaultLanguage;
import namegenerator.domain.Language;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class LanguageDaoTest {

    private final String dbUrl = "jdbc:sqlite:test.db";

    private Database db = new Database(dbUrl);

    private LanguageDao dao;

    private static String[] names = new String[] {
            "English",
            "French",
            "German",
    };

    private Language[] languages = new Language[names.length];

    @Before
    public void setUp() throws SQLException {
        this.dao = new LanguageDao(dbUrl);

        this.setUpLanguages();

        this.cleanDatabase();
        this.seedRecords();
    }

    private void setUpLanguages() {
        DefaultLanguage defaultLanguage = new DefaultLanguage();

        for (int i = 0; i < languages.length; i++) {
            Language language = new Language();
            language.setName(names[i]);
            language.setLetters(defaultLanguage.letters());
            languages[i] = language;
        }
    }

    private void cleanDatabase() throws SQLException {
        Connection c = db.connection();
        PreparedStatement stmt = c.prepareStatement("DELETE FROM language");
        stmt.execute();
        stmt.close();
        c.close();
    }

    private void seedRecords() {
        for (Language language : languages) {
            dao.saveOrUpdate(language);
        }
    }

    @Test
    public void findOneReturnsExistingRecord() {
        assertEquals(languages[0], dao.findOne("English"));
    }

    @Test
    public void findOneReturnsNullOnNoRecord() {
        assertNull(dao.findOne("Chinese"));
    }

    @Test
    public void findAllReturnsExistingRecords() {
        ArrayList<Language> result = dao.findAll();

        assertEquals(names.length, result.size());

        for (int i = 0; i < languages.length; i++) {
            assertEquals(languages[i], dao.findOne(names[i]));
        }
    }

    @Test
    public void findAllReturnsEmptyListOnNoRecords() throws SQLException {
        this.cleanDatabase();

        ArrayList<Language> result = dao.findAll();

        assertEquals(0, result.size());
    }

    @Test
    public void saveOrUpdateStoresNewRecord() {
        Language language = new Language();
        language.setName("Finnish");

        dao.saveOrUpdate(language);

        assertEquals(language, dao.findOne("Finnish"));
    }

    @Test
    public void saveOrUpdateUpdatesExistingRecord() {
        Language language = languages[0];
        language.setLetters(new ArrayList<>());

        dao.saveOrUpdate(language);

        assertEquals(language, dao.findOne(names[0]));
    }

    @Test
    public void deleteRemovesExistingRecord() {
        dao.delete(names[0]);

        ArrayList<Language> result = dao.findAll();

        assertEquals(2, result.size());

        for (int i = 1; i < languages.length; i++) {
            assertEquals(languages[i], dao.findOne(names[i]));
        }
    }
}
