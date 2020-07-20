import java.awt.Graphics2D;
import java.util.LinkedList;

public class Handler{
	public static LinkedList<GameObject> objects = new LinkedList<GameObject>();
	
	public void tick() {
		for(int i = 0; i< objects.size(); i++) {
			GameObject object = objects.get(i);
			object.tick();
		}
	}
	
	public void render(Graphics2D g2d) {
		for(int i = 0; i< objects.size(); i++) {
			GameObject object = objects.get(i);
			if(object.isRenderable()) {
				object.render(g2d);
			}
		}		
	}
	
}