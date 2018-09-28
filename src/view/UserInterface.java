
package view;

import java.util.ArrayList;
import java.util.InputMismatchException;

import java.util.Scanner;

import model.Boat;
import model.DataBase;
import model.Member;

/**
 * @author Jesper Malmberg
 * @since 2018-09-23
 * 
 * Class representing a simple console user interface.
 *
 */
public class UserInterface {


	/**
	 * Creates a new instance of UserInferface.
	 * 
	 */
	public UserInterface() {
	}

	/**
	 * Prints the welcome message in the console.
	 */
	public void welcomeMsg() {
		System.out.println("<------------------------------------->");
		System.out.println("<                                     >");
		System.out.println("<      Welcome to the Jolly Pirate!   >");
		System.out.println("<                                     >");	
	}

	/**
	 * Prints the main options in the console.
	 */
	public void mainOptions () {
		System.out.println("<------------------------------------->");
		System.out.println("< Options:                            >");
		System.out.println("<   [1] Enter new member              >");
		System.out.println("<   [2] Compact List                  >");
		System.out.println("<   [3] Verbose List                  >");
		System.out.println("<   [4] View Member Info              >");
		System.out.println("<   [5] Change Member Info            >");
		System.out.println("<   [6] Delete Member                 >");
		System.out.println("<   [7] Register Boat                 >");
		System.out.println("<   [8] Delete Boat                   >");
		System.out.println("<   [9] Edit Boat                     >");
		System.out.println("<   [11] Exit                         >");
		System.out.println("<------------------------------------->");
	}

	/**
	 * Enters a new member into the temporary database.
	 * 
	 * @param scan <code>Scanner</code>
	 * @param db <code>DataBase</code>
	 */
	public void enterNewMember(Scanner scan, DataBase db) {
		// Clear Scanner
		scan.nextLine();
		String str = "Hello!";
		System.out.print("Enter member name: ");
		String name = scan.nextLine();
		System.out.print("Enter member social security number(YYMMDDNNNN): ");
		String ssn = scan.nextLine();

		db.addMember(name, ssn);

		System.out.println("New Member added to database!");
	}

	/**
	 * Displays a compact list over all members in the console.
	 * 
	 * @param list <code>ArrayList</code> of all the Member objects.
	 */
	public void compactList(ArrayList<Member> list) {
		System.out.println("Member information");
		System.out.println();
		if (list.size() != 0) {
			for (Member m : list) {		
				System.out.println("ID:    " + m.getId());
				System.out.println("Name:  " + m.getName());
				if (m.getBoats() != null) {
					System.out.println("Boats: " + m.getBoats().size());	
				}
				System.out.println();
			}	
		}
	}
	
	/**
	 * Displays a verbose list over all the members in the console.
	 * 
	 * @param list <code>ArrayList</code> of all the Member objects.
	 */
	public void verboseList(ArrayList<Member> list) {
		System.out.println("Member information");
		System.out.println();

		if (list.size() != 0) {
			for (Member m : list) {			
				System.out.println("ID:    " + m.getId());
				System.out.println("Name:  " + m.getName());
				System.out.println("SSN:   " + m.getSsn());

				if (m.getBoats() != null) {
					System.out.println("Boats: " + m.getBoats().size());
					ArrayList<Boat> bList = m.getBoats();
					for(Boat b : bList) {	
						System.out.println("|   +---Type:  " + b.getType());
						System.out.println("|   +---Size:  " + b.getLength());
					}	
				}
				System.out.println();
			}
		}
	}

	/**
	 * View an individual members information.
	 * 
	 * @param scan <code>Scanner</code>
	 * @param list <code>ArrayList</code> of all the Member objects.
	 */
	public void viewMemberInfo(Scanner scan, ArrayList<Member> list) {

		System.out.print("Enter the ID of the member to view: ");

		int selection = getInput(scan);

		ArrayList<Member> singleMember = new ArrayList<Member>();

		for (Member m : list) {
			if (m.getId() == selection) {
				singleMember.add(m);
			}
		} verboseList(singleMember);	
	}

	/**
	 * Remove an individual member from the temporary database.
	 * 
	 * @param scan <code>Scanner</code>
	 * @param list <code>ArrayList</code> of all the Member objects.
	 */
	public void removeMember(Scanner scan, ArrayList<Member> list) {

		System.out.print("Enter the ID of the member to remove: ");

		int selection = getInput(scan);

		Member toRemove = null;

		for (Member m : list) {
			if (m.getId() == selection) {
				toRemove = m;
			}
		}
		if (toRemove != null) {
			list.remove(toRemove);
			System.out.println("Member was succesfully removed!");	
		}
	}

