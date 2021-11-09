import java.awt.Color;


import javax.swing.JFrame;

public class Breakout extends JFrame{
	
	static final long serialVersionUID = 1L;
	
	private BreakoutPanel panel;
	
	
	public Breakout() {
		// Set the title
		setTitle(Settings.WINDOW_NAME);
		// Set resizable to false
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
     	// Set the size of the screen (use Settings.WINDOW_WIDTH/HEIGHT)
     	setSize(Settings.WINDOW_WIDTH, Settings.WINDOW_HEIGHT);
     	panel = new BreakoutPanel(this);
        add(panel);
        // Set the background colour to white
     	panel.setBackground(Settings.BACKGROUND_COLOUR); // Changes to colour only possible when called from the JPanel
		// Set visible to true
        setVisible(true);
	}

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
	         public void run() {
	        	 new Breakout();	
	         }
		});

	}
}