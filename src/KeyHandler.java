import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyHandler extends KeyAdapter{

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		

	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_ESCAPE) {
			System.exit(1);
		}
	}

}
