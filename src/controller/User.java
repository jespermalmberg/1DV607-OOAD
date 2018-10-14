package controller;

/**
 * 
 * @author Jesper Malmberg
 * @since 2018-10-14
 *
 * A class representing the controller.
 * 
 */
public class User {

	/**
	 * Creates a new instance of User, the controller.
	 * 
	 * @param db <code>DataBase</code>
	 * @param ui <code>UserInterface</code>
	 */
	public User(model.DataBase db, view.UserInterface ui) {

		ui.welcomeMsg();

		boolean exit = false;

		ui.mainOptions();

		do {

			int selection = ui.getInput();

			switch(selection) {
			case 1:
				db.addMember(ui.enterNewMember());
				ui.mainOptions();
				break;
			case 2:
				ui.compactList(db.getMemberList());
				ui.mainOptions();
				break;
			case 3:
				ui.verboseList(db.getMemberList());
				ui.mainOptions();
				break;
			case 4:
				ui.compactList(db.getMemberList());
				ui.viewMemberInfo(db.getMemberList());
				ui.mainOptions();
				break;
			case 5:
				ui.compactList(db.getMemberList());
				ui.changeMember(db.getMemberList());
				ui.mainOptions();
				break;
			case 6:
				ui.compactList(db.getMemberList());
				ui.removeMember(db.getMemberList());
				ui.mainOptions();
				break;
			case 7:
				ui.compactList(db.getMemberList());
				ui.registerBoat(db.getMemberList());
				ui.mainOptions();
				break;
			case 8:
				ui.compactList(db.getMemberList());
				ui.deleteBoat(db.getMemberList());
				ui.mainOptions();
				break;
			case 9:
				ui.compactList(db.getMemberList());
				ui.updateBoat(db.getMemberList());
				ui.mainOptions();
				break;
			case 11:
				db.writeXML(db.getMemberList(), db.getSrc());
				ui.closeScan();				
				exit = true;
				break;
			}	

		} while(!exit);
	}
}
