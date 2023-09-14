package il.ac.shenkar.viewmodel;

import il.ac.shenkar.exception.CategoryDAOException;
import il.ac.shenkar.exception.CostDAOException;
import il.ac.shenkar.view.*;
import il.ac.shenkar.model.*;

import java.sql.Date;
import java.util.List;

/**
 * ViewModel is responsible for providing an interface between the view and the model.
 * It implements ViewModelInterface, defining the methods for managing categories and costs.
 */
public class ViewModel implements ViewModelInterface {

    private final CategoryDAO categoryDAO = new CategoryDAO();
    private final CostDAO costDAO = new CostDAO();

    /**
     * Retrieves a list of all categories.
     *
     * @return A list of Category objects.
     * @throws CategoryDAOException If an error occurs during category retrieval.
     */
    @Override
    public List<Category> getAllCategories() throws CategoryDAOException {
        return categoryDAO.getAll();
    }

    /**
     * Adds a new category.
     *
     * @param c The Category object to be added.
     * @throws CategoryDAOException If an error occurs during category addition.
     */
    @Override
    public void addCategory(Category c) throws CategoryDAOException {
        categoryDAO.save(c);
    }

    /**
     * Deletes a category.
     *
     * @param c The Category object to be deleted.
     * @throws CategoryDAOException If an error occurs during category deletion.
     */
    @Override
    public void deleteCategory(Category c) throws CategoryDAOException {
        categoryDAO.delete(c);
    }

    /**
     * Edits an existing category.
     *
     * @param id The ID of the category to be edited.
     * @param c  The updated Category object.
     * @throws CategoryDAOException If an error occurs during category editing.
     */
    @Override
    public void editCategory(int id, Category c) throws CategoryDAOException {
        categoryDAO.update(id, c);
    }

    /**
     * Retrieves a list of all costs.
     *
     * @return A list of Cost objects.
     * @throws CostDAOException If an error occurs during cost retrieval.
     */
    @Override
    public List<Cost> getAllCosts() throws CostDAOException {
        return costDAO.getAll();
    }

    /**
     * Retrieves a list of costs for a specific date.
     *
     * @param date The date for which costs are retrieved.
     * @return A list of Cost objects for the specified date.
     * @throws CostDAOException If an error occurs during cost retrieval by date.
     */
    @Override
    public List<Cost> getCostsByDate(Date date) throws CostDAOException {
        return costDAO.getCostsByDate(date);
    }

    /**
     * Adds a new cost entry.
     *
     * @param c The Cost object to be added.
     * @throws CostDAOException If an error occurs during cost addition.
     */
    @Override
    public void addCost(Cost c) throws CostDAOException {
        costDAO.save(c);
    }

    /**
     * Deletes a cost entry.
     *
     * @param c The Cost object to be deleted.
     * @throws CostDAOException If an error occurs during cost deletion.
     */
    @Override
    public void deleteCost(Cost c) throws CostDAOException {
        costDAO.delete(c);
    }

    /**
     * Edits an existing cost entry.
     *
     * @param id The ID of the cost entry to be edited.
     * @param c  The updated Cost object.
     * @throws CostDAOException If an error occurs during cost editing.
     */
    @Override
    public void editCost(int id, Cost c) throws CostDAOException {
        costDAO.update(id, c);
    }

    /**
     * Retrieves a list of costs for a specific month and year.
     *
     * @param year  The year for which costs are retrieved.
     * @param month The month for which costs are retrieved.
     * @return A list of Cost objects for the specified month and year.
     * @throws CostDAOException If an error occurs during cost retrieval by month and year.
     */
    public List<Cost> getCostsByMonth(int year, int month) throws CostDAOException {
        return costDAO.getCostsByMonth(year, month);
    }
}
