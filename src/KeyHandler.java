import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;

public class KeyHandler extends KeyAdapter{
	
	public static HashMap<String, Boolean> keysPressed = new HashMap<>();
	
	public KeyHandler() {
		keysPressed.put("L", false);
		keysPressed.put("R", false);
		keysPressed.put("U", false);
		keysPressed.put("D", false);
		keysPressed.put("Z", false);
		keysPressed.put("X", false);
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_RIGHT) {
			keysPressed.replace("R",true);
		}else if(key == KeyEvent.VK_LEFT) {
			keysPressed.replace("L",true);
		}
		
		if(key == KeyEvent.VK_UP) {
			keysPressed.replace("U",true);
		}else if(key == KeyEvent.VK_DOWN) {
			keysPressed.replace("D",true);
		}
		
		if(key == KeyEvent.VK_Z){
			keysPressed.replace("Z", true);
		}else if(key == KeyEvent.VK_X){
			keysPressed.replace("X", true);
		}

	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_ESCAPE) {
			System.exit(1);
		}
		
		if(key == KeyEvent.VK_RIGHT) {
			keysPressed.replace("R",false);
		}else if(key == KeyEvent.VK_LEFT) {
			keysPressed.replace("L",false);
		}
		
		if(key == KeyEvent.VK_UP) {
			keysPressed.replace("U",false);
		}else if(key == KeyEvent.VK_DOWN) {
			keysPressed.replace("D",false);
		}
		
		if(key == KeyEvent.VK_SPACE) {
			Fish.nextFish();
		}
	}

}
