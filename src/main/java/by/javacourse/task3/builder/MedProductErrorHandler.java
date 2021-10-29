package by.javacourse.task3.builder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class MedProductErrorHandler implements ErrorHandler{
	
	static Logger logger = LogManager.getLogger();

	public void warning(SAXParseException exception) throws SAXException {
		logger.warn(exception.getLineNumber() + " : " + exception.getColumnNumber() +" - " + exception.getMessage());
	}

	public void error(SAXParseException exception) throws SAXException {
		logger.error(exception.getLineNumber() + " : " + exception.getColumnNumber() +" - " + exception.getMessage());
		//если добавить проброс исключения валидация проходить правильно
		//throw exception;
	}

	public void fatalError(SAXParseException exception) throws SAXException {
		logger.fatal(exception.getLineNumber() + " : " + exception.getColumnNumber() +" - " + exception.getMessage());
	}
	
}
