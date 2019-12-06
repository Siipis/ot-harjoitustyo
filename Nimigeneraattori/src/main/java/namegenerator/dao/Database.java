package namegenerator.dao;

import java.sql.*;

/**
 * Database connection handler.
 */
public class Database {

    private String db;

    /**
     * Constructs the Database object.
     *
     * @param db database url
     */
    public Database(String db) {
        this.db = db;
    }

    /**
     * Returns the database connection.
     *
     * @return a connection to the active database
     * @throws SQLException if no connection could be established
     */
    public Connection connection() throws SQLException {
        return DriverManager.getConnection(db);
    }

    /**
     *  Checks that a connection can be established.
     *
     * @return true on a successful connection
     */
    public boolean canConnect() {
        try {
            Connection c = this.connection();

            Statement stmt = c.createStatement();

            ResultSet result = stmt.executeQuery("SELECT 1");

            if (result.next()) {
                return true;
            }

            stmt.close();
            result.close();
            c.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return false;
    }
}
