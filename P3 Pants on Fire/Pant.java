import java.util.ArrayList;
import java.util.Random;

public class Pant {
	private Graphic graphic; // a reference variable referred to the Graphic
								// class that represent the graphic in the game
	private Random randGen; // the random numbers generator
	private boolean isAlive; // show whether the pant is alive

	/**
	 * This constructor initializes a new Graphic object, the graphic's position
	 * and other fields. In the process of this initialization, all of the
	 * objects are instantiated and initialized to their beginning states.
	 * 
	 * @param x
	 *            is the x direction of the pant
	 * @param y
	 *            is the x direction of the pant
	 * @param randGen
	 *            is the Random number generator for fireballCountdown
	 */
	public Pant(float x, float y, Random randGen) {
		graphic = new Graphic("PANT"); // make the graphic depict the pant
		graphic.setPosition(x, y); // set the position of the pant graphic
		isAlive = true;
		this.randGen = randGen; // assign the parameter value to the field
								// randGen

	}

	/**
	 * This method updates the Pant's condition.
	 * 
	 * @param time
	 *            is the time in milliseconds that have elapsed since the last
	 *            time this method was called.
	 */
	public void update(int time) {
		if (isAlive == true)
			graphic.draw();
		// draws a Graphic to the screen at its current location, and facing
		// in its current direction when fireball is alive

	}

	/**
	 * This method handles Fireball-Pant collisions.
	 * 
	 * @return a new fire object with the same direction as pant's and random direction
	 *         when a Fireball and a Pant collides .
	 *         returns null otherwise
	 */
	public Fire handleFireballCollisions(ArrayList<Fireball> fireballs) {
		if (isAlive == true)
			for (int i = 0; i < fireballs.size(); ++i)
				if (graphic.isCollidingWith(fireballs.get(i).getGraphic())) {
					fireballs.get(i).destroy();
					isAlive = false;
					return new Fire(graphic.getX(), graphic.getY(), randGen);
				}

		return null;
	}

	/**
	 * This method removes the pant when it should be removed.
	 * 
	 * @return false when the pant is alive.
	 *         true otherwise.
	 */
	public boolean shouldRemove() {
		if (isAlive != true)
			return true;
		else
			return false;
	}

	/**
	 * This method helps get the graphic of pant.
	 * 
	 * @return the graphic of the pant.
	 */
	public Graphic getGraphic() {
		return graphic;
	}
}
