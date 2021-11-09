import java.awt.Rectangle;

public class Sprite {
	
	protected int x,y,width,height;
	
	

	public void setX(int newX) {
		x = newX;
	}
	public void setY(int newY) { 
		y = newY;
	}
	public void setWidth(int newWidth) { 
		width = newWidth;
	}
	public void setHeight(int newHeight) { 
		height = newHeight;
	}
	
	
	
	public int getX() { 
		return x;	
	}
	public int getY() { 
		return y;	
	}
	public int getWidth() { 
		return width;	
	}
	public int getHeight() { 
		return height;	
	}
	
	
	Rectangle getRectangle() {
		return new Rectangle(x, y, width, height);
	}
}