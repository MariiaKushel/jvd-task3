package by.javacourse.task3.builder;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import by.javacourse.task3.exeption.MedProductException;

public class MedProductSaxBuilder extends MedProductBuilder {

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
	public void buildMedCatalog(String xmlPath) throws MedProductException {
		try {
			reader.parse(xmlPath);
		} catch (IOException e) {
			logger.error("IOException during work with files " + xmlPath);
			throw new MedProductException ("IOException during work with files " + xmlPath, e);
		} catch (SAXException e) {
			logger.error("SAXException while building Set<MedProduct>");
			throw new MedProductException ("SAXException while building Set<MedProduct>", e);
		}
		medCatalog = handler.getMedCatalog();
	}

}
