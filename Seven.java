package com.srccodes.example;

import acm.program.*;
import acm.graphics.*;
import java.awt.*;

public class Seven extends GraphicsProgram {
	public void run() {
		drawCircle(RADIUS1, Color.RED);
		drawCircle(RADIUS2, Color.WHITE);
		drawCircle(RADIUS3, Color.RED);
	}
	
	private void drawCircle(int radius, Color color) {
		int centerX = getWidth() / 2;
		int centerY = getHeight() / 2;

		int originX = centerX - (radius / 2);
		int originY = centerY - (radius / 2);
		
		GOval circle = new GOval(originX, originY, radius, radius);
		circle.setColor(color);
		circle.setFilled(true);
		circle.setFillColor(color);
		
		add(circle);		
	}
	
	private final int RADIUS1 = 72;
	private final int RADIUS2 = 47;
	private final int RADIUS3 = 22;

}
