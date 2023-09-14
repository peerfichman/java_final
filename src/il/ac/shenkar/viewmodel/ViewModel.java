package il.ac.shenkar.viewmodel;

import il.ac.shenkar.exception.CategoryDAOException;
import il.ac.shenkar.exception.CostDAOException;
import il.ac.shenkar.view.*;
import il.ac.shenkar.model.*;

import java.sql.Date;
import java.util.List;


public class ViewModel implements ViewModelInterface {

    private final CategoryDAO categoryDAO = new CategoryDAO();
    private final CostDAO costDAO = new CostDAO();

    @Override
    public List<Category> getAllCategories()throws CategoryDAOException {
        return categoryDAO.getAll();
    }

    @Override
    public void addCategory(Category c) throws CategoryDAOException {
        categoryDAO.save(c);
    }

    @Override
    public void deleteCategory(Category c)throws CategoryDAOException {
        categoryDAO.delete(c);

    }

    @Override
    public void editCategory(int id, Category c)throws CategoryDAOException {
        categoryDAO.update(id, c);
    }

    @Override
    public List<Cost> getAllCosts() throws CostDAOException {
        return costDAO.getAll();
    }

    @Override
    public List<Cost> getCostsByDate(Date date)throws CostDAOException {
        return costDAO.getCostsByDate(date);
    }

    @Override
    public void addCost(Cost c) throws CostDAOException {
        costDAO.save(c);
    }

    @Override
    public void deleteCost(Cost c) throws CostDAOException {
        costDAO.delete(c);
    }

    @Override
    public void editCost(int id, Cost c) throws CostDAOException{
        costDAO.update(id, c);

    }

    public List<Cost> getCostsByMonth(int year,int month) throws CostDAOException{ return costDAO.getCostsByMonth(year,month);}

}
