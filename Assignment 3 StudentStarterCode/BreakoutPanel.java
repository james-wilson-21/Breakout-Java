import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class BreakoutPanel extends JPanel implements ActionListener, KeyListener {

	static final long serialVersionUID = 2L;
	// Random utility to create different coloured bricks on each playthrough
	Random rand = new Random(); 
	float red;
	float green;
	float blue;
	Color randomColour = new Color(red, green, blue);
	
	//private booleans to add a pause function and also restart or exit the game
	private boolean pause;
	private boolean playAgain;
	private boolean exitGame;
	
	private boolean gameRunning;
	private int livesLeft;
	private String screenMessage;
	private Ball ball;
	private Paddle paddle;
	private Brick bricks[];
	
	public BreakoutPanel(Breakout game) {
		addKeyListener(this);
		setFocusable(true);
		Timer timer = new Timer(1, this);
		timer.start();
		//the start game method is called here to enable restarting of the game with reset features
		startGame(); 
		
		
	}
	
	private void startGame() {
		// Variables initialised 
		playAgain = false;
		exitGame = false;
		pause = false;
		gameRunning = true;
		screenMessage = ""; 
		livesLeft = 3;
		red = rand.nextFloat();
		green = rand.nextFloat();
		blue = rand.nextFloat();
		
		// Create a new colour object for each iteration of play
		randomColour = new Color(red, green, blue);
		// Create a new ball object and assign it to the appropriate variable
		ball = new Ball();
		// Create a new paddle object and assign it to the appropriate variable
		paddle = new Paddle();
		// Create a new bricks array (Use Settings.TOTAL_BRICKS)
		bricks = new Brick [Settings.TOTAL_BRICKS];
		// Call the createBricks() method
		createBricks();
		
		
	}
	private void createBricks() {
		int counter = 0;
		int x_space = 0;
		int y_space = 0;
		for(int x = 0; x < 4; x++) {
			for(int y = 0; y < 5; y++) {
				bricks[counter] = new Brick((x * Settings.BRICK_WIDTH) + Settings.BRICK_HORI_PADDING + x_space, (y * Settings.BRICK_HEIGHT) + Settings.BRICK_VERT_PADDING + y_space);
				counter++;
				y_space++;
			}
			x_space++;
			y_space = 0;
		}
	}
	
	private void paintBricks(Graphics g) {
		// Loop through the bricks and call the paint() method
		for (int x=0; x < Settings.TOTAL_BRICKS; x++) {
			bricks[x].paint(g);
		}
	}
	
	private void update() {
		if(gameRunning) {
			// Update the ball and paddle
			ball.update();
			paddle.update();
			collisions();
			repaint();
		}
	}
	
	private void gameOver() {
		// Set screen message
		screenMessage = "Game Over s: Play Again or e: Exit";
		resetGame();
	}
	
	private void gameWon() {
		// Set screen message
		screenMessage = "You Win! s: Play Again or e: Exit";
		resetGame();
	}
	
	private void stopGame() {
		// exits the programme
		System.exit(0);
	}
	
	private void resetGame() {
		gameRunning = false;
		// if statement to either restart or close the game
		if(playAgain) {
			// restart the game
			startGame();
		}
		if(exitGame) {
			// Call the stopGame method to exit the game
			stopGame();
		}
	}
	
	private void collisions() {
		// Check for loss
		if(ball.y > 450) {
			// Game over
			livesLeft--;
			if(livesLeft <= 0) {
				gameOver();
				return;
			} else {
				ball.resetPosition();
				ball.setYVelocity(-1);
			}
		}
		
		// Check for win
		boolean bricksLeft = false;
		for(int i = 0; i < bricks.length; i++) {
			// Check if there are any bricks left
			if(!bricks[i].isBroken()) {
				// Brick was found, close loop
				bricksLeft = true;
				break;
			}
		}
		if(!bricksLeft) {
			gameWon();
			return;
		}
		
		// Check collisions
		if(ball.getRectangle().intersects(paddle.getRectangle())) {
			// Simplified touching of paddle
			// Proper game would change angle of ball depending on where it hit the paddle
			ball.setYVelocity(-1);
		}
		
		for(int i = 0; i < bricks.length; i++) {
			if (ball.getRectangle().intersects(bricks[i].getRectangle())) {
				int ballLeft = (int) ball.getRectangle().getMinX();
	            int ballHeight = (int) ball.getRectangle().getHeight();
	            int ballWidth = (int) ball.getRectangle().getWidth();
	            int ballTop = (int) ball.getRectangle().getMinY();

	            Point pointRight = new Point(ballLeft + ballWidth + 1, ballTop);
	            Point pointLeft = new Point(ballLeft - 1, ballTop);
	            Point pointTop = new Point(ballLeft, ballTop - 1);
	            Point pointBottom = new Point(ballLeft, ballTop + ballHeight + 1);

	            if (!bricks[i].isBroken()) {
	                if (bricks[i].getRectangle().contains(pointRight)) {
	                    ball.setXVelocity(-1);
	                } else if (bricks[i].getRectangle().contains(pointLeft)) {
	                    ball.setXVelocity(1);
	                }

	                if (bricks[i].getRectangle().contains(pointTop)) {
	                    ball.setYVelocity(1);
	                } else if (bricks[i].getRectangle().contains(pointBottom)) {
	                    ball.setYVelocity(-1);
	                }
	                bricks[i].setBroken(true);
	            }
			}
		}
	}
	
	@Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Settings.PLAYER_COLOUR); // Colour changes made for maintenance
        ball.paint(g);
        g.setColor(Settings.PADDLE_COLOUR); // Colour changes made for maintenance
        paddle.paint(g);
        g.setColor(randomColour);           // Colour changes made for maintenance
        paintBricks(g);
        
        // Draw lives left
        // Draw lives left in the top left hand corner
        g.setFont(new Font("Arial", Font.BOLD, 12));
        g.setColor(Color.BLACK);            // Colour changes for maintenance
        g.drawString("Lives: " + Integer.toString(livesLeft), Settings.LIVES_POSITION_X, Settings.LIVES_POSITION_Y);
        // Draw screen message
        if(screenMessage != null) {
        	g.setFont(new Font("Arial", Font.BOLD, 18));
        	int messageWidth = g.getFontMetrics().stringWidth(screenMessage);
        	g.drawString(screenMessage, (Settings.WINDOW_WIDTH / 2) - (messageWidth / 2), Settings.MESSAGE_POSITION);
        }
    }

	@Override
	public void keyPressed(KeyEvent e) {
		// Game key variables
		int restartKey = e.getKeyCode();
		int pauseKey = e.getKeyCode();
		int exitKey = e.getKeyCode();
		
		// Set the velocity of the paddle depending on whether the player is pressing left or right
			if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
				paddle.setXVelocity(1);
				}if(e.getKeyCode() == KeyEvent.VK_LEFT) {
					paddle.setXVelocity(-1);
				}if(pauseKey == KeyEvent.VK_P) { //Pause key added as implementation of testing feedback
					pause = !pause;
				}
		// Restart and exit added as implementation of testing feedback
		if(!gameRunning) {
		if(restartKey == KeyEvent.VK_S) {
				playAgain = true;
				gameRunning = true;
			}
		if(exitKey ==KeyEvent.VK_E) {
				exitGame = true;
				gameRunning = true;
			}
		}  
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// Set the velocity of the paddle after the player has released the keys
		if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_LEFT) {
            paddle.setXVelocity(0);
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		//pause variable halts the update method for the game
		if(!pause) 
		update();
		
	}
}

