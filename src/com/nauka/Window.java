package com.nauka;

import javax.swing.*;

public class Window extends JFrame {
    public Window() {
        super("Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 500);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);

        setComponents();
    }

    private void setComponents() {
        JTextField equationTextField = new JTextField();
        equationTextField.setName("EquationTextField");
        equationTextField.setBounds(20, 20, 100, 20);
        add(equationTextField);

        JButton solveButton = new JButton("Solve");
        solveButton.setName("Solve");
        solveButton.setBounds(100, 70, 100, 30);
        add(solveButton);

        solveButton.addActionListener(e -> {
            String equation = equationTextField.getText();
            String[] numbersString = equation.split("\\+");
            int result = Integer.parseInt(numbersString[0]) + Integer.parseInt(numbersString[1]);
            equation += "=" + result;
            equationTextField.setText(equation);
        });

    }

}
