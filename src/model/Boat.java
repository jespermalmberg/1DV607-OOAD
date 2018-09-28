
package model;

/**
 * @author Jesper Malmberg
 * @since 2018-09-10
 * 
 * Class representing a Boat. Stores information about type and length.
 *
 */
public class Boat {
	
	private String type;
	private String length;
	
	/**
	 * Creates a new instance of Boat.
	 * 
	 * @param type <code>String</code>
	 * @param length <code>String</code>
	 */
	public Boat(String type, String length) {
		this.type = type;
		this.length = length;
	}
	
	/**
	 * Returns the type of Boat.
	 * 
	 * @return <code>String</code>
	 */
	public String getType() {
		return this.type;
	}
	
	/**
	 * Returns the length of the Boat.
	 * 
	 * @return <code>String</code>
	 */
	public String getLength() {
		return this.length;
	}
	
	/**
	 * Set the type of Boat.
	 * 
	 * @param type <code>String</code>
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * Set the length of the Boat.
	 * 
	 * @param length <code>String</code>
	 */
	public void setLength(String length) {
		this.length = length;
	}

}
