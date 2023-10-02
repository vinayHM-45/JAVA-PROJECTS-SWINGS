import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame implements ActionListener {
    private JTextField inputField;
    private JButton[] numberButtons;
    private JButton addButton, subtractButton, multiplyButton, divideButton, equalsButton, clearButton;
    private JPanel panel;
    private String currentInput = "";
    private double currentResult = 0;
    private char currentOperator = ' ';

    public Calculator() {
        setTitle("Swing Calculator");
        setSize(300, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        inputField = new JTextField();
        inputField.setEditable(false);

        panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4));

        numberButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
        }

        addButton = new JButton("+");
        subtractButton = new JButton("-");
        multiplyButton = new JButton("*");
        divideButton = new JButton("/");
        equalsButton = new JButton("=");
        clearButton = new JButton("C");

        addButton.addActionListener(this);
        subtractButton.addActionListener(this);
        multiplyButton.addActionListener(this);
        divideButton.addActionListener(this);
        equalsButton.addActionListener(this);
        clearButton.addActionListener(this);

        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(addButton);
        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(subtractButton);
        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(multiplyButton);
        panel.add(clearButton);
        panel.add(numberButtons[0]);
        panel.add(equalsButton);
        panel.add(divideButton);

        add(inputField, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
    }

    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();
        String buttonText = source.getText();

        if (Character.isDigit(buttonText.charAt(0))) {
            currentInput += buttonText;
            inputField.setText(currentInput);
        } else if (buttonText.equals("C")) {
            currentInput = "";
            currentResult = 0;
            currentOperator = ' ';
            inputField.setText("");
        } else if (buttonText.equals("=")) {
            double input = Double.parseDouble(currentInput);
            switch (currentOperator) {
                case '+' -> currentResult += input;
                case '-' -> currentResult -= input;
                case '*' -> currentResult *= input;
                case '/' -> currentResult /= input;
                default -> currentResult = input;
            }
            inputField.setText(String.valueOf(currentResult));
            currentInput = String.valueOf(currentResult);
            currentOperator = ' ';

        } else {
            if (currentOperator != ' ') {
                equalsButton.doClick();
            }
            currentOperator = buttonText.charAt(0);
            currentResult = Double.parseDouble(currentInput);
            currentInput = "";
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Calculator cal = new Calculator();
            cal.setVisible(true);
        });
    }
}
