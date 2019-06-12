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
 
@SuppressWarnings("serial") 
public class Breakout extends GraphicsProgram {
	
	/** Width and height of application window in pixels */ 
	public static final int APPLICATION_WIDTH = 400; 
	public static final int APPLICATION_HEIGHT = 600;
	
	/** Dimensions of game board (usually the same) */ 
	private static final int WIDTH = APPLICATION_WIDTH; 
	private static final int HEIGHT = APPLICATION_HEIGHT;
	
	/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 70; 
	private static final int PADDLE_HEIGHT = 10;
	
	/** Offset of the paddle up from the bottom */ 
	private static final int PADDLE_Y_OFFSET = 30;
	
	/** Paddle distance from top of application */
	private static final int PADDLE_POSITION = HEIGHT - PADDLE_Y_OFFSET - PADDLE_HEIGHT;
	
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
    private static final int DELAY = 7;
    
    /** Keyboard paddle speed */
    private static final int KEY_PADDLE_SPEED = 5;
    
    /** Paddle object */
    private GRect paddle;
    
    /** Ball object */
    private GOval ball;
    
    /** message to display */
    private GLabel message;
    
    /** Horizontal velocity of the ball */
    private double vx;
    
    /** Vertical velocity of the ball */
    private double vy;
    
    /** Number of times paddle and ball have collided */
    private int paddleCollisionCount = 0;
    
    /** Paddle distance from left edge in pixels. */
    private int paddleXPosition = (WIDTH / 2) - (PADDLE_WIDTH / 2);
    
    /** Current mouse movement by keyboard: 0, or positive or negative KEY_PADDLE_SPEED. */
    private int keyMouseMove = 0;
    
    /** Bricks remaining in level */
    private int nBricksRemaining = NBRICK_ROWS * NBRICKS_PER_ROW;
    
    /** Random number generator */
    private RandomGenerator rgen = RandomGenerator.getInstance();
    
    /**
     * Handle game setup and start.
     */
    public void run() {
    	while(true) {
    	   	setupGame();
        	playGame();
    	}
    } 
    
    /**
     * Draw the basic game elements.
     */
    private void setupGame() {
    	drawBricks();
    	drawPaddle();
    	drawBall();    	
    	setInitialBallVelocity();
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
    	int x = paddleXPosition;
    	int y = PADDLE_POSITION;
    	
    	paddle = new GRect(PADDLE_WIDTH, PADDLE_HEIGHT);
    	paddle.setFilled(true);;
    	paddle.setFillColor(Color.BLACK);
    	
    	add(paddle, x, y);
    }
    
