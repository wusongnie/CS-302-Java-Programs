
public class Fireball {
	private Graphic graphic; // a reference variable referred to the Graphic
								// class that represent the graphic in the game
	private float speed; // the speed of the fireball
	private boolean isAlive; // show whether the fireball is alive
	private float direction; // the direction of the fireball

	/**
	 * This constructor initializes a new Graphic object, the graphic's position
	 * and other fields. In the process of this initialization, all of the
	 * objects are instantiated and initialized to their beginning states.
	 * 
	 * @param x
	 *            is the x direction of the fireball
	 * @param y
	 *            is the x direction of the fireball
	 * @param directionAngle
	 *            is the direction angle of the fireball
	 */
	public Fireball(float x, float y, float directionAngle) {
		graphic = new Graphic("FIREBALL"); // make the graphic depict the
											// fireball
		graphic.setPosition(x, y); // set the position of the fireball graphic
		speed = 0.2f;
		isAlive = true;
		direction = directionAngle; // assign the parameter value to the field
									// direction
	}

	/**
	 * This method updates the Fireball's current position, direction and condition.
	 * 
	 * @param time
	 *            is the time in milliseconds that have elapsed since the last
	 *            time this method was called.
	 */
	public void update(int time) {

		if (isAlive == true) {
			graphic.draw();
			// draws a Graphic to the screen at its current location, and facing
			// in its current direction when fireball is alive

			graphic.setDirection(direction); // set the direction of the graphic of the fireball

			if (!(graphic.getX() - GameEngine.getWidth() > 100) || (graphic.getY() - GameEngine.getHeight() > 100)) {
				graphic.setX(graphic.getX() + ((speed * time) * (float) (Math.cos((double) direction))));
				graphic.setY(graphic.getY() + ((speed * time) * (float) (Math.sin((double) direction))));
			} else
				isAlive = false;
		}

	}

	/**
	 * This method helps get the graphic of fireball.
	 * 
	 * @return the graphic of the fireball.
	 */
	public Graphic getGraphic() {
		return graphic;
	}

	/**
	 * This method handles Fireball-Water collisions.
	 */
	public void handleWaterCollisions(Water[] water) {
		for (int i = 0; i < 8; ++i)
			if (water[i] != null && graphic.isCollidingWith(water[i].getGraphic())) {
				water[i] = null;
				isAlive = false;
			}
		// go over the water array to see if there's a Water object colliding with a Fire. 
		// change the Water object to null and change isAlive to false.
	}

	/**
	 * This method changes isAlive to false when it's called, in other words,
	 * when the fireball is destroyed.
	 */
	public void destroy() {
		isAlive = false;
	}

	/**
	 * This method removes the fireball when it should be removed.
	 * 
	 * @return false when the fireball is alive.
	 *         true otherwise.
	 */
	public boolean shouldRemove() {
		if (isAlive != true)
			return true;
		else
			return false;
	}
}
