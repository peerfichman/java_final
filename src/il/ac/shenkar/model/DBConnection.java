package il.ac.shenkar.model;

import java.sql.*;


public class DBConnection implements DBInterface{
    private Statement stm = null;
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
                    "costs_id INT GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY, " +
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
    public void DBCloseConnection(){
        try {
            if (stm != null) stm.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addCategory(Category c) {
        try {
            stm.execute("INSERT INTO categories (cat_name) VALUES('" + c.getName() + "')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void getAllCategories(){
        try
        {
            ResultSet results = stm.executeQuery("SELECT * FROM categories");
            System.out.println("\n-------------------------------------------------");

            while(results.next())
            {
                int id = results.getInt(1);
                String cat = results.getString(2);
                System.out.println(id + "\t\t" + cat );
            }
            results.close();
        }
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
        }
}
    @Override
    public void deleteCategory(Category c){
        try {
            stm.executeUpdate("DELETE FROM categories WHERE cat_name = '"+ c.getName() +"'");
        } catch (SQLException e) {
           e.printStackTrace();
        }
    }
}
