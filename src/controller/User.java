package controller;

import java.util.Scanner;

public class User {

	private Scanner in;
	
	public User(model.DataBase db, view.UserInterface ui) {
		in = new Scanner(System.in);
		
		Boolean start = true;
		while(start){
			ui.welcomeMsg();
			start = false;
		}
		
		boolean exit = false;

		ui.mainOptions();
		do {

			System.out.print("Enter selection: ");
			int selection = ui.getInput(in);

			switch(selection) {
			case 1:
				db.addMember(ui.enterNewMember(in));
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
				ui.viewMemberInfo(in, db.getMemberList());
				ui.mainOptions();
				break;
			case 5:
				ui.compactList(db.getMemberList());
				ui.changeMember(in, db.getMemberList());
				ui.mainOptions();
				break;
			case 6:
				ui.compactList(db.getMemberList());
				ui.removeMember(in, db.getMemberList());
				ui.mainOptions();
				break;
			case 7:
				ui.compactList(db.getMemberList());
				ui.registerBoat(in, db.getMemberList());
				ui.mainOptions();
				break;
			case 8:
				ui.compactList(db.getMemberList());
				ui.deleteBoat(in, db.getMemberList());
				ui.mainOptions();
				break;
			case 9:
				ui.compactList(db.getMemberList());
				ui.updateBoat(in, db.getMemberList());
				ui.mainOptions();
				break;
			case 11:
				db.writeXML(db.getMemberList(), db.getSrc());
				exit = true;
				in.close();
				break;
			}	
		} while(!exit);
	}
}
