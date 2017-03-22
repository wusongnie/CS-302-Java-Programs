import java.util.ArrayList;

public class Hero {
	private Graphic graphic; // a reference variable referred to the Graphic
								// class that represent the graphic in the game
	private float speed; // the speed of the hero
	private int controlType; // the type of the keyboard controls
	private float direction; // the direction of the hero

	/**
	 * This constructor initializes a new Graphic object, the graphic's position
	 * and other fields. In the process of this initialization, all of the
	 * objects are instantiated and initialized to their beginning states.
	 * 
	 * @param x
	 *            is the x direction of the hero
	 * @param y
	 *            is the x direction of the hero
	 * @param controlType
	 *            is the type of the keyboard controls
	 */
	public Hero(float x, float y, int controlType) {
		graphic = new Graphic("HERO"); // make the graphic depict the hero
		graphic.setPosition(x, y); // set the position of the hero graphic
		this.controlType = controlType; // assign the parameter value to the
										// field controlType
		speed = 0.12f;

	}

	public void update(int time, Water[] water) {

		switch (controlType) {
		case 1:
			if (GameEngine.isKeyHeld("D")) {
				graphic.setX(graphic.getX() + speed * time);
				graphic.setDirection(0);
			}
			if (GameEngine.isKeyHeld("W")) {
				graphic.setY(graphic.getY() - speed * time);
				graphic.setDirection((float) (3 * Math.PI / 2));
			}
			if (GameEngine.isKeyHeld("A")) {
				graphic.setX(graphic.getX() - speed * time);
				graphic.setDirection((float) Math.PI);
			}
			if (GameEngine.isKeyHeld("S")) {
				graphic.setY(graphic.getY() + speed * time);
				graphic.setDirection((float) (Math.PI / 2));
			}
			break;

		case 2:
			if (GameEngine.isKeyHeld("D")) {
				graphic.setX(graphic.getX() + speed * time);

			}
			if (GameEngine.isKeyHeld("W")) {
				graphic.setY(graphic.getY() - speed * time);

			}
			if (GameEngine.isKeyHeld("A")) {
				graphic.setX(graphic.getX() - speed * time);

			}
			if (GameEngine.isKeyHeld("S")) {
				graphic.setY(graphic.getY() + speed * time);
			}

			graphic.setDirection(GameEngine.getMouseX(), GameEngine.getMouseY());

			break;

		case 3:

			graphic.setDirection(GameEngine.getMouseX(), GameEngine.getMouseY());

			double distance = Math
					.sqrt((GameEngine.getMouseX() - graphic.getX()) * (GameEngine.getMouseX() - graphic.getX())
							+ (GameEngine.getMouseY() - graphic.getY()) * (GameEngine.getMouseY() - graphic.getY()));
			if (distance > 20) {
				graphic.setX(graphic.getX() + graphic.getDirectionX() * speed * time);
				graphic.setY(graphic.getY() + graphic.getDirectionY() * speed * time);
			}
			break;
		}
		int i = 0;
		if (GameEngine.isKeyPressed("SPACE") || GameEngine.isKeyPressed("MOUSE")) {

			for (i = 0; i < 8; i++) {
				if (water[i] == null) {
					water[i] = new Water(graphic.getX(), graphic.getY(), graphic.getDirection());
					break;
				}
			}

		}

		graphic.draw(); // draws a Graphic to the screen at its current
						// location, and facing in its current direction

	}

	boolean handleFireballCollisions(ArrayList<Fireball> fireballs) {
		for (int i = 0; i < fireballs.size(); ++i) {
			if (graphic.isCollidingWith(fireballs.get(i).getGraphic())) {
				fireballs.get(i).destroy();
				return true;
			}
		}

		return false;
	}

	/**
	 * This method helps get the direction of hero.
	 * 
	 * @return the direction of the hero.
	 */
	public float getDirection() {
		return direction;
	}

	/**
	 * This method helps get the graphic of hero.
	 * 
	 * @return the graphic of the hero.
	 */
	public Graphic getGraphic() {
		return graphic;
	}
}
