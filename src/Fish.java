import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.util.LinkedList;
import java.util.Random;

public class Fish extends GameObject{
	
	public static Fish selectedFish = null;
	private static LinkedList<Fish> fishList = new LinkedList<>();
	private static Random rand = new Random();
	
	private static enum States{
		WANDERING,
	};
	
	//The x and y coordinates refer to the top left of the head.
	private String name;
	private float x, y, velX, velY, angle, speed, timer;
	private int width, headHeight, tailHeight, turning;
	private Color headColor, tailColor;
	private boolean selected = false;
	private States state = States.WANDERING;

	public Fish(String name, int x, int y, float speed, int width, int headHeight, int tailHeight, Color headColor, Color tailColor) {
		super(true, true);
		this.name = name;
		this.x = x;
		this.y = y;
		this.velX = 0;
		this.velY = 0;
		this.angle = (float) 0;
		this.speed = speed;
		this.timer = rand.nextInt((int)(10/this.speed))+10/this.speed;
		this.width = width;
		this.headHeight = headHeight;
		this.tailHeight = tailHeight;
		this.turning = 0;
		this.headColor = headColor;
		this.tailColor = tailColor;
		fishList.add(this);
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
		if(this.selected) {
			g2d.setColor(Color.white);
			g2d.draw(this.getHead());
		}
		g2d.setColor(this.tailColor);
		g2d.fill(this.getTail());
		if(this.selected) {
			g2d.setColor(Color.white);
			g2d.draw(this.getTail());
		}
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
		velX = (float)(this.speed * Math.cos(angle+(0.5*Math.PI)));
		velY = (float)(this.speed * Math.sin(angle+(0.5*Math.PI)));
	}
	
	private void ai() {
			if(this.timer <= 0) {
				this.turning = Fish.rand.nextInt(3)-1;
				if(this.turning == 0) {
					this.timer = Fish.rand.nextInt((int)(10/this.speed))+10/this.speed;
				}else {
					this.timer = Fish.rand.nextInt((int)(30/this.speed))+30/this.speed;
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
	
	public static void checkClick() {		
		Rectangle mouse = new Rectangle(MouseHandler.mouseX, MouseHandler.mouseY, 1, 1);
		
		for(int i = 0; i < Fish.fishList.size(); i++) {
			
			Fish candidate = Fish.fishList.get(i);
			if(candidate.isRenderable()) {
				if(candidate.collidesWith(mouse)) {
					selectFish(candidate);
					return;
				}
			}
		}
		selectFish(null);
	}
	
	public static void selectFish(Fish fish) {
		if(fish == null) {
			if(Fish.selectedFish != null) {
				Fish.selectedFish.selected = false;
				Fish.selectedFish = null;
			}
		}else {
			if(Fish.selectedFish!=null) {
				Fish.selectedFish.selected = false;
			}
			Fish.selectedFish = fish;
			fish.selected = true;
		}
		Camera.setCenterOn(Fish.selectedFish);
	}
	
	public static void nextFish() {
		int index;
		if(Fish.selectedFish!=null) {
			index = Fish.fishList.indexOf(Fish.selectedFish)+1;
			if(index >= Fish.fishList.size()) {
				index = 0;
			}
		}else {
			index = 0;
		}
		selectFish(Fish.fishList.get(index));
	}

	public String getName() {
		return name;
	}
	
	public float getSpeed() {
		return speed;
	}
	
	public float getVelX() {
		return velX;
	}

	public float getVelY() {
		return velY;
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeadHeight() {
		return headHeight;
	}
	
	public int getTailHeight() {
		return tailHeight;
	}
	
	public Color getHeadColor() {
		return headColor;
	}
	
	public Color getTailColor() {
		return tailColor;
	}
}
