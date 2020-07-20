import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable{
	
	private static final long serialVersionUID = -6112428091888191314L;
	
	public static final int WIDTH = 640;
	public static final int HEIGHT = 480;
	private static final String TITLE = "Fish Game";
	
	public static Window window;
	private static KeyHandler keyListener = new KeyHandler();
	private static MouseHandler mouseListener = new MouseHandler();
	
	public static Handler handler = new Handler();
	
	private Fish fish = new Fish(100,100,1,16,32,32,Color.red, Color.green);
	
	private Thread thread;
	private boolean running = false;

	public static void main(String[] args) {
		new Game();
	}

	public Game() {
		this.addKeyListener(keyListener);
		this.addMouseListener(mouseListener);
		this.addMouseMotionListener(mouseListener);
		window = new Window(WIDTH, HEIGHT, TITLE, this);
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
		
	}
	
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void run(){
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running) {
			long now = System.nanoTime();
			delta += (now-lastTime)/ns;
			lastTime = now;
			while(delta >= 1) {
				tick();
				delta--;
			}
			if(running) {
				render();
				frames ++;
			
				if(System.currentTimeMillis() - timer >1000) {
					timer += 1000;
					frames = 0;
				}
			}
		}
		stop();
	}
	
	private void tick() {
		handler.tick();
		Camera.tick();
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics2D g2d = (Graphics2D)bs.getDrawGraphics();
		
		g2d.setColor(Color.black);
		g2d.fillRect(0, 0, WIDTH, HEIGHT);
		
		g2d.translate(-Camera.getxOffset(), -Camera.getyOffset());
		
		handler.render(g2d);
		
		
		
		g2d.dispose();
		bs.show();
	}

}
