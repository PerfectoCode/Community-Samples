package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class MyXMLParcer {
	private static DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
	private static DocumentBuilder builder;
	private static Document document;

	public void parceInToXML(InputStream stream) {
		try {
			builder = builderFactory.newDocumentBuilder();
		} catch (ParserConfigurationException arg4) {
			arg4.printStackTrace();
		}

		try {
			document = builder.parse(stream);
		} catch (SAXException arg2) {
			arg2.printStackTrace();
		} catch (IOException arg3) {
			arg3.printStackTrace();
		}

	}

	public List<String> runXpath(String xpath) {
		ArrayList list = new ArrayList();
		document.getDocumentElement().normalize();
		XPath xPath = XPathFactory.newInstance().newXPath();
		NodeList nodeList = null;

		try {
			nodeList = (NodeList) xPath.compile(xpath).evaluate(document, XPathConstants.NODESET);
		} catch (XPathExpressionException arg6) {
			arg6.printStackTrace();
		}

		for (int i = 0; i < nodeList.getLength(); ++i) {
			Element element = (Element) nodeList.item(i);
			list.add(element.getElementsByTagName("deviceId").item(0).getTextContent());
		}

		return list;
	}
}