
package model;

import java.util.ArrayList;

/**
 * @author Jesper Malmberg
 * @since 2018-09-10
 * 
 * A class representing a Member. Contains a list for the Member's Boat objects.
 *
 */
public class Member {

	private String name;
	private int id;
	private String ssn;
	private ArrayList<Boat> boats;
		
	/**
	 * Creates a new instance of Member.
	 * 
	 * @param name <code>String</code>
	 * @param ssn<code>String</code>
	 * @param boats <code>ArrayList</code> of Boat objects.
	 * @param id <code>int</code>
	 */
	public Member(String name, String ssn, ArrayList<Boat> boats, int id) {
		this.name = name;
		this.id = id;
		this.ssn = ssn;
		this.boats = boats;
	}
	
	/**
	 * Creates a new instance of Member, with no previous Boats.
	 * 
	 * @param name <code>String</code>
	 * @param ssn<code>String</code>
	 * @param id <code>int</code>
	 */
	public Member(String name, String ssn, int id) {
		this.name = name;
		this.ssn = ssn;
		this. id = id;
		this.boats = new ArrayList<Boat>();
	}

	/**
	 * Returns the Member name.
	 * 
	 * @return <code>String</code>
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Return the ID of the Member.
	 * 
	 * @return <code>int</code>
	 */
	public int getId() {
		return this.id;
	}
	
	/**
	 * Returns the social security number of the Member.
	 * 
	 * @return <code>String</code>
	 */
	public String getSsn() {
		return this.ssn;
	}
	
	/**
	 * Returns a list of all the Boat objects.
	 * 
	 * @return<code>ArrayList</code> of Boat objects.
	 */
	public ArrayList<Boat> getBoats() {
		return this.boats;
	}
	
	/**
	 * Sets the Member's name.
	 * 
	 * @param str <code>String</code>
	 */
	public void setName(String str) {
		this.name = str;
	}
	
	/**
	 * Sets the Member's ID.
	 * 
	 * @param nmr <code>int</code>
	 */
	public void setId(int nmr) {
		this.id = nmr;
	}
	
	/**
	 * Sets the Member's social security number.
	 * 
	 * @param nmr <code>String</code>
	 */
	public void setSsn(String nmr) {
		this.ssn = nmr;
	}
	
	/**
	 * Adds a Boat object to the Member.
	 * 
	 * @param boat <code>Boat</code>
	 */
	public void setBoat(Boat boat) {
		this.boats.add(boat);
	}
	
	/**
	 * Update the specified Boat object.
	 * 
	 * @param index <code>int</code>
	 * @param type <code>String</code>
	 * @param length <code>String</code>
	 */
	public void updateBoat(int index, String type, String length) {
		boats.get(index).setType(type);
		boats.get(index).setLength(length);
	}
	
	/**
	 * Removes a boat object from the Member's list of Boats.
	 * 
	 * @param index <code>int</code>
	 */
	public void deleteBoat(int index) {
		this.boats.remove(index);
	}
	
}


