import java.awt.Graphics;

public class Paddle extends Sprite {

	private int xVelocity;
	
	public Paddle() {
		// Set width to Settings.PADDLE_WIDTH
		setWidth(Settings.PADDLE_WIDTH);
		// Set width to Settings.PADDLE_HEIGHT
		setHeight(Settings.PADDLE_HEIGHT);
		// Call resetPosition
		resetPosition();
	}
	
	public void resetPosition() {
		// Set initial position x and y (use INITIAL_PADDLE_X/Y)
		setX(Settings.INITIAL_PADDLE_X);
		setY(Settings.INITIAL_PADDLE_Y);
	}
	
	public void update() {
		x += xVelocity;
		// Prevent the paddle from moving outside of the screen
		if(x < 0) {
			setX(0);
		}
		if(x >= Settings.WINDOW_WIDTH - Settings.PADDLE_WIDTH ) {
			x = Settings.WINDOW_WIDTH - Settings.PADDLE_WIDTH;
		}
	}
	
	public void paint(Graphics g) {
		g.fillRect(x, y, Settings.PADDLE_WIDTH, Settings.PADDLE_HEIGHT);
	}
	
	public void setXVelocity(int vel) {
		// Set x velocity
		xVelocity = vel;
	}
}