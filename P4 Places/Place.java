
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

/**
 * The Place class is responsible for managing name and address for a place. It
 * contains appropriate constructors, accessor (getter) and mutator (setter)
 * methods.
 *
 * @author Songnie Wu
 */
public class Place {
	private String name; // the name of the place
	private String address; // the address of the place

	/**
	 * This constructor assigns the values of the parameters(name, address) to
	 * the fields(name, address). In the process of this initialization, the
	 * Place class is able to hold the information of the place when called.
	 *
	 * @param name
	 *            the name of the place
	 * @param address
	 *            the address of the place
	 */
	public Place(String name, String address) {
		this.name = name; // assign the value of the parameter name to the field
							// name
		this.address = address; // assign the value of the parameter address to
								// the field address
	}

	/**
	 * This accessor helps get the name of the place.
	 * 
	 * @return the name of the place
	 */
	public String getName() {
		return name;
	}

	/**
	 * This accessor helps get the address of the place.
	 * 
	 * @return the address of the place
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * This method is to compare Places through data within each place and
	 * indicates whether they are equal or not.
	 *
	 * @param obj
	 *            the input Place object.
	 * @return true if the obj equals to this Place(an instance of the same
	 *         class and same instance's name), false otherwise.
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj.getClass().equals(this)) // compare the classes instances refer
											// to
			if (obj.getClass().getName().equals(name)) // compare the name of
														// the instances
				return true;

		return false;
	}
}
