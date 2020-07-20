
public class Camera {

	private static float xOffset =0;
	private static float yOffset =0;
	private static float speed = 3;
	private static float scrollSpeed = 50;
	private static Fish centerOn = null;
	private static enum States {
		FREE,
		FOLLOWING,
		SCROLLING
	};
	private static States state = States.FREE;
	private static float[] target = new float[2];
	
	
	public static void tick() {
		if(Camera.state == States.FOLLOWING) {
			Camera.xOffset += centerOn.getVelX();
			Camera.yOffset += centerOn.getVelY();
		}else if(Camera.state == States.SCROLLING) {
			Camera.target[0] = centerOn.getX() - Game.WIDTH/2 + centerOn.getWidth()/2;
			Camera.target[1] = centerOn.getY() - Game.HEIGHT/2 ;
			scrollTo();
		}else {
			if(KeyHandler.arrowKeysPressed.get("R")) {
				Camera.xOffset += Camera.speed;
			}else if(KeyHandler.arrowKeysPressed.get("L")) {
				Camera.xOffset -= Camera.speed;
			}
			
			if(KeyHandler.arrowKeysPressed.get("D")) {
				Camera.yOffset += Camera.speed;
			}else if(KeyHandler.arrowKeysPressed.get("U")) {
				Camera.yOffset -= Camera.speed;
			}
		}
	}

	public static float getxOffset() {
		return xOffset;
	}

	public static void setxOffset(float xOffset) {
		Camera.xOffset = xOffset;
	}

	public static float getyOffset() {
		return yOffset;
	}

	public static void setyOffset(float yOffset) {
		Camera.yOffset = yOffset;
	}

	public static float getSpeed() {
		return speed;
	}

	public static void setSpeed(float speed) {
		Camera.speed = speed;
	}
	
	public static Fish getCenterOn() {
		return centerOn;
	}
	
	public static void setCenterOn(Fish centerOn) {
		if(centerOn!=null) {
			Camera.state = States.SCROLLING;
		}else {
			Camera.state = States.FREE;
		}
		
		Camera.centerOn = centerOn;
	}
	
	public static void scrollTo() {
		if(Camera.xOffset > Camera.target[0]) {
			if(Camera.xOffset - Camera.scrollSpeed < Camera.target[0]) {
				Camera.xOffset = Camera.target[0];
			}else {
				Camera.xOffset -= Camera.scrollSpeed;
			}
		}else if(Camera.xOffset < Camera.target[0]) {
			if(Camera.xOffset + Camera.scrollSpeed > Camera.target[0]) {
				Camera.xOffset = Camera.target[0];
			}else {
				Camera.xOffset += Camera.scrollSpeed;
			}
		}
		if(Camera.yOffset > Camera.target[1]) {
			if(Camera.yOffset - Camera.scrollSpeed < Camera.target[1]) {
				Camera.yOffset = Camera.target[1];
			}else {
				Camera.yOffset -= Camera.scrollSpeed;
			}
		}else if(Camera.yOffset < Camera.target[1]) {
			if(Camera.yOffset + Camera.scrollSpeed > Camera.target[1]) {
				Camera.yOffset = Camera.target[1];
			}else {
				Camera.yOffset += Camera.scrollSpeed;
			}
		}
		if(Camera.xOffset == target[0] && Camera.yOffset == target[1]) {
			Camera.state = States.FOLLOWING;
		}
	}
	
	
}
