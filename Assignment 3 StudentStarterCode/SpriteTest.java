import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SpriteTest {

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
	void xTest() {
	Sprite sprite = new Sprite();
	sprite.setX(10);
	assertEquals(10,sprite.getX());
	}
	
	@Test
	void yTest() {
	Sprite sprite = new Sprite();
	sprite.setY(10);
	assertEquals(10,sprite.getY());
	}
	
	@Test
	void widthTest() {
	Sprite sprite = new Sprite();
	sprite.setWidth(10);
	assertEquals(10,sprite.getWidth());
	}
	
	@Test
	void heightTest() {
	Sprite sprite = new Sprite();
	sprite.setHeight(10);
	assertEquals(10,sprite.getHeight());
	}
}
