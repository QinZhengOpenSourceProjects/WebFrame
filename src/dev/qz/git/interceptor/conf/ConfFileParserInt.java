package dev.qz.git.interceptor.conf;

/**
 * 
 * ----------------------------
 * interceptor conf file parser
 * ----------------------------
 * 
 * @author Qin Zheng
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

public class ConfFileParserInt {

	private String ConfFilePath = null;

	private String InterceptorTagName = null;

	private String InterceptorClassName = null;

	private String InterceptorName = null;

	private ConfFileParserInt() {

		ConfFilePath = "InterceptorConf.xml";

		InterceptorTagName = "Interceptor";

		InterceptorClassName = "class";

		InterceptorName = "name";

	}

	private static class ConfFileParserIntFactory {

		private static final ConfFileParserInt INSTANCE = new ConfFileParserInt();

	}

	public static final ConfFileParserInt getInstance() {

		return ConfFileParserIntFactory.INSTANCE;

	}

	public Map<String, Class<?>> LoadConf() {

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		DocumentBuilder db = null;

		Document document = null;

		Map<String, Class<?>> classes = null;

		try {

			db = dbf.newDocumentBuilder();

			document = db.parse(this.getClass().getClassLoader()
					.getResourceAsStream(ConfFilePath));

			NodeList list = document.getElementsByTagName(InterceptorTagName);

			classes = new HashMap<String, Class<?>>();

			for (int i = 0; i < list.getLength(); i += 1) {

				Element element = (Element) list.item(i);

				classes.put(element.getAttribute(InterceptorName), Class
						.forName(element.getAttribute(InterceptorClassName)));

			}

			return classes;

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

		}

	}

}
