package namegenerator.dao;

import java.sql.*;

public class Database {

    private String db;

    public Database(String db) {
        this.db = db;
    }

    public Connection connection() throws SQLException {
        return DriverManager.getConnection(db);
    }

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
