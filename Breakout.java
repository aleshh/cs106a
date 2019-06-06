package com.srccodes.example;

/*
* File: Breakout.java
* -------------------
* This file will eventually implement the game of Breakout.
*/

 import acm.graphics.*;
 import acm.program.*;
 import acm.util.*;
 import java.applet.*;
 import java.awt.*;
 import java.awt.event.*;
 
public class Breakout extends GraphicsProgram {
	
	/** Width and height of application window in pixels */ 
	public static final int APPLICATION_WIDTH = 400; 
	public static final int APPLICATION_HEIGHT = 600;
	
	/** Dimensions of game board (usually the same) */ 
	private static final int WIDTH = APPLICATION_WIDTH; 
	private static final int HEIGHT = APPLICATION_HEIGHT;
	
	/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60; 
	private static final int PADDLE_HEIGHT = 10;
	
	/** Offset of the paddle up from the bottom */ 
	private static final int PADDLE_Y_OFFSET = 30;
	
	/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;
	
	/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;
	
	/** Separation between bricks */
	private static final int BRICK_SEP = 4;
	
	/** Width of a brick */
	private static final int BRICK_WIDTH = (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;
	
	/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;
	
	/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;
	
	/** Offset of the top brick row from the top */ 
	private static final int BRICK_Y_OFFSET = 70;
	
	/** Number of turns */
    private static final int NTURNS = 3;
    
    /** Animation delay */
    private static final int DELAY = 10;

    /** Paddle and ball */
    private GRect paddle;     
    private GOval ball;
    
    /* Velocity of the ball */
    private double vx, vy;
    
    private RandomGenerator rgen = RandomGenerator.getInstance();
    
    public void run() {
    	setupGame();
    	startGame();
    } 
    
    private void setupGame() {
    	drawBricks();
    	drawPaddle();
    	drawBall();
    }
    
    private void startGame() {
    	setInitialBallVelocity();
    	
    	int i = 0;
    	while(true) {
    		moveBall();
    		bounceIfWallCollision();
    		pause(DELAY);
    		i++;
    		if (i == 800) break;
    	}
    }
    
    /**
     * move the ball according to vx and vy.
     */
    private void moveBall() {
    	ball.move(vx, vy);
    }
    
    /**
     * Reverse vx or vy if ball collides with wall.
     */
    private void bounceIfWallCollision() {
    	double x = ball.getX();
    	double y = ball.getY();
    	
    	if (x <= 0 || (x + BALL_RADIUS) >= WIDTH) vx = -vx;
    	if (y <= 0 || (y + BALL_RADIUS) >= HEIGHT) vy = -vy;
    }
    
    /**
     * Set initial value for vx and vy.
     */
    private void setInitialBallVelocity() {
    	vx = rgen.nextDouble(1.0, 3.0);
    	if (rgen.nextBoolean(0.5)) vx = -vx;
    	
    	vy = 3;
    }
    
    /**
     * Draw a grid of bricks on the board.
     */
    private void drawBricks() {
    	int x = BRICK_SEP / 2;
    	int y = BRICK_Y_OFFSET;
    	Color color;
    	
    	for (int i = 0; i < NBRICK_ROWS; i++) {
    		color = getColorForRow(i);
    		
    		for (int j = 0; j < NBRICKS_PER_ROW; j++) {
    			drawBrick(x, y, color);
    			x += BRICK_SEP + BRICK_WIDTH;
    		}
    		
    		x = BRICK_SEP / 2;
    		y += BRICK_SEP + BRICK_HEIGHT;
    	}
    }
    
    /**
     * Draw the paddle
     */
    private void drawPaddle() {
    	int x = (WIDTH / 2) - (PADDLE_WIDTH / 2);
    	int y = HEIGHT - PADDLE_Y_OFFSET - PADDLE_HEIGHT;
    	
    	paddle = new GRect(PADDLE_WIDTH, PADDLE_HEIGHT);
    	paddle.setFilled(true);;
    	paddle.setFillColor(Color.BLACK);
    	
    	add(paddle, x, y);
    }
    
    /**
     * Draw the ball.
     */
    private void drawBall() {
    	int x = WIDTH / 2 - BALL_RADIUS / 2;
    	int y = HEIGHT / 2 - BALL_RADIUS / 2;
    	ball = new GOval(BALL_RADIUS, BALL_RADIUS);
    	ball.setFilled(true);
    	ball.setFillColor(Color.BLACK);
    	
    	add(ball, x, y);
    }
    
    /**
     * Returns a color based on row number.
     * @param row - number of row
     * @return Color
     */
    private Color getColorForRow(int row) {
    	if (row < 2) return Color.RED;
    	if (row < 4) return Color.ORANGE;
    	if (row < 6) return Color.YELLOW;
    	if (row < 8) return Color.GREEN;
    	return Color.CYAN;
    }
    
    /**
     * Draw a brick at a given location with a given color.
     * @param x - Horizontal position of brick.
     * @param y - Vertical position of brick.
     * @param color - Color of brick.
     */
    private void drawBrick(int x, int y, Color color) {    	
    	GRect brick = new GRect(x, y, BRICK_WIDTH, BRICK_HEIGHT);   	
    	brick.setFilled(true);
    	brick.setColor(color);
    	brick.setFillColor(color);
    	add(brick);
    }
}
