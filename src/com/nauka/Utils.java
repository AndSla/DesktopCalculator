package com.nauka;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;

public class Utils {

    boolean isLastCharDigit(String equation) {
        if (equation.length() > 0) {
            char lastChar = equation.charAt(equation.length() - 1);
            return Character.isDigit(lastChar);
        }
        return false;
    }

    private int getRank(Character symbol) {
        for (Symbol value : Symbol.values()) {
            if (value.getSymbol() == symbol) {
                return value.getRank();
            }
        }
        return -1;
    }

    boolean isOperator(String symbol) {
        if (symbol.length() == 1) {
            for (Symbol value : Symbol.values()) {
                if (value.getSymbol() == symbol.charAt(0)) {
                    return value.isOperator();
                }
            }
        }
        return false;
    }

    boolean isOperator(char symbol) {
        for (Symbol value : Symbol.values()) {
            if (value.getSymbol() == symbol) {
                return value.isOperator();
            }
        }
        return false;
    }

    Deque<String> convertToPostFixNotation(String equation) {

        Deque<String> postFixNotationStack = new ArrayDeque<>();
        Deque<Character> operatorsStack = new ArrayDeque<>();

        StringBuilder number = new StringBuilder();

        for (int i = 0; i < equation.length(); i++) {
            char scan = equation.charAt(i);

            if (i == 0 && equation.charAt(i) == Symbol.SUBTRACT.getSymbol()) {
                number.append("-");
                continue;
            }

            if (Character.isDigit(scan) || scan == Symbol.DOT.getSymbol()) {
                number.append(scan);
            } else {
                postFixNotationStack.add(number.toString());
                number.setLength(0);
                if (!operatorsStack.isEmpty()) {
                    int scannedOperatorRank = getRank(scan);
                    int stackedOperatorRank = getRank(operatorsStack.peekLast());
                    if (stackedOperatorRank >= scannedOperatorRank) {
                        postFixNotationStack.add(String.valueOf(operatorsStack.pollLast()));
                    }
                }
                operatorsStack.add(scan);
            }

            if (i == equation.length() - 1) {
                postFixNotationStack.add(number.toString());
            }

        }

        while (!operatorsStack.isEmpty()) {
            postFixNotationStack.add(String.valueOf(operatorsStack.pollLast()));
        }

        return postFixNotationStack;

    }

    boolean isDotAllowed(String equation) {

        boolean isAllowed = true;

        if (equation.length() > 0) {

            while (equation.length() > 0) {

                if (getLastChar(equation) == Symbol.DOT.getSymbol()) {
                    isAllowed = false;
                    break;
                }

                if (isOperator(String.valueOf(getLastChar(equation)))) {
                    break;
                }

                equation = deleteLastChar(equation);

            }

            return isAllowed;

        } else {

            return true;

        }

    }

    String addZeroBeforeDotIfNecessary(String equation) {

        if (equation.length() > 1) {

            int index = equation.length() - 1;

            if (!Character.isDigit(equation.charAt(index - 1))) {
                equation = insertCharAtIndex(equation, index, '0');
            }

        } else {

            equation = insertCharAtIndex(equation, 0, '0');

        }

        return equation;

    }

    String calculate(Deque<String> postFixNotationStack) {

        String result = "0";
        Deque<String> subEquation = new ArrayDeque<>();
        MathContext mathContext = new MathContext(12, RoundingMode.HALF_EVEN);

        while (postFixNotationStack.size() > 0) {

            String scan = postFixNotationStack.pollFirst();
            subEquation.offerLast(scan);

            if (isOperator(scan)) {
                String operator = subEquation.pollLast();
                BigDecimal b = BigDecimal.valueOf(Double.parseDouble(Objects.requireNonNull(subEquation.pollLast())));
                BigDecimal a = BigDecimal.valueOf(Double.parseDouble(Objects.requireNonNull(subEquation.pollLast())));

                if (String.valueOf(Symbol.ADD.getSymbol()).equals(operator)) {
                    result = String.valueOf(a.add(b, mathContext));
                    subEquation.offerLast(result);
                }

                if (String.valueOf(Symbol.SUBTRACT.getSymbol()).equals(operator)) {
                    result = String.valueOf(a.subtract(b, mathContext));
                    subEquation.offerLast(result);
                }

                if (String.valueOf(Symbol.MULTIPLY.getSymbol()).equals(operator)) {
                    result = String.valueOf(a.multiply(b, mathContext));
                    subEquation.offerLast(result);
                }

                if (String.valueOf(Symbol.DIVIDE.getSymbol()).equals(operator)) {
                    if (b.doubleValue() == 0) {
                        result = "DIVIDE_BY_0";
                        break;
                    } else {
                        result = String.valueOf(a.divide(b, mathContext));
                        subEquation.offerLast(result);
                    }
                }

                while (!subEquation.isEmpty()) {
                    postFixNotationStack.offerFirst(subEquation.pollLast());
                }

            }

        }

        return cutTrailingZeros(result);

    }

