import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Shape;
import java.util.LinkedList;

public abstract class GameObject {
	
	//Can the object update?
	protected boolean updateable;
	//Can the object currently be rendered?
	protected boolean renderable;
	
	public static LinkedList<GameObject> objectList= new LinkedList<>();
	
	public GameObject(boolean updateable, boolean renderable) {
		this.updateable = updateable;
		this.renderable = renderable;
		objectList.add(this);
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract LinkedList<Shape> getCollisionBounds();
	
	public boolean isOnScreen() {
		LinkedList<Shape> collisionBounds = this.getCollisionBounds();
		if(collisionBounds.size()==0) return false;
		
		Rectangle window = new Rectangle(0,0,Game.WIDTH,Game.HEIGHT);
		
		for(int i = 0; i < collisionBounds.size(); i++) {
			Shape currentBound = collisionBounds.get(i);
			if(currentBound.intersects(window)) return true;
		}
		return false;
	}
	
	public boolean isUpdateable() {
		return updateable;
	}
	
	public boolean isRenderable() {
		return renderable;
	}
}
