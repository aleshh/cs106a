package com.srccodes.example;

import acm.program.*;

public class Five extends ConsoleProgram {
	public void run() {
		int num = readInt("Enter a number: ");
		int i = 0;
		int newNum;
		while (num != 1) {
			if (num % 2 == 0) {
				newNum = num / 2;
				println(num + " is even so I take half: " + newNum);

			} else {
				newNum = (num * 3) + 1;
				println(num + " is odd so I take 3n + 1: " + newNum);
			}
			num = newNum;
			i++;
		}
		println("The process took " + i + " steps to reach 1.");
	}
}
