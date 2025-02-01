import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorGUI extends JFrame implements ActionListener {
    private JTextField display;
    private double firstNumber = 0;
    private double secondNumber = 0;
    private String currentOperator = "";
    private boolean waitingForSecondNumber = false;

    public CalculatorGUI() {
        createUI();
    }

    private void createUI() {
        setTitle("Java Swing Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 400);
        setLocationRelativeTo(null);

        display = new JTextField();
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.BOLD, 24));
        display.setHorizontalAlignment(JTextField.RIGHT);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 4, 5, 5));

        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+",
            "C", "±", "", ""
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.addActionListener(this);
            button.setFont(new Font("Arial", Font.BOLD, 18));
            if (text.matches("[0-9.]")) {
                button.setBackground(Color.WHITE);
            } else if (text.equals("C")) {
                button.setBackground(new Color(255, 150, 150));
            } else {
                button.setBackground(new Color(220, 220, 220));
            }
            buttonPanel.add(button);
        }

        getContentPane().setLayout(new BorderLayout(5, 5));
        getContentPane().add(display, BorderLayout.NORTH);
        getContentPane().add(buttonPanel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.matches("[0-9]")) {
            handleNumberInput(command);
        } else if (command.equals(".")) {
            handleDecimalPoint();
        } else if (command.matches("[+\\-*/]")) {
            handleOperator(command);
        } else if (command.equals("=")) {
            calculateResult();
        } else if (command.equals("C")) {
            clearCalculator();
        } else if (command.equals("±")) {
            toggleSign();
        }
    }

    private void handleNumberInput(String number) {
        if (waitingForSecondNumber) {
            display.setText("");
            waitingForSecondNumber = false;
        }
        display.setText(display.getText() + number);
    }

    private void handleDecimalPoint() {
        if (!display.getText().contains(".")) {
            display.setText(display.getText() + ".");
        }
    }

    private void handleOperator(String operator) {
        try {
            firstNumber = Double.parseDouble(display.getText());
            currentOperator = operator;
            waitingForSecondNumber = true;
        } catch (NumberFormatException ex) {
            display.setText("Error");
        }
    }

    private void calculateResult() {
        try {
            secondNumber = Double.parseDouble(display.getText());
            switch (currentOperator) {
                case "+":
                    firstNumber += secondNumber;
                    break;
                case "-":
                    firstNumber -= secondNumber;
                    break;
                case "*":
                    firstNumber *= secondNumber;
                    break;
                case "/":
                    if (secondNumber == 0) throw new ArithmeticException();
                    firstNumber /= secondNumber;
                    break;
            }
            display.setText(String.format("%.2f", firstNumber));
            waitingForSecondNumber = true;
        } catch (NumberFormatException ex) {
            display.setText("Error");
        } catch (ArithmeticException ex) {
            display.setText("Div by 0");
        }
    }

    private void clearCalculator() {
        display.setText("");
        firstNumber = 0;
        secondNumber = 0;
        currentOperator = "";
        waitingForSecondNumber = false;
    }

    private void toggleSign() {
        String current = display.getText();
        if (!current.isEmpty()) {
            if (current.startsWith("-")) {
                display.setText(current.substring(1));
            } else {
                display.setText("-" + current);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CalculatorGUI calculator = new CalculatorGUI();
            calculator.setVisible(true);
        });
    }
}
