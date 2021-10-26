package by.javacourse.task3.validator;

import org.testng.Assert;
import org.testng.annotations.Test;

import by.javacourse.task3.exeption.MedicinsException;

public class XMLValidatorTest {

	@Test
	public void testValidateXMLPositive() throws MedicinsException {
		String xmlPath = "src\\test\\resources\\testData\\medicinsTest.xml";
		String schemaPath = "src\\test\\resources\\testData\\schemaTest.xsd";

		boolean condition = XMLMedicinsValidator.validateXML(xmlPath, schemaPath);

		Assert.assertTrue(condition);
	}

	@Test(expectedExceptions = MedicinsException.class)
	public void testValidateXMLException() throws MedicinsException {
		String xmlPath = "src\\test\\resources\\testData\\medicinsTestN.xml";
		String schemaPath = "src\\test\\resources\\testData\\schemaTest.xsd";

		XMLMedicinsValidator.validateXML(xmlPath, schemaPath);
	}
	
	@Test
	public void testValidateXMLNegative() throws MedicinsException {
		String xmlPath = "src\\test\\resources\\testData\\medicinsTest.xml";
		String schemaPath = "src\\test\\resources\\testData\\schemaTestWrong.xsd";

		boolean condition = XMLMedicinsValidator.validateXML(xmlPath, schemaPath);

		Assert.assertFalse(condition);
	}
	
	@Test
	public void testValidateXMLMistake() throws MedicinsException {
		String xmlPath = "src\\test\\resources\\testData\\medicinsTest.xml";
		String schemaPath = "src\\test\\resources\\testData\\schemaTestMistake.xsd";

		boolean condition = XMLMedicinsValidator.validateXML(xmlPath, schemaPath);

		Assert.assertFalse(condition);
	}
}
