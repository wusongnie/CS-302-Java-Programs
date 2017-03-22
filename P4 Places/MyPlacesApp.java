
//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:            Program 4
// Files:            MyPlacesApp.java, Place.java, PlaceList.java
// Semester:         CS302 Fall 2016
//
// Author:           Songnie Wu
// Email:            swu267@wisc.edu
// CS Login:         songnie
// Lecturer's Name:  Gary Dahl
// Lab Section:      341
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:     Yanxi Zhou
// Partner Email:    zhou339@wisc.edu
// Partner CS Login: yanxi
// Lecturer's Name:  Gary Dahl
// Lab Section:      345
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//    _X_ Write-up states that Pair Programming is allowed for this assignment.
//    _X_ We have both read the CS302 Pair Programming policy.
//    _X_ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully
// acknowledge and credit those sources of help here.  Instructors and TAs do
// not need to be credited here, but tutors, friends, relatives, room mates
// strangers, etc do.
//
// Persons:          (identify each person and describe their help in detail)
// Online Sources:   (identify each URL and describe its assistance in detail)
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Scanner;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * The MyPlacesApp class is responsible for output the preface(welcome and
 * ending messages, menu) and managing files. It contains the main method for
 * the program and is a derived class of PlaceList.
 *
 * @author Songnie Wu
 */
public class MyPlacesApp extends PlaceList {
	static Scanner input = new Scanner(System.in); // a scanner of user's input

	public static void main(String[] args) throws IOException {
		int number = 0; // the number of a place
		String userChoice = ""; // user's menu choice
		String choiceLowerCase = ""; // user's menu choice of lower case
		String placeName = ""; // the name of the place
		String placeAddress = ""; // the address of the place

		do {
			System.out.println();
			System.out.println("My Places 2016");
			System.out.println("--------------------------");

			if (!hasPlaces()) {
				System.out.println("No places in memory.");
				System.out.println("--------------------------");
				System.out.print("A)dd R)ead Q)uit: ");
				// print out the menu when there's no place in memory

				userChoice = input.nextLine(); // read user's menu choice
				choiceLowerCase = userChoice.toLowerCase(); // store a lower
															// case version of
															// user's menu
															// choice
				if (!((choiceLowerCase.equals("a")) || choiceLowerCase.equals("r") || choiceLowerCase.equals("q"))) {
					System.out.println("Unrecognized choice: " + userChoice);
					// print out the error message if user's choice is not
					// valid.
				}
			} else {
				for (int i = 0; i < size(); ++i) {
					number = i + 1;
					System.out.println(number + ") " + get(i).getName());
					// print out existing places in the list
				}

				System.out.println("--------------------------");
				System.out.print("A)dd S)how D)elete R)ead W)rite Q)uit: ");
				// print out the menu when the list is not empty

				userChoice = input.nextLine(); // read user's menu choice
				choiceLowerCase = userChoice.toLowerCase(); // store a lower
															// case version of
															// user's menu
															// choice

				if (!((choiceLowerCase.equals("a")) || choiceLowerCase.equals("r") || choiceLowerCase.equals("q")
						|| choiceLowerCase.equals("s") || choiceLowerCase.equals("d") || choiceLowerCase.equals("w"))) {
					System.out.println("Unrecognized choice: " + userChoice);
					// print out the error message if user's choice is not
					// valid.
				}
			}

			if (choiceLowerCase.equals("q")) {
				System.out.println("Thank you for using My Places 2016!");
				break;
			}
			// quit the program when user inputs "q" or "Q"

			switch (choiceLowerCase) { // read user's choice

			case "a":
				add(placeName, placeAddress); // add a place to the list
				break; // break from the switch case

			case "r":
				showFiles(); // display existing files
				System.out.print("Enter filename: ");
				String inFileName = input.nextLine(); // store user's file name
														// input to read
				System.out.println("Reading file: " + inFileName);
				readFiles(inFileName); // read places in the file
				break; // break from the switch case

			case "s":
				show(); // show a place in the list
				break; // break from the switch case

			case "d":
				delete(); // delete a place from the list
				break; // break from the switch case

			case "w":
				showFiles(); // display existing files
				System.out.print("Enter filename: ");
				String outFileName = input.nextLine(); // store user's file name
														// input to write
				System.out.println("Writing file: " + outFileName);
				writeFiles(outFileName); // write the places into the file
				break; // break from the switch case
			}

			System.out.print("Press Enter to continue.");
			if (input.nextLine().equals("ENTER")) {
				continue;
			}
			// continue the program if the user presses ENTER

		} while (true);

	}

	/**
	 * This method is to add user's input place to the places list and print out
	 * the prompt messages.
	 *
	 * @param placeName
	 *            the name of the place to add
	 * @param placeAddress
	 *            the address of the place to add
	 */
	static void add(String placeName, String placeAddress) {
		System.out.print("Enter the name: ");
		placeName = input.nextLine();
		// store user's input of the places's name

		System.out.print("Enter the address: ");
		placeAddress = input.nextLine();
		// store user's input of the places's address

		Place place = new Place(placeName, placeAddress);
		// create a new Place instance of user's input name and address
		add(place); // add the new Place object to the places arrayList

		System.out.println("Adding: " + placeName);
		// print out a message to indicate the success of adding

	}

