package servletwebapp.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqliteConnection {

    private static SqliteConnection instance = null;
    private Connection connection = null;
    private static final String DBNAME = "webapp/WEB-INF/db/database";

    private SqliteConnection() {
        getConnectionToDatabase();
    }

    public static SqliteConnection getInstance() {        //klasyczny singleton
        if (instance == null) {
            instance = new SqliteConnection();
        }
        return instance;
    }

    public void doRollBack() throws SQLException {
        connection.rollback();
    }

    private void getConnectionToDatabase() {
        try {
            Class.forName("org.sqlite.JDBC");
            this.connection = DriverManager.getConnection(DBNAME);
        } catch (SQLException e) {
            System.out.println(e.getClass() + ": " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println(e.getClass() + ": " + e.getMessage());
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return this.connection;
    }

    public void closeConnection() {
        if (this.connection != null) {
            try {
                this.connection.close();
            } catch (SQLException e) {
                /* ignored */
            }
        }
    }
}
