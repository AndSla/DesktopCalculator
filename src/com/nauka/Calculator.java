package com.nauka;

import javax.swing.*;
import java.awt.*;
import java.util.Deque;

public class Calculator extends JFrame {

    JButton oneButton = new JButton();
    JButton twoButton = new JButton();
    JButton threeButton = new JButton();
    JButton fourButton = new JButton();
    JButton fiveButton = new JButton();
    JButton sixButton = new JButton();
    JButton sevenButton = new JButton();
    JButton eightButton = new JButton();
    JButton nineButton = new JButton();
    JButton zeroButton = new JButton();

    JButton clearButton = new JButton();
    JButton delButton = new JButton();

    JButton addButton = new JButton();
    JButton subtractButton = new JButton();
    JButton multiplyButton = new JButton();
    JButton divideButton = new JButton();
    JButton dotButton = new JButton();
    JButton equalsButton = new JButton();

    JLabel resultLabel = new JLabel();
    JLabel equationLabel = new JLabel();

    private final JButton[] digitButtons = new JButton[]{
            oneButton, twoButton, threeButton, fourButton, fiveButton,
            sixButton, sevenButton, eightButton, nineButton, zeroButton
    };

    private final JButton[] operatorButtons = new JButton[]{
            addButton, subtractButton, multiplyButton, divideButton
    };


    public Calculator() throws HeadlessException {
        super("Calculator");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(232, 410);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);

        setResultLabel();
        setEquationLabel();

        setButtons();

        buttonEvents();
    }

    private void setResultLabel() {
        resultLabel.setName("ResultLabel");
        resultLabel.setBounds(2, 2, 222, 30);
        Font font = new Font("Courier", Font.BOLD, 24);
        resultLabel.setFont(font);
        resultLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        resultLabel.setText("0");
        add(resultLabel);
    }

    private void setEquationLabel() {
        equationLabel.setName("EquationLabel");
        equationLabel.setBounds(2, 30, 222, 30);
        Font font = new Font("Courier", Font.BOLD, 16);
        equationLabel.setFont(font);
        equationLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        equationLabel.setText("");
        add(equationLabel);
    }

    private void setButtons() {

        clearButton.setName("Clear");
        clearButton.setText("C");
        clearButton.setBounds(118, 80, 50, 50);
        add(clearButton);

        delButton.setName("Delete");
        delButton.setText("Del");
        delButton.setMargin(new Insets(0, 0, 0, 0));
        delButton.setBounds(176, 80, 50, 50);
        add(delButton);

        sevenButton.setName("Seven");
        sevenButton.setText("7");
        sevenButton.setBounds(2, 138, 50, 50);
        add(sevenButton);

        eightButton.setName("Eight");
        eightButton.setText("8");
        eightButton.setBounds(60, 138, 50, 50);
        add(eightButton);

        nineButton.setName("Nine");
        nineButton.setText("9");
        nineButton.setBounds(118, 138, 50, 50);
        add(nineButton);

        divideButton.setName("Divide");
        divideButton.setText(String.valueOf(Symbol.DIVIDE.getSymbol()));
        divideButton.setBounds(176, 138, 50, 50);
        add(divideButton);

        fourButton.setName("Four");
        fourButton.setText("4");
        fourButton.setBounds(2, 196, 50, 50);
        add(fourButton);

        fiveButton.setName("Five");
        fiveButton.setText("5");
        fiveButton.setBounds(60, 196, 50, 50);
        add(fiveButton);

        sixButton.setName("Six");
        sixButton.setText("6");
        sixButton.setBounds(118, 196, 50, 50);
        add(sixButton);

        multiplyButton.setName("Multiply");
        multiplyButton.setText(String.valueOf(Symbol.MULTIPLY.getSymbol()));
        multiplyButton.setBounds(176, 196, 50, 50);
        add(multiplyButton);

        oneButton.setName("One");
        oneButton.setText("1");
        oneButton.setBounds(2, 254, 50, 50);
        add(oneButton);

        twoButton.setName("Two");
        twoButton.setText("2");
        twoButton.setBounds(60, 254, 50, 50);
        add(twoButton);

        threeButton.setName("Three");
        threeButton.setText("3");
        threeButton.setBounds(118, 254, 50, 50);
        add(threeButton);

        addButton.setName("Add");
        addButton.setText(String.valueOf(Symbol.ADD.getSymbol()));
        addButton.setBounds(176, 254, 50, 50);
        add(addButton);

        dotButton.setName("Dot");
        dotButton.setText(String.valueOf(Symbol.DOT.getSymbol()));
        dotButton.setBounds(2, 312, 50, 50);
        add(dotButton);

        zeroButton.setName("Zero");
        zeroButton.setText("0");
        zeroButton.setBounds(60, 312, 50, 50);
        add(zeroButton);

        equalsButton.setName("Equals");
        equalsButton.setText("=");
        equalsButton.setBounds(118, 312, 50, 50);
        add(equalsButton);

        subtractButton.setName("Subtract");
        subtractButton.setText(String.valueOf(Symbol.SUBTRACT.getSymbol()));
        subtractButton.setBounds(176, 312, 50, 50);
        add(subtractButton);
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
