package il.ac.shenkar.exception;

/**
 * DBConnectionException is a custom exception class for handling errors related to database connections.
 */
public class DBConnectionException extends Exception {

        /**
         * Constructs a new DBConnectionException with the specified detail message.
         *
         * @param msg The detail message for the exception.
         */
        public DBConnectionException(String msg) {
                super(msg);
        }
}
