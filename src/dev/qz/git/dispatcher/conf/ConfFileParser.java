package dev.qz.git.dispatcher.conf;

/**
 * 
 * -----------------------------------------------------------
 * load the dispatcher conf file to init the Filter Dispatcher
 * -----------------------------------------------------------
 * 
 * @author Qin Zheng
 * 
 */

/*
 * 
 * reflect to load the specified class and default method
 * 
 */

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import dev.qz.git.dispatcher.entity.DispatcherEntity;

public class ConfFileParser {

	private Map<String, DispatcherEntity> dispatcherEntities = null;

	private String confFilePath = null;

	private String ActionDispatcherTagName = null;

	private String ResultPageTagName = null;

	private String classAttrName = null;

	private String ResultCaseName = null;

	private String PageTag = null;

	private String ActionAttrName = null;

	private static class ConfFileParserFactory {

		private static final ConfFileParser INSTANCE = new ConfFileParser();

	}

	public static final ConfFileParser getInstance() {

		return ConfFileParserFactory.INSTANCE;

	}

	private ConfFileParser() {

		dispatcherEntities = new HashMap<String, DispatcherEntity>();

		confFilePath = "DispatcherConf.xml";

		ActionDispatcherTagName = "ActionDispactcher";

		ResultPageTagName = "ResultPage";

		classAttrName = "class";

		ResultCaseName = "case";

		PageTag = "page";

		ActionAttrName = "action";

	}

	public Map<String, DispatcherEntity> ParseConfFile() {

		Map<String, String> viewMap = new HashMap<String, String>();

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		DocumentBuilder db = null;

		Document document = null;

		try {

			db = dbf.newDocumentBuilder();

			document = db.parse(this.getClass().getClassLoader()
					.getResourceAsStream(confFilePath));

			NodeList list = document
					.getElementsByTagName(ActionDispatcherTagName);

			for (int i = 0; i < list.getLength(); i++) {

				DispatcherEntity dispatcherEntity = new DispatcherEntity();

				Element element = (Element) list.item(i);

				Class<?> action = Class.forName(element
						.getAttribute(classAttrName));

				dispatcherEntity.setActionClass(action);

				NodeList viewList = document
						.getElementsByTagName(ResultPageTagName);

				for (int j = 0; j < viewList.getLength(); j++) {

					Element viewElement = (Element) viewList.item(j);

					viewMap.put(viewElement.getAttribute(ResultCaseName),
							viewElement.getElementsByTagName(PageTag).item(0)
									.getFirstChild().getNodeValue());

					dispatcherEntity.setViewMap(viewMap);

				}

				dispatcherEntities.put(element.getAttribute(ActionAttrName),
						dispatcherEntity);

			}

			return dispatcherEntities;

		} catch (ParserConfigurationException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();

			return null;

		} catch (SAXException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();

			return null;

		} catch (IOException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();

			return null;

		} catch (ClassNotFoundException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();

			return null;

		} catch (SecurityException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();

			return null;

		}

	}

}
