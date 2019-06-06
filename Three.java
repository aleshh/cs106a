package com.srccodes.example;

import acm.program.*;

public class Three extends ConsoleProgram {
	public void run() {
		println("Enter values to compute the Pythagorean theorem");
		double a = readDouble("a: ");
		double b = readDouble("b: ");
		double c = Math.sqrt(a * a + b * b);
		println("c = " + c);
	}

}
