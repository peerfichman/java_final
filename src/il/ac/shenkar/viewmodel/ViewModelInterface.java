package il.ac.shenkar.viewmodel;

import il.ac.shenkar.model.Category;
import il.ac.shenkar.model.Cost;

import java.sql.Date;
import java.util.List;

public interface ViewModelInterface {

    public List<Category> getAllCategories () throws Exception;
    public void addCategory (Category c)throws Exception;
    public void deleteCategory (Category c)throws Exception;
    public void editCategory (int id, Category c)throws Exception;
    public List<Cost> getAllCosts ()throws Exception;
    public List<Cost> getCostsByDate(Date date)throws Exception;
    public void addCost (Cost c)throws Exception;
    public void deleteCost (Cost c)throws Exception;
    public void editCost (int id, Cost c)throws Exception;

}
