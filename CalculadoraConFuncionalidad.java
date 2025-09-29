import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.*;

public class CalculadoraConFuncionalidad {

    public static double firstValue = 0;
    public static double secondValue = 0;
    public static String operator = "";
    public static double result = 0;
    public static boolean isStartingNewNumber = true;

    private static JTextField textField;

    public static void main(String[] args) {

        JFrame frame = new JFrame("CALCULADORA");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        textField = new JTextField("0");
        textField.setHorizontalAlignment(JTextField.RIGHT);
        textField.setEditable(false);
        frame.add(textField, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 4));

        String[] buttonLabels = {
                "AC", "+/-", "%", "/",
                "7", "8", "9", "X",
                "4", "5", "6", "-",
                "1", "2", "3", "+",
                " ", "0", ".", "="
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);

            button.addActionListener(e -> {
                String currentText = textField.getText();
                String buttonText = button.getText();

                // --- 1. Botón de CONTROL: AC ---
                if (buttonText.equals("AC")) {
                    textField.setText("0");
                    firstValue = 0;
                    operator = "";
                    isStartingNewNumber = true;
                    return;
                }

                // --- 2. Botón de CONTROL: +/- ---
                if (buttonText.equals("+/-")) {
                    try {
                        double num = Double.parseDouble(currentText);
                        textField.setText(String.valueOf(num * -1));
                    } catch (NumberFormatException ex) {

                    }
                    return;
                }

                // --- 3. Botones NUMÉRICOS y DECIMAL ---
                if (buttonText.matches("[0-9]") || buttonText.equals(".")) {
                    if (isStartingNewNumber) {

                        if (buttonText.equals(".")) {
                            textField.setText("0.");
                        } else {
                            textField.setText(buttonText);
                        }
                        isStartingNewNumber = false;
                    } else if (buttonText.equals(".") && currentText.contains(".")) {

                    } else {

                        textField.setText(currentText + buttonText);
                    }
                    return;
                }

                switch (buttonText) {
                    case "+", "-", "X", "/", "%" -> {

                        try {
                            firstValue = Double.parseDouble(currentText);
                        } catch (NumberFormatException ignored) {

                            return;
                        }
                        operator = buttonText;
                        isStartingNewNumber = true;

                    }
                    case "=" -> {

                        try {
                            secondValue = Double.parseDouble(currentText);
                        } catch (NumberFormatException ex) {

                            textField.setText("Error");
                            return;
                        }

                        switch (operator) {
                            case "X" -> result = Calculadora.multi(firstValue, secondValue);
                            case "+" -> result = Calculadora.sumar(firstValue, secondValue);
                            case "-" -> result = Calculadora.restar(firstValue, secondValue);
                            case "/" -> {
                                if (secondValue != 0) {
                                    result = Calculadora.dividir(firstValue, secondValue);
                                } else {
                                    textField.setText("Error");
                                    return;
                                }
                            }
                            case "%" -> result = firstValue * (secondValue / 100);
                            default -> result = secondValue;
                        }

                        // 3. Mostrar
                        textField.setText(String.valueOf(result));
                        firstValue = result;
                        operator = "";
                        isStartingNewNumber = true;
                    }
                }
            });

            buttonPanel.add(button);
        }

        frame.add(buttonPanel, BorderLayout.CENTER);
        frame.setSize(230, 405);
        frame.setVisible(true);
    }
}

