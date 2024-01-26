package User;

import javax.xml.parsers.*;
import org.w3c.dom.*;
import java.io.File;
import java.util.*;

// a class that is specifically designed to read the userInfo.xml
public class UserInfoReader {

	public List<UserInfo> read() {

		List<UserInfo> userList = new ArrayList<>();

		try {
			File inputFile = new File("../assets/user/userInfo.xml");

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);

			doc.getDocumentElement().normalize();

			NodeList nodeList = doc.getElementsByTagName("user");

			for (int temp = 0; temp < nodeList.getLength(); temp++) {
				Node node = nodeList.item(temp);

				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) node;

					UserInfo user = new UserInfo();
					user.setUserName(element.getElementsByTagName("name").item(0).getTextContent());
					user.setPassword(element.getElementsByTagName("password").item(0).getTextContent());
					user.setBalance(Long.parseLong( element.getElementsByTagName("balance").item(0).getTextContent()));
					user.setLoan(Long.parseLong( element.getElementsByTagName("loan").item(0).getTextContent()));

					userList.add(user);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return userList;
	}
}

