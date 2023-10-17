import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class Expensecal extends JFrame implements ActionListener {
    public JFrame expense;
    public JLabel enter, descriptionLabel, categoryLabel, amountLabel, entertxt, totalAmountLabel;
    public JButton add, addfield, display, viewExpenses,saveButton,loadButton;
    public JTextField description, category, amount;
    public JTextArea displayArea;
    public JScrollPane scrollPane;
    public JLabel searchLabel;
    public JTextField searchField;
    public JButton searchButton;

    private static final String DATA_FILE = "C:\\Users\\VINAY HM\\IdeaProjects\\INTERNSHIP_MOTION_CUT\\expenses.txt";
    class Expense implements Serializable {
        String description;
        String category;
        double amount;

        Expense(String description, String category, double amount) {
            this.description = description;
            this.category = category;
            this.amount = amount;
        }
    }

    private List<Expense> expenses = new ArrayList<>();

    public Expensecal() {
        expense = new JFrame("EXPENSE CALCULATOR");
        expense.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        expense.getContentPane().setBackground(Color.BLUE);

        enter = new JLabel(" SIMPLE EXPENSE CALCULATOR  ");
        enter.setBounds(180, 20, 240, 20);
        enter.setForeground(Color.WHITE);

        entertxt = new JLabel(" ENTER THE FOLLOWING FIELDS ");
        entertxt.setBounds(180, 40, 240, 20);
        entertxt.setForeground(Color.WHITE);

        descriptionLabel = new JLabel("Description : ");
        descriptionLabel.setBounds(50, 70, 100, 30);
        descriptionLabel.setForeground(Color.WHITE);
        descriptionLabel.setBackground(Color.BLUE);
        descriptionLabel.setOpaque(true);

        description = new JTextField();
        description.setBounds(160, 70, 250, 30);
        description.setBackground(Color.WHITE);

        categoryLabel = new JLabel("Category   : ");
        categoryLabel.setBounds(50, 110, 100, 30);
        categoryLabel.setForeground(Color.WHITE);
        categoryLabel.setBackground(Color.BLUE);
        categoryLabel.setOpaque(true);

        category = new JTextField();
        category.setBounds(160, 110, 250, 30);
        category.setBackground(Color.WHITE);

        amountLabel = new JLabel("Amount     : ");
        amountLabel.setBounds(50, 150, 100, 30);
        amountLabel.setForeground(Color.WHITE);
        amountLabel.setBackground(Color.BLUE);
        amountLabel.setOpaque(true);

        amount = new JTextField();
        amount.setBounds(160, 150, 250, 30);
        amount.setBackground(Color.WHITE);

        addfield = new JButton("ADD and ADD ANOTHER EXPENSE");
        addfield.setBounds(250, 200, 280, 35);
        addfield.addActionListener(this);
        addfield.setBackground(Color.GREEN);
        addfield.setForeground(Color.WHITE);


        display = new JButton("VIEW EXPENSES");
        display.setBounds(350, 250, 170, 30);
        display.addActionListener(this);
        display.setBackground(Color.GREEN);
        display.setBackground(Color.white);

        displayArea = new JTextArea();
        displayArea.setBounds(50, 390, 360, 200);
        displayArea.setEditable(false);
        displayArea.setVisible(false);
        displayArea.setBackground(Color.WHITE);
        displayArea.setForeground(Color.BLACK);

        scrollPane = new JScrollPane(displayArea);
        scrollPane.setBounds(50, 390, 360, 200);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setVisible(false);

        searchLabel = new JLabel("Search Expense: ");
        searchLabel.setBounds(50, 350, 150, 30);
        searchLabel.setForeground(Color.WHITE);
        searchLabel.setBackground(Color.BLUE);
        searchLabel.setOpaque(true);

        searchField = new JTextField();
        searchField.setBounds(200, 350, 210, 30);
        searchField.setBackground(Color.WHITE);

        searchButton = new JButton("Search Expense");
        searchButton.setBounds(420, 350, 160, 30);
        searchButton.addActionListener(this);
        searchButton.setBackground(Color.GREEN);
        searchButton.setForeground(Color.WHITE);

        saveButton = new JButton("Save Data");
        saveButton.setBounds(50, 250, 120, 30);
        saveButton.addActionListener(this);
        saveButton.setBackground(Color.GREEN);
        saveButton.setForeground(Color.WHITE);

        loadButton = new JButton("Load Data");
        loadButton.setBounds(200, 250, 120, 30);
        loadButton.addActionListener(this);
        loadButton.setBackground(Color.GREEN);
        loadButton.setForeground(Color.WHITE);



        expense.add(enter);
        expense.add(entertxt);
        expense.add(descriptionLabel);
        expense.add(description);
        expense.add(categoryLabel);
        expense.add(category);
        expense.add(amountLabel);
        expense.add(amount);
        expense.add(addfield);
        expense.add(display);
        expense.add(scrollPane);
        expense.add(searchLabel);
        expense.add(searchField);
        expense.add(searchButton);
        expense.add(saveButton);
        expense.add(loadButton);

        expense.setSize(600, 600);
        expense.setLayout(null);
        expense.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Expensecal();
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addfield) {

            addExpense();
            description.setText("");
            category.setText("");
            amount.setText("");
        }

        if (e.getSource() == display) {
            if (expenses.isEmpty()) {
                JOptionPane.showMessageDialog(expense, "No expenses to display.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                displayExpenses();
                displayArea.setVisible(true);
                scrollPane.setVisible(true);
            }
        }
        if (e.getSource() == searchButton) {
            String searchKeyword = searchField.getText();
            if (!searchKeyword.isEmpty()) {
                searchExpenses(searchKeyword);
            }
        }
        if (e.getSource() == saveButton) {
            saveData();
        }
        if (e.getSource() == loadButton) {
            loadData();
        }
    }


    private boolean addExpense() {
        String inputDescription = description.getText();
        String inputCategory = category.getText();
        String amountText = amount.getText();

        if (inputDescription.isEmpty() || inputCategory.isEmpty() || amountText.isEmpty()) {
            JOptionPane.showMessageDialog(expense, "All fields must be filled out.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        try {
            double inputAmount = Double.parseDouble(amountText);
            if (inputAmount < 0) {
                JOptionPane.showMessageDialog(expense, "Amount must be a positive number.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            expenses.add(new Expense(inputDescription, inputCategory, inputAmount));
            return true;
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(expense, "Invalid amount format. Please enter a valid number.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    private void displayExpenses() {
        double totalAmount = 0;

        displayArea.setText(""); // Clear the display area

        if (expenses.isEmpty()) {
            displayArea.append("No expenses to display.");
        } else {
            for (Expense expense : expenses) {
                String displayText = "Description: " + expense.description + "\n" +
                        "Category: " + expense.category + "\n" +
                        "Amount: " + expense.amount + "\n\n";

                displayArea.append(displayText);
                totalAmount += expense.amount;
            }
        }

        // Set the total amount label in red
        String totalAmountText = "Total Amount Spent: " + totalAmount;
        displayArea.append(totalAmountText);

        // Create a separate label for the total amount and set its color to red
        if (totalAmountLabel != null) {
            expense.remove(totalAmountLabel); // Remove the previous label before adding a new one.
        }
        totalAmountLabel = new JLabel(totalAmountText);
        totalAmountLabel.setForeground(Color.RED);
        totalAmountLabel.setBounds(50, 500, 800, 100);
        expense.add(totalAmountLabel);

        // Repaint the JFrame
        expense.revalidate();
        expense.repaint();
    }

    private void searchExpenses(String keyword) {
        StringBuilder searchResult = new StringBuilder();
        boolean found = false;

        for (Expense expense : expenses) {
            if (expense.description.toLowerCase().equals(keyword.toLowerCase())) {
                String displayText = "Description: " + expense.description + "\n" +
                        "Category: " + expense.category + "\n" +
                        "Amount: " + expense.amount + "\n\n";

                searchResult.append(displayText);
                found = true;
            }
        }

        if (!found) {
            searchResult.append("Expense not found.");
        }
        JOptionPane.showMessageDialog(expense, searchResult.toString(), "Search Results", JOptionPane.INFORMATION_MESSAGE);
    }
    private void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            oos.writeObject(expenses);
            JOptionPane.showMessageDialog(expense, "Data saved successfully.", "Data Saved", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(expense, "Failed to save data.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void loadData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
            expenses = (List<Expense>) ois.readObject();
            JOptionPane.showMessageDialog(expense, "Data loaded successfully.", "Data Loaded", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(expense, "Failed to load data.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}

