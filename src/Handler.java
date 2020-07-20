import java.awt.Graphics;
import java.util.LinkedList;

public class Handler{
	public static LinkedList<GameObject> objects = new LinkedList<GameObject>();
	
	public void tick() {
		for(int i = 0; i< objects.size(); i++) {
			GameObject object = objects.get(i);
			if(object.isUpdateable()) {
				object.tick();
			}
		}
	}
	
	public void render(Graphics g) {
		for(int i = 0; i< objects.size(); i++) {
			GameObject object = objects.get(i);
			if(object.isRenderable()) {
				object.render(g);
			}
		}		
	}
	
}