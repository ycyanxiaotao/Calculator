package com.calculator.site.extendable_calculator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import com.calculator.site.extendable_calculator.constants.Constants;

public class Convertor {
	private Map<String, Operation> supportedOperations = new HashMap<String, Operation>();

	//convertor adds all the supported operation during construction
	//in here we add +-*/^%, along with their precendence and associativity, both are configurable
	public Convertor() {
		Operation add = new Add();
		add.setAssociativity(Constants.LEFT_ASSOCIATED);
		add.setPrecendence(Constants.LOW);

		Operation minus = new Minus();
		minus.setAssociativity(Constants.LEFT_ASSOCIATED);
		minus.setPrecendence(Constants.LOW);

		Operation multiply = new Multiply();
		multiply.setAssociativity(Constants.LEFT_ASSOCIATED);
		multiply.setPrecendence(Constants.MID);

		Operation divide = new Divide();
		divide.setAssociativity(Constants.LEFT_ASSOCIATED);
		divide.setPrecendence(Constants.MID);

		Operation mod = new Mod();
		mod.setAssociativity(Constants.LEFT_ASSOCIATED);
		mod.setPrecendence(Constants.MID);

		Operation power = new Power();
		power.setAssociativity(Constants.RIGHT_ASSOCIATED);
		power.setPrecendence(Constants.HIGH);

		supportedOperations.put("+", add);
		supportedOperations.put("-", minus);
		supportedOperations.put("*", multiply);
		supportedOperations.put("/", divide);
		supportedOperations.put("%", mod);
		supportedOperations.put("^", power);

	}

	public Map<String, Operation> getSupportedOperations() {
		return this.supportedOperations;
	}

	public boolean addOperation(String symbol, Operation operation) {
		if (symbol == null || symbol.length() == 0 || operation == null) {
			return false;
		}
		if (supportedOperations.containsKey(symbol)) {
			return false;
		}
		if (supportedOperations.containsValue(operation)) {
			return false;
		}
		supportedOperations.put(symbol, operation);
		return true;
	}

	private boolean isOperator(String token) {
		return supportedOperations.containsKey(token);
	}

	private boolean isAssociative(String token, int type) {
		if (token == null || token.length() == 0 || !isOperator(token)) {
			throw new IllegalArgumentException("Invalid token: " + token);
		}
		if (supportedOperations.get(token).getAssociativity() == type) {
			return true;
		}
		return false;
	}

	private int comparePrecedence(String token1, String token2) {
		if (!isOperator(token1) || !isOperator(token2)) {
			throw new IllegalArgumentException("Invalied tokens: " + token1 + " " + token2);
		}
		return supportedOperations.get(token1).getPrecendence() - supportedOperations.get(token2).getPrecendence();
	}

	public String[] convertInput(String[] inputTokens) {
		List<String> res = new ArrayList<String>();
		Stack<String> stack = new Stack<String>();
		for (String token : inputTokens) {
			if (isOperator(token)) {
				while (!stack.empty() && isOperator(stack.peek())) {
					if ((isAssociative(token, Constants.LEFT_ASSOCIATED) && comparePrecedence(token, stack.peek()) <= 0)
							|| (isAssociative(token, Constants.RIGHT_ASSOCIATED)
									&& comparePrecedence(token, stack.peek()) < 0)) {
						res.add(stack.pop());
						continue;
					}
					break;
				}
				stack.push(token);
			} else if (token.equals("(")) {
				stack.push(token);
			} else if (token.equals(")")) {
				while (!stack.empty() && !stack.peek().equals("(")) {
					res.add(stack.pop());
				}
				stack.pop();
			} else {
				res.add(token);
			}
		}
		while (!stack.empty()) {
			res.add(stack.pop());
		}
		String[] result = new String[res.size()];
		return res.toArray(result);
	}
}

