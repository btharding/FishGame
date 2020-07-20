
public class Camera {

	private static float xOffset =0;
	private static float yOffset =0;
	private static float speed = 2;
	
	
	public static void tick() {

		System.out.println(KeyHandler.arrowKeysPressed.get("R"));
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
	
	
	
}
