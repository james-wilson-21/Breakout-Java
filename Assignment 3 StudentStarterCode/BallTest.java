import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BallTest {
	 

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		Ball ball = new Ball();
		Settings settings = new Settings();
		System.out.println("Using @BeforeClass , executed before all test cases ");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		System.out.println("Using @AfterClass ,executed after all test cases");	
	}

	@BeforeEach
	void setUp() throws Exception {
		System.out.println("Using @Before annotations ,executed before each test cases ");
	}

	@AfterEach
	void tearDown() throws Exception {
		System.out.println("Using @After ,executed after each test cases");
	}
	
	@Test
	void ballObjectTest() {
		Ball ball = new Ball();
		assertNotNull(ball);
	}

	@Test
	void resetPositionTest() {
		Settings settings = new Settings();
		int xResult = 150;
		int yResult = 350;
		assertEquals(xResult, Settings.INITIAL_BALL_X);
		assertEquals(yResult, Settings.INITIAL_BALL_Y);
	}

	@Test
	void xVelocityTest() {
		Ball ball = new Ball();
		assert(ball.getXVelocity() == 1);
	}
	
	@Test
	void yVelocityTest() {
		Ball ball = new Ball();
		assert(ball.getYVelocity() == -1);
	}
}
