import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * The Level class is responsible for managing all of the objects in your game.
 * The GameEngine creates a new Level object for each level, and then calls that
 * Level object's update() method repeatedly until it returns either "ADVANCE"
 * (to go to the next level), or "QUIT" (to end the entire game). <br/>
 * <br/>
 * This class should contain and use at least the following private fields:
 * <tt><ul>
 * <li>private Random randGen;</li>
 * <li>private Hero hero;</li>
 * <li>private Water[] water;</li>
 * <li>private ArrayList&lt;Pant&gt; pants;</li>
 * <li>private ArrayList&lt;Fireball&gt; fireballs;</li>
 * <li>private ArrayList&lt;Fire&gt; fires;</li>
 * </ul></tt>
 */
public class Level {
	private Hero hero; // a reference variable referred to the Hero class
	private float speed; // the speed of the graphic
	private int controlType; // the type of the keyboard controls
	private Water[] water; // an array to store Water references
	private ArrayList<Pant> pants = new ArrayList<Pant>(); // to store pants
															// objects
	private ArrayList<Fireball> fireballs = new ArrayList<Fireball>(); // to store fireball objects
	private ArrayList<Fire> fires = new ArrayList<Fire>(); // to store fire objects
	private Random randGen; // the random numbers generator

	/**
	 * This constructor initializes a new Level object, so that the GameEngine
	 * can begin calling its update() method to advance the game's play. In the
	 * process of this initialization, all of the objects in the current level
	 * should be instantiated and initialized to their beginning states.
	 * 
	 * @param randGen
	 *            is the only Random number generator that should be used
	 *            throughout this level, by the Level itself and all of the
	 *            Objects within.
	 * @param level
	 *            is a string that either contains the word "RANDOM", or the
	 *            contents of a level file that should be loaded and played.
	 */
	public Level(Random randGen, String level) {
		this.randGen = randGen; // assign the parameter value to the field
								// randGen
		if (level == "RANDOM")
			createRandomLevel();
		// When level parameter contains the string “RANDOM”, initialize a new
		// random level
		else
			loadLevel(level);
		// load a crafted level indicated by level
	}

	/**
	 * The GameEngine calls this method repeatedly to update all of the objects
	 * within your game, and to enforce all of the rules of your game.
	 * 
	 * @param time
	 *            is the time in milliseconds that have elapsed since the last
	 *            time this method was called. This can be used to control the
	 *            speed that objects are moving within your game.
	 * @return When this method returns "QUIT" the game will end after a short 3
	 *         second pause and a message indicating that the player has lost.
	 *         When this method returns "ADVANCE", a short pause and win message
	 *         will be followed by the creation of a new level which replaces
	 *         this one. When this method returns anything else (including
	 *         "CONTINUE"), the GameEngine will simply continue to call this
	 *         update() method as usual.
	 */
	public String update(int time) {
		hero.update(time, water); // update hero's position and direction and
									// generation of waters

		for (int i = 0; i < 8; ++i)
			if (water[i] != null)
				water[i] = water[i].update(time);

		for (int i = 0; i < pants.size(); ++i) {

			pants.get(i).update(time);
			if (pants.get(i).shouldRemove() == true)
				pants.remove(i);
			Fire firetemp = pants.get(i).handleFireballCollisions(fireballs);
			if (firetemp != null) {
				fires.add(firetemp);
				pants.remove(i);
			}

		}

		for (int i = 0; i < fires.size(); ++i) {
			Fireball fireballTemp = fires.get(i).update(time);
			fires.get(i).handleWaterCollisions(water);
			if (fireballTemp != null) {
				fireballs.add(fireballTemp);
			}
			if (fires.get(i).shouldRemove() == true)
				fires.remove(i);
		}

		for (int i = 0; i < fireballs.size(); ++i) {

			fireballs.get(i).handleWaterCollisions(water);
			fireballs.get(i).update(time);

			if (fireballs.get(i).shouldRemove() == true)
				fireballs.remove(i);
		}

		if (hero.handleFireballCollisions(fireballs))
			return "QUIT";
		if (fires.size() == 0)
			return "ADVANCE";
		if (pants.size() == 0)
			return "QUIT";
		return "CONTINUE";
	}

