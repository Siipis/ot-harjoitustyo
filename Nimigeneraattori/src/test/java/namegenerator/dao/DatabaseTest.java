package namegenerator.dao;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DatabaseTest {

    Database db;

    @Before
    public void setUp() {
        this.db = new Database("jdbc:sqlite:test.db");
    }

    @Test
    public void canConnectToDbTest() {
        assertTrue(db.canConnect());
    }
}
