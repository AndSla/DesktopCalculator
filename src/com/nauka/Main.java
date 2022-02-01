package com.nauka;

import javax.swing.*;

public class Main {

    public static void main(String[] args) throws Exception {
        Runnable initFrame = Calculator::new;
        SwingUtilities.invokeAndWait(initFrame);
    }

}
