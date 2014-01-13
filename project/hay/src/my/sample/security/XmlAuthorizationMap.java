package my.sample.security;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import my.sample.entity.Role;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XmlAuthorizationMap implements AuthorizationMap {
	private static final String PATH = "path";
	private static final String NAME = "name";
	private static final String ACTION = "action";
	private static final String OTHER_ACTIONS = "other-actions";

	private Map<String, Set<Role>> authorizationMap = new HashMap<String, Set<Role>>();
	private Set<Role> otherActions = new HashSet<Role>();

	public boolean isAuthorize(String path, Role role) {
		Set<Role> theRoleSet = authorizationMap.get(path);

		if (theRoleSet == null) {
			theRoleSet = otherActions;
		}
		return theRoleSet.contains(role == null ? Role.GUEST : role);
	}

	public XmlAuthorizationMap(File xml) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory theFactory = DocumentBuilderFactory.newInstance();
		theFactory.setValidating(true);
		theFactory.setIgnoringElementContentWhitespace(true);
		DocumentBuilder theBuilder = theFactory.newDocumentBuilder();

		Document theDocument = theBuilder.parse(xml);

		Element theRoot = theDocument.getDocumentElement();

		NodeList actionList = theRoot.getChildNodes();
		for (int i = 0; i < actionList.getLength(); i++) {
			if (actionList.item(i).getNodeName().equals(ACTION)) {
				parseAction(actionList.item(i));
			} else if (actionList.item(i).getNodeName().equals(OTHER_ACTIONS)) {
				parseOtherActions(actionList.item(i));
			}
		}
	}

	private void parseAction(Node action) {
		String thePath = action.getAttributes().getNamedItem(PATH).getNodeValue();
		Set<Role> theRoleSet = authorizationMap.get(thePath);
		if (theRoleSet == null) {
			theRoleSet = new HashSet<Role>();
			authorizationMap.put(thePath, theRoleSet);
		}

		NodeList roleList = action.getChildNodes();
		for (int i = 0; i < roleList.getLength(); i++) {
			Role theRole = Role.lookUp(roleList.item(i).getAttributes().getNamedItem(NAME).getNodeValue());
			if(theRole!=null){
				theRoleSet.add(theRole);
			}
		}
	}

	private void parseOtherActions(Node other) {
		NodeList roleList = other.getChildNodes();
		for (int i = 0; i < roleList.getLength(); i++) {
			Role theRole = Role.lookUp(roleList.item(i).getAttributes().getNamedItem(NAME).getNodeValue());
			if(theRole!=null){
				otherActions.add(theRole);
			}
		}
	}

	public String toString() {
		return otherActions + "\n" + authorizationMap;
	}
}
