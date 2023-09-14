package il.ac.shenkar.exception;

/**
 * CostDAOException is a custom exception class for handling errors related to CostDAO operations.
 */
public class CostDAOException extends Exception {

    /**
     * Constructs a new CostDAOException with the specified detail message.
     *
     * @param msg The detail message for the exception.
     */
    public CostDAOException(String msg) {
        super(msg);
    }
}
