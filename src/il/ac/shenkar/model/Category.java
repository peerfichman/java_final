package il.ac.shenkar.model;

/**
 * Category class represents a category for costs.
 */
public class Category {
    private int id = -1; // Default ID value
    private String name;

    /**
     * Initializes a Category object with an ID and a name.
     *
     * @param id   The ID of the category.
     * @param name The name of the category.
     */
    public Category(int id, String name) {
        setId(id);
        setName(name);
    }

    /**
     * Gets the name of the category.
     *
     * @return The name of the category.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the ID of the category.
     *
     * @return The ID of the category.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the name of the category.
     *
     * @param name The name to set for the category.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the ID of the category.
     *
     * @param id The ID to set for the category.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Overrides the default toString method to provide a custom string representation.
     *
     * @return The name of the category.
     */
    @Override
    public String toString() {
        return name;
    }
}
