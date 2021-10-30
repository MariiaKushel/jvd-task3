package by.javacourse.task3.builder;

import java.time.YearMonth;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import by.javacourse.task3.entity.Baa;
import by.javacourse.task3.entity.Country;
import by.javacourse.task3.entity.GroupATC;
import by.javacourse.task3.entity.Medicine;
import by.javacourse.task3.entity.MedProduct;
import by.javacourse.task3.entity.Pack;
import by.javacourse.task3.exeption.MedProductException;

public class MedProductStaxBuilderTest {
	
	private MedProductStaxBuilder staxBuilder;

	@BeforeTest
	public void initialize() {
		staxBuilder = new MedProductStaxBuilder();
	}

	@Test
	public void testBuildMedCatalogPositive() throws MedProductException{

		Medicine med1 = new Medicine();
		med1.setMedProdactId("m1");
		med1.setOutOfProdaction(MedProduct.DEFAULT_OUT_OF_PRODACTION);
		med1.setName("Ibuprofen");
		med1.setPharm("Med Company");
		med1.setGroup(GroupATC.GROUP_M);
		med1.setAnalogs(Arrays.asList("Paracetomol", "Noshpa"));
		med1.getVersion().setCountry(Country.ISRAEL);
		med1.getVersion().setCertificate("1255.DF54565.1587RE");
		med1.getVersion().setDataFrom(YearMonth.parse("2021-01"));
		med1.getVersion().setDataTo(YearMonth.parse("2022-01"));
		med1.getVersion().setPack(Pack.PILL);
		med1.getVersion().setDosage("50mg");
		med1.setCodeCAS("1235-67-8");
		med1.setActiveSubstance("obezbolin");
		med1.setNeedRecipe(false);

		Baa med2 = new Baa();
		med2.setMedProdactId("m2");
		med2.setOutOfProdaction(true);
		med2.setName("Health helper");
		med2.setPharm("Other Med Company");
		med2.setGroup(GroupATC.GROUP_A);
		med2.setAnalogs(Arrays.asList("Gooder", "Weller"));
		med2.getVersion().setCountry(Country.RUSSIA);
		med2.getVersion().setCertificate("1889.589-31.SS157");
		med2.getVersion().setDataFrom(YearMonth.parse("2020-07"));
		med2.getVersion().setDataTo(YearMonth.parse("2023-01"));
		med2.getVersion().setPack(Pack.GEL);
		med2.getVersion().setDosage("100ml");
		med2.setComposition(Arrays.asList("Mentol", "Oil"));

		Set<MedProduct> expected = new HashSet<MedProduct>();
		expected.add(med1);
		expected.add(med2);

		String xmlPath = "src\\test\\resources\\testData\\medCatalogTest.xml";
		
		staxBuilder.buildMedCatalog(xmlPath);
		Set<MedProduct> actual = staxBuilder.getMedCatalog();

		Assert.assertEquals(actual, expected);
	}
	
	@Test (expectedExceptions = MedProductException.class, expectedExceptionsMessageRegExp = "FileNotFoundException.+")
	public void testBuildMedCatalogFileNotFoundException() throws MedProductException {
		
		String xmlPath = "src\\test\\resources\\testData\\medCatalogTestN.xml";
		
		staxBuilder.buildMedCatalog(xmlPath);
	}
	
	@Test (expectedExceptions = MedProductException.class, expectedExceptionsMessageRegExp = "XMLStreamException.+")
	public void testBuildMedCatalogXMLStreamException() throws MedProductException {
		
		String xmlPath = "src\\test\\resources\\testData\\medCatalogTestFatal.xml";
		
		staxBuilder.buildMedCatalog(xmlPath);
	}
	
	@AfterTest
	public void close() {
		staxBuilder = null;
	}

}
