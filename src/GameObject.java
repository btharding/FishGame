import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.util.LinkedList;

public abstract class GameObject {
	
	public GameObject() {
		Handler.objects.add(this);
	}
	
	public abstract void tick();
	public abstract void render(Graphics2D g2d);
	public abstract LinkedList<Shape> getCollisionBounds();
	
	public boolean isOnScreen() {
		
		Rectangle window = new Rectangle((int)(Camera.getxOffset()),(int)(Camera.getyOffset()),Game.WIDTH,Game.HEIGHT);
//		Rectangle window = new Rectangle(0,0,Game.WIDTH,Game.HEIGHT);
		
		return collidesWith(window);
	}
	
	public boolean collidesWith(Rectangle target) {
		LinkedList<Shape> collisionBounds = this.getCollisionBounds();
		if(collisionBounds.size()==0) return false;
		
		for(int i = 0; i < collisionBounds.size(); i++) {
			Shape currentBound = collisionBounds.get(i);
			if(currentBound.intersects(target)) return true;
		}
		return false;
	}
	
	public static Shape getRotatedShape(Rectangle shape, float angle, float rotX, float rotY) {
		AffineTransform transform = new AffineTransform();
		transform.rotate(angle, rotX, rotY);
		return transform.createTransformedShape(shape);
	}
}
