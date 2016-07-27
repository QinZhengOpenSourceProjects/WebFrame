package dev.qz.git.orm.conf;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import dev.qz.git.orm.conf.entity.ConfEntity;

public class ORMConfParser {

	private String confFileNameString = null;

	private String classNameString = null;

	private String columnNameString = null;

	private String columnTagStirng = null;

	private String classTagString = null;

	private String tableTagString = null;

	private Map<String, ConfEntity> confEntities = null;

	private String primaryString = null;

	public ORMConfParser() {

		confFileNameString = "ORMConf.xml";

		classNameString = "class";

		classTagString = "class";

		tableTagString = "table";

		columnNameString = "column";

		columnTagStirng = "name";

		primaryString = "primary";

		confEntities = new HashMap<String, ConfEntity>();

	}

	public Map<String, ConfEntity> load() {

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		DocumentBuilder db = null;

		Document document = null;

		ConfEntity confEntity = null;

		Vector<String> columnVector = null;

		try {

			db = dbf.newDocumentBuilder();

			document = db.parse(this.getClass().getClassLoader()
					.getResourceAsStream(confFileNameString));

			NodeList list = document.getElementsByTagName(classNameString);

			for (int i = 0; i < list.getLength(); i++) {

				confEntity = new ConfEntity();

				Element element = (Element) list.item(i);

				confEntity.setClassName(Class.forName(element
						.getAttribute(classTagString)));

				confEntity.setTableNameString(element
						.getAttribute(tableTagString));

				confEntity.setPrimaryString(element
						.getElementsByTagName(primaryString).item(0)
						.getFirstChild().getNodeValue());

				NodeList columnList = document
						.getElementsByTagName(columnNameString);

				columnVector = new Vector<String>();

				for (int j = 0; j < columnList.getLength(); j++) {

					Element columnElement = (Element) columnList.item(j);

					columnVector.addElement(columnElement
							.getAttribute(columnTagStirng));

				}

				confEntity.setColumnVector(columnVector);

				confEntities.put(element.getAttribute(classTagString),
						confEntity);

			}

		} catch (ParserConfigurationException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (SAXException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (IOException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (ClassNotFoundException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		return confEntities;

	}
}
