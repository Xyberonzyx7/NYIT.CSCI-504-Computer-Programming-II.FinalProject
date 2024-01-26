package User;

import java.util.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.dom.DOMSource;
import org.w3c.dom.*;

public class UserInfoWriter {

	public void write(List<UserInfo> userList) {
		try {

			// create a new Document
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.newDocument();

			// root element
			Element rootElement = doc.createElement("data");
			doc.appendChild(rootElement);

			// data
			for(int i = 0; i < userList.size(); i++){
				Element userElement = doc.createElement("user");
				rootElement.appendChild(userElement);

				Element nameElement = doc.createElement("name");
				nameElement.appendChild(doc.createTextNode(userList.get(i).getUserName()));
				userElement.appendChild(nameElement);

				Element ageElement = doc.createElement("password");
				ageElement.appendChild(doc.createTextNode(userList.get(i).getPassword()));
				userElement.appendChild(ageElement);

				Element balanceElement = doc.createElement("balance");
				balanceElement.appendChild(doc.createTextNode(Long.toString(userList.get(i).getBalance())));
				userElement.appendChild(balanceElement);

				Element loanElement = doc.createElement("loan");
				loanElement.appendChild(doc.createTextNode(Long.toString(userList.get(i).getLoan())));
				userElement.appendChild(loanElement);
			}

			// Write the content into an XML file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");

			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult("../assets/user/userInfo.xml");

			transformer.transform(source, result);

		} catch (ParserConfigurationException | TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}
}


