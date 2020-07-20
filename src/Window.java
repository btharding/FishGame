import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Window extends Canvas{
	public static JFrame frame;

	private static final long serialVersionUID = 1877720651231192133L;
	
	public Window(int width, int height, String title, Game game) {
		Window.frame = new JFrame(title);	
		
		frame.getContentPane().setPreferredSize(new Dimension(width, height));
		frame.getContentPane().setMaximumSize(new Dimension(width, height));
		frame.getContentPane().setMinimumSize(new Dimension(width, height));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.add(game);
		frame.setVisible(true);
		game.start();

	}
	
}