	/**
	 * Update member info.
	 * 
	 * @param scan <code>Scanner</code>
	 * @param list <code>ArrayList</code> of all the Member objects.
	 */
	public void changeMember(Scanner scan, ArrayList<Member> list) {

		System.out.print("Enter the ID of the member to update: ");

		int selection = getInput(scan);	

		if (list.size() != 0) {
			for (Member m : list) {
				if (m.getId() == selection) {
					scan.nextLine();
					System.out.print("Enter new name: ");
					m.setName(scan.nextLine());
					System.out.print("Enter new social security number: ");
					m.setSsn(scan.nextLine());
					System.out.println("Member was succesfully updated!");
				}
			}
		}
	}

	/**
	 * Register a boat to the selected Member. Enter boat type, and length.
	 * 
	 * @param scan <code>Scanner</code>
	 * @param list <code>ArrayList</code> of all the Member objects.
	 */
	public void registerBoat(Scanner scan, ArrayList<Member> list) {

		System.out.print("Enter the ID of the member to register boat: ");

		int selection = getInput(scan);

		if (list.size() != 0) {
			for (Member m : list) {
				if (m.getId() == selection) {
					scan.nextLine();
					System.out.print("Enter boat type: ");
					String type = scan.nextLine();
					System.out.print("Enter boat length: ");
					String length = scan.nextLine();
					Boat boat = new Boat(type, length);
					m.setBoat(boat);
					System.out.println("Boat was succesfully registered!");	
				}
			}
		}
	}

	/**
	 * Remove a boat entry from the selected Member.
	 * 
	 * @param scan <code>Scanner</code>
	 * @param list <code>ArrayList</code> of all the Member objects.
	 */
	public void deleteBoat(Scanner scan, ArrayList<Member> list) {
		int toDelete;

		System.out.print("Enter the ID of the member to remove boat registration: ");

		int selection = getInput(scan);

		for (Member m : list) {
			if (m.getId() == selection) {
				scan.nextLine();
				int counter = 1;
				ArrayList<Boat> bList = m.getBoats();
				for(Boat b : bList) {	
					System.out.println(counter);
					System.out.println("|   +---Type:  " + b.getType());
					System.out.println("|   +---Size:  " + b.getLength());
					counter++;
				}

				if (bList.size() > 0) {
					System.out.println("Which boat would you like to remove?");
					do {
						toDelete = getInput(scan);
						if (toDelete > 0 && toDelete <= bList.size()) {
							m.deleteBoat(toDelete - 1);
							System.out.println("Boat was succesfully removed!");
							break;
						} else {
							System.out.println("Invalid selection, please try again...");
						}
					} while(true);

				} else {
					System.out.println("This member has no boats, choose another one...");
					deleteBoat(scan, list);
				}
			}
		}	
	}

	/**
	 * Update the selected Boat from the selected Member.
	 * 
	 * @param scan <code>Scanner</code>
	 * @param list <code>ArrayList</code> of all the Member objects.
	 */
	public void updateBoat(Scanner scan, ArrayList<Member> list) {

		int toUpdate = -1;

		System.out.print("Enter the ID of the member to update boat registration: ");

		int selection = getInput(scan);	

		for (Member m : list) {
			if (m.getId() == selection) {
				scan.nextLine();
				int counter = 1;
				ArrayList<Boat> bList = m.getBoats();
				for(Boat b : bList) {	
					System.out.println(counter);
					System.out.println("|   +---Type:  " + b.getType());
					System.out.println("|   +---Size:  " + b.getLength());
					counter++;
				}

				if (bList.size() > 0) {
					System.out.println("Which boat would you like to update?");
					do {
						toUpdate = getInput(scan);
						if (toUpdate > 0 && toUpdate <= bList.size()) {
							scan.nextLine();
							System.out.print("Enter boat type: ");
							String type = scan.nextLine();
							System.out.print("Enter boat length: ");
							String length = scan.nextLine();
							m.updateBoat(toUpdate - 1, type, length);;
							System.out.println("Boat was succesfully updated!");
							break;
						} else {
							System.out.println("Invalid selection, please try again...");
						}
					} while(true);

				} else {
					System.out.println("This member has no boats, choose another one...");
					updateBoat(scan, list);
				}
			}
		}
	}

	/**
	 * Method that returns the integer input from the scanner. If other than integer is entered it will catch the error and try again.
	 * 
	 * @param scan <code>Scanner</code>
	 * @return <code>int</code>
	 */
	public int getInput(Scanner scan) {

		int selection = -1;
		try {
			selection = scan.nextInt();	
		} catch(InputMismatchException e) {
			System.out.println(e.getMessage());
			System.out.println("Invalid selection, please try again...");
			//"Clear the scanner"
			scan.nextLine();
			selection = getInput(scan);
		}
		return selection;
	}
}
