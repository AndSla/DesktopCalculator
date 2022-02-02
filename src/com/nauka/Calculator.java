package com.nauka;

import javax.swing.*;
import java.awt.*;
import java.util.Deque;

public class Calculator extends JFrame {

    private JButton clearButton;
    private JButton delButton;
    private JButton sevenButton;
    private JButton eightButton;
    private JButton nineButton;
    private JButton divideButton;
    private JButton fourButton;
    private JButton fiveButton;
    private JButton sixButton;
    private JButton multiplyButton;
    private JButton oneButton;
    private JButton twoButton;
    private JButton threeButton;
    private JButton addButton;
    private JLabel resultLabel;
    private JLabel equationLabel;
    private JButton dotButton;
    private JButton zeroButton;
    private JButton equalsButton;
    private JButton minusButton;
    private JPanel mainPanel;

    private final JButton[] digitButtons = new JButton[]{
            oneButton, twoButton, threeButton, fourButton, fiveButton,
            sixButton, sevenButton, eightButton, nineButton, zeroButton
    };

    private final JButton[] operatorButtons = new JButton[]{
            addButton, minusButton, multiplyButton, divideButton
    };


    public Calculator() throws HeadlessException {
        super("Calculator");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);

        addButton.setText(String.valueOf(Symbol.ADD.getSymbol()));
        minusButton.setText(String.valueOf(Symbol.SUBTRACT.getSymbol()));
        multiplyButton.setText(String.valueOf(Symbol.MULTIPLY.getSymbol()));
        divideButton.setText(String.valueOf(Symbol.DIVIDE.getSymbol()));
        dotButton.setText(String.valueOf(Symbol.DOT.getSymbol()));

        buttonEvents();
    }

    private void buttonEvents() {

        for (JButton digitButton : digitButtons) {
            digitButton.addActionListener(e -> {
                String equation = equationLabel.getText();
                equation += digitButton.getText();
                equationLabel.setText(equation);
            });
        }

        for (JButton operatorButton : operatorButtons) {
            operatorButton.addActionListener(e -> {
                String equation = equationLabel.getText();
                boolean operatorIsMinus = operatorButton.getText().equals(String.valueOf(Symbol.SUBTRACT.getSymbol()));

                if (equation.isEmpty() && operatorIsMinus) {
                    equation += operatorButton.getText();
                    equationLabel.setText(equation);
                } else if (!equation.isEmpty() && Utils.isLastCharDigit(equation)) {
                    equation += operatorButton.getText();
                    equationLabel.setText(equation);
                }

            });
        }

        dotButton.addActionListener(e -> {
            String equation = equationLabel.getText();
            if (Utils.isDotAllowed(equation)) {
                equation += dotButton.getText();
                equationLabel.setText(equation);
            }
        });

        clearButton.addActionListener(e -> {
            equationLabel.setText("");
            resultLabel.setText("0");
        });

        delButton.addActionListener(e -> {
            String equation = equationLabel.getText();

            if (equation.length() > 0) {
                equation = equation.substring(0, equation.length() - 1);
                equationLabel.setText(equation);
            }

        });

        equalsButton.addActionListener(e -> {
            String equation = equationLabel.getText();
            char lastCharacter = equation.charAt(equation.length() - 1);

            if (Character.isDigit(lastCharacter)) {
                Deque<String> postFixNotationStack = Utils.convertToPostFixNotation(equation);
                resultLabel.setText(Utils.calculate(postFixNotationStack));
            }

        });

    }

}
