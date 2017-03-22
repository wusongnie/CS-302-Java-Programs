import java.util.Random;
/**
 * */
public class Fire {
	private Graphic graphic; // a reference variable referred to the Graphic
								// class that represent the graphic in the game
	private Random randGen; // the random numbers generator
	private int fireballCountdown;
	private Fireball fireball; // a reference variable referred to the Fireball class
	private int heat; // the heat of the fire

	/**
	 * This constructor initializes a new Graphic object, the graphic's position
	 * and other fields. In the process of this initialization, all of the
	 * objects are instantiated and initialized to their beginning states.
	 * 
	 * @param x
	 *            is the x direction of the fire
	 * @param y
	 *            is the x direction of the fire
	 * @param randGen
	 *            is the Random number generator for fireballCountdown
	 */
	public Fire(float x, float y, Random randGen) {
		graphic = new Graphic("FIRE"); // make the graphic depict the fire
		graphic.setPosition(x, y); // set the position of the fire graphic
		this.randGen = randGen; // assign the parameter value to the field randGen
		fireballCountdown = randGen.nextInt(3001) + 3000; // initialize to a random value between 3000 and 6000 
		                                                  // (which corresponds to between 3 and 6 seconds) 
		heat = 4;
	}

	/**
	 * This method updates the Fire's current position, its condition and fireballCountdown
	 * 
	 * @param time
	 *            is the time in milliseconds that have elapsed since the last
	 *            time this method was called.
	 * @return the fireball object when the heat is bigger than 1 and fireballCountdown
	 *         is equals to or smaller than 0. Return null otherwise.
	 */
	public Fireball update(int time) {
		if (heat > 1) {
			graphic.draw();
			// draws a Graphic to the screen at its current
			// location, and facing in its current direction
			
			fireballCountdown = fireballCountdown - time; // update fireballCountdown
			
			if (fireballCountdown <= 0) {
				fireball = new Fireball(graphic.getX(), graphic.getY(), (float) (randGen.nextFloat() * Math.PI * 2));
				//create a new fireball object with the same position as the fire's and random direction
				fireballCountdown = randGen.nextInt(3001) + 3000; //reset to a new random value between 3000 and 6000
				return fireball;
			} 
			return null;
		} else
			return null;

	}

	/**
	 * This method handles Fire-Water collisions.
	 */
	public void handleWaterCollisions(Water[] water) {
		for (int i = 0; i < 8; ++i)
			if (water[i] != null && graphic.isCollidingWith(water[i].getGraphic())) {
				water[i] = null;
				--heat;
			}
		// go over the water array to see if there's a Water object colliding with a Fireball. 
		// change the Water object to null and minus one to heat.
	}

	/**
	 * This method helps get the graphic of fire.
	 * 
	 * @return the graphic of the fire.
	 */
	public Graphic getGraphic() {
		return graphic;
	}

	/**
	 * This method removes the fire when it should be removed.
	 * 
	 * @return false when heat is larger than 1.
	 *         true otherwise.
	 */
	public boolean shouldRemove() {
		if (heat > 1)
			return false;
		else
			return true;
	}
}
