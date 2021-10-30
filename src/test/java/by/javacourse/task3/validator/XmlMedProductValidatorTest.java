package by.javacourse.task3.validator;

import org.testng.Assert;
import org.testng.annotations.Test;

import by.javacourse.task3.exeption.MedProductException;

public class XmlMedProductValidatorTest {

	@Test
	public void testValidateXmlPositive() throws MedProductException {
		//String xmlPath = "src\\test\\resources\\testData\\medCatalogTest.xml";
		//String schemaPath = "src\\test\\resources\\testData\\schemaTest.xsd";
		String xmlPath = "src\\test\\resources\\testData\\medCatalogRun.xml";
		String schemaPath = "src\\test\\resources\\testData\\schemaRun.xsd";

		boolean condition = XmlMedProductValidator.validateXml(xmlPath, schemaPath);

		Assert.assertTrue(condition);
	}

	@Test(expectedExceptions = MedProductException.class)
	public void testValidateXmlException() throws MedProductException {
		String xmlPath = "src\\test\\resources\\testData\\medCatalogTestN.xml";
		String schemaPath = "src\\test\\resources\\testData\\schemaTest.xsd";

		XmlMedProductValidator.validateXml(xmlPath, schemaPath);
	}
	
	@Test
	public void testValidateXmlFatal() throws MedProductException {
		String xmlPath = "src\\test\\resources\\testData\\medCatalogTestFatal.xml";
		String schemaPath = "src\\test\\resources\\testData\\schemaTest.xsd";

		boolean condition = XmlMedProductValidator.validateXml(xmlPath, schemaPath);

		Assert.assertFalse(condition);
	}
	
	@Test
	public void testValidateXmlError() throws MedProductException {
		String xmlPath = "src\\test\\resources\\testData\\medCatalogTestError.xml";
		String schemaPath = "src\\test\\resources\\testData\\schemaTest.xsd";

		boolean condition = XmlMedProductValidator.validateXml(xmlPath, schemaPath);

		Assert.assertFalse(condition);
	}
}
