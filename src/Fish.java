import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.util.LinkedList;
import java.util.Random;

public class Fish extends GameObject{
	
	//The x and y coordinates refer to the top left of the head.
	private float x, y, velX, velY, angle, speed;
	private int width, headHeight, tailHeight, turning, timer;
	private Color headColor, tailColor;
	private Random rand = new Random();

	public Fish(int x, int y, float speed, int width, int headHeight, int tailHeight, Color headColor, Color tailColor) {
		super(true, true);
		this.x = x;
		this.y = y;
		this.velX = 0;
		this.velY = 0;
		this.angle = (float) 0;
		this.speed = speed;
		this.width = width;
		this.headHeight = headHeight;
		this.tailHeight = tailHeight;
		this.turning = 0;
		this.timer = rand.nextInt(10)+10;
		
		this.headColor = headColor;
		this.tailColor = tailColor;
	}

	@Override
	public void tick() {
		if(this.isOnScreen()) {
			this.renderable = true;
		}else {
			this.renderable = false;
		}
		this.ai();
		this.move();
	}
	
	@Override
	public void render(Graphics2D g2d) {
		g2d.setColor(this.headColor);
		g2d.fill(this.getHead());
		g2d.setColor(this.tailColor);
		g2d.fill(this.getTail());
	}

	@Override
	public LinkedList<Shape> getCollisionBounds() {
		LinkedList<Shape> output = new LinkedList<>();
		output.add(this.getTail());
		output.add(this.getHead());
		return output;
	}
	
	private Shape getHead() {
		return getRotatedShape(new Rectangle((int)this.x, (int)this.y, this.width, this.headHeight), this.angle, this.x+(this.width/2), this.y);
	}
	
	private Shape getTail() {
		return getRotatedShape(new Rectangle((int)this.x, (int)(this.y-this.tailHeight), this.width, this.tailHeight), (float)(this.angle+this.turning*0.1*Math.PI), this.x+(this.width/2), this.y);
	}
	
	private void move() {
		this.angle = (float) (this.angle % (2*Math.PI));
		this.x += this.velX;
		this.y += this.velY;
		velX = (float) Math.cos(angle+(0.5*Math.PI));
		velY = (float) Math.sin(angle+(0.5*Math.PI));
	}
	
	private void ai() {
		if(this.timer <= 0) {
			this.turning = this.rand.nextInt(3)-1;
			if(this.turning == 0) {
				this.timer = this.rand.nextInt(10)+10;
			}else {
				this.timer = this.rand.nextInt(30)+30;
			}
		}else {
			this.timer -= 1;
		}
		
		if(this.turning == 1) {
			this.angle -= 0.03;
		}else if(this.turning == -1) {
			this.angle += 0.03;
		}
	}

}
