
/////////////////////////////////////////////////////////////////////////////
// Semester:         CS367 Spring 2016 
// PROJECT:          (project name)
// FILE:             (file name)
//
// Authors: (Be sure to check if programming teams are allowed)
// Author1: (name1,email1,netID1,lecture number1)
// Author2: (name2,email2,netID2,lecture number2)
//
// ---------------- OTHER ASSISTANCE CREDITS 
// Persons: Identify persons by name, relationship to you, and email. 
// Describe in detail the the ideas and help they provided. 
// 
// Online sources: avoid web searches to solve your problems, but if you do 
// search, be sure to include Web URLs and description of 
// of any information you find. 
//////////////////////////// 80 columns wide //////////////////////////////////v

import java.util.Scanner;

/**
 * This class contains the game of sticks. It will ask the user for a number of
 * sticks in total. Then choose a mode to play against a friend or an AI.
 * &lt;p&gt;Bugs: (a list of bugs and other problems)
 *
 * @author (Songnie WU)
 */

public class Sticks {

	/**
	 * This is the main method for the game of Sticks. In milestone 1 this
	 * contains the whole program for playing against a friend. In milestone 2
	 * this contains the welcome, name prompt, how many sticks question, menu,
	 * calls appropriate methods and the thank you message at the end. One
	 * method called in multiple places is promptUserForNumber. When the menu
	 * choice to play against a friend is chosen, then playAgainstFriend method
	 * is called. When the menu choice to play against a computer is chosen,
	 * then playAgainstComputer method is called. If the computer with AI option
	 * is chosen then trainAI is called before calling playAgainstComputer.
	 * Finally, call strategyTableToString to prepare a strategy table for
	 * printing.
	 * 
	 * @param args
	 *            (unused)
	 */
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		int userMenuChoice = 0; // the user's menu choice.
		String menuError = ""; // the user's input when it is not a number
		String userName = ""; // the user's name
		
		
		System.out.println("Welcome to the Game of Sticks!");
		System.out.println("==============================");
		System.out.println((100.0/3.0)*100);
		//display the welcome message

		
		System.out.print("What is your name? ");// the name prompt
		userName = input.nextLine().trim(); // eliminate the space on both sides of the string
		System.out.println("Hello " + userName + ".");// say hello to the user

		
		int numOfSticks = promptUserForNumber(input, "How many sticks are there on the table initially ("
				+ Config.MIN_STICKS + "-" + Config.MAX_STICKS + ")? ", Config.MIN_STICKS, Config.MAX_STICKS);
		
		/* 
		 *  lines above call the function to get the number of sticks
		 *  the user intended to play with
		 */
		
		System.out.println("");
		System.out.println("Would you like to:");
		System.out.println(" 1) Play against a friend");
		System.out.println(" 2) Play against computer (basic)");
		System.out.println(" 3) Play against computer with AI");
		//display the menu
		
		do {
			System.out.print("Which do you choose (1,2,3)? "); //prompt the user for a menu choice
			
			if (input.hasNextInt()) // check if the input is an integer
			{
				userMenuChoice = input.nextInt();//to get the menu choice
				if (1 <= userMenuChoice && userMenuChoice <= 3) { //check if the menu choice is valid
					switch (userMenuChoice) {
					case 1:
						System.out.println();
						System.out.print("What is your friend's name? ");
						String friendName = input.next().trim();//eliminate the space on both sides
						input.nextLine(); // to eat an extra line here
						System.out.println("Hello " + friendName + ".");// say hello to the user
						playAgainstFriend(input, numOfSticks, userName, friendName); 
						//have the user played with another person
						break;
					case 2:
						input.nextLine();// to eat an extra line here
						playAgainstComputer(input, numOfSticks, userName, null);
						break;
					case 3:
						input.nextLine();// to eat an extra line here
						int[][] strategyTable = new int[numOfSticks][Config.MAX_ACTION - Config.MIN_ACTION + 1];
						/*
						 * create a 2-D array strategyTable
						 * the row index indicates the number of sticks
						 * the value of column stores the possibility of sticks to take
						 */
						
						strategyTable = createAndInitializeStrategyTable(numOfSticks);
						//call the function to create and initialize the strategyTable
						playAgainstComputer(input, numOfSticks, userName, strategyTable);			
						break;
					default:
						break;
					}
					break; // to get out of the do while loop
				} else {
					System.out.println("Please enter a number between 1 and 3.");
					// show error message when input is greater than 3 or lower than 1
					input.nextLine();// to eat an extra line here
				}
			} else {
				menuError = input.nextLine();
				System.out.println("Error: expected a number between 1 and 3 but found: " + menuError);
				// show error message when input is not a number
			}
		} while (true); // keep the loop circulating until it is broken
		
