package com.calculator.site.extendable_calculator;

public class UserDefinedOperation extends Operation {

	public Double evaluate(Double operand1, Double operand2) {
		return operand1 * operand2 / (operand1 + operand2);
	}

}
