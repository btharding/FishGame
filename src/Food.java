import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.util.LinkedList;

public class Food extends GameObject{
	
	private int x, y, width, height;
	private Color color;

	public Food(int x, int y, Color color) {
		super();
		this.x = x;
		this.y = y;
		this.width = 5;
		this.height = 5;
		this.color = color;
	}

	@Override
	public void tick() {
		return;
	}

	@Override
	public void render(Graphics2D g2d) {
		g2d.setColor(this.color);
		g2d.fillRect(this.x, this.y, this.width, this.height);
	}

	@Override
	public LinkedList<Shape> getCollisionBounds() {
		LinkedList<Shape> out = new LinkedList<>();
		out.add(new Rectangle(this.x, this.y, this.width, this.height));
		return out;
	}

}
