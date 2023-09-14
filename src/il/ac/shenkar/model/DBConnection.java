package il.ac.shenkar.model;
import il.ac.shenkar.exception.DBConnectionException;
import java.sql.*;

/**
 * DBConnection class is responsible for managing the database connection.
 * It is implemented as a singleton to ensure a single connection instance.
 */
public class DBConnection implements AutoCloseable {
    public Statement stm = null;
    private Connection conn = null;
    private static DBConnection accountingDB = null;

    /**
     * Private constructor to create a database connection.
     *
     * @throws DBConnectionException If an error occurs during database connection setup.
     */
    private DBConnection() throws DBConnectionException {
        try {
            conn = DriverManager.getConnection("jdbc:derby:accountingDB;create=true");
            stm = conn.createStatement();
        } catch (SQLException e) {
            throw new DBConnectionException(e.getMessage());
        }

        try {
            stm.executeUpdate(
                    "CREATE TABLE categories ( " +
                            "cat_id INT GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY, " +
                            "cat_name VARCHAR(50) NOT NULL )"
            );
        } catch (SQLException e) {
            if (!e.getSQLState().equals("X0Y32"))
                throw new DBConnectionException(e.getMessage());
        }
        try {
            stm.executeUpdate("CREATE TABLE costs ( " +
                    "cost_id INT GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY, " +
                    "cat_id INT NOT NULL, " +
                    "amount DECIMAL(10,2) NOT NULL, " +
                    "currency VARCHAR(10) NOT NULL, " +
                    "description VARCHAR(255), " +
                    "date DATE DEFAULT CURRENT_DATE NOT NULL, " +
                    "FOREIGN KEY (cat_id) REFERENCES categories(cat_id))");
        } catch (SQLException e) {
            if (!e.getSQLState().equals("X0Y32"))
                throw new DBConnectionException(e.getMessage());
        }
    }

    /**
     * Get an instance of the DBConnection (Singleton pattern).
     *
     * @return An instance of the DBConnection.
     * @throws DBConnectionException If an error occurs during instance creation.
     */
    public static synchronized DBConnection getInstance() throws DBConnectionException {
        if (accountingDB == null)
            accountingDB = new DBConnection();
        return accountingDB;
    }

    /**
     * Closes the database connection and statement.
     *
     * @throws DBConnectionException If an error occurs during closing.
     */
    @Override
    public void close() throws DBConnectionException {
        try {
            if (stm != null) stm.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            throw new DBConnectionException(e.getMessage());
        }
    }
}
