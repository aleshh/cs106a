package com.srccodes.example;

import acm.program.*;

public class Four extends ConsoleProgram {
	public void run() {
		println("This program finds the smallest and largest numbers.");
		println("Enter 0 to end");
		
		int val = readInt("? ");

		if (val == 0) {
			println("You entered 0 as the first number!");
			return;
		}
		
		int smallest = val;
		int largest = val;
		
		while (val != 0) {
			val = readInt("? ");
			
			if (val != 0) {
				if (val > largest) {
					largest = val;
				}
				if (val < smallest) {
					smallest = val;
				}				
			}
		}
		
		println("Smallest: " + smallest);
		println("Largest: " + largest);
		
	}

}
