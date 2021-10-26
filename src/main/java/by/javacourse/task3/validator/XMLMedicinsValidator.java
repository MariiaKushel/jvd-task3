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

import by.javacourse.task3.builder.MedicinsErrorHandler;
import by.javacourse.task3.exeption.MedicinsException;

public class XMLMedicinsValidator {

	static Logger logger = LogManager.getLogger();

	public static boolean validateXML(String xmlPath, String schemaPath) throws MedicinsException {

		logger.info("start");

		String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
		SchemaFactory factory = SchemaFactory.newInstance(language);
		File schemaLocation = new File(schemaPath);

		try {
			Schema schema = factory.newSchema(schemaLocation);
			Validator validator = schema.newValidator();
			Source source = new StreamSource(xmlPath);

			validator.setErrorHandler(new MedicinsErrorHandler());
			validator.validate(source);

		} catch (IOException ioEx) {
			logger.info("IO exception");
			throw new MedicinsException(ioEx);
		} catch (SAXException saxEx) {
			logger.info("XML " +  " is not valid.");
			return false;
		}

		logger.info("finish");

		return true;
	}
}
