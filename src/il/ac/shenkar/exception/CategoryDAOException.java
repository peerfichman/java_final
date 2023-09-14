package il.ac.shenkar.exception;

/**
 * CategoryDAOException is a custom exception class for handling errors related to CategoryDAO operations.
 */
public class CategoryDAOException extends Exception {

    /**
     * Constructs a new CategoryDAOException with the specified detail message.
     *
     * @param msg The detail message for the exception.
     */
    public CategoryDAOException(String msg) {
        super(msg);
    }
}
