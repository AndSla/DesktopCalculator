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

    private final JButton[] basicOperatorButtons = new JButton[]{
            addButton, subtractButton, multiplyButton, divideButton
    };

    private final Utils ut = new Utils();

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
                boolean lastCharIsCloseParenthesis = ut.getLastChar(equation) == Symbol.RIGHT_PARENTHESIS.getSymbol();
                if (!lastCharIsCloseParenthesis) {
                    equation += digitButton.getText();
                    equationLabel.setText(equation);
                }
            });
        }

        for (JButton basicOperatorButton : basicOperatorButtons) {
            basicOperatorButton.addActionListener(e -> {
                String equation = equationLabel.getText();
                String operator = basicOperatorButton.getText();
                equation = ut.insertProperOperator(equation, operator);
                equationLabel.setText(equation);
            });
        }

        parenthesisButton.addActionListener(e -> {
            String equation = equationLabel.getText();
            equation = ut.openOrCloseParenthesis(equation);
            equationLabel.setText(equation);
        });

        squareRootButton.addActionListener(e -> {
            String equation = equationLabel.getText();
            equation = ut.insertSquareRootIfPossible(equation);
            equationLabel.setText(equation);
        });

        powerTwoButton.addActionListener(e -> {
            String equation = equationLabel.getText();
            equation = ut.insertPowerTwoIfPossible(equation);
            equationLabel.setText(equation);
        });

        powerYButton.addActionListener(e -> {
            String equation = equationLabel.getText();
            equation = ut.insertPowerYIfPossible(equation);
            equationLabel.setText(equation);
        });

        dotButton.addActionListener(e -> {
            String equation = equationLabel.getText();
            if (ut.isDotAllowed(equation)) {
                equation += dotButton.getText();
                equation = ut.addZeroBeforeDotIfNecessary(equation);
                equationLabel.setText(equation);
            }
        });

        plusMinusButton.addActionListener(e -> {
            String equation = equationLabel.getText();
            equation = ut.negateOrCancelNegate(equation);
            equationLabel.setText(equation);
        });

        clearButton.addActionListener(e -> {
            equationLabel.setText("");
            equationLabel.setForeground(Color.BLACK);
            resultLabel.setText("0");
        });

        delButton.addActionListener(e -> {
            String equation = equationLabel.getText();
            String squareRoot = Symbol.SQUARE_ROOT.getSymbol() + "" + Symbol.LEFT_PARENTHESIS.getSymbol();
            String powerY = "^(";
            String powerTwo = "^(2)";

            if (equation.length() > 0) {

                if (equation.endsWith(squareRoot) || equation.endsWith(powerY)) {
                    equation = equation.substring(0, equation.length() - 2);
                } else if (equation.endsWith(powerTwo)) {
                    equation = equation.substring(0, equation.length() - 4);
                } else {
                    equation = equation.substring(0, equation.length() - 1);
                }
                equationLabel.setText(equation);

            }

        });

        equalsButton.addActionListener(e -> {
            String equation = equationLabel.getText();
            char lastCharacter = ut.getLastChar(equation);

            if (Character.isDigit(lastCharacter) || lastCharacter == Symbol.RIGHT_PARENTHESIS.getSymbol()) {
                equationLabel.setForeground(Color.BLACK);
                Deque<String> postFixNotationStack = ut.convertToPostFixNotation(equation);
                String result = ut.calculate(postFixNotationStack);
                if (result.equals("DIVIDE_BY_0") || result.equals("NaN")) {
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