    /**
     * Draw the ball.
     */
    private void drawBall() {
    	int x = getWidth() / 2 - BALL_RADIUS;
    	int y = getHeight() / 2 - BALL_RADIUS;
    	ball = new GOval(BALL_RADIUS * 2, BALL_RADIUS * 2);
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

	/**
	 * Main game loop.
	 */
    private void playGame() {    	
    	addMouseListeners();
    	addKeyListeners();

    	int remainingTurns = NTURNS;

	   	displayModalUntilClick("BREAKOUT", "Click to start!");
	   	
    	while(nBricksRemaining > 0 && remainingTurns > 0) {
    		int score = (100 - nBricksRemaining) * 100;
        	displayMessage("Turns left: " + remainingTurns + "    Score: " + score);

        	ball.move(vx, vy);
    		
    		if (!didBallHitFloor()) {
    			remainingTurns--;
    			
    			if (remainingTurns > 0) {
    				resetForNextTurn();
    			}
    		}
    		
    		bounceIfWallCollision();
    		handleObjectCollision();
    		movePaddleOnKeydown();
    		
    		pause(DELAY);
    	}
    	
    	
    	if (nBricksRemaining == 0) {
    	   	displayModalUntilClick("Level finished!", "Click to continue");
    	} else {
    	   	displayModalUntilClick("Game Over!", "Click to continue");
    	}	
    	removeAll();
    }
        
	/**
	 * Set initial value for horizontal and vertical ball velocity.
	 */
	private void setInitialBallVelocity() {
		vx = rgen.nextDouble(1.0, 3.0);
		if (rgen.nextBoolean(0.5)) vx = -vx;
		
		vy = 3;
	}    
    
	/**
	 * Check for collision with floor.
	 * @return - Boolean - True on floor collision.
	 */
    private boolean didBallHitFloor() {
    	double y = ball.getY();
    	
    	return (y + BALL_RADIUS * 2) <= HEIGHT;
    }

    /**
     * Prepare board for next ball.
     */
    private void resetForNextTurn() {
		remove(ball);
		drawBall();
		setInitialBallVelocity();
		paddleCollisionCount = 0;
		pause(2000);    				
    }
    
    /**
     * Reverse velocity if ball collides with wall or ceiling.
     */
    private void bounceIfWallCollision() {
    	double x = ball.getX();
    	double y = ball.getY();
    	
    	// Ball hits side
    	if (x <= 0 || (x + BALL_RADIUS * 2) >= getWidth()) vx = -vx;
    	
    	// Ball hits top
    	if (y <= 0) vy = -vy;
    }
    
    /**
     * If the ball collides with a brick, remove it; bounce for brick or paddle.
     */
    private void handleObjectCollision() {
    	GObject obstacle = getCollidingObject();
    	
    	if (obstacle == null) return;
    	if (obstacle == message) return;
    	
		vy = -vy;
		
		if (obstacle == paddle) {
			paddleCollisionCount++;
			
			// if we're hitting the paddle's side, we need to also bounce horizontally
			if (ball.getY() + BALL_RADIUS * 2 <= PADDLE_POSITION) {
				vx = -vx;
			}
		} else {
    		// if the object isn't the paddle, it's a brick
			remove(obstacle);
			nBricksRemaining--;
		}
		
		if (paddleCollisionCount == 7) {
			vx *= 1.1;
		}
    }
    
    /**
     * Return object the paddle has collided with, if any.
     * @return - Colliding GObject or null.
     */
    private GObject getCollidingObject() {
    	double x1 = ball.getX();
    	double y1 = ball.getY();
    	double x2 = x1 + BALL_RADIUS * 2;
    	double y2 = y1 + BALL_RADIUS * 2;
   
    	if (getElementAt(x1, y1) != null) return getElementAt(x1, y1);
    	if (getElementAt(x1, y2) != null) return getElementAt(x1, y2);
    	if (getElementAt(x2, y1) != null) return getElementAt(x2, y1);
    	if (getElementAt(x2, y2) != null) return getElementAt(x2, y2);
    
    	return null;
    }    

    /**
     * Move the paddle if an arrow key is being held down.
     */
    public void movePaddleOnKeydown() {
    	if (keyMouseMove == 0) return;
    	double paddlePosition = paddle.getX();
    	
    	// don't move past the edges of the board
    	if (paddlePosition <= 0 && keyMouseMove == -KEY_PADDLE_SPEED) return;
    	if (paddlePosition > getWidth() - PADDLE_WIDTH && keyMouseMove == KEY_PADDLE_SPEED) return;

    	paddle.move(keyMouseMove, 0);
    }

    /**
     * Handle mouse movement events.
     */
    public void mouseMoved(MouseEvent e) {
    	int paddleOffset = e.getX() - paddleXPosition - PADDLE_WIDTH / 2;
    	paddleXPosition += paddleOffset;
    	
    	// don't move past the edges of the board
    	if (paddle.getX() <= 0 && paddleOffset < 0) return;
    	if (paddle.getX() + PADDLE_WIDTH >= getWidth() && paddleOffset > 0) return;
    	
    	paddle.move(paddleOffset, 0);
    }
    
    /**
     * Key event: set negative paddle speed for left, positive for right.
     */
    public void keyPressed(KeyEvent e) {
    	int keyCode = e.getKeyCode();
    	
    	if (keyCode == 37) {
    		keyMouseMove = -KEY_PADDLE_SPEED;
    	}
    	if (keyCode == 39) {
    		keyMouseMove = KEY_PADDLE_SPEED;
    	}
    }
    
    /**
     * Key event: reset paddle speed on key up.
     */
    public void keyReleased(KeyEvent e)  {
    	keyMouseMove = 0;
    }
    
    /**
     * Display 2-line modal message and wait for click, then remove message.
     * @param line1 - string - larger title line.
     * @param line2 - string - smaller secondary line.
     */
    private void displayModalUntilClick(String line1, String line2) {
    	GRect frame1 = new GRect(260, 150);
    	frame1.setFilled(true);
    	frame1.setFillColor(Color.WHITE);

    	GRect frame2 = new GRect(260 - 6, 150 - 6);

    	GLabel label1 = new GLabel(line1);
    	label1.setFont("SansSerif-30");

    	GLabel label2 = new GLabel(line2);
    	label2.setFont("SansSerif-20");
    	
    	int cx = getWidth() / 2;
    	int cy = getHeight() / 2;
    	
    	add(frame1, (cx - frame1.getWidth() / 2), (cy - frame1.getHeight() / 2));
    	add(frame2, (cx - frame2.getWidth() / 2), (cy - frame2.getHeight() / 2));
    	add(label1, (cx - label1.getWidth() /2), (cy - label1.getHeight() / 2 ));
    	add(label2, (cx - label2.getWidth() /2), (cy - label2.getHeight() / 2 + 46));
    	
    	waitForClick();
    	
    	remove(frame1);
    	remove(frame2);
    	remove(label1);
    	remove(label2);
    	
    }
        
    /**
     * Display message in top left corner.
     * @param info - String - message to display.
     */
    private void displayMessage(String info) {
    	if (message == null) {
    		setupMessage();
    	}
    	message.setLabel(info);
		add(message, 6, 15);
    }
    
    /**
     * Create label for displaying debug message.
     */
    private void setupMessage() {
    	message = new GLabel("");
    	message.setFont("SansSerif-12");
    }    
}
