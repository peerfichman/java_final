package il.ac.shenkar.model;

import il.ac.shenkar.exception.CategoryDAOException;
import il.ac.shenkar.exception.DBConnectionException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class CategoryDAO implements DAO<Category> {
    private static final DBConnection conn;

    static {
        try {
            conn = DBConnection.getInstance();
        } catch (DBConnectionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Category get(int id) throws Exception {


        try {
            ResultSet cat = conn.stm.executeQuery("SELECT * FROM categories WHERE cat_id = "+ id);
            cat.next();
            return new Category(cat.getInt("cat_id"), cat.getString("cat_name"));
        } catch (SQLException e) {
           throw new CategoryDAOException(e.getMessage());
        }
    }
    @Override
    public List<Category> getAll() throws CategoryDAOException{
        List <Category> cats = new LinkedList<>();
        try {
            ResultSet rs = conn.stm.executeQuery("SELECT * FROM  categories");
            while(rs.next()){
                cats.add(new Category(
                        rs.getInt("cat_id"),
                        rs.getString("cat_name")
                ));
            }
        } catch (SQLException e) {
            throw new CategoryDAOException(e.getMessage());
        }
        return cats;
    }

    @Override
    public void save(Category c) throws CategoryDAOException{
        try {
            conn.stm.executeUpdate("INSERT INTO categories (cat_name) VALUES ('"+ c.getName()+"')");
        } catch (SQLException e) {
           throw new CategoryDAOException(e.getMessage()); }
    }
    @Override
    public void update(int id, Category c) throws CategoryDAOException {
        try {
            conn.stm.executeUpdate("UPDATE categories SET cat_name = '"+ c.getName()+"'WHERE cat_id =" + c.getId());
        } catch (SQLException e) {
            throw new CategoryDAOException(e.getMessage());
        }
    }

    @Override
    public void delete(Category c) throws CategoryDAOException {
        try {
            conn.stm.execute("DELETE FROM categories WHERE cat_id =" + c.getId());
        } catch (SQLException e) {
            throw new CategoryDAOException(e.getMessage());
        }
    }
}
