import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Temp extends JFrame implements ActionListener {
    private JLabel welcome_field;

    private JButton CtF,Ftc;

    public Temp() {
        setTitle("TEMPERATURE CONVERTER");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 100);
        setLayout(new BorderLayout());

        welcome_field = new JLabel("WELCOME TO TEMPERATURE CONVERTER");
        welcome_field.setHorizontalAlignment(JLabel.CENTER);

        CtF = new JButton("CELSIUS TO FAHRENHEIT ");
        CtF.setBounds(50,50,100,30);
        CtF.addActionListener(this);


        Ftc = new JButton(" FAHRENHEIT TO CELSIUS ");
        Ftc.setBounds(250, 50, 200, 30);
        Ftc.addActionListener(this);

        CtF.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ctf page1Frame = new ctf();
                page1Frame.setVisible(true);
            }
        });

        Ftc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ftc page2Frame = new ftc();
                page2Frame.setVisible(true);
            }
        });
        add(welcome_field, BorderLayout.NORTH);
        add(CtF,BorderLayout.LINE_START);
        add(Ftc,BorderLayout.LINE_END);
    }

    public void actionPerformed(ActionEvent e) {
    }

    public static void main(String[] args) {
        Temp temp = new Temp();
        temp.setVisible(true);
    }
}
class ctf extends JFrame implements ActionListener {

    private JLabel instruction_field, result_field;

    private JTextField enter_field;

    ctf() {
        setTitle("CELSIUS TO FAHRENHEIT");
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 100);
        setLayout(new BorderLayout());

        instruction_field = new JLabel("ENTER THE TEMPERATURE IN CELSIUS");
        instruction_field.setHorizontalAlignment(JLabel.LEFT);

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        enter_field = new JTextField(10);
        JButton convertButton = new JButton("Convert");
        result_field = new JLabel("RESULT");
        result_field.setHorizontalAlignment(JLabel.LEFT);
        panel.add(instruction_field);
        panel.add(enter_field);
        convertButton.addActionListener(this);

        convertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                convertAndDisplay();
            }
        });

        add(panel, BorderLayout.CENTER);
        add(convertButton, BorderLayout.SOUTH);
        add(result_field, BorderLayout.LINE_END);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Convert")) {
            convertAndDisplay();
        }
    }

    private void convertAndDisplay() {
        try {
            String inputText = enter_field.getText();
            if (inputText.trim().isEmpty()) {
                result_field.setForeground(Color.red);
                result_field.setText("Please enter a valid temperature.");
                return;
            }
            double temperature = Double.parseDouble(inputText);
            if (temperature < -100 || temperature > 100) {
                result_field.setForeground(Color.red);
                result_field.setText("Please enter a temperature between -100 and 100 degrees Celsius.");
                return;
            }
            double fahrenheit = (temperature * 9.0 / 5.0) + 32.0;
            result_field.setText("RESULT: " + String.format("%.2f Fahrenheit", fahrenheit));
        } catch (NumberFormatException ex) {
            result_field.setText("Invalid input. Please enter a valid number.");
        }
    }

}

class ftc extends JFrame implements ActionListener {
    private JLabel instruction_field, result_field;

    private JTextField enter_field;

    ftc() {
        setTitle("FAHRENHEIT TO CELSIUS ");
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 100);
        setLayout(new BorderLayout());

        instruction_field = new JLabel("ENTER THE TEMPERATURE IN FAHRENHEIT");
        instruction_field.setHorizontalAlignment(JLabel.LEFT);

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        enter_field = new JTextField(10);
        JButton convertButton = new JButton("Convert");
        panel.add(instruction_field);
        panel.add(enter_field);

        result_field = new JLabel("RESULT");
        result_field.setHorizontalAlignment(JLabel.LEFT);

        convertButton.addActionListener(this);

        convertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                convertAndDisplay();
            }
        });

        add(panel, BorderLayout.CENTER);
        add(convertButton, BorderLayout.SOUTH);
        add(result_field, BorderLayout.LINE_END);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Convert")) {
            convertAndDisplay();
        }
    }

    private void convertAndDisplay() {
        String inputText = enter_field.getText();

        if (isValidFahrenheitInput(inputText)) {
            double fahrenheit = Double.parseDouble(inputText);
            double celsius = (fahrenheit - 32.0) * 5.0 / 9.0;
            result_field.setText("RESULT: " + String.format("%.2f Celsius", celsius));
        } else {
            result_field.setForeground(Color.red);
            result_field.setText("Invalid input. Please enter a valid Fahrenheit temperature");
        }
    }

    private boolean isValidFahrenheitInput(String input) {
        try {
            double fahrenheit = Double.parseDouble(input);
            if (fahrenheit >= -459.67) {
                return true;
            } else {
                return false;
            }
        } catch (NumberFormatException ex) {
            return false;
        }
    }
}