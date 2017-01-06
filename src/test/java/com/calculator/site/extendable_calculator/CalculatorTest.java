package com.calculator.site.extendable_calculator;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.calculator.site.extendable_calculator.constants.Constants;



public class CalculatorTest {
	@Test
	public void testExpression() {
		Calculator c = new Calculator();
		Double result1 = c.calculate("5+(3*9)+6^2+3%4");
		assertEquals(Double.valueOf(71.0), result1);
		
		Double result2 = c.calculate("5/2+9/3+2^3");
		assertEquals(Double.valueOf(13.5), result2);
		
		Double result3 = c.calculate("5  +(3   *9)+6 ^2+3% 4");
		assertEquals(Double.valueOf(71.0), result3);
		
		Double result4 = c.calculate("  5 /2+9  /3+2^3   ");
		assertEquals(Double.valueOf(13.5), result4);

		
	}
	
	@Test
	public void testUserDefinedOperator() {
		//define an user defined operation which does a * b / (a + b)
		//a and b are the operands, using operator #
		//so the operation 5 # 3 = 5 * 3 / (5 + 3) = 15 / 8 = 1.875
		Calculator calculator = new Calculator();
		Convertor converter = new Convertor();
		Operation userOperation = new UserDefinedOperation();
		userOperation.setPrecendence(Constants.LOW);
		userOperation.setAssociativity(Constants.LEFT_ASSOCIATED);
		converter.addOperation("#", userOperation);
		calculator.setConvertor(converter);
		Double result = calculator.calculate("5#3");
		
		assertEquals(Double.valueOf(1.875), result);
	}
}

