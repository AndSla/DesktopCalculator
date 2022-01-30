package com.nauka;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

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

    private final JButton[] inputButtons = new JButton[]{
            oneButton, twoButton, threeButton, fourButton, fiveButton,
            sixButton, sevenButton, eightButton, nineButton, zeroButton,
            addButton, minusButton, multiplyButton, divideButton, dotButton
    };

    public Window() throws HeadlessException {
        super("Calculator");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);

        buttonEvents();
    }

    private void buttonEvents() {

        for (JButton inputButton : inputButtons) {
            inputButton.addActionListener(e -> {
                String equation = equationLabel.getText();
                equation += inputButton.getText();
                equationLabel.setText(equation);
            });
        }

        clearButton.addActionListener(e -> equationLabel.setText(""));

        delButton.addActionListener(e -> {
            String equation = equationLabel.getText();
            if (equation.length() > 0) {
                equation = equation.substring(0, equation.length() - 1);
                equationLabel.setText(equation);
            }
        });

    }

}
