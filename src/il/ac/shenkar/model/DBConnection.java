package il.ac.shenkar.model;

import java.sql.*;


public class DBConnection implements AutoCloseable{
    public Statement stm = null;
    private Connection conn = null;
    private static DBConnection accountingDB = null;

    private DBConnection()  {
        try {
            conn = DriverManager.getConnection("jdbc:derby:accountingDB ;create=true");
            stm = conn.createStatement();
        } catch (SQLException e) {
           e.printStackTrace();
        }

        try{
            stm.executeUpdate(
                        "CREATE TABLE categories ( " +
                            "cat_id INT GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY, " +
                            "cat_name VARCHAR(50) NOT NULL )"
            );
        } catch (SQLException e) {
            if (!e.getSQLState().equals("X0Y32"))
                e.printStackTrace();
        }
        try{
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
                e.printStackTrace();
        }

    }
    public static synchronized DBConnection getInstance()
    {
        if (accountingDB == null)
            accountingDB = new DBConnection();
        return accountingDB;
    }
    @Override
    public void close(){
        try {
            if (stm != null) stm.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
