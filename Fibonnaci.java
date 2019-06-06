package com.srccodes.example;

import acm.program.*;

public class Fibonnaci extends ConsoleProgram {
	public void run() {
		println("This program prints the fibonacci sequence.");
		int fib1 = 0;
		int fib2 = 1;
		println(fib1);
		while (true) {
			println(fib2);
			int tmp = fib1 + fib2;
			if (tmp > 10000) {
				break;
			}
			fib1 = fib2;
			fib2 = tmp;
		}
	}

}
