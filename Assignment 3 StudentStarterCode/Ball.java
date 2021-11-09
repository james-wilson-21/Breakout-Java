import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Ball extends Sprite {

	private int xVelocity = 1, yVelocity = -1;
	
	// Constructor
	public Ball() {
		
		// Set width to Settings.BALL_WIDTH
		setWidth(Settings.BALL_WIDTH);
		// Set width to Settings.BALL_HEIGHT
		setHeight(Settings.BALL_HEIGHT);
		// Call resetPosition
		resetPosition();
	}
	

	public void resetPosition() {
		setX(Settings.INITIAL_BALL_X);
		// Set the balls y by using the INITIAL_BALL_Y (see above)
		setY(Settings.INITIAL_BALL_Y);
	}
	
	public void update() {
		x += xVelocity;
		// Increase the y variable by yVelocity (see above)
		y += yVelocity;
		// Bounce off left side of screen
		if(x <= 0) {
			// Set x to 0 so it does not leave the screen
			setX(0);
			// Change the x velocity to make the ball go right
			setXVelocity(-getXVelocity());
		}
		
		// Bounce off right side of screen
		if(x >= Settings.WINDOW_WIDTH - Settings.BALL_WIDTH) {
			// Set x to the right edge of the screen (see the above if condition)
			x = Settings.WINDOW_WIDTH - Settings.BALL_WIDTH;
			// Change the x velocity to make the ball go left
			setXVelocity(-1);
		}
		
		// Bounce off top of screen
		if(y <= 0) {
			// TODO: Set y to 0 so it does not leave the screen
			setY(0);
			// TODO: Change the y velocity to make the ball go downward
			setYVelocity(-getYVelocity());
		}
		
	}
	
	public void setXVelocity(int x) {
		// Set the x velocity
		xVelocity = x;
	}
	public void setYVelocity(int y) {
		// Set the y velocity
		yVelocity = y;
	}
	
	public int getXVelocity() {
		return xVelocity;	// Return the x velocity
	}
	public int getYVelocity() {
		return yVelocity;	// Return the y velocity
	}
	
	public void paint(Graphics g) {
		g.fillOval(x, y, Settings.BALL_WIDTH, Settings.BALL_HEIGHT);
		
	}
}
