package com.srccodes.example;

import acm.program.*;

public class HA4_2 extends ConsoleProgram {

	public void run() {
		println(removeAllOccurrences("This is a test", 't'));
		println(removeAllOccurrences("Summer is here!", 'e'));
		println(removeAllOccurrences("---0---", '-'));
	}
	
	private String removeAllOccurrences(String str, char ch) {		
		while(true) {
			int index = str.indexOf(ch);
			if (index == -1) {
				break;
			}
			str = str.substring(0, index) + str.substring(index + 1);
		}
		
		return str;
	}
}
