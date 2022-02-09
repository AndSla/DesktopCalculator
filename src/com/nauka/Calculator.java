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
    JButton powerTwoButton = new JButton();
    JButton powerYButton = new JButton();
    JButton squareRootButton = new JButton();

    JButton equalsButton = new JButton();
    JButton dotButton = new JButton();
    JButton parenthesisButton = new JButton();
    JButton plusMinusButton = new JButton();

    JLabel resultLabel = new JLabel();
    JLabel equationLabel = new JLabel();

    private final JButton[] digitButtons = new JButton[]{
            oneButton, twoButton, threeButton, fourButton, fiveButton,
            sixButton, sevenButton, eightButton, nineButton, zeroButton
    };

    private final JButton[] operatorButtons = new JButton[]{
            addButton, subtractButton, multiplyButton, divideButton,
            powerTwoButton, powerYButton, squareRootButton
    };


    public Calculator() throws HeadlessException {
        super("Calculator");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(234, 460);
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

    private void createButton(JButton button,
                              String name,
                              String text,
                              int row,
                              int col) {

        int bWidth = 50;
        int bHeight = 50;
        int hSpacer = 58;
        int vSpacer = 58;
        int topMargin = 80;
        int leftMargin = 2;

        button.setName(name);
        button.setText(text);
        button.setMargin(new Insets(0, 0, 0, 0));
        button.setBounds(leftMargin + col * hSpacer, topMargin + row * vSpacer, bWidth, bHeight);
        add(button);

    }

    private void setButtons() {

        createButton(parenthesisButton, "Parentheses", "( )", 0, 0);
        createButton(clearButton, "Clear", "C", 0, 2);
        createButton(delButton, "Delete", "Del", 0, 3);
        createButton(powerTwoButton, "PowerTwo", "x" + Symbol.POWER_TWO.getSymbol(), 1, 0);
        createButton(powerYButton, "PowerY", "x" + Symbol.POWER_Y.getSymbol(), 1, 1);
        createButton(squareRootButton, "SquareRoot", String.valueOf(Symbol.SQUARE_ROOT.getSymbol()), 1, 2);
        createButton(divideButton, "Divide", String.valueOf(Symbol.DIVIDE.getSymbol()), 1, 3);
        createButton(sevenButton, "Seven", "7", 2, 0);
        createButton(eightButton, "Eight", "8", 2, 1);
        createButton(nineButton, "Nine", "9", 2, 2);
        createButton(multiplyButton, "Multiply", String.valueOf(Symbol.MULTIPLY.getSymbol()), 2, 3);
        createButton(fourButton, "Four", "4", 3, 0);
        createButton(fiveButton, "Five", "5", 3, 1);
        createButton(sixButton, "Six", "6", 3, 2);
        createButton(subtractButton, "Subtract", String.valueOf(Symbol.SUBTRACT.getSymbol()), 3, 3);
        createButton(oneButton, "One", "1", 4, 0);
        createButton(twoButton, "Two", "2", 4, 1);
        createButton(threeButton, "Three", "3", 4, 2);
        createButton(addButton, "Add", String.valueOf(Symbol.ADD.getSymbol()), 4, 3);
        createButton(plusMinusButton, "PlusMinus", String.valueOf(Symbol.PLUS_MINUS.getSymbol()), 5, 0);
        createButton(zeroButton, "Zero", "0", 5, 1);
        createButton(dotButton, "Dot", String.valueOf(Symbol.DOT.getSymbol()), 5, 2);
        createButton(equalsButton, "Equals", "=", 5, 3);

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
                StringBuilder equationSb = new StringBuilder(equation);
                boolean operatorIsMinus = operatorButton.getText().equals(String.valueOf(Symbol.SUBTRACT.getSymbol()));

                if (equationSb.charAt(0) == Symbol.DOT.getSymbol()) {
                    equationSb.insert(0, 0);
                    equation = equationSb.toString();
                    equationLabel.setText(equation);
                }
                if (equationSb.charAt(equationSb.length() - 1) == Symbol.DOT.getSymbol()) {
                    equationSb.insert(equationSb.length(), 0);
                    equation = equationSb.toString();
                    equationLabel.setText(equation);
                }

                if (equation.isEmpty() && operatorIsMinus) {
                    equation += operatorButton.getText();
                    equationLabel.setText(equation);
                } else if (!equation.isEmpty() && Utils.isLastCharDigit(equation)) {
                    equation += operatorButton.getText();
                    equationLabel.setText(equation);
                } else if (!equation.isEmpty() && Utils.isOperator(equation.substring(equation.length() - 1))) {
                    if (!equation.equals(String.valueOf(Symbol.SUBTRACT.getSymbol()))) {
                        equationSb.deleteCharAt(equationSb.length() - 1);
                        equation = equationSb.toString();
                        equation += operatorButton.getText();
                        equationLabel.setText(equation);
                    }
                }

            });
        }

        dotButton.addActionListener(e -> {
            String equation = equationLabel.getText();
            //if (Utils.isDotAllowed(equation)) {
            equation += dotButton.getText();
            equationLabel.setText(equation);
            //}
        });

        plusMinusButton.addActionListener(e -> {
            String equation = equationLabel.getText();
            StringBuilder sb = new StringBuilder(equation);

            switch (equation.length()) {

                case 0:

                    equationLabel.setText("-");
                    break;

                case 1:

                    if (equationLabel.getText().equals("-")) {
                        equationLabel.setText("");
                    } else if (equationLabel.getText().equals("-")) {
                        equationLabel.setText("-");
                    }
                    break;

                default:

                    if (sb.charAt(sb.length() - 1) == '-') {
                        sb.deleteCharAt(sb.length() - 1);
                        sb.append('+');
                        equationLabel.setText(sb.toString());
                    } else if (sb.charAt(sb.length() - 1) == '+') {
                        sb.deleteCharAt(sb.length() - 1);
                        sb.append('-');
                        equationLabel.setText(sb.toString());
                    }

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
                equationLabel.setForeground(Color.BLACK);
                Deque<String> postFixNotationStack = Utils.convertToPostFixNotation(equation);
                String result = Utils.calculate(postFixNotationStack);
                if (result.equals("DIVIDE_BY_0")) {
                    equationLabel.setForeground(Color.RED.darker());
                    resultLabel.setText("0");
                } else {
                    resultLabel.setText(result);
                }
            } else {
                equationLabel.setForeground(Color.RED.darker());
                resultLabel.setText("0");
            }

        });

    }
}
