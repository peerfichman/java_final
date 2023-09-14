package il.ac.shenkar.viewmodel;

import il.ac.shenkar.model.Category;
import il.ac.shenkar.model.Cost;

import java.sql.Date;
import java.util.List;

public interface ViewModelInterface {
    public Category getCategory(int id);
    public Cost getCost(int id);
    public List<Category> getAllCategories ();
    public void addCategory (Category c);
    public void deleteCategory (Category c);
    public void editCategory (int id, Category c);
    public List<Cost> getAllCosts ();
    public List<Cost> getCostsByDate(Date date);
    public void addCost (Cost c);
    public void deleteCost (Cost c);
    public void editCost (int id, Cost c);

}