	/**
	 * This method is to show the place in the list of a number specified by the
	 * user.
	 */
	static void show() {
		int placeNum; // the number of the place to show

		System.out.print("Enter number of place to Show: ");
		placeNum = input.nextInt();
		// to store user's choice of the number of the place to show

		input.nextLine(); // to read the extra line

		if (placeNum > 0 && placeNum <= size()) {
			// examine whether the input number is in the valid range
			System.out.println(get(placeNum - 1).getName());
			System.out.println(get(placeNum - 1).getAddress());
			// print out the name and address of the place
		} else {
			System.out.println("Expected a number between 1 and " + size() + ".");
			// print out the error message
		}

	}

	/**
	 * This method is to show the list of places files ending with the ".mp"
	 * file extension in the file path. Managing files in MyPlaceApp avoids
	 *  confusion in the function of PlaceList class, which is used to handle 
	 *  Place object list.
	 */
	static void showFiles() {
		System.out.println("My Places Files: ");
		System.out.println();

		File folder = new File(".");// the path of files

		for (File file : folder.listFiles()) {
			// go through every file to look place files up
			if (file.getName().endsWith(".mp")) {
				System.out.println("" + file.getName());
				// print out a string with the name
				// of each file that
			}
		}
	}

	/**
	 * This method is to read places from a file into the memory.
	 * 
	 *
	 * @param fileName
	 *            the name of the file to read
	 */
	static void readFiles(String fileName) {
		String name; // the name of the place
		String address; // the address of the place
		String line = ""; // the line in the file

		try {
			FileInputStream fileByteStream = null; 
			fileByteStream = new FileInputStream(fileName);
			// create a file input stream and opens the file denoted by the
			// String literal for reading.
			Scanner inFS = new Scanner(fileByteStream);
			// create a new Scanner object using the fileByteStream object

			while (inFS.hasNext()) {
				// examine if inFS has another token available in its input
				line = inFS.nextLine();
				// store a line of the file in the line String
				Scanner scnr = new Scanner(line);
				// create a new Scanner object using the line String

				scnr.useDelimiter(";");
				// sets this scanner's delimiting pattern to ";".
				name = scnr.next(); // to store the name of the place
				address = scnr.next(); // to store the address of the place

				Place place = new Place(name, address);
				// create a new Place object with the name and address read in
				// the file

				if (PlaceList.contains(place))
					// examine whether the place is already in the list
					System.out.println(name + " already in list.");
				else
					PlaceList.add(place); // add the place to the list
			}

			fileByteStream.close(); // close the files when no longer in use

		} catch (FileNotFoundException except) {
			System.out.println("Unable to read from file: " + fileName);
			// catch and print out the error message when an attempt to open the file
			// denoted by a specified pathname has failed.

		} catch (IOException except) {
			System.out.println("Unable to read from file: " + fileName);
			// catch and print out the error message when an I/O exception of some sort
			// has occurred.
		}

	}

	/**
	 * This method writes the list of places in memory to a specified file in
	 * the specified format.
	 *
	 * @param fileName
	 *            the name of the file to write
	 */
	static void writeFiles(String fileName) {

		try {
			 FileOutputStream fileByteStream = null; // File output stream
		     PrintWriter outFS = null;               // Output stream
			fileByteStream = new FileOutputStream(fileName);
			// create a file output stream and opens the file denoted by the
			// String literal for writing.
			outFS = new PrintWriter(fileByteStream);
			// create a new PrintWriter object using the fileByteStream object

			for (int i = 0; i < PlaceList.size(); i++) {
				outFS.print(PlaceList.get(i).getName() + "; ");
				outFS.println(PlaceList.get(i).getAddress() + "");
				// print out the names and addresses of places in the file
			}

			fileByteStream.close(); // close the files when no longer in use

		} catch (FileNotFoundException except) {
			System.out.println("Unable to write to file: " + fileName);
			// print out the error message when an attempt to open the file
			// denoted by a specified pathname has failed.

		} catch (IOException except) {
			System.out.println("Unable to write to file: " + fileName);
			// print out the error message when an I/O exception of some sort
			// has occurred.
		}

	}

	/**
	 * This method is to delete the place in the list of a number specified by
	 * the user.
	 */
	static void delete() {
		int placeNum; // the number of the place to delete

		System.out.print("Enter number of place to Delete: ");
		placeNum = input.nextInt();
		// to store user's choice of the number of the place to delete

		input.nextLine(); // to read the extra line

		if (placeNum > 0 && placeNum <= size()) {
			// examine whether the input number is in the valid range
			System.out.println("Deleting: " + get(placeNum - 1).getName());
			// print out a message to indicate the success of deleting
			remove(placeNum - 1);
			// remove the place of an index specified by the user from the list

		} else {
			System.out.println("Expected a number between 1 and " + size() + ".");
			// print out the error message
		}
	}
}
