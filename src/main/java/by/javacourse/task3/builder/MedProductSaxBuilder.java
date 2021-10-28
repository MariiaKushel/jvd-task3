package by.javacourse.task3.builder;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

public class MedProductSaxBuilder extends AbstractMedProductBuilder {

	static Logger logger = LogManager.getLogger();

	private MedProductHandler handler = new MedProductHandler();
	private XMLReader reader;

	public MedProductSaxBuilder() {
		super();
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
			SAXParser saxParser = factory.newSAXParser();
			reader = saxParser.getXMLReader();
		} catch (ParserConfigurationException e) {
			logger.error("ParserConfigurationException while configuring the parser");
		} catch (SAXException e) {
			logger.error("SAXException while configuring the parser");
		}
		reader.setErrorHandler(new MedProductErrorHandler());
		reader.setContentHandler(handler);
	}

	@Override
	public void buildMedCatalog(String xmlPath) {
		try {
			reader.parse(xmlPath);
		} catch (IOException e) {
			logger.error("IOException during work with files " + xmlPath);
		} catch (SAXException e) {
			logger.error("SAXException while building Set<MedProduct>");
		}
		medCatalog = handler.getMedCatalog();
	}

}
