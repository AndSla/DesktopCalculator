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

    JButton parenthesisButton = new JButton();
    JButton powerTwoButton = new JButton();
    JButton powerYButton = new JButton();
    JButton squareRootButton = new JButton();
    JButton plusMinusButton = new JButton();

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

    private void setButtons() {
        int bWidth = 50;
        int bHeight = 50;
        int hSpacer = 58;
        int vSpacer = 58;
        int topMargin = 80;
        int leftMargin = 2;

        parenthesisButton.setName("Parentheses");
        parenthesisButton.setText("( )");
        parenthesisButton.setBounds(leftMargin, topMargin, bWidth, bHeight);
        add(parenthesisButton);

        clearButton.setName("Clear");
        clearButton.setText("C");
        clearButton.setBounds(leftMargin + 2 * hSpacer, topMargin, bWidth, bHeight);
        add(clearButton);

        delButton.setName("Delete");
        delButton.setText("Del");
        delButton.setMargin(new Insets(0, 0, 0, 0));
        delButton.setBounds(leftMargin + 3 * hSpacer, topMargin, bWidth, bHeight);
        add(delButton);

        powerTwoButton.setName("PowerTwo");
        powerTwoButton.setText("x" + Symbol.POWER_TWO.getSymbol());
        powerTwoButton.setBounds(leftMargin, topMargin + vSpacer, bWidth, bHeight);
        add(powerTwoButton);

        powerYButton.setName("PowerY");
        powerYButton.setText("x" + Symbol.POWER_Y.getSymbol());
        powerYButton.setBounds(leftMargin + hSpacer, topMargin + vSpacer, bWidth, bHeight);
        add(powerYButton);

        squareRootButton.setName("SquareRoot");
        squareRootButton.setText(String.valueOf(Symbol.SQUARE_ROOT.getSymbol()));
        squareRootButton.setBounds(leftMargin + 2 * hSpacer, topMargin + vSpacer, bWidth, bHeight);
        add(squareRootButton);

        divideButton.setName("Divide");
        divideButton.setText(String.valueOf(Symbol.DIVIDE.getSymbol()));
        divideButton.setBounds(leftMargin + 3 * hSpacer, topMargin + vSpacer, bWidth, bHeight);
        add(divideButton);

        sevenButton.setName("Seven");
        sevenButton.setText("7");
        sevenButton.setBounds(leftMargin, topMargin + 2 * vSpacer, bWidth, bHeight);
        add(sevenButton);

        eightButton.setName("Eight");
        eightButton.setText("8");
        eightButton.setBounds(leftMargin + hSpacer, topMargin + 2 * vSpacer, bWidth, bHeight);
        add(eightButton);

        nineButton.setName("Nine");
        nineButton.setText("9");
        nineButton.setBounds(leftMargin + 2 * hSpacer, topMargin + 2 * vSpacer, bWidth, bHeight);
        add(nineButton);

        multiplyButton.setName("Multiply");
        multiplyButton.setText(String.valueOf(Symbol.MULTIPLY.getSymbol()));
        multiplyButton.setBounds(leftMargin + 3 * hSpacer, topMargin + 2 * vSpacer, bWidth, bHeight);
        add(multiplyButton);

        fourButton.setName("Four");
        fourButton.setText("4");
        fourButton.setBounds(leftMargin, topMargin + 3 * vSpacer, bWidth, bHeight);
        add(fourButton);

        fiveButton.setName("Five");
        fiveButton.setText("5");
        fiveButton.setBounds(leftMargin + hSpacer, topMargin + 3 * vSpacer, bWidth, bHeight);
        add(fiveButton);

        sixButton.setName("Six");
        sixButton.setText("6");
        sixButton.setBounds(leftMargin + 2 * hSpacer, topMargin + 3 * vSpacer, bWidth, bHeight);
        add(sixButton);

        subtractButton.setName("Subtract");
        subtractButton.setText(String.valueOf(Symbol.SUBTRACT.getSymbol()));
        subtractButton.setBounds(leftMargin + 3 * hSpacer, topMargin + 3 * vSpacer, bWidth, bHeight);
        add(subtractButton);

        oneButton.setName("One");
        oneButton.setText("1");
        oneButton.setBounds(leftMargin, topMargin + 4 * vSpacer, bWidth, bHeight);
        add(oneButton);

        twoButton.setName("Two");
        twoButton.setText("2");
        twoButton.setBounds(leftMargin + hSpacer, topMargin + 4 * vSpacer, bWidth, bHeight);
        add(twoButton);

        threeButton.setName("Three");
        threeButton.setText("3");
        threeButton.setBounds(leftMargin + 2 * hSpacer, topMargin + 4 * vSpacer, bWidth, bHeight);
        add(threeButton);

        addButton.setName("Add");
        addButton.setText(String.valueOf(Symbol.ADD.getSymbol()));
        addButton.setBounds(leftMargin + 3 * hSpacer, topMargin + 4 * vSpacer, bWidth, bHeight);
        add(addButton);

        plusMinusButton.setName("PlusMinus");
        plusMinusButton.setText(String.valueOf(Symbol.PLUS_MINUS.getSymbol()));
        plusMinusButton.setBounds(leftMargin, topMargin + 5 * vSpacer, bWidth, bHeight);
        add(plusMinusButton);

        zeroButton.setName("Zero");
        zeroButton.setText("0");
        zeroButton.setBounds(leftMargin + hSpacer, topMargin + 5 * vSpacer, bWidth, bHeight);
        add(zeroButton);

        dotButton.setName("Dot");
        dotButton.setText(String.valueOf(Symbol.DOT.getSymbol()));
        dotButton.setBounds(leftMargin + 2 * hSpacer, topMargin + 5 * vSpacer, bWidth, bHeight);
        add(dotButton);

        equalsButton.setName("Equals");
        equalsButton.setText("=");
        equalsButton.setBounds(leftMargin + 3 * hSpacer, topMargin + 5 * vSpacer, bWidth, bHeight);
        add(equalsButton);
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
