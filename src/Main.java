import il.ac.shenkar.model.*;

public class Main {
    public static void main(String[] args) {

        DBConnection db = DBConnection.getInstance();
        db.addCategory(new Category("test1"));
        db.addCategory(new Category("test2"));
        db.addCategory(new Category("test3"));
        db.getAllCategories();
        db.deleteCategory(new Category("test1"));
        db.getAllCategories();
        db.deleteCategory(new Category("test2"));
        db.getAllCategories();
        db.deleteCategory(new Category("test3"));

        db.getAllCategories();

        db.DBCloseConnection();
    }

}


