package by.javacourse.task3.validator;

import java.io.File;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import by.javacourse.task3.builder.MedProductErrorHandler;
import by.javacourse.task3.exeption.MedProductException;

public class XmlMedProductValidator {

	private static final XmlMedProductValidator instance = new XmlMedProductValidator();

	static Logger logger = LogManager.getLogger();

	private XmlMedProductValidator() {
	}

	public static XmlMedProductValidator getInstance() {
		return instance;
	}

	public static boolean validateXml(String xmlPath, String schemaPath) throws MedProductException {

		logger.info("start");

		String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
		SchemaFactory factory = SchemaFactory.newInstance(language);
		File schemaLocation = new File(schemaPath);

		try {
			Schema schema = factory.newSchema(schemaLocation);
			Validator validator = schema.newValidator();
			Source source = new StreamSource(xmlPath);
			MedProductErrorHandler errorHandler = new MedProductErrorHandler();

			validator.setErrorHandler(errorHandler);
			validator.validate(source);
			
			if(errorHandler.isErrorHappend()) {
				logger.info("Xml file " + xmlPath + " is not valid.");
				return false;
			}

		} catch (IOException ioEx) {
			logger.info("IO exception during work with files " + xmlPath + "; " + schemaPath);
			throw new MedProductException(ioEx);
		} catch (SAXException saxEx) {
			logger.info("Xml file " + xmlPath + " is not valid.");
			return false;
		}

		logger.info("finish");

		return true;
	}
}