		System.out.println("");
		System.out.println("=========================================");
		System.out.println("Thank you for playing the Game of Sticks!");
		// display the ending message
		
		input.close(); // close the scanner

	}

	/**
	 * This method encapsulates the code for prompting the user for a number and
	 * verifying the number is within the expected bounds.
	 * 
	 * @param input
	 *            The instance of the Scanner reading System.in.
	 * @param prompt
	 *            The prompt to the user requesting a number within a specific
	 *            range.
	 * @param min
	 *            The minimum acceptable number.
	 * @param max
	 *            The maximum acceptable number.
	 * @return The number entered by the user between and including min and max.
	 */
	static int promptUserForNumber(Scanner input, String prompt, int min, int max) {
		int userSticks = 0; // the initial sticks on the table that user set
		String sticksError = ""; // the user's input when the input is not a number

		do {
			System.out.print(prompt); // prompt the user for a input
			if (input.hasNextInt()) // check if the input is an integer
			{
				userSticks = input.nextInt(); // get the user input of sticks
				input.nextLine(); // to eat an extra line here
				if (min > userSticks || userSticks > max) {
					System.out.println("Please enter a number between " + Config.MIN_STICKS + " and "
		                       + Config.MAX_STICKS + ".");
			     // display the error message when the input number is not between 10 and 100
					
				} else 
				{
					break; // when get a valid input, break from the loop
				}
			} else 
			{
				sticksError = input.nextLine();
				System.out.println("Error: expected a number between " + Config.MIN_STICKS + " and "
				                   + Config.MAX_STICKS + " but found: " + sticksError);
				// display the error message when the input is not an integer
			}
		} while (true); // keep the loop circulating until it is broken

		return userSticks; // return the valid user input
	}

	/**
	 * This method has one person play the Game of Sticks against another
	 * person.
	 * 
	 * @param input
	 *            An instance of Scanner to read user answers.
	 * @param startSticks
	 *            The number of sticks to start the game with.
	 * @param player1Name
	 *            The name of one player.
	 * @param player2Name
	 *            The name of the other player.
	 * 
	 *            As a courtesy, player2 is considered the friend and gets to
	 *            pick up sticks first.
	 * 
	 */

	static void playAgainstFriend(Scanner input, int startSticks, String player1Name, String player2Name) {

		String sticksTaken = ""; // the sticks user and user's friend take every turn
		String currentPlayer = player2Name; // initialize currentPlayer as user's friend
		String winner = ""; // the winner of the game
		String loser = ""; // the loser of the game
		
		System.out.println();
		System.out.println("There are " + startSticks + " sticks on the board.");
		// display the sticks on the board in the beginning
		
		do {
			
			if (startSticks >= Config.MAX_ACTION)
				System.out.print(currentPlayer + ": How many sticks do you take (" + Config.MIN_ACTION + "-"
						         + Config.MAX_ACTION + ")? ");
			else
				System.out.print(currentPlayer + ": How many sticks do you take (" + Config.MIN_ACTION + "-"
						         + startSticks + ")? ");
			// lines above change the extent of the sticks can be chosen from
			sticksTaken = input.nextLine(); // read player's input of sticks taken

			
			if (Character.isDigit(sticksTaken.charAt(0))) // check if the input's first character is a digit
			{				
				if (Config.MIN_ACTION <= Character.getNumericValue(sticksTaken.charAt(0))
						&& Character.getNumericValue(sticksTaken.charAt(0)) <= Config.MAX_ACTION) {
					startSticks = startSticks - Character.getNumericValue(sticksTaken.charAt(0));
					// check if the input's first character is in the extent of the allowed actions

					if (startSticks == 1)
						System.out.println("There is 1 stick on the board.");
					// change the message to single form when there's only 1 stick
					
					else if (startSticks == 0) {
						if (currentPlayer.equals(player1Name)) {
							winner = player2Name;
							loser = player1Name;
						} else {
							winner = player1Name;
							loser = player2Name;
						}
						break;
						// assign loser to current player and winner to the other, then break the loop
					}
					
					else
						System.out.println("There are " + startSticks + " sticks on the board.");
					    // display current sticks on the board
										
					if (currentPlayer.equals(player1Name))
						currentPlayer = player2Name;
					else
						currentPlayer = player1Name;
					    // change currentPlayer to the next player
					
				} else 
				{
					System.out.println("Please enter a number between " + Config.MIN_STICKS + " and "
				                       + Config.MAX_STICKS + ".");
					continue;
				   /*
					*display the error message when the input's first number 
					*is not between Config.MIN_STICKS and Config.MAX_STICKS
					*/
				}
			} else 
			{
				System.out.println("Error: expected a number between " + Config.MIN_GAMES + " and "
			                       + Config.MAX_GAMES + " but found: " + sticksTaken);
				continue;
				// display the error message when the input is not a number or numbers
			}
			
		} while (true); // keep the loop circulating until it is broken
		
		System.out.println(winner + " wins. " + loser + " loses.");
		// display the winner and the loser
	}

	/**
	 * Make a choice about the number of sticks to pick up when given the number
	 * of sticks remaining.
	 * 
	 * Algorithm: If there are less than Config.MAX_ACTION sticks remaining,
	 * then pick up the minimum number of sticks (Config.MIN_ACTION). If
	 * Config.MAX_ACTION sticks remain, randomly choose a number between
	 * Config.MIN_ACTION and Config.MAX_ACTION. Use Config.RNG.nextInt(?) method
	 * to generate an appropriate random number.
	 * 
	 * @param sticksRemaining
	 *            The number of sticks remaining in the game.
	 * @return The number of sticks to pick up, or 0 if sticksRemaining is <= 0.
	 */
	static int basicChooseAction(int sticksRemaining) {

		int compChoice = 0; //to store the choice of computer

		if (sticksRemaining <= 0)
			compChoice = 0; // return 0 when remaining sticks is 0 or less
		
		else if (sticksRemaining >= Config.MIN_ACTION && sticksRemaining < Config.MAX_ACTION)
			compChoice = Config.MIN_ACTION; // return the the minimum number of sticks (Config.MIN_ACTION)
		
		else if (sticksRemaining >= Config.MAX_ACTION)
			compChoice = Config.RNG.nextInt(Config.MAX_ACTION) + 1;
		// generate a random number between Config.MIN_ACTION and Config.MAX_ACTION		 

		return compChoice;

	}

	/**
	 * This method has a person play against a computer. Call the
	 * promptUserForNumber method to obtain user input. Call the aiChooseAction
	 * method with the actionRanking row for the number of sticks remaining.
	 * 
	 * If the strategyTable is null, then this method calls the
	 * basicChooseAction method to make the decision about how many sticks to
	 * pick up. If the strategyTable parameter is not null, this method makes
	 * the decision about how many sticks to pick up by calling the
	 * aiChooseAction method.
	 * 
	 * @param input
	 *            An instance of Scanner to read user answers.
	 * @param startSticks
	 *            The number of sticks to start the game with.
	 * @param playerName
	 *            The name of one player.
	 * @param strategyTable
	 *            An array of action rankings. One action ranking for each stick
	 *            that the game begins with.
	 * 
	 */
	static void playAgainstComputer(Scanner input, int startSticks, String playerName, int[][] strategyTable) {
		int computerSticks = 0; // the number of sticks computer choose
		int numberOfGames = 0; // the number of games AI learns from
		String sticksTaken = ""; // the sticks user and user's friend take every turn
		char stategyTableChoice = 0; // store user's choice on whether to display the strategyTable
		String currentPlayer = playerName; // initialize currentPlayer as user									
		String winner = ""; // the winner of the game
		String loser = ""; // the loser of the game
		String userAiGames = ""; // to store user's games choice when input is not a number 
		int[] actionRanking = new int[Config.MAX_ACTION - Config.MIN_ACTION + 1];
		// to indicate the possibility for each action
		
		initializeActionRanking(actionRanking); //call a function to initialize actionRanking as 1

		System.out.println();
		
		if (strategyTable != null) {
			do {				
				System.out.print("How many games should the AI learn from (" + Config.MIN_GAMES + " to "
						+ Config.MAX_GAMES + ")? ");
				// display the number of games prompt

				if (input.hasNextInt()) // check if the input is an integer
				{
					numberOfGames = input.nextInt(); // get the user input of sticks
					input.nextLine(); // to eat an extra line here
					
					if (Config.MIN_GAMES <= numberOfGames && numberOfGames <= Config.MAX_GAMES) {
						break; // when get a valid input, break from the loop
					} else 
					{
						System.out.println("Please enter a number between " + Config.MIN_STICKS
								           + " and " + Config.MAX_STICKS + ".");
						/* 
						 * display the error message when the input number is not 
						 * between Config.MIN_GAMES and Config.MIN_GAMES
						 */
					}
				} else 
				{
					userAiGames = input.nextLine();
					System.out.println("Error: expected a number between " + Config.MIN_GAMES + " and "
							           + Config.MAX_GAMES + " but found: " + userAiGames);
					// display the error message when the input is not an integer
				}				
			} while (true); // keep the loop circulating until it is broken

			strategyTable = trainAi(startSticks, numberOfGames);
			// assign the result of trainAi to strategyTable
		}

		System.out.println("There are " + startSticks + " sticks on the board.");
		// display the sticks on the board in the beginning

		do {
			if (startSticks >= Config.MAX_ACTION)
				System.out.print(playerName + ": How many sticks do you take (" + Config.MIN_ACTION
				                 + "-" + Config.MAX_ACTION + ")? ");
			else
				System.out.print(playerName + ": How many sticks do you take (" + Config.MIN_ACTION
						         + "-" + startSticks + ")? ");
			// change the extent of the sticks can be chosen from
			
			sticksTaken = input.nextLine(); // read player's input of sticks taken
			
			if (Character.isDigit(sticksTaken.charAt(0)))// check if the input's first character is a digit
			{				
				if (Config.MIN_ACTION <= Character.getNumericValue(sticksTaken.charAt(0))
						&& Character.getNumericValue(sticksTaken.charAt(0)) <= Config.MAX_ACTION)
					// check if the input's first character is in the extent of the allowed actions
				{
					startSticks = startSticks - Character.getNumericValue(sticksTaken.charAt(0));
					//update current sticks on the table
					
					if (startSticks == 1)
						System.out.println("There is 1 stick on the board.");
					// change the message to single form when there's only 1 stick
					
					else if (startSticks == 0) {
						if (currentPlayer.equals(playerName)) {
							winner = "Computer";
							loser = playerName;
						} else {
							winner = playerName;
							loser = "Computer";
						}
						break;
						// assign loser to current player and winner to the other, then break the loop
					}
					
					else
						System.out.println("There are " + startSticks + " sticks on the board.");
					    // display current sticks on the board
					
					if (currentPlayer.equals(playerName))
						currentPlayer = "Computer";
					else
						currentPlayer = playerName;
					    // change currentPlayer to the next player
					
				} else 
				{
					if (startSticks >= Config.MAX_ACTION)
						System.out.println("Please enter a number between " + Config.MIN_ACTION + " and "
								+ Config.MAX_ACTION + ".");
					else
						System.out.println(
								"Please enter a number between " + Config.MIN_ACTION + " and " + startSticks + ".");
					// display the error message when the input's first number is not between 1 and 3
					continue;//do the loop again
				}
				
			} else 
			{
				System.out.println("Error: expected a number between " + Config.MIN_ACTION + " and " + Config.MAX_ACTION
						+ " but found: " + sticksTaken);
				// display the error message when the input is not a number or numbers
				continue;//do the loop again
			}
			
			
			if (strategyTable == null)
				computerSticks = basicChooseAction(startSticks);
			//use basicChooseAction to decide the sticks computer choose when there is no strategyTable
			else
				computerSticks = aiChooseAction(startSticks, strategyTable[startSticks - 1]);
			//use basicChooseAction to decide the sticks computer choose when there is no strategyTable
			
			startSticks = startSticks - computerSticks;
			//update current sticks on the table
			

			if (computerSticks == 1)
				System.out.println("Computer selects 1 stick.");
			else
				System.out.println("Computer selects " + computerSticks + " sticks.");
			// change the message to single form when there's only 1 stick
			
			
			if (startSticks == 1)
				System.out.println("There is 1 stick on the board.");
			/*
			 *  assign loser to current player and winner to the other, then
			 *  break the loop
			 */ 
			else if (startSticks == 0) {
				if (currentPlayer.equals(playerName)) {
					winner = "Computer";
					loser = playerName;
				} else {
					winner = playerName;
					loser = "Computer";
				}				
				break;
				//decide the winner and the loser when no sticks remain and break the loop
			}
			
			else
				System.out.println("There are " + startSticks + " sticks on the board.");
			// display current sticks on the board
			
			if (currentPlayer.equals(playerName))
				currentPlayer = "Computer";
			else
				currentPlayer = playerName;
			// change currentPlayer to the next player

		} while (true);
		
		System.out.println(winner + " wins. " + loser + " loses.");
		// display the winner and the loser
		
		System.out.print("Would you like to see the strategy table (Y/N)? ");
		// ask the user if he/she wants to see the strategy table		 
		stategyTableChoice = input.nextLine().toUpperCase().charAt(0); // to store the user's decision
		if (stategyTableChoice == 'Y')
			System.out.println(strategyTableToString(strategyTable));
		//display the strategy table when user answers 'y' or 'Y'

	}

	/**
	 * This method chooses the number of sticks to pick up based on the
	 * sticksRemaining and actionRanking parameters.
	 * 
	 * Algorithm: If there are less than Config.MAX_ACTION sticks remaining then
	 * the chooser must pick the minimum number of sticks (Config.MIN_ACTION).
	 * For Config.MAX_ACTION or more sticks remaining then pick based on the
	 * actionRanking parameter.
	 * 
	 * The actionRanking array has one element for each possible action. The 0
	 * index corresponds to Config.MIN_ACTION and the highest index corresponds
	 * to Config.MAX_ACTION. For example, if Config.MIN_ACTION is 1 and
	 * Config.MAX_ACTION is 3, an action can be to pick up 1, 2 or 3 sticks.
	 * actionRanking[0] corresponds to 1, actionRanking[1] corresponds to 2,
	 * etc. The higher the element for an action in comparison to other
	 * elements, the more likely the action should be chosen.
	 * 
	 * First calculate the total number of possibilities by summing all the
	 * element values. Then choose a particular action based on the relative
	 * frequency of the various rankings. For example, if Config.MIN_ACTION is 1
	 * and Config.MAX_ACTION is 3: If the action rankings are {9,90,1}, the
	 * total is 100. Since actionRanking[0] is 9, then an action of picking up 1
	 * should be chosen about 9/100 times. 2 should be chosen about 90/100 times
	 * and 1 should be chosen about 1/100 times. Use Config.RNG.nextInt(?)
	 * method to generate appropriate random numbers.
	 * 
	 * @param sticksRemaining
	 *            The number of sticks remaining to be picked up.
	 * @param actionRanking
	 *            The counts of each action to take. The 0 index corresponds to
	 *            Config.MIN_ACTION and the highest index corresponds to
	 *            Config.MAX_ACTION.
	 * @return The number of sticks to pick up. 0 is returned for the following
	 *         conditions: actionRanking is null, actionRanking has a length of
	 *         0, or sticksRemaining is <= 0.
	 * 
	 */
	static int aiChooseAction(int sticksRemaining, int[] actionRanking) {
		/*
		 * algorithm: Since the values stored in actionRanking represents the possibility
		 * of each action(corresponding to index+1), the lines below use array "frequency", 
		 * whose length is the sum of values in actionRnaking, to generate random numbers
		 * from valid extent. 
		 *
		 */
				
		int aiChoice = 0; // the final number of sticks AI choose to take
		int rankingSum = 0; // the sum of every element in actionRanking
		int tracker = 0; 
		
		if (sticksRemaining <= 0 || actionRanking == null || actionRanking.length == 0)
			return 0;
		/*
		 * return 0 when sticks remaining is 0 or less or when actionRanking is 
		 * null or when the length of actionRanking is 0
		 */		

		for (int i = 0; i < actionRanking.length; ++i) {
			rankingSum = rankingSum + actionRanking[i];
		}
		// calculate the sum of actionRanking

		int frequency[] = new int[rankingSum]; // to simulate a set of situations to pick from

		for (int j = 0; j < actionRanking.length; ++j) {
			if (j == 0) {
				for (int i = 0; i < tracker + actionRanking[0]; ++i)
					frequency[i] = Config.MIN_ACTION;
				    //set all of the situations to Config.MIN_ACTION
			} else {
				for (int k = tracker; k < tracker + actionRanking[j]; ++k)
					frequency[k] = Config.MIN_ACTION + j;
				    //set all of the situations to Config.MIN_ACTION+j
			}
			tracker = tracker + actionRanking[j];// update the tracker
		}

		if (sticksRemaining < Config.MAX_ACTION)
			aiChoice = Config.MIN_ACTION;
		//take the minimal sticks when there are less than Config.MAX_ACTION sticks remaining
		else if (sticksRemaining >= Config.MAX_ACTION)
			aiChoice = frequency[Config.RNG.nextInt(rankingSum)];
		//simulate a random generator to make all the situations explicit

		return aiChoice;

	}

	/**
	 * This method initializes each element of the array to 1. If actionRanking
	 * is null then method simply returns.
	 * 
	 * @param actionRanking
	 *            The counts of each action to take. Use the length of the
	 *            actionRanking array rather than rely on constants for the
	 *            function of this method.
	 */
	static void initializeActionRanking(int[] actionRanking) {
		if (actionRanking != null)//check if the action ranking is null 
		{
			for (int i = 0; i <= actionRanking.length - 1; ++i)
				actionRanking[i] = 1;
			//initialize every element of actionRanking as 1
		}

		return;
	}

	/**
	 * This method returns a string with the number of sticks left and the
	 * ranking for each action as follows.
	 * 
	 * An example: 10 3,4,11
	 * 
	 * The string begins with a number (number of sticks left), then is followed
	 * by 1 tab character, then a comma separated list of rankings, one for each
	 * action choice in the array. The string is terminated with a newline (\n)
	 * character.
	 * 
	 * @param sticksLeft
	 *            The number of sticks left.
	 * @param actionRanking
	 *            The counts of each action to take. Use the length of the
	 *            actionRanking array rather than rely on constants for the
	 *            function of this method.
	 * @return A string formatted as described.
	 */
	static String actionRankingToString(int sticksLeft, int[] actionRanking) {
		String rankingOutput = sticksLeft + "\t";
		// the string of the sticks remaining and the ranking for each action

		for (int i = 0; i < actionRanking.length; ++i) {
			rankingOutput = rankingOutput + actionRanking[i];
			//print the value of action ranking
			
			if (i == actionRanking.length - 1)
				break;// when actionRanking[i] is the last element, break to avoid adding a comma
			else
				rankingOutput = rankingOutput + ",";//add a comma
		}

		return rankingOutput + "\n";// switch to a new line at the last
	}

	/**
	 * This method updates the actionRanking based on the action. Since the game
	 * was lost, the actionRanking for the action is decremented by 1, but not
	 * allowing the value to go below 1.
	 * 
	 * @param actionRanking
	 *            The counts of each action to take. The 0 index corresponds to
	 *            Config.MIN_ACTION and the highest index corresponds to
	 *            Config.MAX_ACTION.
	 * @param action
	 *            A specific action between and including Config.MIN_ACTION and
	 *            Config.MAX_ACTION.
	 */
	static void updateActionRankingOnLoss(int[] actionRanking, int action) {
		if (actionRanking[action - 1] > 1)
			actionRanking[action - 1] = actionRanking[action - 1] - 1;
		// minus 1 to the value in actionRanking on loss if the value is greater than 1
	}

	/**
	 * This method updates the actionRanking based on the action. Since the game
	 * was won, the actionRanking for the action is incremented by 1.
	 * 
	 * @param actionRanking
	 *            The counts of each action to take. The 0 index corresponds to
	 *            Config.MIN_ACTION and the highest index corresponds to
	 *            Config.MAX_ACTION.
	 * @param action
	 *            A specific action between and including Config.MIN_ACTION and
	 *            Config.MAX_ACTION.
	 */
	static void updateActionRankingOnWin(int[] actionRanking, int action) {
		actionRanking[action - 1] = actionRanking[action - 1] + 1;
		// add 1 to the value in actionRanking on win

	}

	/**
	 * Allocates and initializes a 2 dimensional array. The number of rows
	 * corresponds to the number of startSticks. Each row is an actionRanking
	 * with an element for each possible action. The possible actions range from
	 * Config.MIN_ACTION to Config.MAX_ACTION. Each actionRanking is initialized
	 * with the initializeActionRanking method.
	 * 
	 * @param startSticks
	 *            The number of sticks the game is starting with.
	 * @return The two dimensional strategyTable, properly initialized.
	 */
	static int[][] createAndInitializeStrategyTable(int startSticks) {
		int[][] strategyTable = new int[startSticks][Config.MAX_ACTION - Config.MIN_ACTION + 1];
		//create a two-dimensional array strategyTable
		
		for (int i = 0; i < startSticks; ++i)
			initializeActionRanking(strategyTable[i]);
		//call initializeActionRanking to initialize strategy table row by row
		
		return strategyTable;
	}

	/**
	 * This formats the whole strategyTable as a string utilizing the
	 * actionRankingToString method. For example:
	 * 
	 * Strategy Table Sticks Rankings 10 3,4,11 9 6,2,5 8 7,3,1 etc.
	 * 
	 * The title "Strategy Table" should be proceeded by a \n.
	 * 
	 * @param strategyTable
	 *            An array of actionRankings.
	 * @return A string containing the properly formatted strategy table.
	 */
	static String strategyTableToString(int[][] strategyTable) {
		String strategyOutput = "\nStrategy Table\n" + "Sticks	Rankings\n";
		// assign the heading of strategyTable as a string display to strategyOutput
		
		for (int i = strategyTable.length - 1; i >= 0; --i)
			strategyOutput = strategyOutput + actionRankingToString(i + 1, strategyTable[i]);
		//call actionRankingToString to form the output of strategy table row by row
		
		return strategyOutput;
	}

	/**
	 * This updates the strategy table since a game was won.
	 * 
	 * The strategyTable has the set of actionRankings for each number of sticks
	 * left. The actionHistory array records the number of sticks the user took
	 * when a given number of sticks remained on the table. Remember that
	 * indexing starts at 0. For example, if actionHistory at index 6 is 2, then
	 * the user took 2 sticks when there were 7 sticks remaining on the table.
	 * For each action noted in the history, this calls the
	 * updateActionRankingOnWin method passing the corresponding action and
	 * actionRanking. After calling this method, the actionHistory is cleared
	 * (all values set to 0).
	 * 
	 * @param strategyTable
	 *            An array of actionRankings.
	 * 
	 * @param actionHistory
	 *            An array where the index indicates the sticks left and the
	 *            element is the action that was made.
	 */

	static void updateStrategyTableOnWin(int[][] strategyTable, int[] actionHistory) {
		for (int i = 0; i < actionHistory.length; ++i) {
			if (actionHistory[i] >= Config.MIN_ACTION) {
				updateActionRankingOnWin(strategyTable[i], actionHistory[i]);
				//call updateActionRankingOnWin to update the winner's strategy table				
				actionHistory[i] = 0;// clear the action history
			}
		}

	}

	/**
	 * This updates the strategy table for a loss.
	 * 
	 * The strategyTable has the set of actionRankings for each number of sticks
	 * left. The actionHistory array records the number of sticks the user took
	 * when a given number of sticks remained on the table. Remember that
	 * indexing starts at 0. For example, if actionHistory at index 6 is 2, then
	 * the user took 2 sticks when there were 7 sticks remaining on the table.
	 * For each action noted in the history, this calls the
	 * updateActionRankingOnLoss method passing the corresponding action and
	 * actionRanking. After calling this method, the actionHistory is cleared
	 * (all values set to 0).
	 * 
	 * @param strategyTable
	 *            An array of actionRankings.
	 * @param actionHistory
	 *            An array where the index indicates the sticks left and the
	 *            element is the action that was made.
	 */
	static void updateStrategyTableOnLoss(int[][] strategyTable, int[] actionHistory) {
		for (int i = 0; i < actionHistory.length; ++i) {
			if (actionHistory[i] >= Config.MIN_ACTION) {
				updateActionRankingOnLoss(strategyTable[i], actionHistory[i]);
				//call updateActionRankingOnLoss to update the loser's strategy table
				actionHistory[i] = 0;// clear the action history
			}
		}
	}

	/**
	 * This method simulates a game between two players using their
	 * corresponding strategyTables. Use the aiChooseAction method to choose an
	 * action for each player. Record each player's actions in their
	 * corresponding history array. This method doesn't print out any of the
	 * actions being taken. Player 1 should make the first move in the game.
	 * 
	 * @param startSticks
	 *            The number of sticks to start the game with.
	 * @param player1StrategyTable
	 *            An array of actionRankings.
	 * @param player1ActionHistory
	 *            An array for recording the actions that occur.
	 * @param player2StrategyTable
	 *            An array of actionRankings.
	 * @param player2ActionHistory
	 *            An array for recording the actions that occur.
	 * @return 1 or 2 indicating which player won the game.
	 */
	static int playAiVsAi(int startSticks, int[][] player1StrategyTable, int[] player1ActionHistory,
			int[][] player2StrategyTable, int[] player2ActionHistory) {

		int currentPlayer = 2;// assign 2 to current player to make sure player 1 goes first
		int winner = 0;// the winner's number
		int aiChoice = 0;// to store the AI's choice of sticks
		
		do {
			switch (currentPlayer) {
			case 1://player 1 is the current player
				
				aiChoice = aiChooseAction(startSticks, player1StrategyTable[startSticks - 1]);
				//call the method to get the number of sticks AI takes				
				player1ActionHistory[startSticks - 1] = aiChoice; // record the action to history
				startSticks = startSticks - aiChoice; // update sticks remaining
				
				if (startSticks == 0)
					break;// break when sticks remaining is 0 to avoid the switch
				currentPlayer = 2;// switch the player
				break;

			case 2://player 2 is the current player
				
				aiChoice = aiChooseAction(startSticks, player2StrategyTable[startSticks - 1]);
				//call the method to get the number of sticks AI takes				
				player2ActionHistory[startSticks - 1] = aiChoice;// record the action to history
				startSticks = startSticks - aiChoice; // update sticks remaining
				
				if (startSticks == 0)
					break;// break when sticks remaining is 0 to avoid the switch
				currentPlayer = 1; // switch the player
				break;
			}
		} while (startSticks > 0);// keep the loop circulating when there are more than 0 stick

		if (currentPlayer == 1)
			winner = 2;
		else
			winner = 1;
		// decide the winner
		
		return winner;

	}

	/**
	 * This method has the computer play against itself many times. Each time it
	 * plays it records the history of its actions and uses those actions to
	 * improve its strategy.
	 * 
	 * Algorithm: 1) Create a strategy table for each of 2 players with
	 * createAndInitializeStrategyTable. 2) Create an action history for each
	 * player. An action history is a single dimension array of int. Each index
	 * in action history corresponds to the number of sticks remaining where the
	 * 0 index is 1 stick remaining. 3) For each game,
	 * 
	 * 4) Call playAiVsAi with the return value indicating the winner.
	 * 
	 * 5) Call updateStrategyTableOnWin for the winner and
	 * 
	 * 6) Call updateStrategyTableOnLoss for the loser. 7) After the games are
	 * played then the strategyTable for whichever
	 * 
	 * strategy won the most games is returned. When both players win the same
	 * number of games, return the first player's strategy table.
	 * 
	 * @param startSticks
	 *            The number of sticks to start with.
	 * @param numberOfGamesToPlay
	 *            The number of games to play and learn from.
	 * @return A strategyTable that can be used to make action choices when
	 *         playing a person. Returns null if startSticks is less than
	 *         Config.MIN_STICKS or greater than Config.MAX_STICKS. Also returns
	 *         null if numberOfGamesToPlay is less than 1.
	 */
	static int[][] trainAi(int startSticks, int numberOfGamesToPlay) {
		int player1Win = 0;// counts the number of win of player1
		int player2Win = 0;// counts the number of win of player2
		int[][] strategyTable1 = createAndInitializeStrategyTable(startSticks);
	    // the strategy table of player 1
		int[][] strategyTable2 = createAndInitializeStrategyTable(startSticks);
		// the strategy table of player 2
		int[] actionHistory1 = new int[startSticks]; // the action history of player 1
		int[] actionHistory2 = new int[startSticks]; // the action history of player 2

		if (startSticks < Config.MIN_STICKS)
			return null;// if there is less than minimal amount of sticks, return null

		for (int i = 1; i <= numberOfGamesToPlay; ++i) {
			
			if (playAiVsAi(startSticks, strategyTable1, actionHistory1, strategyTable2, actionHistory2) == 1) {
				updateStrategyTableOnWin(strategyTable1, actionHistory1); 
				// update the strategy table of the winner
				updateStrategyTableOnLoss(strategyTable2, actionHistory2); 
				// update the strategy table of the loser
				++player1Win;// update the times of winning of player1
				
			} else {
				updateStrategyTableOnWin(strategyTable2, actionHistory2); 
				// update the strategy table of the winner
				updateStrategyTableOnLoss(strategyTable1, actionHistory1); 
				// update the strategy table of the loser
				++player2Win;// update the times of winning of player2
			}
		}

		if (player1Win > player2Win)
			return strategyTable1;
		else if (player1Win < player2Win)
			return strategyTable2;
		else
			return strategyTable1;
		/*
		 *  lines above returns the strategy table of the player who scores
		 *  more and return the strategy table of player1 when both players 
		 *  scores the same.
		 */
	}

}
