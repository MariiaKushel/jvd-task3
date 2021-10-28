package by.javacourse.task3.builder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.javacourse.task3.entity.Baa;
import by.javacourse.task3.entity.Country;
import by.javacourse.task3.entity.GroupATC;
import by.javacourse.task3.entity.Medicine;
import by.javacourse.task3.entity.MedProduct;
import by.javacourse.task3.entity.Pack;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.YearMonth;
import java.util.Arrays;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class MedProductStaxBuilder extends AbstractMedProductBuilder {
	
	static Logger logger = LogManager.getLogger();

	private static final String SPACE_SEPARATOR = " ";
	
	private XMLInputFactory inputFactory;

	public MedProductStaxBuilder() {
		super();
		inputFactory = XMLInputFactory.newInstance();
	}

	@Override
	public void buildMedCatalog(String xmlPath) {
		XMLStreamReader reader;
		String name;
		try (FileInputStream inputStream = new FileInputStream(new File(xmlPath))) {
			reader = inputFactory.createXMLStreamReader(inputStream);
			while (reader.hasNext()) {
				int type = reader.next();
				if (type == XMLStreamConstants.START_ELEMENT) {
					name = reader.getLocalName();
					if (name.equals(MedProductXmlTag.MEDICINE.toString()) || name.equals(MedProductXmlTag.BAA.toString())) {
						MedProduct medProduct = buildMedProduct(reader);
						medCatalog.add(medProduct);
					}
				}
			}
		} catch (FileNotFoundException e) {
			logger.error("FileNotFoundException during work with file " + xmlPath);
		} catch (XMLStreamException e) {
			logger.error("XMLStreamException while building Set<MedProduct>");
		} catch (IOException e) {
			logger.error("IOException during work with file " + xmlPath);
		}
	}

	private MedProduct buildMedProduct(XMLStreamReader reader) throws XMLStreamException {
		MedProduct currentMedProduct = reader.getLocalName().equals(MedProductXmlTag.MEDICINE.toString()) 
				? new Medicine()
				: new Baa();

		currentMedProduct.setMedProdactId(reader.getAttributeValue(null, MedProductXmlAttribute.ID.toString()));

		String content = reader.getAttributeValue(null, MedProductXmlAttribute.OUT_OF_PRODACTION.toString());
		if (content != null) {
			currentMedProduct.setOutOfProdaction(Boolean.parseBoolean(content));
		} else {
			currentMedProduct.setOutOfProdaction(MedProduct.DEFAULT_OUT_OF_PRODACTION);
		}

		String name;

		while (reader.hasNext()) {
			int type = reader.next();
			switch (type) {
			case XMLStreamConstants.START_ELEMENT:
				name = reader.getLocalName();
				switch (MedProductXmlTag.valueOfXmlTag(name)) {
				case NAME -> currentMedProduct.setName(getXMLText(reader));
				case PHARM -> currentMedProduct.setPharm(getXMLText(reader));
				case GROUP -> currentMedProduct.setGroup(GroupATC.valueOfXmlContent(getXMLText(reader)));
				case ANALOGS -> currentMedProduct.setAnalogs(Arrays.asList(getXMLText(reader).split(SPACE_SEPARATOR)));
				case VERSION -> currentMedProduct.setVersion(getXMLMedProductVersion(reader));
				case CODE_CAS -> {
					Medicine temp = (Medicine) currentMedProduct;
					temp.setCodeCAS(getXMLText(reader));
					currentMedProduct = temp;
				}
				case ACTIVE_SUBSTANCE -> {
					Medicine temp = (Medicine) currentMedProduct;
					temp.setActiveSubstance(getXMLText(reader));
					currentMedProduct = temp;
				}
				case NEED_RECIPE -> {
					Medicine temp = (Medicine) currentMedProduct;
					temp.setNeedRecipe(Boolean.parseBoolean(getXMLText(reader)));
					currentMedProduct = temp;
				}
				case COMPOSITION -> {
					Baa temp = (Baa) currentMedProduct;
					temp.setComposition(Arrays.asList(getXMLText(reader).split(SPACE_SEPARATOR)));
					currentMedProduct = temp;
				}
				}
				break;
			case XMLStreamConstants.END_ELEMENT:
				name = reader.getLocalName();
				if (name.equals(MedProductXmlTag.MEDICINE.toString()) || name.equals(MedProductXmlTag.BAA.toString())) {
					return currentMedProduct;
				}
			}
		}
		throw new XMLStreamException("Unknown element in tag <medicine> or <baa>");
	}

	private MedProduct.Version getXMLMedProductVersion(XMLStreamReader reader) throws XMLStreamException{
		MedProduct.Version version = new Medicine().getVersion();

		String name;
		while (reader.hasNext()) {
			int type = reader.next();
			switch (type) {
			case XMLStreamConstants.START_ELEMENT:
				name = reader.getLocalName();
				switch (MedProductXmlTag.valueOfXmlTag(name)) {
				case COUNTRY -> version.setCountry(Country.valueOfXmlContent(getXMLText(reader)));
				case CERTIFICATE -> version.setCertificate(getXMLText(reader));
				case DATA_FROM -> version.setDataFrom(YearMonth.parse(getXMLText(reader)));
				case DATA_TO -> version.setDataTo(YearMonth.parse(getXMLText(reader)));
				case PACK -> version.setPack(Pack.valueOfXmlContent(getXMLText(reader)));
				case DOSAGE -> version.setDosage(getXMLText(reader));
				}
				break;
			case XMLStreamConstants.END_ELEMENT: 
				name = reader.getLocalName();
				if (name.equals(MedProductXmlTag.VERSION.toString())) {
					return version;
				}
			}
		}
		throw new XMLStreamException("Unknown element in tag <version>");
	}

	private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
		String text = null;
		if (reader.hasNext()) {
			reader.next();
			text = reader.getText();
		}
		return text;
	}
}
