package com.srccodes.example;

import acm.program.*;

public class HA4_1 extends ConsoleProgram {

	public void run() {
        while (true) {
        	String digits = readLine("Enter a numeric string: "); 
        	if (digits.length() == 0) break; 
        	println(addCommasToNumericString(digits));
        } 
	}
	
	private String addCommasToNumericString(String digits) {
		int nCommas = digits.length() / 3;
		int beforeComma = digits.length() % 3;
		
		String result = digits.substring(0, beforeComma);
		
		for (int i = 0; i < nCommas; i++) {
			int startPosition = beforeComma + (i * 3);
			if (result.length() > 0) {
				result += ",";
			}
			result += digits.substring(startPosition, startPosition + 3);
		}
		return result;
	}
}
