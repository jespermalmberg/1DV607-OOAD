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

			view.Event selection = ui.getSelection();

			switch(selection) {
			case NEW_MEMBER:
				db.addMember(ui.enterNewMember());
				ui.mainOptions();
				break;
			case COMPACT_LIST:
				ui.compactList(db.getMemberList());
				ui.mainOptions();
				break;
			case VERBOSE_LIST:
				ui.verboseList(db.getMemberList());
				ui.mainOptions();
				break;
			case MEMBER_INFO:
				ui.compactList(db.getMemberList());
				ui.viewMemberInfo(db.getMemberList());
				ui.mainOptions();
				break;
			case CHANGE_MEMBER:
				ui.compactList(db.getMemberList());
				ui.changeMember(db.getMemberList());
				ui.mainOptions();
				break;
			case DELETE_MEMBER:
				ui.compactList(db.getMemberList());
				ui.removeMember(db.getMemberList());
				ui.mainOptions();
				break;
			case REGISTER_BOAT:
				ui.compactList(db.getMemberList());
				ui.registerBoat(db.getMemberList());
				ui.mainOptions();
				break;
			case DELETE_BOAT:
				ui.compactList(db.getMemberList());
				ui.deleteBoat(db.getMemberList());
				ui.mainOptions();
				break;
			case EDIT_BOAT:
				ui.compactList(db.getMemberList());
				ui.updateBoat(db.getMemberList());
				ui.mainOptions();
				break;
			case EXIT:
				db.writeXML(db.getMemberList(), db.getSrc());
				ui.closeScan();				
				exit = true;
				break;
			case NULL:
				break;
			}	

		} while(!exit);
	}
}
