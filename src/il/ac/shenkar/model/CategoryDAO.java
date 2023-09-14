package il.ac.shenkar.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class CategoryDAO implements DAO<Category> {
    @Override
    public Category get(int id) {
        try {
            ResultSet cat = conn.stm.executeQuery("SELECT * FROM categories WHERE cat_id = " + id);
            cat.next();
            return new Category(cat.getInt("cat_id"), cat.getString("cat_name"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Category> getAll() {
        List<Category> cats = new LinkedList<>();
        try {
            ResultSet rs = conn.stm.executeQuery("SELECT * FROM  categories");
            while (rs.next()) {
                cats.add(new Category(
                        rs.getInt("cat_id"),
                        rs.getString("cat_name")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cats;
    }

    @Override
    public void save(Category c) {
        try {
            conn.stm.executeUpdate("INSERT INTO categories (cat_name) VALUES ('" + c.getName() + "')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(int id, Category c) {
        try {
            conn.stm.executeUpdate("UPDATE categories SET cat_name = '" + c.getName() + "'WHERE cat_id =" + c.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Category c) {
        try {
            conn.stm.execute("DELETE FROM categories WHERE cat_id =" + c.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}

