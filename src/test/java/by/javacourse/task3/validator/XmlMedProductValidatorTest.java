package by.javacourse.task3.validator;

import org.testng.Assert;
import org.testng.annotations.Test;

import by.javacourse.task3.exeption.MedProductException;

public class XmlMedProductValidatorTest {

	@Test
	public void testValidateXmlPositive() throws MedProductException {
		String xmlPath = "src\\test\\resources\\testData\\medicinsTest.xml";
		String schemaPath = "src\\test\\resources\\testData\\schemaTest.xsd";

		boolean condition = XmlMedProductValidator.validateXml(xmlPath, schemaPath);

		Assert.assertTrue(condition);
	}

	@Test(expectedExceptions = MedProductException.class)
	public void testValidateXmlException() throws MedProductException {
		String xmlPath = "src\\test\\resources\\testData\\medicinsTestN.xml";
		String schemaPath = "src\\test\\resources\\testData\\schemaTest.xsd";

		XmlMedProductValidator.validateXml(xmlPath, schemaPath);
	}
	
	@Test
	public void testValidateXmlFatal() throws MedProductException {
		String xmlPath = "src\\test\\resources\\testData\\medicinsTestFatal.xml";
		String schemaPath = "src\\test\\resources\\testData\\schemaTest.xsd";

		boolean condition = XmlMedProductValidator.validateXml(xmlPath, schemaPath);

		Assert.assertFalse(condition);
	}
	
	@Test//тест валится
	public void testValidateXmlError() throws MedProductException {
		String xmlPath = "src\\test\\resources\\testData\\medicinsTestError.xml";
		String schemaPath = "src\\test\\resources\\testData\\schemaTest.xsd";

		boolean condition = XmlMedProductValidator.validateXml(xmlPath, schemaPath);

		Assert.assertFalse(condition);
	}
}
