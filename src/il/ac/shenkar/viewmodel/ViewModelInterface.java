package il.ac.shenkar.viewmodel;

import il.ac.shenkar.model.Category;
import il.ac.shenkar.model.Cost;

import java.sql.Date;
import java.util.List;

/**
 * ViewModelInterface defines the interface for the ViewModel class.
 * It specifies the methods that the ViewModel should implement.
 */
public interface ViewModelInterface {

    /**
     * Retrieves a list of all categories.
     *
     * @return A list of Category objects.
     * @throws Exception If an error occurs during the operation.
     */
    public List<Category> getAllCategories() throws Exception;

    /**
     * Adds a new category.
     *
     * @param c The Category object to be added.
     * @throws Exception If an error occurs during the operation.
     */
    public void addCategory(Category c) throws Exception;

    /**
     * Deletes a category.
     *
     * @param c The Category object to be deleted.
     * @throws Exception If an error occurs during the operation.
     */
    public void deleteCategory(Category c) throws Exception;

    /**
     * Edits an existing category.
     *
     * @param id The ID of the category to be edited.
     * @param c  The updated Category object.
     * @throws Exception If an error occurs during the operation.
     */
    public void editCategory(int id, Category c) throws Exception;

    /**
     * Retrieves a list of all costs.
     *
     * @return A list of Cost objects.
     * @throws Exception If an error occurs during the operation.
     */
    public List<Cost> getAllCosts() throws Exception;

    /**
     * Retrieves a list of costs for a specific date.
     *
     * @param date The date for which costs are retrieved.
     * @return A list of Cost objects for the specified date.
     * @throws Exception If an error occurs during the operation.
     */
    public List<Cost> getCostsByDate(Date date) throws Exception;

    /**
     * Adds a new cost entry.
     *
     * @param c The Cost object to be added.
     * @throws Exception If an error occurs during the operation.
     */
    public void addCost(Cost c) throws Exception;

    /**
     * Deletes a cost entry.
     *
     * @param c The Cost object to be deleted.
     * @throws Exception If an error occurs during the operation.
     */
    public void deleteCost(Cost c) throws Exception;

    /**
     * Edits an existing cost entry.
     *
     * @param id The ID of the cost entry to be edited.
     * @param c  The updated Cost object.
     * @throws Exception If an error occurs during the operation.
     */
    public void editCost(int id, Cost c) throws Exception;
}