    private String cutTrailingZeros(String result) {
        StringBuilder sb = new StringBuilder(result);
        String prettyResult = result;

        if (result.matches("-*\\d+" + Symbol.DOT.getSymbol() + "\\d+")) {

            while (true) {
                int i = sb.length() - 1;

                if (sb.charAt(i) == '0') {
                    sb.deleteCharAt(i);
                } else if (sb.charAt(i) == Symbol.DOT.getSymbol()) {
                    sb.deleteCharAt(i);
                    prettyResult = sb.toString();
                    break;
                } else {
                    prettyResult = sb.toString();
                    break;
                }

            }

        }

        return prettyResult;

    }

    char getLastChar(String equation) {
        if (equation.length() < 1) {
            return '#';
        }
        return equation.charAt(equation.length() - 1);
    }

    String changeLastChar(String equation, char changeInto) {
        StringBuilder sb = new StringBuilder(equation);
        sb.setCharAt(equation.length() - 1, changeInto);
        return sb.toString();
    }

    String deleteLastChar(String equation) {
        StringBuilder sb = new StringBuilder(equation);
        sb.deleteCharAt(equation.length() - 1);
        return sb.toString();
    }

    String addChar(String equation, char charToAdd) {
        return equation + charToAdd;
    }

    String insertCharAtIndex(String equation, int index, char insertChar) {
        StringBuilder sb = new StringBuilder(equation);
        sb.insert(index, insertChar);
        return sb.toString();
    }

    String openOrCloseParenthesis(String equation) {
        int leftQuantity = 0;
        int rightQuantity = 0;

        if (getLastChar(equation) == Symbol.LEFT_PARENTHESIS.getSymbol()) {
            return equation + Symbol.LEFT_PARENTHESIS.getSymbol();
        }

        if (isOperator((getLastChar(equation)))) {
            return equation + Symbol.LEFT_PARENTHESIS.getSymbol();
        }

        for (int i = 0; i < equation.length(); i++) {
            if (equation.charAt(i) == Symbol.LEFT_PARENTHESIS.getSymbol()) {
                leftQuantity += 1;
            }
            if (equation.charAt(i) == Symbol.RIGHT_PARENTHESIS.getSymbol()) {
                rightQuantity += 1;
            }
        }

        if (leftQuantity == rightQuantity) {

            if (getLastChar(equation) == Symbol.RIGHT_PARENTHESIS.getSymbol()) {
                return equation;
            } else if (isLastCharDigit(equation)) {
                return equation;
            } else {
                return equation + Symbol.LEFT_PARENTHESIS.getSymbol();
            }

        }

        return equation + Symbol.RIGHT_PARENTHESIS.getSymbol();

    }

    String negateOrCancelNegate(String equation) {
        char lastChar = getLastChar(equation);

        if (equation.length() == 0) {
            equation = "(-";
        } else {
            if (equation.endsWith("(-")) {
                equation = deleteLastChar(equation);
                equation = deleteLastChar(equation);
            } else if (isOperator(lastChar)) {
                equation += "(-";
            }
        }

        return equation;

    }

    String insertProperOperator(String equation, String operator) {
        char lastChar = getLastChar(equation);

        if (lastChar == Symbol.DOT.getSymbol()) {
            equation = addChar(equation, '0');
        }

        if (isOperator(lastChar)) {
            equation = deleteLastChar(equation);
        } else if (isLastCharDigit(equation)) {

        } else if (getLastChar(equation) == Symbol.RIGHT_PARENTHESIS.getSymbol()) {

        } else {
            operator = "";
        }

        return equation + operator;

    }

}
