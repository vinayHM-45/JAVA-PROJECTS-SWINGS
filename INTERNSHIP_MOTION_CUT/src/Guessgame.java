import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class Guessgame extends JFrame implements ActionListener {
    private JLabel titleLabel, guessLabel, resultLabel;
    private JTextField guessField;
    private JButton guessButton;
    private int num, attempts;

    public Guessgame() {
        setTitle("Guessing Game");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 1));

        titleLabel = new JLabel("Welcome to the Guessing Game!");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);

        guessLabel = new JLabel("Enter your guess (0-100):");
        guessLabel.setHorizontalAlignment(JLabel.CENTER);

        guessField = new JTextField();
        guessField.setHorizontalAlignment(JTextField.CENTER);

        guessButton = new JButton("Guess");
        guessButton.addActionListener(this);

        resultLabel = new JLabel("");
        resultLabel.setHorizontalAlignment(JLabel.CENTER);

        add(titleLabel);
        add(guessLabel);
        add(guessField);
        add(guessButton);
        add(resultLabel);

        num = generateRandomNumber();
        attempts = 0;
    }

    public int generateRandomNumber() {
        Random rand = new Random();
        return rand.nextInt(100) + 1;
    }
    public void actionPerformed(ActionEvent e) {
        try {
            int entered = Integer.parseInt(guessField.getText());

            if (entered < 0 || entered > 100) {
                resultLabel.setText("Enter a number in the range 0 to 100.");
            } else {
                attempts++;
                if (entered > num) {
                    resultLabel.setText("Oops! Wrong guess. Guess is too high.");
                } else if (entered < num) {
                    resultLabel.setText("Oops! Wrong guess. Guess is too low.");
                } else {
                    resultLabel.setText("Congratulations! You guessed it right in " + attempts + " attempts.");
                    guessField.setEnabled(false);
                    guessButton.setEnabled(false);
                }
            }
        } catch (NumberFormatException ex) {
            resultLabel.setText("Please enter a valid number.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Guessgame game = new Guessgame();
            game.setVisible(true);
        });
    }
}
