package com.srccodes.example;

import acm.program.*;
import acm.graphics.*;

import java.awt.Color;
import java.awt.Component;
import java.awt.color.*;

public class Face extends GraphicsProgram {
	public void run() {
		int centerX = getWidth() / 2;
		int centerY = getHeight() / 2;
		
		// head
		GRect head = new GRect(HEAD_WIDTH, HEAD_HEIGHT);
		head.setFilled(true);
		head.setFillColor(Color.GRAY);
		
		int headX = centerX - HEAD_WIDTH / 2;
		int headY = centerY - HEAD_HEIGHT / 2;
		
		add(head, headX, headY);
		
		// eyes
		GOval eye1 = eye();
		GOval eye2 = eye();
		
		int eye1X = centerX - (HEAD_WIDTH / 4 + EYE_RADIUS / 2);
		int eye2X = centerX + (HEAD_WIDTH / 4 - EYE_RADIUS / 2);
		int eyeY = centerY - (HEAD_HEIGHT /4);
		add(eye1, eye1X, eyeY);
		add(eye2, eye2X, eyeY);
		
		// mouth
		GRect mouth = new GRect(MOUTH_WIDTH, MOUTH_HEIGHT);
		mouth.setColor(Color.WHITE);
		mouth.setFilled(true);
		mouth.setFillColor(Color.WHITE);
		
		int mouthX = centerX - (MOUTH_WIDTH / 2);
		int mouthY = centerY + (HEAD_HEIGHT / 4) - (MOUTH_HEIGHT / 2);
		add(mouth, mouthX, mouthY);
	}
	
	private GOval eye() {
		GOval eye = new GOval(EYE_RADIUS, EYE_RADIUS);
		eye.setColor(Color.YELLOW);
		eye.setFilled(true);
		eye.setFillColor(Color.YELLOW);
		return eye;
	}
	
	private final int HEAD_WIDTH = 100;
	private final int HEAD_HEIGHT = 150;
	private final int EYE_RADIUS = 15;
	private final int MOUTH_WIDTH = 60;
	private final int MOUTH_HEIGHT = 20;
}
