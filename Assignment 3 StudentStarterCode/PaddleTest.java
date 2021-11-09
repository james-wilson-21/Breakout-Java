import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.Graphics;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PaddleTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		 
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void resetPositionTest() {
	Settings settings = new Settings();
	Paddle paddle = new Paddle();
	assertEquals(paddle.getX(), Settings.INITIAL_PADDLE_X);
	assertEquals(paddle.getY(), Settings.INITIAL_PADDLE_Y);
	}
	
	
	@Test // Argh can't do this one yet
	void paintTest(Graphics g) {
		Paddle paddle = new Paddle();
		Sprite sprite = new Sprite();
		sprite.setX(10);
		sprite.setY(10);
		g.fillRect(sprite.x, sprite.y, Settings.PADDLE_WIDTH, Settings.PADDLE_HEIGHT);
		Graphics h = g;
		h.fillRect(10,10,10,10);
		assertEquals(h, g);
	}
	
	@Test
	void xVelocityTest() {
		Paddle paddle = new Paddle();
		int vel = 10;
		paddle.setXVelocity(vel);
		assertEquals(10, vel);
	}
}
