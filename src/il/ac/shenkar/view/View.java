package il.ac.shenkar.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View {

    private JFrame frame;
    private JPanel currentPanel;
    private JComboBox<String> categoryComboBox;
    private JComboBox<String> currencyComboBox;

    public View() {
        frame = createMainFrame();
        currentPanel = createHomePanel();
        frame.add(currentPanel);
        frame.setVisible(true);
    }

    private JFrame createMainFrame() {
        JFrame frame = new JFrame("Cost Manager - Pe'er & Itay");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        return frame;
    }

    private JPanel createHomePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel("Cost Manager", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        buttonPanel.setBackground(Color.WHITE);

        JButton addExpenseButton = createButton("Add Expense");
        addExpenseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchToPanel(createAddExpensePanel());
            }
        });

        JButton addCategoryButton = createButton("Add Category");
        addCategoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchToPanel(createAddCategoryPanel());
            }
        });

        JButton generateReportButton = createButton("Generate Report");
        generateReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchToPanel(createReportPanel());
            }
        });

        buttonPanel.add(addExpenseButton);
        buttonPanel.add(addCategoryButton);
        buttonPanel.add(generateReportButton);

        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createAddExpensePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel("Add Expense", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel categoryLabel = new JLabel("Category:");
        categoryLabel.setFont(new Font("Arial", Font.BOLD, 18));
        formPanel.add(categoryLabel, gbc);

        categoryComboBox = new JComboBox<>(new String[]{"Food", "Fuel", "Loans", "Clothes", "Rent", "Utilities", "Entertainment", "Healthcare", "Transportation", "Education", "Miscellaneous"});
        gbc.gridx = 1;
        formPanel.add(categoryComboBox, gbc);

        JLabel currencyLabel = new JLabel("Currency:");
        currencyLabel.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(currencyLabel, gbc);

        currencyComboBox = new JComboBox<>(new String[]{"JPY", "GBP", "CAD", "AUD", "NIS", "CHF", "USD", "EUR"});
        gbc.gridx = 1;
        formPanel.add(currencyComboBox, gbc);

        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(amountLabel, gbc);

        JTextField amountTextField = new JTextField(10);
        gbc.gridx = 1;
        formPanel.add(amountTextField, gbc);

        JLabel descriptionLabel = new JLabel("Description:");
        descriptionLabel.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(descriptionLabel, gbc);

        JTextArea descriptionTextArea = new JTextArea(4, 30);
        descriptionTextArea.setLineWrap(true);
        JScrollPane descriptionScrollPane = new JScrollPane(descriptionTextArea);
        gbc.gridx = 1;
        formPanel.add(descriptionScrollPane, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2; // Span two columns
        JButton submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.anchor = GridBagConstraints.CENTER; // Center the button
        formPanel.add(submitButton, gbc);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle expense submission here
                String category = (String) categoryComboBox.getSelectedItem();
                String currency = (String) currencyComboBox.getSelectedItem();
                String amount = amountTextField.getText();
                String description = descriptionTextArea.getText();

                // Process the expense data
                // ...

                // Reset form fields
                amountTextField.setText("");
                descriptionTextArea.setText("");
            }
        });

        JButton backButton = createButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchToPanel(createHomePanel());
            }
        });

        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(formPanel, BorderLayout.CENTER);
        panel.add(backButton, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createAddCategoryPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel("Add Category", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 5, 5, 5); // Margin from the top
        gbc.anchor = GridBagConstraints.WEST;

        JLabel categoryNameLabel = new JLabel("Category Name:");
        categoryNameLabel.setFont(new Font("Arial", Font.BOLD, 18));
        formPanel.add(categoryNameLabel, gbc);

        JTextField categoryNameTextField = new JTextField(20);
        gbc.gridx = 1;
        formPanel.add(categoryNameTextField, gbc);

        JButton addButton = new JButton("Submit");
        addButton.setFont(new Font("Arial", Font.BOLD, 18));
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String categoryName = categoryNameTextField.getText();
                // Process and add the new category here
                // ...

                // Clear the text field
                categoryNameTextField.setText("");
            }
        });

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2; // Span two columns
        gbc.anchor = GridBagConstraints.CENTER; // Center the button horizontally
        formPanel.add(addButton, gbc);

        JButton backButton = createButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchToPanel(createHomePanel());
            }
        });

        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(formPanel, BorderLayout.CENTER);
        panel.add(backButton, BorderLayout.SOUTH);

        return panel;
    }
    private JPanel createReportPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel("Reports", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 25));

        JPanel criteriaPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        criteriaPanel.setBackground(Color.WHITE);

        JLabel dayLabel = new JLabel("Day");
        dayLabel.setFont(new Font("Arial", Font.BOLD, 20));
        Integer[] days = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31};
        JComboBox<Integer> dayComboBox = new JComboBox<>(days);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        criteriaPanel.add(dayLabel, gbc);

        gbc.gridx = 1;
        criteriaPanel.add(dayComboBox, gbc);

        JLabel monthLabel = new JLabel("Month");
        monthLabel.setFont(new Font("Arial", Font.BOLD, 20));
        Integer[] months = {1,2,3,4,5,6,7,8,9,10,11,12};
        JComboBox<Integer> monthComboBox = new JComboBox<>(months);
        gbc.gridx = 0;
        gbc.gridy = 1;
        criteriaPanel.add(monthLabel, gbc);

        gbc.gridx = 1;
        criteriaPanel.add(monthComboBox, gbc);

        JLabel yearLabel = new JLabel("Year");
        yearLabel.setFont(new Font("Arial", Font.BOLD, 20));
        Integer[] years = {2023, 2022, 2021, 2020, 2019, 2018, 2017, 2016, 2015, 2014, 2013, 2012, 2011, 2010, 2009, 2008, 2007, 2006, 2005, 2004, 2003, 2002, 2001, 2000};
        JComboBox<Integer> yearComboBox = new JComboBox<>(years);
        gbc.gridx = 0;
        gbc.gridy = 2;
        criteriaPanel.add(yearLabel, gbc);

        gbc.gridx = 1;
        criteriaPanel.add(yearComboBox, gbc);

        JCheckBox specificDateCheckBox = new JCheckBox("Specific Date");
        specificDateCheckBox.setFont(new Font("Arial", Font.PLAIN, 16));
        specificDateCheckBox.setOpaque(false);
        specificDateCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dayComboBox.setEnabled(specificDateCheckBox.isSelected());
                monthComboBox.setEnabled(specificDateCheckBox.isSelected());
                yearComboBox.setEnabled(specificDateCheckBox.isSelected());
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        criteriaPanel.add(specificDateCheckBox, gbc);

        // Create 'create report' button
        JButton createButton = new JButton("Submit");
        createButton.setFont(new Font("Arial", Font.BOLD, 18));
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle report generation here based on the selected criteria
                String report = generateReport(
                        specificDateCheckBox.isSelected(),
                        (Integer) dayComboBox.getSelectedItem(),
                        (Integer) monthComboBox.getSelectedItem(),
                        (Integer) yearComboBox.getSelectedItem()
                );

                // Display the report or take further action
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        criteriaPanel.add(createButton, gbc);

        // Create back button
        JButton backButton = createButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchToPanel(createHomePanel());
            }
        });

        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(criteriaPanel, BorderLayout.CENTER);
        panel.add(backButton, BorderLayout.SOUTH);

        return panel;
    }


    private String generateReport(boolean specificDay, int day, int month, int year) {
        StringBuilder report = new StringBuilder("Report Criteria:\n");

        if (specificDay) {
            report.append("Specific Day: ").append(day).append("/").append(month).append("/").append(year).append("\n");
        } else {
            report.append("Full Month: ").append(month).append("/").append(year).append("\n");
        }

        // You can add more details and generate the actual report here
        // For example, fetching data from a database or processing existing data

        return report.toString();
    }

    private JButton createButton(String label) {
        JButton button = new JButton(label);
        button.setFont(new Font("Arial", Font.PLAIN, 18));
        return button;
    }

    private void switchToPanel(JPanel newPanel) {
        frame.remove(currentPanel);
        currentPanel = newPanel;
        frame.add(currentPanel);
        frame.revalidate();
        frame.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new View();
            }
        });
    }
}
