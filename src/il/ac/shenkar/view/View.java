package il.ac.shenkar.view;

import il.ac.shenkar.model.Category;
import il.ac.shenkar.model.Cost;
import il.ac.shenkar.model.Currency;
import il.ac.shenkar.viewmodel.ViewModel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.sql.Date;

public class View {

    private JFrame frame;
    private JPanel currentPanel;
    private JComboBox<Category> categoryComboBox;
    private JComboBox<Currency> currencyComboBox;
    private ViewModel VM = new ViewModel();
    private List<Category> categories;
    private JPanel reportPanel = createReportDisplayPanel();


    private void updateCategories(){
        categories = VM.getAllCategories();
    }

    private String getCategoryNameByID(int id)
    {
        for(Category c : categories)
        {
            if(c.getId() == id)
                return c.getName();
        }
        return "none";
    }

    public View() {
        categories = VM.getAllCategories();
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

        DefaultComboBoxModel<Category> categoryDefaultComboBoxModel = new DefaultComboBoxModel<>(categories.toArray(new Category[0]));
        categoryComboBox = new JComboBox<>(categoryDefaultComboBoxModel);
        gbc.gridx = 1;
        formPanel.add(categoryComboBox, gbc);

        JLabel currencyLabel = new JLabel("Currency:");
        currencyLabel.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy++;
        formPanel.add(currencyLabel, gbc);

        Currency[] currenciesList = Currency.values();
        Arrays.sort(currenciesList, Comparator.comparing(Enum::name));
        currencyComboBox = new JComboBox<>(currenciesList);
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

        // Disable the submit button initially
        submitButton.setEnabled(false);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle expense submission here
                Category category = (Category) categoryComboBox.getSelectedItem();
                Currency currency = (Currency) currencyComboBox.getSelectedItem();
                String amountText = amountTextField.getText();
                Double amount = Double.parseDouble(amountText);
                String description = descriptionTextArea.getText();

                if (amount > 0) {
                    Cost newCost = new Cost(amount, category.getId(), currency, description, new Date(System.currentTimeMillis()));
                    VM.addCost(newCost);

                    // Reset form fields
                    amountTextField.setText("");
                    descriptionTextArea.setText("");
                }
            }
        });

        // Add a document listener to the amountTextField to enable/disable the submit button based on the amount
        amountTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateSubmitButton();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateSubmitButton();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateSubmitButton();
            }

            private void updateSubmitButton() {
                try {
                    String amountText = amountTextField.getText();
                    Double amount = Double.parseDouble(amountText);
                    submitButton.setEnabled(amount > 0);
                } catch (NumberFormatException ex) {
                    submitButton.setEnabled(false);
                }
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
                VM.addCategory(new Category(-1,categoryName));
                updateCategories();
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
        dayComboBox.setEnabled(false);
        dayLabel.setEnabled(false);
        JCheckBox specificDateCheckBox = new JCheckBox("Specific Date");
        specificDateCheckBox.setFont(new Font("Arial", Font.PLAIN, 16));
        specificDateCheckBox.setOpaque(false);
        specificDateCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                boolean isChecked = specificDateCheckBox.isSelected();
                dayComboBox.setEnabled(isChecked);
                dayLabel.setEnabled(isChecked);
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        criteriaPanel.add(specificDateCheckBox, gbc);

        // Create 'create report' button
        JButton createButton = new JButton("Submit");
        createButton.setFont(new Font("Arial", Font.BOLD, 18));

        // Define scrollPane and backButton here
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Category");
        tableModel.addColumn("Amount");
        tableModel.addColumn("Currency");
        tableModel.addColumn("Date");
        tableModel.addColumn("Description");

        JScrollPane scrollPane = new JScrollPane(new JTable(tableModel));

        JButton backButton = createButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchToPanel(createHomePanel());
            }
        });

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (specificDateCheckBox.isSelected()) // specific date with day month year.
                {

                    int selectedYear = (Integer) yearComboBox.getSelectedItem();
                    int selectedMonth = (Integer) monthComboBox.getSelectedItem();
                    int selectedDay = (Integer) dayComboBox.getSelectedItem();
                    LocalDate localDate = LocalDate.of(selectedYear, selectedMonth, selectedDay);
                    java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);
                    List<Cost> reportDataList = VM.getCostsByDate(sqlDate);
                    for (Cost data : reportDataList) {
                        tableModel.addRow(new Object[]{
                                getCategoryNameByID(data.getCategoryID()),
                                data.getSum(),
                                data.getCurrency(),
                                data.getTime().toString(),
                                data.getDesc()
                        });
                    }
                    reportPanel.removeAll();
                    reportPanel.add(scrollPane, BorderLayout.CENTER);
                    reportPanel.add(backButton, BorderLayout.SOUTH);
                    reportPanel.setVisible(true);
                    switchToPanel(reportPanel);
                } else // only month and year (without a day)
                {

                    int selectedYear = (Integer) yearComboBox.getSelectedItem();
                    int selectedMonth = (Integer) monthComboBox.getSelectedItem();
                    List<Cost> reportDataList = VM.getCostsByMonth(selectedYear, selectedMonth);
                    for (Cost data : reportDataList) {
                        tableModel.addRow(new Object[]{
                                getCategoryNameByID(data.getCategoryID()),
                                data.getSum(),
                                data.getCurrency(),
                                data.getTime().toString(),
                                data.getDesc()
                        });
                    }
                    reportPanel.removeAll();
                    reportPanel.add(scrollPane, BorderLayout.CENTER);
                    reportPanel.add(backButton, BorderLayout.SOUTH);
                    reportPanel.setVisible(true);
                    switchToPanel(reportPanel);
                }
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        criteriaPanel.add(createButton, gbc);

        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(criteriaPanel, BorderLayout.CENTER);
        panel.add(backButton, BorderLayout.SOUTH);

        return panel;
    }
    private JPanel createReportDisplayPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);

        DefaultTableModel tableModel = new DefaultTableModel();
        JTable reportTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(reportTable);

        // Set the table to fill the entire available space
        scrollPane.setPreferredSize(new Dimension(600, 400)); // Adjust the dimensions as needed

        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton backButton = createButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchToPanel(createReportPanel());
            }
        });
        buttonPanel.add(backButton);

        // Locate the back button at the bottom of the screen
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
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


}
