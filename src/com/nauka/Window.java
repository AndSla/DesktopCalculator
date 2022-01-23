package com.nauka;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Window extends JFrame {

    JTextField equationTextField = new JTextField();

    JButton buttonOne = new JButton();
    JButton buttonTwo = new JButton();
    JButton buttonThree = new JButton();
    JButton buttonFour = new JButton();
    JButton buttonFive = new JButton();
    JButton buttonSix = new JButton();
    JButton buttonSeven = new JButton();
    JButton buttonEight = new JButton();
    JButton buttonNine = new JButton();
    JButton buttonZero = new JButton();

    JButton buttonDivide = new JButton();
    JButton buttonMultiply = new JButton();
    JButton buttonSubtract = new JButton();
    JButton buttonAdd = new JButton();
    JButton buttonSolve = new JButton();

    Set<JButton> buttons = new HashSet<>();

    public Window() {
        super("Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(234, 290);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);

        setEquationTextField();

        setButtons();

        setButtonsActions();

        solve();

    }

    private void setEquationTextField() {
        equationTextField.setName("EquationTextField");
        equationTextField.setBounds(2, 2, 225, 30);
        Font font = new Font("Courier", Font.BOLD, 16);
        equationTextField.setFont(font);
        add(equationTextField);
    }

    private void setButtons() {
        buttonSeven.setName("Seven");
        buttonSeven.setText("7");
        buttonSeven.setBounds(2, 35, 50, 50);
        buttons.add(buttonSeven);

        buttonEight.setName("Eight");
        buttonEight.setText("8");
        buttonEight.setBounds(60, 35, 50, 50);
        buttons.add(buttonEight);

        buttonNine.setName("Nine");
        buttonNine.setText("9");
        buttonNine.setBounds(118, 35, 50, 50);
        buttons.add(buttonNine);

        buttonDivide.setName("Divide");
        buttonDivide.setText("/");
        buttonDivide.setBounds(176, 35, 50, 50);
        buttons.add(buttonDivide);

        buttonFour.setName("Four");
        buttonFour.setText("4");
        buttonFour.setBounds(2, 93, 50, 50);
        buttons.add(buttonFour);

        buttonFive.setName("Five");
        buttonFive.setText("5");
        buttonFive.setBounds(60, 93, 50, 50);
        buttons.add(buttonFive);

        buttonSix.setName("Six");
        buttonSix.setText("6");
        buttonSix.setBounds(118, 93, 50, 50);
        buttons.add(buttonSix);

        buttonMultiply.setName("Multiply");
        buttonMultiply.setText("x");
        buttonMultiply.setBounds(176, 93, 50, 50);
        buttons.add(buttonMultiply);

        buttonOne.setName("One");
        buttonOne.setText("1");
        buttonOne.setBounds(2, 151, 50, 50);
        buttons.add(buttonOne);

        buttonTwo.setName("Two");
        buttonTwo.setText("2");
        buttonTwo.setBounds(60, 151, 50, 50);
        buttons.add(buttonTwo);

        buttonThree.setName("Three");
        buttonThree.setText("3");
        buttonThree.setBounds(118, 151, 50, 50);
        buttons.add(buttonThree);

        buttonAdd.setName("Add");
        buttonAdd.setText("+");
        buttonAdd.setBounds(176, 151, 50, 50);
        buttons.add(buttonAdd);

        buttonZero.setName("Zero");
        buttonZero.setText("0");
        buttonZero.setBounds(2, 209, 50, 50);
        buttons.add(buttonZero);

        buttonSubtract.setName("Subtract");
        buttonSubtract.setText("-");
        buttonSubtract.setBounds(176, 209, 50, 50);
        buttons.add(buttonSubtract);

        buttons.forEach(this::add);

        buttonSolve.setName("Equals");
        buttonSolve.setText("=");
        buttonSolve.setBounds(60, 209, 108, 50);
        add(buttonSolve);

    }

    private void setButtonsActions() {

        buttons.forEach(b -> b.addActionListener(e -> {
            String text = equationTextField.getText();
            text += b.getText();
            equationTextField.setText(text);
        }));

    }

    private void solve() {
        buttonSolve.addActionListener(e -> {
            String equation = equationTextField.getText();
            String operator = operator(equation);
            List<Double> numbers;
            double result = 0;

            if (operator != null) {

                if (operator.equals("+")) {
                    numbers = Arrays.stream(equation.split("\\+")).map(Double::valueOf).collect(Collectors.toList());
                } else {
                    numbers = Arrays.stream(equation.split(operator)).map(Double::valueOf).collect(Collectors.toList());
                }

                switch (operator) {
                    case "/":
                        result = numbers.get(0) / numbers.get(1);
                        break;
                    case "x":
                        result = numbers.get(0) * numbers.get(1);
                        break;
                    case "-":
                        result = numbers.get(0) - numbers.get(1);
                        break;
                    case "+":
                        result = numbers.get(0) + numbers.get(1);
                }
            }

            equationTextField.setText(equation + " = " + result);

        });
    }

    private String operator(String equation) {
        if (equation.contains("/")) {
            return "/";
        }
        if (equation.contains("x")) {
            return "x";
        }
        if (equation.contains("+")) {
            return "+";
        }
        if (equation.contains("-")) {
            return "-";
        }

        return null;

    }

}
