import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App extends JFrame implements ActionListener {
    private JTextField telaField;
    private JButton[] numeroButtons;
    private JButton[] operacaoButtons;
    private JButton igualButton;
    private JButton limparButton;
    private JButton limpartudoButton;
    private double num1, num2, resultado;
    private String operador;

    public App() {
        setTitle("Calculadora operacional");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        telaField = new JTextField();
        telaField.setFont(new Font("Arial", Font.PLAIN, 15));
        telaField.setHorizontalAlignment(JTextField.RIGHT);
        add(telaField, BorderLayout.NORTH);

        numeroButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            numeroButtons[i] = new JButton(String.valueOf(i));
            numeroButtons[i].addActionListener(this);
        }
        operacaoButtons = new JButton[4];
        String[] operadores = {"+", "-", "*", "/"};
        for (int i = 0; i < 4; i++) {
            operacaoButtons[i] = new JButton(operadores[i]);
            operacaoButtons[i].addActionListener(this);
        }
        igualButton = new JButton("=");
        igualButton.addActionListener(this);
        limparButton = new JButton("C");
        limparButton.addActionListener(this);
        limpartudoButton = new JButton("AC");
        limpartudoButton.addActionListener(this);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4));

        buttonPanel.add(numeroButtons[1]);
        buttonPanel.add(numeroButtons[2]);
        buttonPanel.add(numeroButtons[3]);
        buttonPanel.add(numeroButtons[4]);
        buttonPanel.add(numeroButtons[5]);
        buttonPanel.add(numeroButtons[6]);
        buttonPanel.add(numeroButtons[7]);
        buttonPanel.add(numeroButtons[8]);
        buttonPanel.add(numeroButtons[9]);
        buttonPanel.add(operacaoButtons[0]);
        buttonPanel.add(operacaoButtons[1]);
        buttonPanel.add(operacaoButtons[2]);
        buttonPanel.add(operacaoButtons[3]);
        buttonPanel.add(igualButton);
        buttonPanel.add(limparButton);
        buttonPanel.add(limpartudoButton);

        add(buttonPanel, BorderLayout.CENTER);

        num1 = 0;
        num2 = 0;
        resultado = 0;
        operador = "";
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numeroButtons[i]) {
                if (telaField.getText().length() < 8) {
                    telaField.setText(telaField.getText() + i);
                }
            }
        }

        for (int i = 0; i < 4; i++) {
            if (e.getSource() == operacaoButtons[i]) {
                operador = operacaoButtons[i].getText();
                num1 = Double.parseDouble(telaField.getText());
                telaField.setText("");
            }
        }
        if (e.getSource() == igualButton) {
            num2 = Double.parseDouble(telaField.getText());
            switch (operador) {
                case "+":
                    resultado = num1 + num2;
                    break;
                case "-":
                    resultado = num1 - num2;
                    break;
                case "*":
                    resultado = num1 * num2;
                    break;
                case "/":
                    if (num2 != 0) {
                        resultado = num1 / num2;
                    } else {
                        telaField.setText("Erro");
                        return;
                    }
                    break;
                default:
                    break;
            }
            if (String.valueOf(resultado).length() > 8) {
                telaField.setText("Erro");
            } else {
                telaField.setText(String.valueOf(resultado));
            }
        }
        if (e.getSource() == limparButton) {
            if (!operador.isEmpty()) {
                telaField.setText(String.valueOf(num1));
            } else {
                telaField.setText("");
            }
        }
        if (e.getSource() == limpartudoButton) {
            telaField.setText("");
            num1 = 0;
            num2 = 0;
            resultado = 0;
            operador = "";
        }
    }

    public static void main(String[] args) {
        new App();
    }
}
