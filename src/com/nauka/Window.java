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
        oneButton.addActionListener(e -> {
            String equation = equationLabel.getText();
            equation += oneButton.getText();
            equationLabel.setText(equation);
        });
    }

}
