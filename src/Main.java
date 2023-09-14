import il.ac.shenkar.view.View;

import javax.swing.*;

/**
 * The Main class contains the main entry point for the application.
 */
public class Main {
    public static void main(String[] args) {
        // Run the application on the Swing event dispatch thread
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // Create and initialize the main application view
                new View();
            }
        });
    }
}
