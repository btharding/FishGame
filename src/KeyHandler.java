import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;

public class KeyHandler extends KeyAdapter{
	
	public static HashMap<String, Boolean> arrowKeysPressed = new HashMap<>();
	
	public KeyHandler() {
		arrowKeysPressed.put("L", false);
		arrowKeysPressed.put("R", false);
		arrowKeysPressed.put("U", false);
		arrowKeysPressed.put("D", false);
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_RIGHT) {
			arrowKeysPressed.replace("R",true);
		}else if(key == KeyEvent.VK_LEFT) {
			arrowKeysPressed.replace("L",true);
		}
		
		if(key == KeyEvent.VK_UP) {
			arrowKeysPressed.replace("U",true);
		}else if(key == KeyEvent.VK_DOWN) {
			arrowKeysPressed.replace("D",true);
		}

	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_ESCAPE) {
			System.exit(1);
		}
		
		if(key == KeyEvent.VK_RIGHT) {
			arrowKeysPressed.replace("R",false);
		}else if(key == KeyEvent.VK_LEFT) {
			arrowKeysPressed.replace("L",false);
		}
		
		if(key == KeyEvent.VK_UP) {
			arrowKeysPressed.replace("U",false);
		}else if(key == KeyEvent.VK_DOWN) {
			arrowKeysPressed.replace("D",false);
		}
	}

}
