
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


import java.util.ArrayList;

/**
 * The PlaceList class is responsible for managing a list of instances of the
 * place class. It contains appropriate instances methods to add a place, remove
 * a place, return the number of places, return whether there are places, return
 * a place and return whether a place is in the list.
 *
 * @author Songnie Wu
 */
public class PlaceList {

	private static ArrayList<Place> places = new ArrayList<Place>(); // to store
																		// Place
																		// objects.

	/**
	 * This constructor initializes the arrayList places to hold Place objects.
	 */
	public PlaceList() {
		places = new ArrayList<Place>();
	}

	/**
	 * This method is to add the place to the arrayList places.
	 *
	 * @param place
	 *            a place Object
	 */
	public static void add(Place place) {
		places.add(place);
	}

	/**
	 * This method is to remove an object of specified index from the arrayList
	 * places.
	 *
	 * @param index
	 *            the index of the removed Place object in places
	 */
	public static void remove(int index) {
		places.remove(index);
	}

	/**
	 * This method is to return the number of places in places arrayList.
	 *
	 * @return the size of places
	 */
	public static int size() {
		return places.size();
	}

	/**
	 * This method indicates whether there are places in the places arrayList.
	 *
	 * @return true if places is empty, false otherwise.
	 */
	public static boolean hasPlaces() {
		if (places.isEmpty()) // examine whether places arrayList is empty
			return false;
		return true;
	}

	/**
	 * This method is to return a Place object of index(indicated by the
	 * parameter variable) in places.
	 *
	 * @param index
	 *            the index of the Place object returned
	 * @return the place Object of an index specified in the parameter
	 */
	public static Place get(int index) {
		return places.get(index);
	}

	/**
	 * This method indicates whether a place is in the list.
	 *
	 * @param place
	 *            an input place
	 * @return true if the place is in the list, false otherwise.
	 */
	public static boolean contains(Place place) {
		for (int i = 0; i < places.size(); ++i) // go through all the places in
												// the list
			if (places.get(i).getName().equals(place.getName())
					&& (places.get(i).getAddress().equals(place.getAddress())))
				// examine whether the names and addresses of the place
				// parameter and the place in the list are the same
				return true;

		return false;
	}
}
