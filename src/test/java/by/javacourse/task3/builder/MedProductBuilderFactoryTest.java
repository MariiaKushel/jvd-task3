package by.javacourse.task3.builder;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import by.javacourse.task3.exeption.MedProductException;

public class MedProductBuilderFactoryTest {

	@DataProvider(name = "providerBuilder")
	public Object[][] creatData() {
		return new Object[][] { 
			{ "sax", MedProductSaxBuilder.class }, 
			{ "dom", MedProductDomBuilder.class },
			{ "stax", MedProductStaxBuilder.class}, 
			};
	}

	@Test(dataProvider = "providerBuilder")
	public void testCreateBuiderPositive(String typeParser, Class<? extends AbstractMedProductBuilder> expected) throws MedProductException{
		Class<? extends AbstractMedProductBuilder> actual = MedProductBuilderFactory.createBuider(typeParser).getClass();
		Assert.assertEquals(actual, expected);
	}
	
	@Test (expectedExceptions = MedProductException.class)
	public void testCreateBuiderException() throws MedProductException{
		MedProductBuilderFactory.createBuider("ololo");
	}
}
