package com.calculator.site.extendable_calculator;

public abstract class Operation {
	private int precendence;
	private int associativity;
	public abstract Double evaluate(Double operand1, Double operand2);

	
	public int getPrecendence() {
		return precendence;
	}
	public void setPrecendence(int precendence) {
		this.precendence = precendence;
	}
	public int getAssociativity() {
		return associativity;
	}

	public void setAssociativity(int associativity) {
		this.associativity = associativity;
	}
}