	/**
	 * This method returns a string of text that will be displayed in the upper
	 * left hand corner of the game window. Ultimately this text should convey
	 * the number of unburned pants and fires remaining in the level. However,
	 * this may also be useful for temporarily displaying messages that help you
	 * to debug your game.
	 * 
	 * @return a string of text to be displayed in the upper-left hand corner of
	 *         the screen by the GameEngine.
	 */
	public String getHUDMessage() {

		return "Pants Left: " + pants.size() + "\n" + "Fires Left: " + fires.size();
		// return a display of the pants left and the fires left
	}

	/**
	 * This method creates a random level consisting of a single Hero centered
	 * in the middle of the screen, along with 6 randomly positioned Fires, and
	 * 20 randomly positioned Pants.
	 */
	public void createRandomLevel() {
		controlType = randGen.nextInt(2) + 1;
		hero = new Hero(GameEngine.getHeight() / 2, GameEngine.getWidth() / 2, 3);
		water = new Water[8];
		for (int i = 0; i < 8; ++i)
			water[i] = null;

		pants = new ArrayList<Pant>();
		fireballs = new ArrayList<Fireball>();
		fires = new ArrayList<Fire>();

		for (int i = 0; i < 20; i++)
			pants.add(new Pant(randGen.nextFloat() * GameEngine.getWidth(),
					randGen.nextFloat() * GameEngine.getHeight(), randGen));

		for (int i = 0; i < 6; i++)
			fires.add(new Fire(randGen.nextFloat() * GameEngine.getWidth(),
					randGen.nextFloat() * GameEngine.getHeight(), randGen));
	}

	/**
	 * This method initializes the current game according to the Object location
	 * descriptions within the level parameter.
	 * 
	 * @param level
	 *            is a string containing the contents of a custom level file
	 *            that is read in by the GameEngine. The contents of this file
	 *            are then passed to Level through its Constructor, and then
	 *            passed from there to here when a custom level is loaded. You
	 *            can see the text within these level files by dragging them
	 *            onto the code editing view in Eclipse, or by printing out the
	 *            contents of this level parameter. Try looking through a few of
	 *            the provided level files to see how they are formatted. The
	 *            first line is always the "ControlType: #" where # is either 1,
	 *            2, or 3. Subsequent lines describe an object TYPE, along with
	 *            an X and Y position, formatted as: "TYPE @ X, Y". This method
	 *            should instantiate and initialize a new object of the correct
	 *            type and at the correct position for each such line in the
	 *            level String.
	 */
	public void loadLevel(String level) {
		Scanner para = new Scanner(level);

		while (para.hasNextLine()) {
			String line = para.nextLine();
			Scanner lines = new Scanner(line);
			String firstWord = lines.next();
			if (firstWord.equals("ControlType:")) {
				// System.out.println("True");
				controlType = lines.nextInt();
			}
			if (firstWord.equals("FIRE")) {
				lines.next();
				lines.useDelimiter(",");
				float x = Float.parseFloat(lines.next());
				float y = Float.parseFloat(lines.next());
				Fire fire = new Fire(x, y, randGen);
				fires.add(fire);

			}
			if (firstWord.equals("HERO")) {
				lines.next();
				lines.useDelimiter(",");
				float x = Float.parseFloat(lines.next());
				float y = Float.parseFloat(lines.next());
				hero = new Hero(x, y, controlType);

			}
			if (firstWord.equals("PANT")) {
				lines.next();
				lines.useDelimiter(",");
				float x = Float.parseFloat(lines.next());
				float y = Float.parseFloat(lines.next());
				pants.add(new Pant(x, y, randGen));
			}
		}

		water = new Water[8];
		for (int i = 0; i < 8; ++i)
			water[i] = null;

	}

	/**
	 * This method creates and runs a new GameEngine with its first Level. Any
	 * command line arguments passed into this program are treated as a list of
	 * custom level filenames that should be played in a particular order.
	 * 
	 * @param args
	 *            is the sequence of custom level files to play through.
	 */
	public static void main(String[] args) {

		GameEngine.start(null, args); // creates a single Engine, and begins
										// playing through the specified
										// sequence of levels

	}
}
