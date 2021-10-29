package by.javacourse.task3.builder;

import java.io.IOException;
import java.time.YearMonth;
import java.util.Arrays;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import by.javacourse.task3.entity.Baa;
import by.javacourse.task3.entity.Country;
import by.javacourse.task3.entity.GroupATC;
import by.javacourse.task3.entity.Medicine;
import by.javacourse.task3.entity.MedProduct;
import by.javacourse.task3.entity.Pack;
import by.javacourse.task3.exeption.MedProductException;

public class MedProductDomBuilder extends AbstractMedProductBuilder {

	static Logger logger = LogManager.getLogger();

	private static final String SPACE_SEPARATOR = " ";
	private DocumentBuilder docBuilder;

	public MedProductDomBuilder() {
		super();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			docBuilder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			logger.error("ParserConfigurationException while configuring the parser");
		}
	}

	@Override
	public void buildMedCatalog(String xmlPath) throws MedProductException {
		Document doc;

		try {
			doc = docBuilder.parse(xmlPath);
			Element root = doc.getDocumentElement();
			NodeList medProductList;
			medProductList = root.getElementsByTagName(MedProductXmlTag.MEDICINE.toString());
			for (int i = 0; i < medProductList.getLength(); i++) {
				Element medProductElement = (Element) medProductList.item(i);
				MedProduct newMedProduct = buildMedProduct(medProductElement);
				logger.info("add to set " + newMedProduct);
				medCatalog.add(newMedProduct);
			}

			medProductList = root.getElementsByTagName(MedProductXmlTag.BAA.toString());
			for (int i = 0; i < medProductList.getLength(); i++) {
				Element medicinsElement = (Element) medProductList.item(i);
				MedProduct newMedicins = buildMedProduct(medicinsElement);
				logger.info("add to set " + newMedicins);
				medCatalog.add(newMedicins);
			}

		} catch (SAXException e) {
			logger.error("SAXException while building Set<MedProduct>");
			throw new MedProductException (e);
		} catch (IOException e) {
			logger.error("IOException during work with files " + xmlPath);
			throw new MedProductException (e);
		}

	}

	private MedProduct buildMedProduct(Element MedProductElement) {

		MedProduct currentMedProduct = MedProductElement.getTagName().equals(MedProductXmlTag.MEDICINE.toString())
				? new Medicine()
				: new Baa();

		// add null check

		String content;

		content = MedProductElement.getAttribute(MedProductXmlAttribute.ID.toString());
		currentMedProduct.setMedProdactId(content);
		content = MedProductElement.getAttribute(MedProductXmlAttribute.OUT_OF_PRODACTION.toString());
		if (!content.isEmpty()) {
			currentMedProduct.setOutOfProdaction(Boolean.parseBoolean(content));
		} else {
			currentMedProduct.setOutOfProdaction(MedProduct.DEFAULT_OUT_OF_PRODACTION);
		}

		content = getElementTextContent(MedProductElement, MedProductXmlTag.NAME.toString());
		currentMedProduct.setName(content);
		content = getElementTextContent(MedProductElement, MedProductXmlTag.PHARM.toString());
		currentMedProduct.setPharm(content);
		content = getElementTextContent(MedProductElement, MedProductXmlTag.GROUP.toString());
		currentMedProduct.setGroup(GroupATC.valueOfXmlContent(content));
		content = getElementTextContent(MedProductElement, MedProductXmlTag.ANALOGS.toString());
		currentMedProduct.setAnalogs(Arrays.asList(content.split(SPACE_SEPARATOR)));

		MedProduct.Version currentVersion = currentMedProduct.getVersion();
		content = getElementTextContent(MedProductElement, MedProductXmlTag.COUNTRY.toString());
		currentVersion.setCountry(Country.valueOfXmlContent(content));
		content = getElementTextContent(MedProductElement, MedProductXmlTag.CERTIFICATE.toString());
		currentVersion.setCertificate(content);
		content = getElementTextContent(MedProductElement, MedProductXmlTag.DATA_FROM.toString());
		currentVersion.setDataFrom(YearMonth.parse(content));
		content = getElementTextContent(MedProductElement, MedProductXmlTag.DATA_TO.toString());
		currentVersion.setDataTo(YearMonth.parse(content));
		content = getElementTextContent(MedProductElement, MedProductXmlTag.PACK.toString());
		currentVersion.setPack(Pack.valueOfXmlContent(content));
		content = getElementTextContent(MedProductElement, MedProductXmlTag.DOSAGE.toString());
		currentVersion.setDosage(content);

		if (currentMedProduct instanceof Medicine) {
			Medicine temp = (Medicine) currentMedProduct;
			
			content = getElementTextContent(MedProductElement, MedProductXmlTag.CODE_CAS.toString());
			temp.setCodeCAS(content);
			content = getElementTextContent(MedProductElement, MedProductXmlTag.ACTIVE_SUBSTANCE.toString());
			temp.setActiveSubstance(content);
			content = getElementTextContent(MedProductElement, MedProductXmlTag.NEED_RECIPE.toString());
			temp.setNeedRecipe(Boolean.parseBoolean(content));

			currentMedProduct = temp;
		} else {
			Baa temp = (Baa) currentMedProduct;
			
			content = getElementTextContent(MedProductElement, MedProductXmlTag.COMPOSITION.toString());
			temp.setComposition(Arrays.asList(content.split(SPACE_SEPARATOR)));
			
			currentMedProduct = temp;
		}

		return currentMedProduct;
	}

	private static String getElementTextContent(Element element, String elementName) {
		NodeList nList = element.getElementsByTagName(elementName);
		Node node = nList.item(0);
		String text = node.getTextContent();
		return text;
	}

}
