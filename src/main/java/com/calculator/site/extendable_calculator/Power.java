package com.calculator.site.extendable_calculator;

public class Power extends Operation {

	public Double evaluate(Double operand1, Double operand2) {
		return Math.pow(operand1, operand2);
	}

}
