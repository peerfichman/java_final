package il.ac.shenkar.viewmodel;

import il.ac.shenkar.view.*;
import il.ac.shenkar.model.*;

import java.sql.Date;
import java.util.List;


public class ViewModel implements ViewModelInterface {

    private final CategoryDAO categoryDAO = new CategoryDAO();
    private final CostDAO costDAO = new CostDAO();

    @Override
    public Category getCategory(int id){
        return categoryDAO.get(id);
    }

    @Override
    public Cost getCost(int id) {
        return costDAO.get(id);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryDAO.getAll();
    }

    @Override
    public void addCategory(Category c) {
        categoryDAO.save(c);
    }

    @Override
    public void deleteCategory(Category c) {
        categoryDAO.delete(c);

    }

    @Override
    public void editCategory(int id, Category c) {
        categoryDAO.update(id, c);
    }

    @Override
    public List<Cost> getAllCosts() {
        return costDAO.getAll();
    }

    @Override
    public List<Cost> getCostsByDate(Date date) {
        return costDAO.getCostsByDate(date);
    }

    @Override
    public void addCost(Cost c) {
        costDAO.save(c);
    }

    @Override
    public void deleteCost(Cost c) {
        costDAO.delete(c);
    }

    @Override
    public void editCost(int id, Cost c) {
        costDAO.update(id, c);

    }

    public List<Cost> getCostsByMonth(int year,int month) { return costDAO.getCostsByMonth(year,month);}

}
