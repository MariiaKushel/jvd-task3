package by.javacourse.task3.builder;

import java.util.EnumSet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.javacourse.task3.exeption.MedProductException;

public class MedProductBuilderFactory {

	static Logger logger = LogManager.getLogger();

	private enum TypeParser {
		SAX, DOM, STAX
	}

	private MedProductBuilderFactory() {

	}

	public static MedProductBuilder createBuider(String typeParser) throws MedProductException {

		EnumSet<TypeParser> types = EnumSet.allOf(TypeParser.class);
		if (!types.contains(typeParser.toUpperCase())) {
			throw new MedProductException("Invalid value of emun TypeParser constant");
		}

		TypeParser type = TypeParser.valueOf(typeParser.toUpperCase());
		MedProductBuilder currentBuilder = null;

		switch (type) {
		case SAX -> currentBuilder = new MedProductSaxBuilder();
		case DOM -> currentBuilder = new MedProductDomBuilder();
		case STAX -> currentBuilder = new MedProductStaxBuilder();
		default -> new EnumConstantNotPresentException(type.getDeclaringClass(), type.name());
		}

		return currentBuilder;
	}
}
