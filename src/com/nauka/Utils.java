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

            if (scan == '-' && equation.charAt(i - 1) == '(') {
                number.append("-");
                continue;
            }

            if (Character.isDigit(scan) || scan == Symbol.DOT.getSymbol()) {
                number.append(scan);
            }

            if (isOperator(scan)) {

                addNumberToPostfixNotationStack(postFixNotationStack, number);

                int scannedOperatorRank = getRank(scan);
                int stackedOperatorRank = -1;

                if (!operatorsStack.isEmpty()) {
                    stackedOperatorRank = getRank(operatorsStack.peekLast());
                }

                if (!operatorsStack.isEmpty() &&
                        scannedOperatorRank <= stackedOperatorRank) {

                    while (!operatorsStack.isEmpty()) {

                        if (operatorsStack.peekLast() == Symbol.LEFT_PARENTHESIS.getSymbol() ||
                                operatorsStack.peekLast() == Symbol.RIGHT_PARENTHESIS.getSymbol()) {
                            break;
                        }

                        stackedOperatorRank = getRank(operatorsStack.peekLast());

                        if (stackedOperatorRank >= scannedOperatorRank) {
                            postFixNotationStack.add(String.valueOf(operatorsStack.pollLast()));
                        } else {
                            break;
                        }

                    }

                }

                operatorsStack.add(scan);

            }

            if (scan == Symbol.LEFT_PARENTHESIS.getSymbol()) {
                addNumberToPostfixNotationStack(postFixNotationStack, number);
                operatorsStack.add(scan);
            }

            if (scan == Symbol.RIGHT_PARENTHESIS.getSymbol()) {

                addNumberToPostfixNotationStack(postFixNotationStack, number);

                while (true) {

                    if (!operatorsStack.isEmpty() &&
                            operatorsStack.peekLast() == Symbol.LEFT_PARENTHESIS.getSymbol()) {
                        operatorsStack.pollLast();
                        break;
                    }

                    postFixNotationStack.add(String.valueOf(operatorsStack.pollLast()));

                }
            }

        }

        addNumberToPostfixNotationStack(postFixNotationStack, number);

        while (!operatorsStack.isEmpty()) {
            postFixNotationStack.add(String.valueOf(operatorsStack.pollLast()));
        }
        return postFixNotationStack;

    }

    void addNumberToPostfixNotationStack(Deque<String> postFixNotationStack, StringBuilder number) {
        if (number.toString().matches("-*\\d+" + Symbol.DOT.getSymbol() + "*\\d*")) {
            postFixNotationStack.add(number.toString());
            number.setLength(0);
        }
    }

    boolean isDotAllowed(String equation) {

        boolean isAllowed = true;

        if (equation.length() > 0) {

            while (equation.length() > 0) {
                boolean isLastCharDot = getLastChar(equation) == Symbol.DOT.getSymbol();
                boolean isLastCharRightParenthesis = getLastChar(equation) == Symbol.RIGHT_PARENTHESIS.getSymbol();

                if (isLastCharDot || isLastCharRightParenthesis) {
                    isAllowed = false;
                    break;
                }

                if (isOperator(getLastChar(equation))) {
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
                equation = insertZeroAtIndex(equation, index);
            }

        } else {

            equation = insertZeroAtIndex(equation, 0);

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
                BigDecimal a = new BigDecimal(0);
                if (!String.valueOf(Symbol.SQUARE_ROOT.getSymbol()).equals(operator)) {
                    a = BigDecimal.valueOf(Double.parseDouble(Objects.requireNonNull(subEquation.pollLast())));
                }

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

                if (String.valueOf(Symbol.POWER.getSymbol()).equals(operator)) {
                    result = String.valueOf(a.pow(b.intValue(), mathContext));
                    subEquation.offerLast(result);
                }

                if (String.valueOf(Symbol.SQUARE_ROOT.getSymbol()).equals(operator)) {
                    result = String.valueOf(Math.sqrt(b.doubleValue()));
                    subEquation.offerLast(result);
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

    String deleteLastChar(String equation) {
        StringBuilder sb = new StringBuilder(equation);
        sb.deleteCharAt(equation.length() - 1);
        return sb.toString();
    }

    String addZeroAfter(String equation) {
        return equation + '0';
    }

    String insertZeroAtIndex(String equation, int index) {
        StringBuilder sb = new StringBuilder(equation);
        sb.insert(index, '0');
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

        if (getLastChar(equation) == Symbol.DOT.getSymbol()) {
            return equation;
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
        String negate = Symbol.LEFT_PARENTHESIS.getSymbol() + "" + Symbol.SUBTRACT.getSymbol();
        String negateAll = Symbol.SUBTRACT.getSymbol() + "" + Symbol.LEFT_PARENTHESIS.getSymbol();
        String rightParenthesis = String.valueOf(Symbol.RIGHT_PARENTHESIS.getSymbol());

        if (equation.length() == 0) {
            equation = negate;
        } else {
            if (equation.endsWith(negate)) {
                equation = deleteLastChar(equation);
                equation = deleteLastChar(equation);
            } else if (isOperator(lastChar)) {
                equation += negate;
            } else if (equation.startsWith(negate)) {
                equation = equation.substring(2);
            } else if (equation.startsWith(negateAll) && equation.endsWith(rightParenthesis)) {
                equation = equation.substring(2, equation.length() - 1);
            } else {
                equation = negateAll + equation + rightParenthesis;
            }

        }

        return equation;

    }

    String insertProperOperator(String equation, String operator) {
        char lastChar = getLastChar(equation);
        char rightParenthesis = Symbol.RIGHT_PARENTHESIS.getSymbol();
        char dot = Symbol.DOT.getSymbol();

        if (lastChar == dot) {
            equation = addZeroAfter(equation);
        }

        if (isOperator(lastChar)) {
            equation = deleteLastChar(equation);
        } else if (!(isLastCharDigit(equation) || lastChar == rightParenthesis)) {
            operator = "";
        }

        return equation + operator;

    }

    String insertSquareRootIfPossible(String equation) {
        char lastChar = getLastChar(equation);

        if (equation.length() == 0) {
            equation = Symbol.SQUARE_ROOT.getSymbol() + "" + Symbol.LEFT_PARENTHESIS.getSymbol();
        } else if (isOperator(lastChar)) {
            equation += Symbol.SQUARE_ROOT.getSymbol() + "" + Symbol.LEFT_PARENTHESIS.getSymbol();
        }

        return equation;

    }

    String insertPowerTwoIfPossible(String equation) {
        if (isLastCharDigit(equation)) {
            equation += Symbol.POWER.getSymbol() + "(2)";
        }

        return equation;

    }

    String insertPowerYIfPossible(String equation) {
        if (isLastCharDigit(equation)) {
            equation += Symbol.POWER.getSymbol() + "(";
        }

        return equation;

    }

    String smartDelete(String equation) {
        String squareRoot = Symbol.SQUARE_ROOT.getSymbol() + "" + Symbol.LEFT_PARENTHESIS.getSymbol();
        String powerY = Symbol.POWER.getSymbol() + "" + Symbol.LEFT_PARENTHESIS.getSymbol();
        String powerTwo = Symbol.POWER.getSymbol() + "" +
                Symbol.LEFT_PARENTHESIS.getSymbol() + "2" + Symbol.RIGHT_PARENTHESIS.getSymbol();
        String negate = Symbol.LEFT_PARENTHESIS.getSymbol() + "" + Symbol.SUBTRACT.getSymbol();

        if (equation.length() > 0) {

            if (equation.endsWith(squareRoot) || equation.endsWith(powerY) || equation.endsWith(negate)) {
                equation = equation.substring(0, equation.length() - 2);
            } else if (equation.endsWith(powerTwo)) {
                equation = equation.substring(0, equation.length() - 4);
            } else {
                equation = equation.substring(0, equation.length() - 1);
            }

        }

        return equation;

    }

}
