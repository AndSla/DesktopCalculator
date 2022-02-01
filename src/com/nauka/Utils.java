package com.nauka;

public class Utils {
    static boolean isLastCharDigit(String equation) {
        char lastChar = equation.charAt(equation.length() - 1);
        return Character.isDigit(lastChar);
    }
}
