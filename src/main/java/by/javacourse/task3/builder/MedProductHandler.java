package by.javacourse.task3.builder;

import org.xml.sax.Attributes;

import java.time.YearMonth;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.helpers.DefaultHandler;

import by.javacourse.task3.entity.Baa;
import by.javacourse.task3.entity.Country;
import by.javacourse.task3.entity.GroupATC;
import by.javacourse.task3.entity.Medicine;
import by.javacourse.task3.entity.MedProduct;
import by.javacourse.task3.entity.Pack;

public class MedProductHandler extends DefaultHandler {

	static Logger logger = LogManager.getLogger();
	
	private static final String SPACE_SEPARATOR = " ";
	private Set<MedProduct> medCatalog;
	private MedProduct currentMedProduct;
	private EnumSet<MedProductXmlTag> possibleXmlTag;
	private MedProductXmlTag currentXmlTag;

	public MedProductHandler() {
		medCatalog = new HashSet<MedProduct>();
		possibleXmlTag = EnumSet.range(MedProductXmlTag.NAME, MedProductXmlTag.DOSAGE);
	}

	public Set<MedProduct> getMedCatalog() {
		return medCatalog;
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) {

		String medicineTag = MedProductXmlTag.MEDICINE.toString();
		String baaTag = MedProductXmlTag.BAA.toString();

		if (medicineTag.equals(qName) || baaTag.equals(qName)) {
			currentMedProduct = medicineTag.equals(qName) ? new Medicine() : new Baa();
			defineAttributes(attributes);
		} else {
			MedProductXmlTag temp = MedProductXmlTag.valueOfXmlTag(qName);
			if (possibleXmlTag.contains(temp)) {
				currentXmlTag = temp;
			}
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) {

		String medicinTag = MedProductXmlTag.MEDICINE.toString();
		String baaTag = MedProductXmlTag.BAA.toString();

		if (medicinTag.equals(qName) || baaTag.equals(qName)) {
			medCatalog.add(currentMedProduct);
			logger.info("add to set " + currentMedProduct);
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) {

		String data = new String(ch, start, length).trim();

		if (currentXmlTag != null) {

			switch (currentXmlTag) {
			case NAME -> currentMedProduct.setName(data);
			case PHARM -> currentMedProduct.setPharm(data);
			case GROUP -> currentMedProduct.setGroup(GroupATC.valueOfXmlContent(data));
			case ANALOGS -> currentMedProduct.setAnalogs(Arrays.asList(data.split(SPACE_SEPARATOR)));
			case COUNTRY -> currentMedProduct.getVersion().setCountry(Country.valueOfXmlContent(data));
			case CERTIFICATE -> currentMedProduct.getVersion().setCertificate(data);
			case DATA_FROM -> currentMedProduct.getVersion().setDataFrom(YearMonth.parse(data));
			case DATA_TO -> currentMedProduct.getVersion().setDataTo(YearMonth.parse(data));
			case PACK -> currentMedProduct.getVersion().setPack(Pack.valueOfXmlContent(data));
			case DOSAGE -> currentMedProduct.getVersion().setDosage(data);
			case CODE_CAS -> {
				Medicine temp = (Medicine) currentMedProduct;
				temp.setCodeCAS(data);
				currentMedProduct = temp;
			}
			case ACTIVE_SUBSTANCE -> {
				Medicine temp = (Medicine) currentMedProduct;
				temp.setActiveSubstance(data);
				currentMedProduct = temp;
			}
			case NEED_RECIPE -> {
				Medicine temp = (Medicine) currentMedProduct;
				temp.setNeedRecipe(Boolean.parseBoolean(data));
				currentMedProduct = temp;
			}
			case COMPOSITION -> {
				Baa temp = (Baa) currentMedProduct;
				temp.setComposition(Arrays.asList(data.split(SPACE_SEPARATOR)));
				currentMedProduct = temp;
			}
			default -> throw new EnumConstantNotPresentException(currentXmlTag.getDeclaringClass(),
					currentXmlTag.name());
			}
		}
		currentXmlTag = null;
	}

	private void defineAttributes(Attributes attributes) {

		String medProductId = attributes.getValue(MedProductXmlAttribute.ID.toString());
		currentMedProduct.setMedProdactId(medProductId);

		String outOfProdaction = attributes.getValue(MedProductXmlAttribute.OUT_OF_PRODACTION.toString());
		if (outOfProdaction != null) {
			currentMedProduct.setOutOfProdaction(Boolean.parseBoolean(outOfProdaction));
		} else {
			currentMedProduct.setOutOfProdaction(MedProduct.DEFAULT_OUT_OF_PRODACTION);
		}
	}

}
