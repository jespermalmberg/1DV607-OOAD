
package model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 * @author Jesper Malmberg
 * @since 2018-09-15
 * 
 * Class representing a simple database. All the Members are stored temporarily in an ArrayList and then written to a .xml file.
 */
public class DataBase {

	private ArrayList<Member> memberList;
	private String src;


	/**
	 * Creates a new instance of a DataBase.
	 * 
	 * @constructor
	 * @param src <code>String</code>
	 */
	public DataBase(String src) {
		this.src = src;
		createMemberList(this.src);
		
	}

	/**
	 * Returns a list of all the Member objects.
	 * 
	 * @return <code>ArrayList</code> Member
	 */
	public ArrayList<Member> getMemberList() {
		return this.memberList;
	}
	
	/**
	 * Returns the source of the xml file where the database will be permanently written.
	 * 
	 * @return <code>String</code>
	 */
	public String getSrc() {
		return this.src;
	}
	
	/**
	 * Sets the new source for the xml database.
	 * 
	 * @param src <code>String</code>
	 */
	public void setSrc(String src) {
		this.src = src;
	}

	/**
	 * Adds a new member to the memberList with a unique new id.
	 * 
	 * @param m <code>Member</code>
	 */
	public void addMember(Member m) {

		m.setId(assignId(memberList));
		memberList.add(m);
	}

	/**
	 * Writes new XML of the ArrayList of Member objects to the relevant .xml file.
	 * 
	 * @param member_list <code>ArrayList</code> of Member objects.
	 * @param src <code>String</code> the source of the xml file.
	 */
	public void writeXML(ArrayList<Member> member_list, String src) {
		try {

			Element data_base = new Element("data_base");
			Document doc = new Document(data_base);

			// If database is not empty
			if (member_list.size() != 0) {
				for (Member m : member_list) {
					doc.getRootElement().addContent(newElement(m));
				}				
			}

			//new XMLOutputter().output(doc, System.out);
			XMLOutputter xmlOutput = new XMLOutputter();

			// display nice nice
			xmlOutput.setFormat(Format.getPrettyFormat());
			xmlOutput.output(doc, new FileWriter(src));

			System.out.println("File Saved!");
		} catch (IOException io) {
			System.out.println(io.getMessage());
		}
	}

	/**
	 * Creates an ArrayList of Members from the .xml file.
	 * 
	 * @return <code>ArrayList</code>
	 */
	private void createMemberList(String src) {
		Document doc;
		Element rootNode;
		memberList = new ArrayList<Member>();

		try {
			SAXBuilder builder = new SAXBuilder();

			File xmlFile = new File(src);
			//System.out.println(getClass().getClassLoader().getResource(src).getFile());
			doc = (Document)builder.build(xmlFile);
			rootNode = doc.getRootElement();


			List<Element> memberNodes = rootNode.getChildren();

			for(Element e : memberNodes) {
				int id = Integer.parseInt(e.getAttributeValue("id"));
				String name = e.getChild("name").getText();
				String ssn = e.getChild("ssn").getText();

				ArrayList<Boat> boats = new ArrayList<Boat>();
				List<Element> elementBoats = e.getChildren("boat");
				for (Element b : elementBoats) {
					Boat newBoat = new Boat(b.getChildText("type"), b.getChildText("length"));
					boats.add(newBoat);
				}

				Member m = new Member(name, ssn, boats, id);
				memberList.add(m);
			}

		} catch (IOException io) {	
			io.printStackTrace();
		} catch (JDOMException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Checks all the current id's of the memberList array and returns the next available one.
	 * Ensures previously deleted Id's are recycled.
	 * 
	 * @return <code>int</code>
	 */
	private int assignId(ArrayList<Member> list) {

		ArrayList<Member> members = list;
		// If memberList is empty return 1.
		if (members.size() == 0) {
			return 1;
		}

		ArrayList<Integer> allId = new ArrayList<Integer>();
		int id = 0;
		for (Member m : members) {
			allId.add(m.getId());
		}
		Collections.sort(allId);

		if (allId.get(0) != 1) {
			return 1;
		}

		for (int i = 1; i < allId.size(); i++) {
			if (allId.get(i) - allId.get(i - 1) > 1) {
				id = allId.get(i - 1) + 1;
				return id;
			} else {
				id = allId.get(allId.size() - 1) + 1;
			}
		}
		return id;
	}

	/**
	 * Returns a new Element to be stored in the xml file.
	 * 
	 * @param mbr <code>Member</code> the Member Object to be converted into an Element.
	 * @return <code>Element</code>
	 */
	private Element newElement(Member mbr) {
		Member m = mbr;
		ArrayList<Boat> boats = mbr.getBoats();

		Element member = new Element("member");
		member.setAttribute(new Attribute("id", Integer.toString(m.getId())));
		member.addContent(new Element("name").setText(m.getName()));
		member.addContent(new Element("ssn").setText(m.getSsn()));
		if (mbr.getBoats() != null) {
			for (Boat b : boats) {
				Element boat = new Element("boat");
				boat.addContent(new Element("type").setText(b.getType()));
				boat.addContent(new Element("length").setText(b.getLength()));
				member.addContent(boat);
			}
		}
		return member;
	}
}
