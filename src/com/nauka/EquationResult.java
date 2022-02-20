package com.nauka;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class EquationResult {
    private CalculateMethod method;

    public void setMethod(CalculateMethod method) {
        this.method = method;
    }

    public String calculate(String operandA, String operandB) {
        BigDecimal a = BigDecimal.valueOf(Double.parseDouble(operandA));
        BigDecimal b = BigDecimal.valueOf(Double.parseDouble(operandB));
        MathContext mathContext = new MathContext(12, RoundingMode.HALF_EVEN);
        return this.method.calculate(a, b, mathContext);
    }

}
