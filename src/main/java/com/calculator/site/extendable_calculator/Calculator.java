package com.calculator.site.extendable_calculator;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Calculator {
	private Convertor convertor;
	private Map<String, Operation> supportedOperations = new HashMap<String, Operation>();

	public Calculator() {
		this.convertor = new Convertor();
		this.supportedOperations = convertor.getSupportedOperations();
	}

	public Double calculate(String input) {
		if (input == null || input.length() == 0) {
			throw new IllegalArgumentException("input should not be empty");
		}
		String[] str = input.replaceAll("\\s+", "").split("");
		return calculate(str);
	}

	private Double calculate(String[] input) {
		String[] converteTokens = convertor.convertInput(input);
		Stack<Double> stack = new Stack<Double>();
		for (String token : converteTokens) {
			if (supportedOperations.containsKey(token)) {
				Double b = stack.pop();
				Double a = stack.pop();
				stack.push(supportedOperations.get(token).evaluate(a, b));
			} else {
				stack.push(Double.parseDouble(token));
			}
		}
		return stack.pop();
	}

	public void addOperation(String symbol, Operation newOperation) {
		supportedOperations.put(symbol, newOperation);
	}

	public Convertor getConvertor() {
		return convertor;
	}

	public void setConvertor(Convertor convertor) {
		this.convertor = convertor;
		this.supportedOperations = convertor.getSupportedOperations();
	}

}

