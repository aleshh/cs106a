package com.srccodes.example;

import acm.program.*;
import acm.graphics.*;
import java.awt.Color;



public class WarmupOne extends GraphicsProgram {
	public void run() {
		GRect rectangle = new GRect(width, height);
		rectangle.setColor(Color.BLUE);
		rectangle.setFilled(true);
		rectangle.setFillColor(Color.BLUE);

		double x = getWidth() - width / 2;
		double y = getHeight() - height / 2;

		add(rectangle, x, y);
	}
	private double width = 360;
	private double height = 270;
}
