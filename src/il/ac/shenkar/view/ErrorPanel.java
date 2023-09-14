package il.ac.shenkar.view;
import javax.swing.*;
import java.awt.*;

public class ErrorPanel extends JPanel {
    private JLabel errorLabel;

    public ErrorPanel() {
        setLayout(new BorderLayout());
        errorLabel = new JLabel();
        add(errorLabel, BorderLayout.CENTER);
    }

    public void setErrorText(String errorMessage) {
        errorLabel.setForeground(Color.RED);
        errorLabel.setText(errorMessage);
    }
    public void setSuccessText(String successMessage) {
        errorLabel.setForeground(Color.GREEN);
        errorLabel.setText(successMessage);
    }
}
