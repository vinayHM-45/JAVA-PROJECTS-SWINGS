import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Expensecal extends JFrame implements ActionListener {
    public JFrame expense;
    public JLabel enter, descriptionLabel, categoryLabel, amountLabel, entertxt, totalAmountLabel;
    public JButton add, addfield, display, viewExpenses;
    public JTextField description, category, amount;
    public JTextArea displayArea;
    public JScrollPane scrollPane;

    class Expense {
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

        addfield = new JButton("ADD ANOTHER EXPENSE");
        addfield.setBounds(250, 200, 200, 35);
        addfield.addActionListener(this);
        addfield.setBackground(Color.GREEN);
        addfield.setForeground(Color.WHITE);


        display = new JButton("VIEW EXPENSES");
        display.setBounds(250, 250, 170, 30);
        display.addActionListener(this);
        display.setBackground(Color.GREEN);
        display.setBackground(Color.white);

        displayArea = new JTextArea();
        displayArea.setBounds(50, 290, 360, 200);
        displayArea.setEditable(false);
        displayArea.setVisible(false);
        displayArea.setBackground(Color.WHITE);
        displayArea.setForeground(Color.BLACK);

        scrollPane = new JScrollPane(displayArea);
        scrollPane.setBounds(50, 290, 360, 200);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setVisible(false);

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
            displayArea.setVisible(false);
            scrollPane.setVisible(false);
            description.setText("");
            category.setText("");
            amount.setText("");
        }

        if (e.getSource() == display) {
            if (expenses.isEmpty()) {
                JOptionPane.showMessageDialog(expense, "No expenses to display.", "Error", JOptionPane.ERROR_MESSAGE);}
            else {
                displayExpenses();
                displayArea.setVisible(true);
                scrollPane.setVisible(true);
            }
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

        String inputDescription = description.getText();
        String inputCategory = category.getText();
        double inputAmount = Double.parseDouble(amount.getText());

        if (!inputDescription.isEmpty() && !inputCategory.isEmpty()) {
            // Check if the expense is not already in the list
            boolean isNewExpense = true;
            for (Expense expense : expenses) {
                if (expense.description.equals(inputDescription) && expense.category.equals(inputCategory) && expense.amount == inputAmount) {
                    isNewExpense = false;
                    break;
                }
            }

            if (isNewExpense) {
                expenses.add(new Expense(inputDescription, inputCategory, inputAmount));
            }
        }

        displayArea.setText(""); // Clear the display area

        for (Expense expense : expenses) {
            String displayText = "Description: " + expense.description + "\n" +
                    "Category: " + expense.category + "\n" +
                    "Amount: " + expense.amount + "\n\n";

            displayArea.append(displayText);
            totalAmount += expense.amount;
        }
        String totalAmountText = "Total Amount Spent: " + totalAmount;
        displayArea.append(totalAmountText);

        // Create a separate label for the total amount and set its color to red
        totalAmountLabel = new JLabel(totalAmountText);
        totalAmountLabel.setForeground(Color.RED);
        totalAmountLabel.setBounds(50, 500, 800, 100);
        expense.add(totalAmountLabel);

        // Repaint the JFrame
        expense.revalidate();
        expense.repaint();
    }
}