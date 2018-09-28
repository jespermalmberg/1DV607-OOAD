/**
 * The starting point of the Jolly Pirate software.
 */
package program;

import controller.User;
import model.DataBase;
import view.UserInterface;

/**
 * @author Jesper Malmberg
 * @since 2018-09-23
 *
 */
public class Program {

	/**
	 * The starting point of the application
	 * 
	 * @param args <code>String[]</code>
	 */
	public static void main(String[] args) {
		DataBase db = new DataBase("db.xml");
		UserInterface ui = new UserInterface();
		User user = new User(db, ui);
	}
}
