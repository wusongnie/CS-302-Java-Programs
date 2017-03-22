
public class Water {
	private Graphic graphic; // a reference variable referred to the Graphic
								// class that represent the graphic in the game
	private float speed; // the speed of the water
	private float distanceTraveled; // the distance the water has traveled
	private float direction; // the direction of the water

	/**
	 * This constructor initializes a new Graphic object, the graphic's position
	 * and other fields. In the process of this initialization, all of the
	 * objects are instantiated and initialized to their beginning states.
	 * 
	 * @param x
	 *            is the x direction of the water
	 * @param y
	 *            is the x direction of the water
	 * @param direction
	 *            is the direction of the water
	 */
	public Water(float x, float y, float direction) {
		graphic = new Graphic("WATER"); // make the graphic depict the water
		graphic.setPosition(x, y); // set the position of the water graphic
		speed = 0.7f;
		this.direction = direction; // assign the parameter value to the field direction

	}

	/**
	 * This method updates the Water's current position, direction and distanceTraveled.
	 * 
	 * @param time
	 *            is the time in milliseconds that have elapsed since the last
	 *            time this method was called.
	 * @return the Water object when it travels more than 200 pixels.
	 *         Return null otherwise.
	 */
	public Water update(int time) {
		graphic.draw(); // draws a Graphic to the screen at its current
						// location, and facing in its current direction
		graphic.setDirection(direction); // set the direction of the graphic of
											// the water

		graphic.setX(graphic.getX() + ((speed * time) * (float) (Math.cos((double) direction))));
		graphic.setY(graphic.getY() + ((speed * time) * (float) (Math.sin((double) direction))));
		// set the position of the water

		distanceTraveled = distanceTraveled + time * speed; // update the distance the water has traveled

		if (distanceTraveled < 200) {
			return this;
			// return the water object when it has traveled less than or equal
			// to 200 pixels
		} else
			return null;
	}

	/**
	 * This method helps get the graphic of water.
	 * 
	 * @return the graphic of the water.
	 */
	public Graphic getGraphic() {
		return graphic;
	}
}
