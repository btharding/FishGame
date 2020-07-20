import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable{
	
	private static final long serialVersionUID = -6112428091888191314L;
	
	public static final int WIDTH = 640;
	public static final int HEIGHT = 480;
	private static final String TITLE = "Fish Game";
	
	public static Window window;
	private static KeyHandler keyListener = new KeyHandler();
	private static MouseHandler mouseListener = new MouseHandler();
	
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
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		g.dispose();
		bs.show();
	}

}
