package il.ac.shenkar.model;

import java.sql.Date;

/**
 * Cost class represents a cost object with various properties.
 */
public class Cost {

    private int id;
    private double sum;
    private int categoryID;
    private Currency currency;
    private String desc;
    private Date time;

    /**
     * Constructor for creating a Cost object.
     *
     * @param sum        The cost amount.
     * @param categoryID The ID of the associated category.
     * @param currency   The currency of the cost.
     * @param desc       The description of the cost.
     * @param time       The date and time when the cost occurred.
     */
    public Cost(double sum, int categoryID, Currency currency, String desc, Date time) {
        setSum(sum);
        setCategoryID(categoryID);
        setCurrency(currency);
        setDesc(desc);
        setTime(time);
    }

    /**
     * Setter for the cost ID.
     *
     * @param id The ID to set.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Setter for the cost amount.
     *
     * @param sum The amount to set.
     */
    public void setSum(double sum) {
        this.sum = sum;
    }

    /**
     * Setter for the category ID associated with the cost.
     *
     * @param categoryID The category ID to set.
     */
    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    /**
     * Setter for the currency of the cost.
     *
     * @param currency The currency to set.
     */
    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    /**
     * Getter for the cost ID.
     *
     * @return The cost ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Setter for the cost description.
     *
     * @param desc The description to set.
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * Setter for the date and time when the cost occurred.
     *
     * @param time The date and time to set.
     */
    public void setTime(Date time) {
        this.time = time;
    }

    /**
     * Getter for the cost amount.
     *
     * @return The cost amount.
     */
    public double getSum() {
        return sum;
    }

    /**
     * Getter for the category ID associated with the cost.
     *
     * @return The category ID.
     */
    public int getCategoryID() {
        return categoryID;
    }

    /**
     * Getter for the currency of the cost.
     *
     * @return The currency of the cost.
     */
    public Currency getCurrency() {
        return currency;
    }

    /**
     * Getter for the cost description.
     *
     * @return The cost description.
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Getter for the date and time when the cost occurred.
     *
     * @return The date and time of the cost.
     */
    public Date getTime() {
        return time;
    }
}
